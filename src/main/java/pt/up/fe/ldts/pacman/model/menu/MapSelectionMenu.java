package pt.up.fe.ldts.pacman.model.menu;

import com.googlecode.lanterna.TextColor;
import pt.up.fe.ldts.pacman.model.Position;
import pt.up.fe.ldts.pacman.model.menu.element.TextBox;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MapSelectionMenu extends Menu {
    private final String folderstring;

    public MapSelectionMenu(String folderstring) {
        this.folderstring = folderstring;
        initializeOptions();
    }

    @Override
    protected List<TextBox> createOptions() {
        List<TextBox> options = new ArrayList<>();
        int y = 80;
        File mapFolder = new File("src/main/resources/Maps/" + folderstring);
        for (final File fileEntry : Objects.requireNonNull(mapFolder.listFiles())) {
            if (!fileEntry.isDirectory() && fileEntry.getName().endsWith(".txt")) {
                options.add(new TextBox(fileEntry.getName().substring(0, fileEntry.getName().length() - 4),
                        new Position(160 - ((fileEntry.getName().length() - 4) * 5) / 2, y), //math to center the text box
                        new TextColor.RGB(255, 255, 255)));
                y += 11;
            }
        }
        return options;
    }

    @Override
    protected TextBox createTitle() {
        return new TextBox("MAP SELECTION", new Position(128, 30), new TextColor.RGB(255, 255, 255));
    }

    public String getFolderstring() {
        return folderstring;
    }
}
