package pt.up.fe.ldts.pacman.viewer.menu;

import com.googlecode.lanterna.TextColor;
import pt.up.fe.ldts.pacman.gui.GUI;
import pt.up.fe.ldts.pacman.model.Position;
import pt.up.fe.ldts.pacman.model.game.Arena;
import pt.up.fe.ldts.pacman.model.menu.GameOverMenu;
import pt.up.fe.ldts.pacman.model.menu.element.TextBox;
import pt.up.fe.ldts.pacman.viewer.ModelViewer;
import pt.up.fe.ldts.pacman.viewer.ViewerFactory;
import pt.up.fe.ldts.pacman.viewer.game.ImageLoader;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URISyntaxException;

public class GameOverMenuViewer extends ModelViewer<GameOverMenu> {
    private final BufferedImage gameOverText;

    public GameOverMenuViewer() throws IOException, URISyntaxException {
        super(ViewerFactory.createGameOverMenuViewers());
        this.gameOverText = ImageLoader.loadBufferedImage("PNGs/gameover.png");
    }

    @Override
    public void drawElements(GUI gui, GameOverMenu menu, long frameCount) {
        Arena arena = menu.getArena();
        arena.getBlankPositions().forEach(position -> gui.erase(new Position(position.getX()*11, position.getY()*11)));
        arena.getWalls().forEach(wall -> drawElement(gui, wall, frameCount));
        drawElement(gui, arena.getGhostGate(), frameCount);
        arena.getCollectibles().forEach(collectible -> drawElement(gui, collectible, frameCount));
        arena.getGhosts().forEach(ghost -> drawElement(gui, ghost, frameCount));
        arena.getPacmans().forEach(pacman -> drawElement(gui,pacman, frameCount));
        drawElement(gui,new TextBox("Score:" + arena.getScore(), new Position(11,0), new TextColor.RGB(255,255,255)), frameCount);
        if(arena.getPacmans().size() == 2) {
            drawElement(gui,new TextBox("Lives P1:" + arena.getPacmans().getFirst().getLife(), new Position(199,0), new TextColor.RGB(255,255,255)), frameCount);
            drawElement(gui,new TextBox("Lives P2:" + arena.getPacmans().get(1).getLife(), new Position(259,0), new TextColor.RGB(255,255,255)), frameCount);
        }
        else drawElement(gui,new TextBox("Lives:" + arena.getPacmans().getFirst().getLife(), new Position(274,0), new TextColor.RGB(255,255,255)), frameCount);

        gui.drawImage(new Position(121,44), gameOverText, gameOverText.getWidth(), gameOverText.getHeight());
        menu.getOptions().forEach(textBox -> drawElement(gui, textBox, frameCount));
    }

}
