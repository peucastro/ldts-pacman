package pt.up.fe.ldts.pacman.viewer.game;

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
import pt.up.fe.ldts.pacman.viewer.Viewer;
import pt.up.fe.ldts.pacman.viewer.game.element.Viewable;
import pt.up.fe.ldts.pacman.viewer.game.element.WallViewer;
import pt.up.fe.ldts.pacman.viewer.game.element.ghost.BlinkyViewer;
import pt.up.fe.ldts.pacman.viewer.game.element.ghost.ClydeViewer;
import pt.up.fe.ldts.pacman.viewer.game.element.ghost.InkyViewer;
import pt.up.fe.ldts.pacman.viewer.game.element.ghost.PinkyViewer;
import pt.up.fe.ldts.pacman.viewer.game.element.item.AppleViewer;
import pt.up.fe.ldts.pacman.viewer.game.element.item.CoinViewer;
import pt.up.fe.ldts.pacman.viewer.game.element.pacman.PacmanViewer;

import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

public class ArenaViewer extends Viewer {
    private final Arena arena;
    private final Map<Class<?>, Viewable> viewers;

    public ArenaViewer(Arena arena) throws IOException {
        this.arena = arena;
        this.viewers = new HashMap<>();

        this.viewers.put(Wall.class, new WallViewer());
        this.viewers.put(Coin.class, new CoinViewer());
        this.viewers.put(PowerUp.class, new AppleViewer());
        this.viewers.put(Pacman.class, new PacmanViewer());
        this.viewers.put(Blinky.class, new BlinkyViewer());
        this.viewers.put(Pinky.class, new PinkyViewer());
        this.viewers.put(Inky.class, new InkyViewer());
        this.viewers.put(Clyde.class, new ClydeViewer());
    }

    private void drawElement(TextGraphics graphics, Element element) {
        Viewable drawer = viewers.get(element.getClass());
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
