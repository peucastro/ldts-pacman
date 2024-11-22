package pt.up.fe.ldts.pacman.viewer.game;

import com.googlecode.lanterna.graphics.TextGraphics;
import pt.up.fe.ldts.pacman.model.game.element.Element;
import pt.up.fe.ldts.pacman.model.game.element.State;
import pt.up.fe.ldts.pacman.model.game.element.ghost.Ghost;
import pt.up.fe.ldts.pacman.viewer.MultipleElementViewer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class GhostViewer extends MultipleElementViewer {
    public GhostViewer(Map<Enum<?>, BufferedImage> images) throws IOException {
        super(images);
        this.images.put(State.SCARED, ImageIO.read(new File("src/main/resources/PNGs/ghosts/common/scaredghost.png")));
        //images.put(State.DEAD_UP,ImageIO.read(new File("src/main/resources/PNGs/ghosts/common/deadghostup.png")));
        //images.put(State.DEAD_DOWN,ImageIO.read(new File("src/main/resources/PNGs/ghosts/common/deadghostdown.png")));
        //images.put(State.DEAD_LEFT,ImageIO.read(new File("src/main/resources/PNGs/ghosts/common/deadghostleft.png")));
        //images.put(State.DEAD_RIGHT,ImageIO.read(new File("src/main/resources/PNGs/ghosts/common/deadghostright.png")));
    }

    @Override
    public void drawElement(TextGraphics graphics, Element element) {
        if(((Ghost)element).getState() == State.SCARED) draw(graphics,element.getPosition(),images.get(State.SCARED));
        else draw(graphics,element.getPosition(),images.get(((Ghost)element).getDirection()));
    }
}
