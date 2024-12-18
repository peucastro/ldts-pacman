package pt.up.fe.ldts.pacman.viewer.menu;

import com.googlecode.lanterna.TextColor;
import pt.up.fe.ldts.pacman.gui.GUI;
import pt.up.fe.ldts.pacman.model.Element;
import pt.up.fe.ldts.pacman.model.Position;
import pt.up.fe.ldts.pacman.model.game.Arena;
import pt.up.fe.ldts.pacman.model.menu.WinMenu;
import pt.up.fe.ldts.pacman.model.menu.element.TextBox;
import pt.up.fe.ldts.pacman.viewer.Viewer;
import pt.up.fe.ldts.pacman.viewer.ViewerFactory;
import pt.up.fe.ldts.pacman.viewer.game.ImageLoader;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

public class WinMenuViewer extends Viewer<WinMenu> {
    private final Map<Class<?>, Viewer<Element>> viewers;
    private final BufferedImage winText;

    public WinMenuViewer() throws IOException, URISyntaxException {
        this.viewers = ViewerFactory.createGameOverMenuViewers();
        this.winText = ImageLoader.loadBufferedImage("PNGs/youwin.png");
    }

    public void drawElement(GUI gui, Element element, long frameCount) {
        Viewer<Element> viewer = viewers.get(element.getClass());
        if (viewer != null) {
            viewer.drawElement(gui, element, frameCount);
        }
    }

    public void drawElements(GUI gui, WinMenu menu, long frameCount) {
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

        gui.drawImage(new Position(60,38), winText, winText.getWidth(), winText.getHeight());
        menu.getOptions().forEach(textBox -> drawElement(gui, textBox, frameCount));

        if(menu.getMaxScore() != null) drawElement(gui, menu.getMaxScore(), frameCount);
    }

    @Override
    public void drawElement(GUI gui, WinMenu menu, long frameCount) {
        drawElements(gui, menu, frameCount);
        try {
            gui.refresh();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
