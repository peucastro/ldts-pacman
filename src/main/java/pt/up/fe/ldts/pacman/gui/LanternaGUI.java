package pt.up.fe.ldts.pacman.gui;


import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.BasicTextImage;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import pt.up.fe.ldts.pacman.model.Position;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class LanternaGUI implements GUI {
    private Screen screen;

    public LanternaGUI(Screen screen) {
        this.screen = screen;
    }

    public LanternaGUI(int width, int height, SCREEN_RESOLUTION resolution) throws IOException, FontFormatException, URISyntaxException {
        AWTTerminalFontConfiguration fontConfig = loadSquareFont(resolutionToFontSize(resolution));
        Terminal terminal = createTerminal(width, height, fontConfig);
        this.screen = createScreen(terminal);
    }

    private Screen createScreen(Terminal terminal) throws IOException {
        final Screen screen = new TerminalScreen(terminal);

        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();
        return screen;
    }

    private Terminal createTerminal(int width, int height, AWTTerminalFontConfiguration fontConfig) throws IOException {
        TerminalSize terminalSize = new TerminalSize(width, height + 1);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
        terminalFactory.setForceAWTOverSwing(true);
        terminalFactory.setTerminalEmulatorFontConfiguration(fontConfig);
        return terminalFactory.createTerminal();
    }

    private AWTTerminalFontConfiguration loadSquareFont(int fontSize) throws URISyntaxException, FontFormatException, IOException {
        URL resource = getClass().getClassLoader().getResource("Fonts/square.ttf");
        assert resource != null;
        File fontFile = new File(resource.toURI());
        Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);

        Font loadedFont = font.deriveFont(Font.PLAIN, fontSize);
        return AWTTerminalFontConfiguration.newInstance(loadedFont);
    }

    @Override
    public void resizeScreen(int width, int height, SCREEN_RESOLUTION newResolution) throws URISyntaxException, IOException, FontFormatException {
        screen.close();
        AWTTerminalFontConfiguration fontConfig = loadSquareFont(resolutionToFontSize(newResolution));
        Terminal terminal = createTerminal(width, height, fontConfig);
        this.screen = createScreen(terminal);
    }

    private int resolutionToFontSize(SCREEN_RESOLUTION resolution){
        return switch (resolution){
            case _360p -> 2;
            case _540p -> 3;
            case _720p -> 4;
            case _900p -> 5;
            case _1080p -> 6;
            case _1440p -> 8;
            case _2160p -> 12;
        };
    }

    @Override
    public ACTION getNextAction() throws IOException {
        KeyStroke keyStroke = screen.pollInput();
        if (keyStroke == null) return ACTION.NONE;

        if (keyStroke.getKeyType() == KeyType.EOF || keyStroke.getKeyType() == KeyType.Escape) return ACTION.QUIT;
        if (keyStroke.getKeyType() == KeyType.Character && keyStroke.getCharacter() == 'q') return ACTION.QUIT;

        if (keyStroke.getKeyType() == KeyType.ArrowUp) return ACTION.UP;
        if (keyStroke.getKeyType() == KeyType.ArrowRight) return ACTION.RIGHT;
        if (keyStroke.getKeyType() == KeyType.ArrowDown) return ACTION.DOWN;
        if (keyStroke.getKeyType() == KeyType.ArrowLeft) return ACTION.LEFT;

        if (keyStroke.getKeyType() == KeyType.Character && keyStroke.getCharacter() == 'w') return ACTION.W;
        if (keyStroke.getKeyType() == KeyType.Character && keyStroke.getCharacter() == 'a') return ACTION.A;
        if (keyStroke.getKeyType() == KeyType.Character && keyStroke.getCharacter() == 's') return ACTION.S;
        if (keyStroke.getKeyType() == KeyType.Character && keyStroke.getCharacter() == 'd') return ACTION.D;


        if (keyStroke.getKeyType() == KeyType.Enter) return ACTION.SELECT;

        return ACTION.NONE;
    }

    @Override
    public void drawImage(Position position, BasicTextImage image) {
        screen.newTextGraphics().drawImage(position.toTerminalPosition(), image);
    }

    @Override
    public void drawImage(Position position, BufferedImage image) {
        TextGraphics tg = screen.newTextGraphics();
        int posX = position.getX();
        int posY = position.getY();

        for (int y = 0; y < 11; y++) {
            for (int x = 0; x < 11; x++) {
                if (image.getRGB(x, y) == 0) continue;

                int RGB = image.getRGB(x, y);
                int red = RGB >> 16 & 0xFF;
                int green = RGB >> 8 & 0xFF;
                int blue = RGB & 0xFF;

                tg.setBackgroundColor(new TextColor.RGB(red, green, blue));
                tg.setCharacter(posX + x, posY + y, ' ');
            }
        }
    }

    @Override
    public void drawCharacter(Position position, BufferedImage character, TextColor color) {
        TextGraphics tg = screen.newTextGraphics();
        tg.setBackgroundColor(color);
        int posX = position.getX();
        int posY = position.getY();

        for (int y = 0; y < 11; y++) {
            for (int x = 0; x < 5; x++) {
                if (character.getRGB(x, y) == 0) continue;
                tg.setCharacter(posX + x, posY + y, ' ');
            }
        }
    }

    @Override
    public void erase(Position position) {
        TextGraphics tg = screen.newTextGraphics();
        tg.setBackgroundColor(new TextColor.RGB(0,0,0));
        tg.fillRectangle(position.toTerminalPosition(), new TerminalSize(11,11), ' ');
    }

    @Override
    public void clear() {
        screen.clear();
    }

    @Override
    public void refresh() throws IOException {
        screen.refresh();
    }

    @Override
    public void close() throws IOException {
        screen.close();
    }
}
