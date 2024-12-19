package pt.up.fe.ldts.pacman.model.menu;

import com.googlecode.lanterna.TextColor;
import pt.up.fe.ldts.pacman.model.Position;
import pt.up.fe.ldts.pacman.model.game.Arena;
import pt.up.fe.ldts.pacman.model.menu.element.TextBox;

import java.util.List;

public class WinMenu extends Menu{
    private final Arena arena;
    private TextBox maxScore = null;

    public WinMenu(Arena arena, boolean maxScore){
        super();
        this.arena = arena;
        initializeOptions();
        if(maxScore) this.maxScore = new TextBox("Highest score!!!", new Position(120,0), new TextColor.RGB(255,255,0));
    }

    @Override
    protected List<TextBox> createOptions() {
        return List.of(
                new TextBox("Play Again", new Position(135, 99), new TextColor.RGB(255, 255, 255)),
                new TextBox("Back to main menu", new Position(118, 110), new TextColor.RGB(255, 255, 255))
        );
    }

    @Override
    protected TextBox createTitle() {
        return null;
    }

    public Arena getArena() {
        return arena;
    }

    public boolean PlayAgainSelected(){return getSelectedOption() == 0;}

    public boolean ExitSelected(){return getSelectedOption() == 1;}

    public TextBox getMaxScore() {
        return maxScore;
    }
}
