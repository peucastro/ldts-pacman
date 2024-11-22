package pt.up.fe.ldts.pacman.model.game;

import pt.up.fe.ldts.pacman.model.game.element.item.Collectible;
import pt.up.fe.ldts.pacman.model.game.element.pacman.Pacman;
import pt.up.fe.ldts.pacman.model.game.element.Wall;
import pt.up.fe.ldts.pacman.model.game.element.ghost.*;

import java.util.HashSet;

public class Arena {
    private final Pacman pacman;

    private HashSet<Ghost> ghosts;
    private HashSet<Wall> walls;
    private HashSet<Collectible> collectibles;

    private final int width, height;

    public Arena(int width, int height) {
        this.width = width;
        this.height = height;

        this.pacman = new Pacman(new Position(0, 0));

        this.ghosts = new HashSet<>();
        this.walls = new HashSet<>();
        this.collectibles = new HashSet<>();
    }

    public Pacman getPacman() {
        return pacman;
    }

    public HashSet<Ghost> getGhosts() {
        return ghosts;
    }

    public HashSet<Wall> getWalls() {
        return walls;
    }

    public HashSet<Collectible> getCollectibles() {
        return collectibles;
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

    public void setCollectibles(HashSet<Collectible> collectibles) {
        this.collectibles = collectibles;
    }

    public void setGhosts(HashSet<Ghost> ghosts) {
        this.ghosts = ghosts;
    }

    public void addWall(Wall wall) {
        walls.add(wall);
    }

    public void addCollectible(Collectible collectible) {collectibles.add(collectible);}

    public void addGhost(Ghost ghost) {ghosts.add(ghost);}

    public void setPacmanPosition(Position position) {
        pacman.setPosition(position);
    }
}
