package pt.up.fe.ldts.pacman.controller.game.element;

import pt.up.fe.ldts.pacman.Game;
import pt.up.fe.ldts.pacman.states.game.DyingState;
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
import java.util.List;
import java.util.Map;

public class GhostController extends GameController {
    private final AudioPlayer ghostsAliveSiren;
    private final AudioPlayer ghostsScaredSiren;
    private final AudioPlayer ghostEatenAudio;
    private final Map<Class<?>, GhostMovementBehaviour> movementBehaviours;
    private int ghostsEaten; //ghosts eaten in current scared state
    private static int scaredTimeLeft = 0;
    private int frameCount; //useful for alternating between chase and scatter states when ghosts are alive
    private int deadPacmanCounter; //for multiplayer
    private int targetPacman;

    public GhostController(Arena arena, AudioManager audioManager) {
        super(arena);

        if (!audioManager.audioExists("ghostsAliveSiren"))
            audioManager.addAudio("ghostsAliveSiren", new AudioPlayer("Audio/ghostsAlive.wav"));
        if (!audioManager.audioExists("ghostsScared"))
            audioManager.addAudio("ghostsScared", new AudioPlayer("Audio/ghostsScared.wav"));
        if (!audioManager.audioExists("ghostEaten"))
            audioManager.addAudio("ghostEaten", new AudioPlayer("Audio/ghostEaten.wav"));
        this.ghostsAliveSiren = audioManager.getAudio("ghostsAliveSiren");
        this.ghostsAliveSiren.setVolume(0.2f);
        this.ghostsScaredSiren = audioManager.getAudio("ghostsScared");
        this.ghostsScaredSiren.setVolume(0.25f);
        this.ghostEatenAudio = audioManager.getAudio("ghostEaten");
        this.ghostEatenAudio.setVolume(0.40f);

        this.movementBehaviours = Map.of(
                Blinky.class, new BlinkyMovementBehaviour(),
                Pinky.class, new PinkyMovementBehaviour(),
                Inky.class, new InkyMovementBehaviour(),
                Clyde.class, new ClydeMovementBehaviour()
        );
        this.ghostsEaten = 0;
        this.frameCount = 0;
        this.deadPacmanCounter = 0;
        this.targetPacman = 0;
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
        Position targetPosition = movementBehaviours.get(ghost.getClass()).getTargetPosition(ghost, getModel(), getModel().getPacmans().get(targetPacman), isChaseMode());
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

    private boolean isChaseMode(){
        return ((frameCount >= 600 && frameCount < 2500) || frameCount >= 3200);
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

    private void processCollisionWithPacmans(Game game, Ghost ghost) throws IOException, URISyntaxException {
        for(Pacman pacman : getModel().getPacmans()) {
            if(pacman.isDying()) continue; //don't process collisions with dead pacmans
            if (ghost.getPosition().equals(pacman.getPosition())) {
                switch (ghost.getState()) {
                    case ALIVE:
                        pacman.decreaseLife();
                        pacman.setDying(true);
                        int alivePacmans = 0; //number of pacmans still alive
                        for(Pacman pacman1 : getModel().getPacmans()) if(!pacman1.isDying()) ++alivePacmans;
                        if(alivePacmans == 0) { //if no pacman is alive go to dying state, else just keep going and "stun" the dead pacman
                            game.getAudioManager().stopAllAudios();
                            game.setState(new DyingState(getModel(), game.getAudioManager()));
                        }
                        else deadPacmanCounter = 110;
                        break;
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
        }
    }

    @Override
    public void step(Game game, List<GUI.ACTION> actions, long time) throws IOException, URISyntaxException {
        if (!ghostsAliveSiren.isPlaying() && !ghostsScaredSiren.isPlaying()) {
            if (scaredTimeLeft == 0) ghostsAliveSiren.playInLoop(); //start of the game or leaving pause menu
            else ghostsScaredSiren.playInLoop(); //leaving pause menu and scared state was on before
        }

        if((frameCount == 600 || frameCount == 2500 || frameCount == 3200) && scaredTimeLeft == 0)
            getModel().getGhosts().forEach(Ghost::invertDirection); //toggle between chase and scatter modes

        //change the target pacman from time to time on multiplayer
        if(frameCount%2000 == 0 && getModel().getPacmans().size() > 1 && !getModel().getPacmans().get((targetPacman == 0 ? 1 : 0)).isDying())
            targetPacman = (targetPacman == 0 ? 1 : 0);

        if (scaredTimeLeft == 1500) { //whenever a powerUp gets eaten the counter gets reset
            ghostsEaten = 0;
            ghostsAliveSiren.stopPlaying();
            if (!ghostsScaredSiren.isPlaying()) ghostsScaredSiren.playInLoop();
        }

        if(deadPacmanCounter > 0 && --deadPacmanCounter == 0){//when playing multiplayer and one pacman is still alive, the other comes to life
            for (Pacman pacman : getModel().getPacmans()) if(pacman.isDying() && pacman.getLife() > 0){
                pacman.setDying(false);
                pacman.setPosition(pacman.getRespawnPosition());
                pacman.setCounter(0);
            }
        }

        if ((scaredTimeLeft > 0 && --scaredTimeLeft == 0) || ghostsEaten == getModel().getGhosts().size()) { //if scared time reaches 0 then all scared ghosts go back to normal
            getModel().getGhosts().forEach(ghost -> {
                if (ghost.isScared()) {
                    ghost.setState(GhostState.ALIVE);
                    ghost.setSpeed(Arena.GHOST_NORMAL_SPEED);
                }
            });
            ghostsScaredSiren.stopPlaying();
            ghostsAliveSiren.playInLoop();
            for(Pacman pacman : getModel().getPacmans()) pacman.setSpeed(Arena.PACMAN_NORMAL_SPEED);
            ghostsEaten = 0;
        }

        for (Ghost ghost : getModel().getGhosts()) {//move all ghosts
            processCollisionWithPacmans(game, ghost);

            if (time % ghost.getSpeed() != 1) moveGhost(ghost);

            processCollisionWithPacmans(game, ghost);
        }

        ++frameCount;
    }

    public static void setScaredTimeLeft(int scaredTimeLeft) {
        GhostController.scaredTimeLeft = scaredTimeLeft;
    }
}
