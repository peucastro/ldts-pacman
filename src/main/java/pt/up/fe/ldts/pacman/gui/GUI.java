package pt.up.fe.ldts.pacman.gui;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.BasicTextImage;
import pt.up.fe.ldts.pacman.model.Position;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URISyntaxException;

public interface GUI {
    ACTION getNextAction() throws IOException;

    void drawImage(Position position, BasicTextImage image);

    void drawImage(Position position, BufferedImage image);

    void drawCharacter(Position position, BufferedImage character, TextColor color);

    void clear();

    void erase(Position position);

    void refresh() throws IOException;

    void close() throws IOException;

    void resizeScreen(int width, int height, SCREEN_RESOLUTION newResolution) throws URISyntaxException, IOException, FontFormatException;

    enum ACTION {UP, RIGHT, DOWN, LEFT, NONE, QUIT, SELECT, W, A, S, D}

    enum SCREEN_RESOLUTION {
        _360p,
        _540p,
        _720p,
        _900p,
        _1080p,
        _1440p,
        _2160p;

        @Override
        public String toString() {
            return  switch (this){
                case _360p -> "360p";
                case _540p -> "540p";
                case _720p -> "720p";
                case _900p -> "900p";
                case _1080p -> "1080p";
                case _1440p -> "1440p";
                case _2160p -> "2160p";
            };
        }
    }
}
