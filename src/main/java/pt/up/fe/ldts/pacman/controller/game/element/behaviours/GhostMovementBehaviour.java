package pt.up.fe.ldts.pacman.controller.game.element.behaviours;

import pt.up.fe.ldts.pacman.model.Position;
import pt.up.fe.ldts.pacman.model.game.Arena;
import pt.up.fe.ldts.pacman.model.game.element.ghost.Ghost;
import pt.up.fe.ldts.pacman.model.game.element.pacman.Pacman;


public abstract class GhostMovementBehaviour {
    public Position getTargetPosition(Ghost ghost, Arena arena, Pacman targetPacman, boolean chaseMode) {
        return switch (ghost.getState()) {
            case ALIVE -> getAlivePosition(ghost, arena, targetPacman, chaseMode);
            case DEAD -> arena.getGhostGate().getPosition();
            case SCARED ->
                    (ghost.isInsideGate() ? getAlivePosition(ghost, arena, targetPacman, chaseMode) : new Position((int) (Math.random() * 29), (int) (Math.random() * 16)));
        };
    }

    protected abstract Position getAlivePosition(Ghost ghost, Arena arena, Pacman targetPacman, boolean chaseMode);
}
