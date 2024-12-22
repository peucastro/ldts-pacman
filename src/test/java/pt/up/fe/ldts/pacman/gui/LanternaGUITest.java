package pt.up.fe.ldts.pacman.gui;


import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.BasicTextImage;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.up.fe.ldts.pacman.model.Position;
import pt.up.fe.ldts.pacman.viewer.game.ImageLoader;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class LanternaGUITest {
    private Screen screen;
    private LanternaGUI gui;
    private TextGraphics tg;

    @BeforeEach
    void setUp() {
        this.screen = mock(Screen.class);
        this.tg = mock(TextGraphics.class);
        when(screen.newTextGraphics()).thenReturn(tg);
        this.gui = new LanternaGUI(screen, GUI.SCREEN_RESOLUTION._360p);
    }

    @Test
    void testBasicScreenCreation() {
        assertDoesNotThrow(() -> new LanternaGUI(screen, GUI.SCREEN_RESOLUTION._900p));
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

    @Test
    void testGetNextAction() throws IOException {
        List<KeyStroke> testKeyStrokes = List.of(
                new KeyStroke('q', false, false),
                new KeyStroke(KeyType.ArrowUp),
                new KeyStroke(KeyType.ArrowDown),
                new KeyStroke(KeyType.ArrowLeft),
                new KeyStroke(KeyType.ArrowRight),
                new KeyStroke('w', false, false),
                new KeyStroke('a', false, false),
                new KeyStroke('s', false, false),
                new KeyStroke('d', false, false),
                new KeyStroke(KeyType.Enter),
                new KeyStroke(KeyType.Escape)
        );

        List<GUI.ACTION> testActions = List.of(
                GUI.ACTION.QUIT,
                GUI.ACTION.UP,
                GUI.ACTION.DOWN,
                GUI.ACTION.LEFT,
                GUI.ACTION.RIGHT,
                GUI.ACTION.W,
                GUI.ACTION.A,
                GUI.ACTION.S,
                GUI.ACTION.D,
                GUI.ACTION.SELECT,
                GUI.ACTION.QUIT
        );

        for (int i = 0; i < testKeyStrokes.size(); ++i) {
            when(screen.pollInput()).thenReturn(testKeyStrokes.get(i)).thenReturn(null);

            List<GUI.ACTION> actions = gui.getNextAction();

            verify(screen, times(2)).pollInput(); //one for the keystroke and the other for the null
            assertEquals(testActions.get(i), actions.getFirst());
            reset(screen);
        }
    }

    @Test
    void testRefresh() throws IOException {
        gui.refresh();

        verify(screen, times(1)).refresh();
    }

    @Test
    void testClear() {
        gui.clear();

        verify(screen, times(1)).clear();
    }

    @Test
    void testClose() throws IOException {
        gui.close();

        verify(screen, times(1)).close();
    }

    @Test
    void testErase() {
        gui.erase(new Position(0, 0));

        verify(screen, times(1)).newTextGraphics();
        verify(tg, times(1)).setBackgroundColor(any());
        verify(tg, times(1)).fillRectangle(any(), any(), anyChar());
    }

    @Test
    void testDrawCharacter() throws IOException {
        BufferedImage letterA = ImageLoader.loadFontImages().get('A');

        gui.drawCharacter(new Position(0, 0), letterA, new TextColor.RGB(0, 0, 0));

        verify(screen, times(1)).newTextGraphics();
        verify(tg, times(1)).setBackgroundColor(new TextColor.RGB(0, 0, 0));
        /* 18 positions are drawn
        XXXXXXX
           X  X
           X  X
        XXXXXXX
         */
        verify(tg, times(18)).setCharacter(anyInt(), anyInt(), eq(' '));
    }

    @Test
    void testDrawBufferedImage() throws IOException {
        BufferedImage image = ImageLoader.loadBufferedImage("PNGs/wall.png");

        gui.drawImage(new Position(0, 0), image);

        verify(screen, times(1)).newTextGraphics();
        //both the background color and the character are set everytime, so 11*11 times
        verify(tg, times(11 * 11)).setBackgroundColor(any());
        verify(tg, times(11 * 11)).setCharacter(anyInt(), anyInt(), eq(' '));
    }

    @Test
    void testDrawBufferedImageWithSize() throws IOException {
        BufferedImage image = ImageLoader.loadBufferedImage("PNGs/wall.png");

        //only draw the first 5*5 portion of the image
        gui.drawImage(new Position(0, 0), image, 5, 5);

        verify(screen, times(1)).newTextGraphics();
        //both the background color and the character are set everytime, so 5*5 times
        verify(tg, times(5 * 5)).setBackgroundColor(any());
        verify(tg, times(5 * 5)).setCharacter(anyInt(), anyInt(), eq(' '));
    }

    @Test
    void testDrawTextImage() throws IOException {
        BasicTextImage textImage = ImageLoader.loadTextImage("PNGs/wall.png");

        gui.drawImage(new Position(0, 0), textImage);

        verify(screen, times(1)).newTextGraphics();
        verify(tg, times(1)).drawImage(new TerminalPosition(0, 0), textImage);
    }
}