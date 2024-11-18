package pt.up.fe.ldts.pacman.view.game;

import com.googlecode.lanterna.graphics.TextGraphics;
import pt.up.fe.ldts.pacman.model.game.Arena;
import pt.up.fe.ldts.pacman.model.game.Position;
import pt.up.fe.ldts.pacman.model.game.element.*;
import pt.up.fe.ldts.pacman.model.game.element.item.Coin;
import pt.up.fe.ldts.pacman.model.game.element.item.PowerUp;
import pt.up.fe.ldts.pacman.view.game.element.*;
import pt.up.fe.ldts.pacman.view.game.element.ghost.*;
import pt.up.fe.ldts.pacman.view.game.element.item.*;
import pt.up.fe.ldts.pacman.view.game.element.pacman.PacmanDrawer;

import java.io.IOException;

public class ArenaDrawer extends Drawer {
    private final Arena arena;
    private final BlinkyDrawer blinkyDrawer;
    private final PinkyDrawer pinkyDrawer;
    private final PacmanDrawer pacmanDrawer;
    private final ClydeDrawer clydeDrawer;
    private final InkyDrawer inkyDrawer;
    private final AppleDrawer appleDrawer;
    private final CherryDrawer cherryDrawer;
    private final DeadGhostDrawer deadGhostDrawer;
    private final KeyDrawer keyDrawer;
    private final OrangeDrawer orangeDrawer;
    private final CoinDrawer coinDrawer;
    private final StrawberryDrawer strawberryDrawer;
    private final WallDrawer wallDrawer;

    public ArenaDrawer(Arena arena) throws IOException {
        this.arena = arena;
        this.blinkyDrawer = new BlinkyDrawer();
        this.clydeDrawer = new ClydeDrawer();
        this.inkyDrawer = new InkyDrawer();
        this.pacmanDrawer = new PacmanDrawer();
        this.pinkyDrawer = new PinkyDrawer();
        this.appleDrawer = new AppleDrawer();
        this.cherryDrawer = new CherryDrawer();
        this.deadGhostDrawer = new DeadGhostDrawer();
        this.keyDrawer = new KeyDrawer();
        this.orangeDrawer = new OrangeDrawer();
        this.coinDrawer = new CoinDrawer();
        this.strawberryDrawer = new StrawberryDrawer();
        this.wallDrawer = new WallDrawer();
    }

    public void drawElements(TextGraphics graphics) {

        for (Wall wall : arena.getWalls()) {
            wallDrawer.draw(graphics, new Position(wall.getPosition().getX()*14, wall.getPosition().getY()*14));
        }

        for (Coin coin : arena.getCoins()) {
            coinDrawer.draw(graphics, new Position(coin.getPosition().getX()*14, coin.getPosition().getY()*14));
        }

        for (PowerUp powerUp : arena.getPowerUps()) {
            appleDrawer.draw(graphics, new Position(powerUp.getPosition().getX()*14, powerUp.getPosition().getY()*14));
        }

        pacmanDrawer.draw(graphics, new Position(arena.getPacman().getPosition().getX()*14, arena.getPacman().getPosition().getY()*14));
        pinkyDrawer.draw(graphics, new Position(arena.getPinky().getPosition().getX()*14, arena.getPinky().getPosition().getY()*14));
        inkyDrawer.draw(graphics, new Position(arena.getInky().getPosition().getX()*14, arena.getInky().getPosition().getY()*14));
        clydeDrawer.draw(graphics, new Position(arena.getClyde().getPosition().getX()*14, arena.getClyde().getPosition().getY()*14));
        blinkyDrawer.draw(graphics, new Position(arena.getBlinky().getPosition().getX()*14, arena.getBlinky().getPosition().getY()*14));
    }
}
