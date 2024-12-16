package pt.up.fe.ldts.pacman.model.menu;

import com.googlecode.lanterna.TextColor;
import pt.up.fe.ldts.pacman.model.Position;
import pt.up.fe.ldts.pacman.model.game.element.pacman.Pacman;
import pt.up.fe.ldts.pacman.model.menu.element.TextBox;

import java.util.ArrayList;
import java.util.List;

public class MainMenu extends Menu {
    private final Pacman pacman;

    public MainMenu() {
        super();
        pacman = new Pacman(new Position(14, 4));
    }

    @Override
    public List<TextBox> createOptions() {
        return new ArrayList<>(List.of(
                new TextBox("Start", new Position(148, 80), new TextColor.RGB(255, 255, 255)),
                new TextBox("Settings", new Position(140, 91), new TextColor.RGB(255, 255, 255)),
                new TextBox("Exit", new Position(150, 102), new TextColor.RGB(255, 255, 255))
        ));
    }

    @Override
    protected TextBox createTitle() {
        return new TextBox("PACMAN", new Position(145, 30), new TextColor.RGB(255, 255, 255));
    }

    public boolean StartSelected() {
        return getSelectedOption() == 0;
    }

    public boolean SettingsSelected() {
        return getSelectedOption() == 1;
    }

    public boolean ExitSelected() {
        return getSelectedOption() == 2;
    }


    public Pacman getPacman() {
        return pacman;
    }
}
