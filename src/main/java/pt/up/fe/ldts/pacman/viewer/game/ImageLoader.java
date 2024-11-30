package pt.up.fe.ldts.pacman.viewer.game;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class ImageLoader {

    public static Map<Character, BufferedImage> loadGhostImages(String ghostName) throws IOException {
        return Map.of(
                'L', ImageIO.read(new File("src/main/resources/PNGs/ghosts/" + ghostName + "/" + ghostName + "left.png")),
                'U', ImageIO.read(new File("src/main/resources/PNGs/ghosts/" + ghostName + "/" + ghostName + "up.png")),
                'D', ImageIO.read(new File("src/main/resources/PNGs/ghosts/" + ghostName + "/" + ghostName + "down.png")),
                'R', ImageIO.read(new File("src/main/resources/PNGs/ghosts/" + ghostName + "/" + ghostName + "right.png")),
                'S', ImageIO.read(new File("src/main/resources/PNGs/ghosts/common/scaredghost.png")),
                'r', ImageIO.read(new File("src/main/resources/PNGs/ghosts/common/deadghostright.png")),
                'l', ImageIO.read(new File("src/main/resources/PNGs/ghosts/common/deadghostleft.png")),
                'u', ImageIO.read(new File("src/main/resources/PNGs/ghosts/common/deadghostup.png")),
                'd', ImageIO.read(new File("src/main/resources/PNGs/ghosts/common/deadghostdown.png"))
        );
    }

    public static Map<Character, BufferedImage> loadPacmanImages() throws IOException {
        return Map.of(
                'L', ImageIO.read(new File("src/main/resources/PNGs/pacman/pacmanleft.png")),
                'U', ImageIO.read(new File("src/main/resources/PNGs/pacman/pacmanup.png")),
                'D', ImageIO.read(new File("src/main/resources/PNGs/pacman/pacmandown.png")),
                'R', ImageIO.read(new File("src/main/resources/PNGs/pacman/pacmanright.png"))
        );
    }
}
