package pt.up.fe.ldts.pacman.model.menu;

import com.googlecode.lanterna.TextColor;
import pt.up.fe.ldts.pacman.States.State;
import pt.up.fe.ldts.pacman.gui.GUI;
import pt.up.fe.ldts.pacman.model.Position;
import pt.up.fe.ldts.pacman.model.menu.element.TextBox;

import java.util.ArrayList;
import java.util.List;

public class PauseMenu extends Menu{
    private final TextBox pauseSign;
    private final State<?> pausedState;

    public PauseMenu(State<?> pausedState, GUI.SCREEN_RESOLUTION resolution){
        this.pausedState = pausedState;
        this.pauseSign = new TextBox("||",new Position(155,41), new TextColor.RGB(255,255,255));
        setResolution(resolution);
    }

    @Override
    public List<TextBox> createOptions() {
        return new ArrayList<>(List.of(
                new TextBox("Resume", new Position(145, 80), new TextColor.RGB(255, 255, 255)),
                new TextBox("Resolution: 900p", new Position(118, 91), new TextColor.RGB(255, 255, 255)),
                new TextBox("Exit to main menu", new Position(118, 102), new TextColor.RGB(255, 255, 255))
        ));
    }

    @Override
    protected TextBox createTitle() {
        return new TextBox("Paused", new Position(145, 30), new TextColor.RGB(255, 255, 255));
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

    public boolean ResolutionSelected() {
        return getSelectedOption() == 1;
    }

    public boolean ExitSelected() {
        return getSelectedOption() == 2;
    }

    public void setResolution(GUI.SCREEN_RESOLUTION newResolution){
        getOptions().get(1).setText("Resolution: " + newResolution);
    }
}
