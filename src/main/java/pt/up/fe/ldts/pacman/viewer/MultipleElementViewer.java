package pt.up.fe.ldts.pacman.viewer;

import java.awt.image.BufferedImage;
import java.util.Map;

public abstract class MultipleElementViewer extends Viewer{
    protected final Map<Enum<?>,BufferedImage> images;
    public MultipleElementViewer(Map<Enum<?>,BufferedImage> images){
        this.images = images;
    }
}
