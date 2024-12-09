package pt.up.fe.ldts.pacman.model.game;

import pt.up.fe.ldts.pacman.model.Position;
import pt.up.fe.ldts.pacman.model.game.element.Wall;
import pt.up.fe.ldts.pacman.model.game.element.collectibles.Collectible;
import pt.up.fe.ldts.pacman.model.game.element.ghost.Ghost;
import pt.up.fe.ldts.pacman.model.game.element.pacman.Pacman;

import java.util.HashSet;
import java.util.Set;

public class Arena {
    private final Pacman pacman;

    private Set<Ghost> ghosts;
    private Set<Wall> walls;
    private Set<Collectible> collectibles;

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

    public Set<Ghost> getGhosts() {
        return ghosts;
    }

    public Set<Wall> getWalls() {
        return walls;
    }

    public Set<Collectible> getCollectibles() {
        return collectibles;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setWalls(Set<Wall> walls) {
        this.walls = walls;
    }

    public void setCollectibles(Set<Collectible> collectibles) {
        this.collectibles = collectibles;
    }

    public void setGhosts(Set<Ghost> ghosts) {
        this.ghosts = ghosts;
    }

    public void addWall(Wall wall) {
        walls.add(wall);
    }

    public void addCollectible(Collectible collectible) {
        collectibles.add(collectible);
    }

    public void addGhost(Ghost ghost) {
        ghosts.add(ghost);
    }

    public void setPacmanPosition(Position position) {
        pacman.setPosition(position);
    }

    public boolean isEmpty(Position position) {
        for (Wall wall : walls) {
            if (wall.getPosition().equals(position))
                return false;
        }
        return true;
    }

    public boolean isGhost(Position position) {
        for (Ghost ghost : ghosts)
            if (ghost.getPosition().equals(position))
                return true;
        return false;
    }
}
