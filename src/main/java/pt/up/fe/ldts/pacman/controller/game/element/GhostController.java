package pt.up.fe.ldts.pacman.controller.game.element;

import pt.up.fe.ldts.pacman.Game;
import pt.up.fe.ldts.pacman.States.DyingState;
import pt.up.fe.ldts.pacman.audio.AudioManager;
import pt.up.fe.ldts.pacman.audio.AudioPlayer;
import pt.up.fe.ldts.pacman.controller.game.GameController;
import pt.up.fe.ldts.pacman.controller.game.element.behaviours.*;
import pt.up.fe.ldts.pacman.gui.GUI;
import pt.up.fe.ldts.pacman.model.Position;
import pt.up.fe.ldts.pacman.model.game.Arena;
import pt.up.fe.ldts.pacman.model.game.element.Direction;
import pt.up.fe.ldts.pacman.model.game.element.ghost.*;
import pt.up.fe.ldts.pacman.model.game.element.pacman.Pacman;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

public class GhostController extends GameController {
    private final AudioPlayer ghostsAliveSiren;
    private final AudioPlayer ghostsScaredSiren;
    private final AudioPlayer ghostEatenAudio;
    private final Map<Class<?>, GhostMovementBehaviour> movementBehaviours;
    private int ghostsEaten; //ghosts eaten in current scared state
    private static int scaredTimeLeft = 0;

    public GhostController(Arena arena, AudioManager audioManager) {
        super(arena);

        if (!audioManager.audioExists("ghostsAliveSiren"))
            audioManager.addAudio("ghostsAliveSiren", new AudioPlayer("Audio/ghostsAlive.wav"));
        if (!audioManager.audioExists("ghostsScared"))
            audioManager.addAudio("ghostsScared", new AudioPlayer("Audio/ghostsScared.wav"));
        if (!audioManager.audioExists("ghostEaten"))
            audioManager.addAudio("ghostEaten", new AudioPlayer("Audio/ghostEaten.wav"));
        this.ghostsAliveSiren = audioManager.getAudio("ghostsAliveSiren");
        this.ghostsScaredSiren = audioManager.getAudio("ghostsScared");
        this.ghostsScaredSiren.setVolume(0.70f);
        this.ghostEatenAudio = audioManager.getAudio("ghostEaten");
        this.ghostEatenAudio.setVolume(0.40f);

        this.movementBehaviours = Map.of(
                Blinky.class, new BlinkyMovementBehaviour(),
                Pinky.class, new PinkyMovementBehaviour(),
                Inky.class, new InkyMovementBehaviour(),
                Clyde.class, new ClydeMovementBehaviour()
        );
        this.ghostsEaten = 0;
    }


    private Position getNextPosition(Position position, Direction direction) {
        return switch (direction) {
            case UP -> position.getUp();
            case DOWN -> position.getDown();
            case LEFT -> position.getLeft();
            case RIGHT -> position.getRight();
        };
    }

    private void moveGhost(Ghost ghost) {//checks if the ghost can move in newDirection and moves it if it can
        if (ghost.getCounter() > 0) {
            ghost.incrementCounter();
            return;
        }
        Position targetPosition = movementBehaviours.get(ghost.getClass()).getTargetPosition(ghost, getModel());
        Direction nextDirection = getDirectionTowards(ghost, targetPosition);
        ghost.setDirection(nextDirection);
        if (ghost.getPosition().equals(getModel().getGhostGate().getPosition())) {
            if (ghost.isDead()) { //dead ghost arrives at the ghost gate
                ghost.setState(GhostState.ALIVE);
                ghost.setInsideGate();
                ghost.setSpeed(Arena.GHOST_NORMAL_SPEED);
            } else ghost.setOutsideGate();
        }
        ghost.incrementCounter();
    }

    private Direction getDirectionTowards(Ghost ghost, Position targetPosition) {//choose new direction to follow (the one with the minimum linear distance from target)
        Direction currentDirection = ghost.getDirection();
        Direction nextDirection = Direction.UP;
        double minimumDistance = Double.MAX_VALUE;
        double tempDistance;
        for (Direction direction : Direction.values()) {
            Position testPosition = getNextPosition(ghost.getPosition(), direction);
            if (!direction.isOpposite(currentDirection) && //can't move in opposite direction
                    (tempDistance = testPosition.squaredDistance(targetPosition)) < minimumDistance && //can't move in a direction that is farther away from target
                    getModel().isEmpty(testPosition) && //can't move in a direction where there is a wall
                    (!testPosition.equals(getModel().getGhostGate().getPosition()) || ghost.isInsideGate() || ghost.isDead())) //can't move to the ghost gate, unless the ghost is inside
            {
                minimumDistance = tempDistance;
                nextDirection = direction;
            }
        }
        return nextDirection;
    }

    private boolean processCollisionWithPacman(Game game, Ghost ghost) throws IOException, URISyntaxException {
        Pacman pacman = getModel().getPacman();
        if (ghost.getPosition().equals(pacman.getPosition())) {
            switch (ghost.getState()) {
                case ALIVE:
                    getModel().getPacman().decreaseLife();
                    game.getAudioManager().stopAllAudios();
                    game.setState(new DyingState(getModel(), game.getAudioManager()));
                    return true;
                case SCARED:
                    ghostEatenAudio.playOnce();
                    ghost.setState(GhostState.DEAD);
                    ghost.setSpeed(Arena.GHOST_DEAD_SPEED);
                    getModel().incrementScore((int) (200 * Math.pow(2, ghostsEaten++)));
                    break;
                default:
                    break;
            }
        }
        return false;
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException, URISyntaxException {
        if (!ghostsAliveSiren.isPlaying() && !ghostsScaredSiren.isPlaying()) {
            if (scaredTimeLeft == 0) ghostsAliveSiren.playInLoop();
            else ghostsScaredSiren.playInLoop();
        }

        if (scaredTimeLeft == 1500) { //whenever a powerUp gets eaten the counter gets reset
            ghostsEaten = 0;
            ghostsAliveSiren.stopPlaying();
            if (!ghostsScaredSiren.isPlaying()) ghostsScaredSiren.playInLoop();
        }

        if (scaredTimeLeft > 0 && --scaredTimeLeft == 0) { //if scared time reaches 0 then all scared ghosts go back to normal
            getModel().getGhosts().forEach(ghost -> {
                if (ghost.isScared()) {
                    ghost.setState(GhostState.ALIVE);
                    ghost.setSpeed(Arena.GHOST_NORMAL_SPEED);
                }
            });
            ghostsScaredSiren.stopPlaying();
            ghostsAliveSiren.playInLoop();
            getModel().getPacman().setSpeed(Arena.PACMAN_NORMAL_SPEED);
            ghostsEaten = 0;
        }

        for (Ghost ghost : getModel().getGhosts()) {//move all ghosts
            if (processCollisionWithPacman(game, ghost)) return;

            if (time % ghost.getSpeed() != 1) moveGhost(ghost);

            if (processCollisionWithPacman(game, ghost)) return;

        }
    }

    public static void setScaredTimeLeft(int scaredTimeLeft) {
        GhostController.scaredTimeLeft = scaredTimeLeft;
    }
}
