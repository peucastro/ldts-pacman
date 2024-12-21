package pt.up.fe.ldts.pacman.gui;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class LanternaGUITest {
    @Test
    void testBasicScreenCreation() {
        assertDoesNotThrow(() -> new LanternaGUI(20, 20, GUI.SCREEN_RESOLUTION._900p));
    }

    @Test
    void invalidScreenSize() {
        assertThrows(IllegalArgumentException.class, () -> new LanternaGUI(-20, 20, GUI.SCREEN_RESOLUTION._900p));
        assertThrows(IllegalArgumentException.class, () -> new LanternaGUI(0, -500, GUI.SCREEN_RESOLUTION._900p));
    }

    @Test
    void screenResolutionToString() {
        String _360p = GUI.SCREEN_RESOLUTION._360p.toString();
        String _540p = GUI.SCREEN_RESOLUTION._540p.toString();
        String _720p = GUI.SCREEN_RESOLUTION._720p.toString();
        String _900p = GUI.SCREEN_RESOLUTION._900p.toString();
        String _1080p = GUI.SCREEN_RESOLUTION._1080p.toString();
        String _1440p = GUI.SCREEN_RESOLUTION._1440p.toString();
        String _2160p = GUI.SCREEN_RESOLUTION._2160p.toString();
        assertEquals("360p", _360p);
        assertEquals("540p", _540p);
        assertEquals("720p", _720p);
        assertEquals("900p", _900p);
        assertEquals("1080p", _1080p);
        assertEquals("1440p", _1440p);
        assertEquals("2160p", _2160p);
    }
}