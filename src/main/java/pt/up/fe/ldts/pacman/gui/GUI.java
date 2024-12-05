package pt.up.fe.ldts.pacman.gui;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.BasicTextImage;
import pt.up.fe.ldts.pacman.model.Position;

import java.awt.image.BufferedImage;
import java.io.IOException;

public interface GUI {
    ACTION getNextAction() throws IOException;

    void drawImage(Position position, BasicTextImage image);

    void drawText(Position position, String text, TextColor textColor);

    void clear();

    void refresh() throws IOException;

    void close() throws IOException;

    enum ACTION {UP, RIGHT, DOWN, LEFT, NONE, QUIT, SELECT}
}
