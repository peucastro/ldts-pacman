package pt.up.fe.ldts.pacman.viewer.game;

import com.googlecode.lanterna.graphics.TextGraphics;
import pt.up.fe.ldts.pacman.model.game.Position;

public interface Viewable {
    void draw(TextGraphics graphics, Position position);
}
