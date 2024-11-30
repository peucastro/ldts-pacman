package pt.up.fe.ldts.pacman.viewer.game;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import pt.up.fe.ldts.pacman.model.game.element.*;
import pt.up.fe.ldts.pacman.model.game.element.ghost.GhostState;

public class ImageLoader {

    public static Map<GhostState, BufferedImage> loadGhostImages(String ghostName) throws IOException {
        return Map.of(
                GhostState.LEFT, ImageIO.read(new File("src/main/resources/PNGs/ghosts/" + ghostName + "/" + ghostName + "left.png")),
                GhostState.UP, ImageIO.read(new File("src/main/resources/PNGs/ghosts/" + ghostName + "/" + ghostName + "up.png")),
                GhostState.DOWN, ImageIO.read(new File("src/main/resources/PNGs/ghosts/" + ghostName + "/" + ghostName + "down.png")),
                GhostState.RIGHT, ImageIO.read(new File("src/main/resources/PNGs/ghosts/" + ghostName + "/" + ghostName + "right.png")),
                GhostState.SCARED, ImageIO.read(new File("src/main/resources/PNGs/ghosts/common/scaredghost.png")),
                GhostState.DEAD, ImageIO.read(new File("src/main/resources/PNGs/ghosts/common/deadghostright.png"))
        );
    }

    public static Map<Direction, BufferedImage> loadPacmanImages() throws IOException {
        return Map.of(
                Direction.LEFT, ImageIO.read(new File("src/main/resources/PNGs/pacman/pacmanleft.png")),
                Direction.UP, ImageIO.read(new File("src/main/resources/PNGs/pacman/pacmanup.png")),
                Direction.DOWN, ImageIO.read(new File("src/main/resources/PNGs/pacman/pacmandown.png")),
                Direction.RIGHT, ImageIO.read(new File("src/main/resources/PNGs/pacman/pacmanright.png"))
        );
    }
}
