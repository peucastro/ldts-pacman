package pt.up.fe.ldts.pacman.view.game;

import com.googlecode.lanterna.graphics.TextGraphics;
import pt.up.fe.ldts.pacman.model.game.Arena;
import pt.up.fe.ldts.pacman.model.game.Position;
import pt.up.fe.ldts.pacman.model.game.element.*;
import pt.up.fe.ldts.pacman.model.game.element.ghost.Blinky;
import pt.up.fe.ldts.pacman.model.game.element.ghost.Clyde;
import pt.up.fe.ldts.pacman.model.game.element.ghost.Inky;
import pt.up.fe.ldts.pacman.model.game.element.ghost.Pinky;
import pt.up.fe.ldts.pacman.model.game.element.item.Coin;
import pt.up.fe.ldts.pacman.model.game.element.item.PowerUp;
import pt.up.fe.ldts.pacman.model.game.element.pacman.Pacman;
import pt.up.fe.ldts.pacman.view.game.element.*;
import pt.up.fe.ldts.pacman.view.game.element.ghost.*;
import pt.up.fe.ldts.pacman.view.game.element.item.*;
import pt.up.fe.ldts.pacman.view.game.element.pacman.PacmanDrawer;

import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

public class ArenaDrawer extends Drawer {
    private final Arena arena;
    private final Map<Class<?>, Drawable> drawers;

    public ArenaDrawer(Arena arena) throws IOException {
        this.arena = arena;
        this.drawers = new HashMap<>();

        this.drawers.put(Wall.class, new WallDrawer());
        this.drawers.put(Coin.class, new CoinDrawer());
        this.drawers.put(PowerUp.class, new AppleDrawer());
        this.drawers.put(Pacman.class, new PacmanDrawer());
        this.drawers.put(Blinky.class, new BlinkyDrawer());
        this.drawers.put(Pinky.class, new PinkyDrawer());
        this.drawers.put(Inky.class, new InkyDrawer());
        this.drawers.put(Clyde.class, new ClydeDrawer());
    }

    private void drawElement(TextGraphics graphics, Element element) {
        Drawable drawer = drawers.get(element.getClass());
        if (drawer != null) {
            drawer.draw(graphics, new Position(element.getPosition().getX() * 14, element.getPosition().getY() * 14));
        }
    }

    public void drawElements(TextGraphics graphics) {
        arena.getWalls().forEach(wall -> drawElement(graphics, wall));
        arena.getCoins().forEach(coin -> drawElement(graphics, coin));
        arena.getPowerUps().forEach(powerUp -> drawElement(graphics, powerUp));
        drawElement(graphics, arena.getPacman());
        drawElement(graphics, arena.getBlinky());
        drawElement(graphics, arena.getPinky());
        drawElement(graphics, arena.getInky());
        drawElement(graphics, arena.getClyde());
    }
}
