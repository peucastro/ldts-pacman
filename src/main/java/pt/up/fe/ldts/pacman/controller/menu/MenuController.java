package pt.up.fe.ldts.pacman.controller.menu;

import pt.up.fe.ldts.pacman.controller.Controller;
import pt.up.fe.ldts.pacman.model.menu.Menu;

public abstract class MenuController<T extends Menu> extends Controller<T> {
    public MenuController(T model) {
        super(model);
    }
}
