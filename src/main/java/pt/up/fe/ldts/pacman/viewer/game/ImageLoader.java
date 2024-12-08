package pt.up.fe.ldts.pacman.viewer.game;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.BasicTextImage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ImageLoader {

    public static Map<Character, BufferedImage> loadGhostImages(String ghostName) throws IOException, URISyntaxException {
        BufferedImage leftResource = loadBufferedImage("PNGs/ghosts/" + ghostName + "/" + ghostName + "left.png");
        BufferedImage upResource = loadBufferedImage("PNGs/ghosts/" + ghostName + "/" + ghostName + "up.png");
        BufferedImage downResource = loadBufferedImage("PNGs/ghosts/" + ghostName + "/" + ghostName + "down.png");
        BufferedImage rightResource = loadBufferedImage("PNGs/ghosts/" + ghostName + "/" + ghostName + "right.png");
        BufferedImage scaredResource = loadBufferedImage("PNGs/ghosts/common/scaredghost.png");
        BufferedImage deadRightResource = loadBufferedImage("PNGs/ghosts/common/deadghostright.png");
        BufferedImage deadLeftResource = loadBufferedImage("PNGs/ghosts/common/deadghostleft.png");
        BufferedImage deadUpResource = loadBufferedImage("PNGs/ghosts/common/deadghostup.png");
        BufferedImage deadDownResource = loadBufferedImage("PNGs/ghosts/common/deadghostdown.png");


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

    public static Map<Character, BufferedImage> loadPacmanImages() throws IOException, URISyntaxException {
        BufferedImage leftResource = loadBufferedImage("PNGs/pacman/pacmanleft.png");
        BufferedImage upResource = loadBufferedImage("PNGs/pacman/pacmanup.png");
        BufferedImage downResource = loadBufferedImage("PNGs/pacman/pacmandown.png");
        BufferedImage rightResource = loadBufferedImage("PNGs/pacman/pacmanright.png");

        return Map.of(
                'L', leftResource,
                'U', upResource,
                'D', downResource,
                'R', rightResource
        );
    }


    public static BufferedImage loadBufferedImage(String filePath) throws IOException {
        URL resource = ImageLoader.class.getClassLoader().getResource(filePath);
        assert resource != null;
        return ImageIO.read(resource);
    }

    public static BasicTextImage loadTextImage(String filePath) throws IOException {
        URL resource = ImageLoader.class.getClassLoader().getResource(filePath);
        assert resource != null;
        return toTextImage(ImageIO.read(resource));
    }

    private static BasicTextImage toTextImage(BufferedImage image){
        BasicTextImage textImage = new BasicTextImage(11,11);

        for (int y = 0; y < 11; y++) {
            for (int x = 0; x < 11; x++) {
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

    public static Map<Character,BufferedImage> loadFontImages() throws IOException {
        InputStream fontMapResource = ImageLoader.class.getClassLoader().getResourceAsStream("Fonts/ingamefontmap.txt");
        URL fontResource = ImageLoader.class.getClassLoader().getResource("Fonts/ingamefont.png");
        assert fontMapResource != null;
        assert fontResource != null;

        Map<Character, BufferedImage> characters = new HashMap<>();
        String fontMap = new Scanner(fontMapResource, StandardCharsets.UTF_8).next(); fontMapResource.close();
        BufferedImage font = ImageIO.read(fontResource);

        int x = 0, y = 0;
        for(int i = 0; i < fontMap.length();++i){
            if(fontMap.charAt(i) == '\n'){
                x = 0; y += 11;
                continue;
            }
            characters.put(fontMap.charAt(i),font.getSubimage(x,y,5, 11));
            x += 5;
        }

        return characters;
    }
}
