package pt.up.fe.ldts.pacman.model.game;

import pt.up.fe.ldts.pacman.model.game.element.*;
import pt.up.fe.ldts.pacman.model.game.element.item.Coin;
import pt.up.fe.ldts.pacman.model.game.element.item.PowerUp;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ArenaLoader {
    private final Arena arena;
    private final int width;
    private final int height;

    public ArenaLoader(Arena arena) {
        this.arena = arena;
        this.width = arena.getWidth();
        this.height = arena.getHeight();
    }

    public void loadMap(String mapFile) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(mapFile))) {
            String line;
            int row = 0;

            while ((line = reader.readLine()) != null && row < height) {
                for (int col = 0; col < Math.min(line.length(), width); col++) {
                    char currentChar = line.charAt(col);
                    loadElementAt(col, row, currentChar);
                }
                row++;
            }
        }
    }

    private void loadElementAt(int col, int row, char element) {
        Position position = new Position(col, row);

        switch (element) {
            case 'W':
                arena.addWall(new Wall(position));
                break;
            case 'P':
                arena.setPacmanPosition(position);
                break;
            case 'o':
                arena.addCoin(new Coin(position));
                break;
            case 'O':
                arena.addPowerUp(new PowerUp(position));
                break;
            case 'p':
                arena.setGhostPosition(arena.getPinky(), position);
                break;
            case 'i':
                arena.setGhostPosition(arena.getInky(), position);
                break;
            case 'c':
                arena.setGhostPosition(arena.getClyde(), position);
                break;
            case 'b':
                arena.setGhostPosition(arena.getBlinky(), position);
                break;
            /*case 'T': para caso usemos no futuro
                arena.addElement(new Teleporter(position));
                break; */
            case ' ':
                break;
            default:
                System.out.println("Unknown element '" + element + "' at (" + row + ", " + col + ")");
                break;
        }
    }
}