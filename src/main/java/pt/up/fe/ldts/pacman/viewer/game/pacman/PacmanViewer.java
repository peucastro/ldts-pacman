package pt.up.fe.ldts.pacman.viewer.game.pacman;

import pt.up.fe.ldts.pacman.model.game.element.Direction;
import pt.up.fe.ldts.pacman.model.game.element.pacman.Pacman;
import pt.up.fe.ldts.pacman.viewer.Renderer;
import pt.up.fe.ldts.pacman.viewer.game.MultipleElementViewer;

import java.awt.image.BufferedImage;
import java.util.Map;

public class PacmanViewer extends MultipleElementViewer<Pacman, Direction> {

    public PacmanViewer(Renderer renderer, Map<Direction, BufferedImage> images) {
        super(renderer, images);
    }

    @Override
    protected BufferedImage getCurrentImage(Pacman pacman) {
        return images.get(pacman.getDirection());
    }
}
