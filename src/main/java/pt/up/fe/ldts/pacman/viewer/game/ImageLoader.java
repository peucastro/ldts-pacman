package pt.up.fe.ldts.pacman.viewer.game;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.BasicTextImage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;

public class ImageLoader {

    public static Map<Character, BasicTextImage> loadGhostImages(String ghostName) throws IOException, URISyntaxException {
        BasicTextImage leftResource = loadImage("PNGs/ghosts/" + ghostName + "/" + ghostName + "left.png");
        BasicTextImage upResource = loadImage("PNGs/ghosts/" + ghostName + "/" + ghostName + "up.png");
        BasicTextImage downResource = loadImage("PNGs/ghosts/" + ghostName + "/" + ghostName + "down.png");
        BasicTextImage rightResource = loadImage("PNGs/ghosts/" + ghostName + "/" + ghostName + "right.png");
        BasicTextImage scaredResource = loadImage("PNGs/ghosts/common/scaredghost.png");
        BasicTextImage deadRightResource = loadImage("PNGs/ghosts/common/deadghostright.png");
        BasicTextImage deadLeftResource = loadImage("PNGs/ghosts/common/deadghostleft.png");
        BasicTextImage deadUpResource = loadImage("PNGs/ghosts/common/deadghostup.png");
        BasicTextImage deadDownResource = loadImage("PNGs/ghosts/common/deadghostdown.png");


        return Map.of(
                'L', leftResource,
                'U', upResource,
                'D', downResource,
                'R', rightResource,
                'S', scaredResource,
                'r', deadRightResource,
                'l', deadLeftResource,
                'u', deadUpResource,
                'd', deadDownResource
        );
    }

    public static Map<Character, BasicTextImage> loadPacmanImages() throws IOException, URISyntaxException {
        BasicTextImage leftResource = loadImage("PNGs/pacman/pacmanleft.png");
        BasicTextImage upResource = loadImage("PNGs/pacman/pacmanup.png");
        BasicTextImage downResource = loadImage("PNGs/pacman/pacmandown.png");
        BasicTextImage rightResource = loadImage("PNGs/pacman/pacmanright.png");

        return Map.of(
                'L', leftResource,
                'U', upResource,
                'D', downResource,
                'R', rightResource
        );
    }

    public static BasicTextImage loadImage(String filePath) throws IOException {
        URL resource = ImageLoader.class.getClassLoader().getResource(filePath);
        assert resource != null;
        return toTextImage(ImageIO.read(resource));
    }

    private static BasicTextImage toTextImage(BufferedImage image){
        BasicTextImage textImage = new BasicTextImage(14,14);

        for (int y = 0; y < 14; y++) {
            for (int x = 0; x < 14; x++) {
                if (image.getRGB(x, y) == 0) continue;

                int RGB = image.getRGB(x,y);
                int red = RGB >> 16 & 0xFF;
                int green = RGB >> 8 & 0xFF;
                int blue = RGB & 0xFF;

                textImage.setCharacterAt(x,y,new TextCharacter(' ', null, new TextColor.RGB(red,green,blue)));
            }
        }
        return textImage;
    }
}
