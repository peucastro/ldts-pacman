package pt.up.fe.ldts.pacman.viewer;

import org.junit.jupiter.api.Test;
import pt.up.fe.ldts.pacman.model.Element;
import pt.up.fe.ldts.pacman.model.game.element.*;
import pt.up.fe.ldts.pacman.model.game.element.collectibles.*;
import pt.up.fe.ldts.pacman.model.game.element.ghost.*;
import pt.up.fe.ldts.pacman.model.game.element.pacman.Pacman;
import pt.up.fe.ldts.pacman.model.menu.element.TextBox;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ViewerFactoryTest {

    @Test
    void testCreateArenaViewers() throws IOException {
        Map<Class<?>, Viewer<Element>> viewers = ViewerFactory.createArenaViewers();

        assertNotNull(viewers.get(Wall.class));
        assertNotNull(viewers.get(GhostGate.class));
        assertNotNull(viewers.get(Coin.class));
        assertNotNull(viewers.get(Apple.class));
        assertNotNull(viewers.get(Cherry.class));
        assertNotNull(viewers.get(Key.class));
        assertNotNull(viewers.get(Orange.class));
        assertNotNull(viewers.get(Strawberry.class));
        assertNotNull(viewers.get(PowerUp.class));

        assertNotNull(viewers.get(TextBox.class));
        assertNotNull(viewers.get(Pacman.class));

        assertNotNull(viewers.get(Blinky.class));
        assertNotNull(viewers.get(Pinky.class));
        assertNotNull(viewers.get(Inky.class));
        assertNotNull(viewers.get(Clyde.class));
    }

    @Test
    void testCreateMainMenuViewers() throws IOException {
        Map<Class<?>, Viewer<Element>> viewers = ViewerFactory.createMainMenuViewers();

        assertNotNull(viewers.get(TextBox.class));
        assertNotNull(viewers.get(Pacman.class));

        assertNotNull(viewers.get(Blinky.class));
        assertNotNull(viewers.get(Pinky.class));
        assertNotNull(viewers.get(Inky.class));
        assertNotNull(viewers.get(Clyde.class));
    }

    @Test
    void testCreatePauseMenuViewers() throws IOException {
        Map<Class<?>, Viewer<Element>> viewers = ViewerFactory.createPauseMenuViewers();

        assertNotNull(viewers.get(TextBox.class));
    }

    @Test
    void testCreateMapSelectionMenuViewers() throws IOException {
        Map<Class<?>, Viewer<Element>> viewers = ViewerFactory.createMapSelectionMenuViewers();

        assertNotNull(viewers.get(TextBox.class));
    }

    @Test
    void testCreateAlertMenuViewers() throws IOException {
        Map<Class<?>, Viewer<Element>> viewers = ViewerFactory.createAlertMenuViewers();

        Map<Class<?>, Viewer<Element>> arenaViewers = ViewerFactory.createArenaViewers();
        for (Class<?> key : arenaViewers.keySet()) {
            assertNotNull(viewers.get(key));
        }
    }
}
