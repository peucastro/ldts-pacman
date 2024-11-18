package pt.up.fe.ldts.pacman.view.game;

import com.googlecode.lanterna.graphics.TextGraphics;
import pt.up.fe.ldts.pacman.model.game.Arena;
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
    private BlinkyDrawer blinkyDrawer;
    private PinkyDrawer pinkyDrawer;
    private PacmanDrawer pacmanDrawer;
    private ClydeDrawer clydeDrawer;
    private InkyDrawer inkyDrawer;
    private AppleDrawer appleDrawer;
    private CherryDrawer cherryDrawer;
    private DeadGhostDrawer deadGhostDrawer;
    private KeyDrawer keyDrawer;
    private OrangeDrawer orangeDrawer;
    private CoinDrawer coinDrawer;
    private StrawberryDrawer strawberryDrawer;
    private WallDrawer wallDrawer;

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

    public void drawEntities(TextGraphics graphics) throws IOException {

        for (Wall wall : arena.getWalls()) {
            wallDrawer.draw(graphics, wall.getPosition());
        }

        for (Coin coin : arena.getCoins()) {
            coinDrawer.draw(graphics, coin.getPosition());
        }

        for (PowerUp powerUp : arena.getPowerUps()) {
            appleDrawer.draw(graphics, powerUp.getPosition());
        }

        pacmanDrawer.draw(graphics, arena.getPacman().getPosition());
        pinkyDrawer.draw(graphics, arena.getPinky().getPosition());
        inkyDrawer.draw(graphics, arena.getInky().getPosition());
        clydeDrawer.draw(graphics, arena.getClyde().getPosition());
        blinkyDrawer.draw(graphics, arena.getBlinky().getPosition());
    }
}
