package pt.up.fe.ldts.pacman.model.menu;

import com.googlecode.lanterna.TextColor;
import pt.up.fe.ldts.pacman.model.Position;
import pt.up.fe.ldts.pacman.model.menu.element.TextBox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainMenu extends Menu{
    @Override
    public List<TextBox> createOptions() {
        return new ArrayList<TextBox>(List.of(
                new TextBox("Start",new Position(100,100),new TextColor.RGB(255,255,255)),
                new TextBox("Settings", new Position(100,110),new TextColor.RGB(255,255,255)),
                new TextBox("Exit",new Position(100,120),new TextColor.RGB(255,255,255))
        ));
    }

    public boolean StartSelected(){
        return getSelectedOption() == 0;
    }

    public boolean SettingsSelected(){
        return getSelectedOption() == 1;
    }

    public boolean ExitSelected(){
        return getSelectedOption() == 2;
    }
}
