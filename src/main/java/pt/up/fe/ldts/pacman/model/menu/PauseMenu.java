package pt.up.fe.ldts.pacman.model.menu;

import com.googlecode.lanterna.TextColor;
import pt.up.fe.ldts.pacman.States.State;
import pt.up.fe.ldts.pacman.model.Position;
import pt.up.fe.ldts.pacman.model.menu.element.TextBox;

import java.util.ArrayList;
import java.util.List;

public class PauseMenu extends Menu{
    private final TextBox pauseSign;
    private final State<?> pausedState;

    public PauseMenu(State<?> pausedState){
        this.pausedState = pausedState;
        this.pauseSign = new TextBox("||",new Position(105,41), new TextColor.RGB(255,255,255));
    }

    @Override
    public List<TextBox> createOptions() {
        return new ArrayList<>(List.of(
                new TextBox("Resume", new Position(95, 100), new TextColor.RGB(255, 255, 255)),
                new TextBox("Settings", new Position(90, 111), new TextColor.RGB(255, 255, 255)),
                new TextBox("Exit to main menu", new Position(68, 122), new TextColor.RGB(255, 255, 255))
        ));
    }

    @Override
    protected TextBox createTitle() {
        return new TextBox("Paused", new Position(95, 30), new TextColor.RGB(255, 255, 255));
    }

    public State<?> getPausedState() {
        return pausedState;
    }

    public TextBox getPauseSign() {
        return pauseSign;
    }

    public boolean ResumeSelected() {
        return getSelectedOption() == 0;
    }

    public boolean SettingsSelected() {
        return getSelectedOption() == 1;
    }

    public boolean ExitSelected() {
        return getSelectedOption() == 2;
    }
}
