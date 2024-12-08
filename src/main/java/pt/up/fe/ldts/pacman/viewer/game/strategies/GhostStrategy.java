package pt.up.fe.ldts.pacman.viewer.game.strategies;

import com.googlecode.lanterna.graphics.BasicTextImage;
import pt.up.fe.ldts.pacman.model.Element;
import pt.up.fe.ldts.pacman.model.game.element.ghost.Ghost;
import pt.up.fe.ldts.pacman.model.game.element.ghost.GhostState;

import java.awt.image.BufferedImage;
import java.util.Map;

public class GhostStrategy extends MultipleElementStrategy{
    @Override
    public BufferedImage getCurrentImage(Element element, Map<Character, BufferedImage> images) {
        Ghost ghost = (Ghost)element;
        if(ghost.isScared()) return images.get('S');
        return switch (ghost.getDirection()) {
            case UP -> images.get(ghost.getState() == GhostState.ALIVE ? 'U' : 'u');
            case DOWN -> images.get(ghost.getState() == GhostState.ALIVE ? 'D' : 'd');
            case RIGHT -> images.get(ghost.getState() == GhostState.ALIVE ? 'R' : 'r');
            case LEFT -> images.get(ghost.getState() == GhostState.ALIVE ? 'L' : 'l');
        };
    }
}
