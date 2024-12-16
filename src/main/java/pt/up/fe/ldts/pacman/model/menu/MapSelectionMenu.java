package pt.up.fe.ldts.pacman.model.menu;

import com.googlecode.lanterna.TextColor;
import pt.up.fe.ldts.pacman.model.Position;
import pt.up.fe.ldts.pacman.model.menu.element.TextBox;

import java.util.ArrayList;
import java.util.List;

public class MapSelectionMenu extends Menu {
    public MapSelectionMenu() {
        super();
    }

    @Override
    protected List<TextBox> createOptions() {
        return new ArrayList<>(List.of(
                new TextBox("Map 1", new Position(148, 80), new TextColor.RGB(255, 255, 255))
        ));
    }

    @Override
    protected TextBox createTitle() {
        return new TextBox("MAP SELECTION", new Position(128, 30), new TextColor.RGB(255, 255, 255));
    }
}
