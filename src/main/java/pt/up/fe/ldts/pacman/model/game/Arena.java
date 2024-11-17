package pt.up.fe.ldts.pacman.model.game;

import pt.up.fe.ldts.pacman.model.game.element.Pacman;
import pt.up.fe.ldts.pacman.model.game.element.Wall;
import pt.up.fe.ldts.pacman.model.game.element.ghost.*;
import pt.up.fe.ldts.pacman.model.game.element.item.Coin;
import pt.up.fe.ldts.pacman.model.game.element.item.PowerUp;

import java.util.HashSet;

public class Arena {
    private final Pacman pacman;
    private final Ghost blinky;
    private final Ghost pinky;
    private final Ghost inky;
    private final Ghost clyde;

    private HashSet<Wall> walls;
    private HashSet<Coin> coins;
    private HashSet<PowerUp> powerUps;

    private final int width, height;

    public Arena(int width, int height) {
        this.width = width;
        this.height = height;

        this.pacman = new Pacman(new Position(0, 0));
        this.blinky = new Blinky(new Position(0, 0));
        this.pinky = new Pinky(new Position(0, 0));
        this.inky = new Inky(new Position(0, 0));
        this.clyde = new Clyde(new Position(0, 0));

        walls = new HashSet<>();
        coins = new HashSet<>();
        powerUps = new HashSet<>();
    }

    public Pacman getPacman() {
        return pacman;
    }

    public Ghost getBlinky() {
        return blinky;
    }

    public Ghost getPinky() {
        return pinky;
    }

    public Ghost getInky() {
        return inky;
    }

    public Ghost getClyde() {
        return clyde;
    }

    public HashSet<Wall> getWalls() {
        return walls;
    }

    public HashSet<Coin> getCoins() {
        return coins;
    }

    public HashSet<PowerUp> getPowerUps() {
        return powerUps;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setWalls(HashSet<Wall> walls) {
        this.walls = walls;
    }

    public void setCoins(HashSet<Coin> coins) {
        this.coins = coins;
    }

    public void setPowerUps(HashSet<PowerUp> powerUps) {
        this.powerUps = powerUps;
    }

    public void addWall(Wall wall) {
        walls.add(wall);
    }

    public void addCoin(Coin coin) {
        coins.add(coin);
    }

    public void addPowerUp(PowerUp powerUp) {
        powerUps.add(powerUp);
    }

    public void setPacmanPosition(Position position) {
        pacman.setPosition(position);
    }

    public void setGhostPosition(Ghost ghost, Position position) {
        ghost.setPosition(position);
    }
}
