package pt.up.fe.ldts.pacman.model.game.element.pacman;

import pt.up.fe.ldts.pacman.model.Position;
import pt.up.fe.ldts.pacman.model.game.element.MovableElement;

public class Pacman extends MovableElement {
    private int life;
    private boolean mouthOpen; // State to track whether the mouth is open or closed
    private int frameCounter;  // Counter to manage mouth animation

    public Pacman(Position pos) {
        super(pos);
        this.life = 3;
        this.mouthOpen = true;  // Initially, the mouth is open
        this.frameCounter = 0;
        setSpeed(4);
    }

    // Method to toggle the mouth state
    public void toggleMouth() {
        this.mouthOpen = !this.mouthOpen;
    }

    // Method to update mouth state based on frame counter
    public void updateMouthState() {
        frameCounter++;
        if (frameCounter % 10 == 0) { // Change mouth state every 10 frames
            toggleMouth();
        }
    }

    // Getter for mouth state
    public boolean isMouthOpen() {
        return mouthOpen;
    }

    public void decreaseLife() {
        this.life--;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }
}
