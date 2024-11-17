package pt.up.fe.ldts.pacman.view.game;

import pt.up.fe.ldts.pacman.view.game.element.*;

import java.io.IOException;

public class View {
    public BlinkyDrawer blinkyDrawer;
    public PinkyDrawer pinkyDrawer;
    public PacmanDrawer pacmanDrawer;
    public ClydeDrawer clydeDrawer;
    public InkyDrawer inkyDrawer;
    public AppleDrawer appleDrawer;
    public CherryDrawer cherryDrawer;
    public DeadGhostDrawer deadGhostDrawer;
    public KeyDrawer keyDrawer;
    public OrangeDrawer orangeDrawer;
    public CoinDrawer coinDrawer;
    public StrawberryDrawer strawberryDrawer;
    public WallDrawer wallDrawer;

    public View() throws IOException {
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
}
