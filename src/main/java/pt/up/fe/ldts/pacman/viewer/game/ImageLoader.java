package pt.up.fe.ldts.pacman.viewer.game;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;

public class ImageLoader {

    public static Map<Character, BufferedImage> loadGhostImages(String ghostName) throws IOException, URISyntaxException {
        URL leftResource = ImageLoader.class.getClassLoader().getResource("PNGs/ghosts/" + ghostName + "/" + ghostName + "left.png");
        URL upResource = ImageLoader.class.getClassLoader().getResource("PNGs/ghosts/" + ghostName + "/" + ghostName + "up.png");
        URL downResource = ImageLoader.class.getClassLoader().getResource("PNGs/ghosts/" + ghostName + "/" + ghostName + "down.png");
        URL rightResource = ImageLoader.class.getClassLoader().getResource("PNGs/ghosts/" + ghostName + "/" + ghostName + "right.png");
        URL scaredResource = ImageLoader.class.getClassLoader().getResource("PNGs/ghosts/common/scaredghost.png");
        URL deadRightResource = ImageLoader.class.getClassLoader().getResource("PNGs/ghosts/common/deadghostright.png");
        URL deadLeftResource = ImageLoader.class.getClassLoader().getResource("PNGs/ghosts/common/deadghostleft.png");
        URL deadUpResource = ImageLoader.class.getClassLoader().getResource("PNGs/ghosts/common/deadghostup.png");
        URL deadDownResource = ImageLoader.class.getClassLoader().getResource("PNGs/ghosts/common/deadghostdown.png");

        assert leftResource != null;
        assert upResource != null;
        assert downResource != null;
        assert rightResource != null;
        assert scaredResource != null;
        assert deadRightResource != null;
        assert deadLeftResource != null;
        assert deadUpResource != null;
        assert deadDownResource != null;

        return Map.of(
                'L', ImageIO.read(new File(leftResource.toURI())),
                'U', ImageIO.read(new File(upResource.toURI())),
                'D', ImageIO.read(new File(downResource.toURI())),
                'R', ImageIO.read(new File(rightResource.toURI())),
                'S', ImageIO.read(new File(scaredResource.toURI())),
                'r', ImageIO.read(new File(deadRightResource.toURI())),
                'l', ImageIO.read(new File(deadLeftResource.toURI())),
                'u', ImageIO.read(new File(deadUpResource.toURI())),
                'd', ImageIO.read(new File(deadDownResource.toURI()))
        );
    }

    public static Map<Character, BufferedImage> loadPacmanImages() throws IOException, URISyntaxException {
        URL leftResource = ImageLoader.class.getClassLoader().getResource("PNGs/pacman/pacmanleft.png");
        URL upResource = ImageLoader.class.getClassLoader().getResource("PNGs/pacman/pacmanup.png");
        URL downResource = ImageLoader.class.getClassLoader().getResource("PNGs/pacman/pacmandown.png");
        URL rightResource = ImageLoader.class.getClassLoader().getResource("PNGs/pacman/pacmanright.png");

        assert leftResource != null;
        assert upResource != null;
        assert downResource != null;
        assert rightResource != null;

        return Map.of(
                'L', ImageIO.read(new File(leftResource.toURI())),
                'U', ImageIO.read(new File(upResource.toURI())),
                'D', ImageIO.read(new File(downResource.toURI())),
                'R', ImageIO.read(new File(rightResource.toURI()))
        );
    }

    public static BufferedImage loadImage(String filePath) throws URISyntaxException, IOException {
        URL resource = ImageLoader.class.getClassLoader().getResource(filePath);
        assert resource != null;
        File imageFile = new File(resource.toURI());
        return ImageIO.read(imageFile);
    }
}
