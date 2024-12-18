package pt.up.fe.ldts.pacman.model.menu;

import com.googlecode.lanterna.TextColor;
import pt.up.fe.ldts.pacman.model.Position;
import pt.up.fe.ldts.pacman.model.game.Arena;
import pt.up.fe.ldts.pacman.model.menu.element.TextBox;

import java.util.List;

public class GameOverMenu extends Menu{
    private final Arena arena;

    public GameOverMenu(Arena arena){
        super();
        this.arena = arena;
        initializeOptions();
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
        return new TextBox("GAME OVER", new Position(160,30), new TextColor.RGB(255,0,0));
    }

    public Arena getArena() {
        return arena;
    }

    public boolean PlayAgainSelected(){return getSelectedOption() == 0;}

    public boolean ExitSelected(){return getSelectedOption() == 1;}
}
