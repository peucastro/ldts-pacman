package pt.up.fe.ldts.pacman.model.game;

import com.googlecode.lanterna.TextColor;
import pt.up.fe.ldts.pacman.model.Position;
import pt.up.fe.ldts.pacman.model.game.element.Wall;
import pt.up.fe.ldts.pacman.model.game.element.collectibles.Collectible;
import pt.up.fe.ldts.pacman.model.game.element.ghost.Ghost;
import pt.up.fe.ldts.pacman.model.game.element.pacman.Pacman;
import pt.up.fe.ldts.pacman.model.menu.element.TextBox;

import java.util.HashSet;
import java.util.Set;

public class Arena {
    private final Pacman pacman;
    private final int width, height;
    private int score;
    private Set<Ghost> ghosts;
    private Set<Wall> walls;
    private Set<Collectible> collectibles;

    public Arena(int width, int height) {
        this.width = width;
        this.height = height;

        this.score = 0;

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

    public void setGhosts(Set<Ghost> ghosts) {
        this.ghosts = ghosts;
    }

    public Set<Wall> getWalls() {
        return walls;
    }

    public void setWalls(Set<Wall> walls) {
        this.walls = walls;
    }

    public Set<Collectible> getCollectibles() {
        return collectibles;
    }

    public void setCollectibles(Set<Collectible> collectibles) {
        this.collectibles = collectibles;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void incrementScore(int increment){
        this.score += increment;
    }

}
