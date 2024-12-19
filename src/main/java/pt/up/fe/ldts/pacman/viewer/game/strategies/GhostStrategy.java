package pt.up.fe.ldts.pacman.viewer.game.strategies;

import pt.up.fe.ldts.pacman.model.Element;
import pt.up.fe.ldts.pacman.model.game.element.ghost.Ghost;
import pt.up.fe.ldts.pacman.model.game.element.ghost.GhostState;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;

public class GhostStrategy extends MultipleElementStrategy {
    @Override
    public BufferedImage getCurrentImage(Element element, Map<Character, List<BufferedImage>> images, long frameCount) {
        Ghost ghost = (Ghost) element;
        int index = (ghost.getState() != GhostState.DEAD ? (frameCount%20 < 10 ? 0 : 1) : 0);
        if (ghost.isScared()) return images.get('S').get(index);
        return switch (ghost.getDirection()) {
            case UP -> images.get(ghost.getState() == GhostState.ALIVE ? 'U' : 'u').get(index);
            case DOWN -> images.get(ghost.getState() == GhostState.ALIVE ? 'D' : 'd').get(index);
            case RIGHT -> images.get(ghost.getState() == GhostState.ALIVE ? 'R' : 'r').get(index);
            case LEFT -> images.get(ghost.getState() == GhostState.ALIVE ? 'L' : 'l').get(index);
        };
    }
}
