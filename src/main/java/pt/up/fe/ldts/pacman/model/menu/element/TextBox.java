package pt.up.fe.ldts.pacman.model.menu.element;

import com.googlecode.lanterna.TextColor;
import pt.up.fe.ldts.pacman.model.Element;
import pt.up.fe.ldts.pacman.model.Position;

public class TextBox extends Element {
    private String text;
    private TextColor color;

    public TextBox(String text, Position pos, TextColor color) {
        super(pos);
        this.text = text;
        this.color = color;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public TextColor getColor() {
        return color;
    }

    public void setColor(TextColor color) {
        this.color = color;
    }
}
