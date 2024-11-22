package pt.up.fe.ldts.pacman.viewer;

import pt.up.fe.ldts.pacman.model.game.element.Element;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public abstract class MultipleElementViewer extends Viewer{
    protected Map<Enum<?>,BufferedImage> images;
    public MultipleElementViewer(Map<Enum<?>,BufferedImage> images){
        this.images = images;
    }
}
