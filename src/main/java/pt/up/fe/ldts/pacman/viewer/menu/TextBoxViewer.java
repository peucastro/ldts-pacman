package pt.up.fe.ldts.pacman.viewer.menu;

import pt.up.fe.ldts.pacman.gui.GUI;
import pt.up.fe.ldts.pacman.model.Element;
import pt.up.fe.ldts.pacman.model.Position;
import pt.up.fe.ldts.pacman.model.menu.element.TextBox;
import pt.up.fe.ldts.pacman.viewer.Viewer;

import java.awt.image.BufferedImage;
import java.util.Map;

public class TextBoxViewer extends Viewer<Element> {
    private final Map<Character, BufferedImage> characters;

    public TextBoxViewer(Map<Character, BufferedImage> characters) {
        this.characters = characters;
    }

    @Override
    public void drawElement(GUI gui, Element element) {
        TextBox textBox = (TextBox) element;
        String text = textBox.getText();
        int posX = textBox.getPosition().getX(), posY = textBox.getPosition().getY();
        for (int x = 0; x < text.length(); ++x) {
            BufferedImage character = characters.get(Character.toUpperCase(text.charAt(x)));
            if (character != null) {
                gui.drawCharacter(new Position(posX + 5 * x, posY), character, textBox.getColor());
            }
        }
    }
}
