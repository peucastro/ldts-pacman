package pt.up.fe.ldts.pacman.view.game.element;

import com.googlecode.lanterna.graphics.TextGraphics;
import pt.up.fe.ldts.pacman.model.game.Position;

public interface Drawable {
    void draw(TextGraphics graphics, Position position);
}
