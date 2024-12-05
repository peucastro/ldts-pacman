package pt.up.fe.ldts.pacman.viewer.menu;

import pt.up.fe.ldts.pacman.gui.GUI;
import pt.up.fe.ldts.pacman.model.menu.element.TextBox;
import pt.up.fe.ldts.pacman.viewer.Viewer;

public class TextBoxViewer extends Viewer<TextBox> {
    @Override
    public void drawElement(GUI gui, TextBox textBox) {
        gui.drawText(textBox.getPosition(),textBox.getText(),textBox.getColor());
    }
}
