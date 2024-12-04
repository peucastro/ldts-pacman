package pt.up.fe.ldts.pacman.gui;

import pt.up.fe.ldts.pacman.model.game.Position;

import java.awt.image.BufferedImage;
import java.io.IOException;

public interface GUI {
    ACTION getNextAction() throws IOException;

    void drawImage(Position position, BufferedImage image);

    void clear();

    void refresh() throws IOException;

    void close() throws IOException;

    enum ACTION {UP, RIGHT, DOWN, LEFT, NONE, QUIT, SELECT}
}
