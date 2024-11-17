package pt.up.fe.ldts.pacman.view.game;

import com.googlecode.lanterna.graphics.TextGraphics;
import pt.up.fe.ldts.pacman.model.game.Arena;
import pt.up.fe.ldts.pacman.model.game.element.*;
import pt.up.fe.ldts.pacman.model.game.element.item.Coin;
import pt.up.fe.ldts.pacman.model.game.element.item.PowerUp;

public class ArenaDrawer extends Drawer {
    private final Arena arena;

    public ArenaDrawer(Arena arena) {
        this.arena = arena;
    }

    public void draw(TextGraphics graphics) {
        drawEntities(graphics, arena);
    }

    private void drawEntities(TextGraphics graphics, Arena arena) {
        try {
            View view = new View();

            for (Wall wall : arena.getWalls()) {
                view.wallDrawer.draw(graphics, wall.getPosition());
            }

            for (Coin coin : arena.getCoins()) {
                view.coinDrawer.draw(graphics, coin.getPosition());
            }

            for (PowerUp powerUp : arena.getPowerUps()) {
                view.appleDrawer.draw(graphics, powerUp.getPosition());
            }

            view.pacmanDrawer.draw(graphics, arena.getPacman().getPosition());
            view.pinkyDrawer.draw(graphics, arena.getPinky().getPosition());
            view.inkyDrawer.draw(graphics, arena.getInky().getPosition());
            view.clydeDrawer.draw(graphics, arena.getClyde().getPosition());
            view.blinkyDrawer.draw(graphics, arena.getBlinky().getPosition());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
