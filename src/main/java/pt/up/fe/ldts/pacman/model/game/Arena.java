package pt.up.fe.ldts.pacman.model.game;

import pt.up.fe.ldts.pacman.model.Position;
import pt.up.fe.ldts.pacman.model.game.element.GhostGate;
import pt.up.fe.ldts.pacman.model.game.element.Wall;
import pt.up.fe.ldts.pacman.model.game.element.collectibles.Collectible;
import pt.up.fe.ldts.pacman.model.game.element.ghost.Ghost;
import pt.up.fe.ldts.pacman.model.game.element.pacman.Pacman;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Arena {
    //due to how the module operator works, the higher the speed the faster the element goes, except 1 which is the highest speed
    public static final int PACMAN_BOOSTED_SPEED = 6;
    public static final int PACMAN_NORMAL_SPEED = 4;
    public static final int GHOST_NORMAL_SPEED = 3;
    public static final int GHOST_SCARED_SPEED = 2;
    public static final int GHOST_DEAD_SPEED = 1;

    private final List<Pacman> pacmans;
    private final int width, height;
    private int score;
    private int collectedCollectibles;
    private final GhostGate ghostGate;
    private Set<Position> blankPositions;
    private Set<Ghost> ghosts;
    private Set<Wall> walls;
    private Set<Collectible> collectibles;

    public Arena(int width, int height) {
        this.width = width;
        this.height = height;

        this.score = 0;
        this.collectedCollectibles = 0;

        this.pacmans = new ArrayList<>();
        this.ghostGate = new GhostGate(new Position(10,10));

        this.ghosts = new HashSet<>();
        this.walls = new HashSet<>();
        this.collectibles = new HashSet<>();
        this.blankPositions = new HashSet<>();
    }

    public List<Pacman> getPacmans() {
        return pacmans;
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

    public void addPacman(Pacman pacman) {
        pacmans.add(pacman);
    }

    public boolean isEmpty(Position position) {
        for (Wall wall : walls) {
            if (wall.getPosition().equals(position))
                return false;
        }
        for (Pacman pm: pacmans) {
            if (pm.getPosition().equals(position))
                return false;
        }
        return true;
    }

    public Ghost isGhost(Position position) {
        for (Ghost ghost : ghosts)
            if (ghost.getPosition().equals(position))
                return ghost;
        return null;
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

    public int getCollectedCollectibles() {
        return collectedCollectibles;
    }

    public void incrementCollectedCollectibles(){
        ++collectedCollectibles;
    }

    public GhostGate getGhostGate() {
        return ghostGate;
    }

    public void setGhostGatePosition(Position position){
        this.ghostGate.setPosition(position);
    }

    public Set<Position> getBlankPositions() {
        return blankPositions;
    }

    public void addBlankPosition(Position position){
        blankPositions.add(position);
    }
}
