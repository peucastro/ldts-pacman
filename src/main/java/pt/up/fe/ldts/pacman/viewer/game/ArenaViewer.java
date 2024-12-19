package pt.up.fe.ldts.pacman.viewer.game;


import com.googlecode.lanterna.TextColor;
import pt.up.fe.ldts.pacman.gui.GUI;
import pt.up.fe.ldts.pacman.model.Position;
import pt.up.fe.ldts.pacman.model.game.Arena;
import pt.up.fe.ldts.pacman.model.menu.element.TextBox;
import pt.up.fe.ldts.pacman.viewer.ModelViewer;
import pt.up.fe.ldts.pacman.viewer.ViewerFactory;

import java.io.IOException;
import java.net.URISyntaxException;

public class ArenaViewer extends ModelViewer<Arena> {
    public ArenaViewer() throws IOException, URISyntaxException {
        super(ViewerFactory.createArenaViewers());
    }

    @Override
    public void drawElements(GUI gui, Arena arena, long frameCount) {
        //before drawing all the elements erase the blank positions
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
    }

}
