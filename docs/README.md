# LDTS_10_03 - Pacman

## Introduction

In this project, we developed a Pacman clone using **Java** and the **Lanterna** library, offering a text-based,
retro-style gaming experience. The player controls Pacman, navigating through an arena, collecting coins and power-ups,
all while avoiding four unique ghosts: Blinky, Pinky, Inky, and Clyde. The game is designed to be simple yet
challenging, and it implements various object-oriented design patterns to ensure maintainability and scalability.

This project was developed by <a href="https://github.com/GustavoCMadureira">Gustavo Madureira</a> (
up202304978@fe.up.pt), <a href="https://github.com/PauloSaa29">Paulo Saavedra</a> (up202307477@fe.up.pt)
and <a href="https://github.com/peucastro">Pedro Castro</a> (up202200044@fe.up.pt) for the LDTS 2024/25 course at FEUP.

---

## Implemented features

### Gameplay

- **Arena**: A maze filled with coins and power-ups.

---

## Planned features

### **Gameplay**

- **Player Character (Pacman)**: The player controls Pacman, which can move in four directions—up, down, left, and
  right—using corresponding arrow keys.
- **Ghosts**: Each ghost will have a unique behavior.
- **Coins**: Collectible items that increase the player’s score. The game ends when all PacDots are consumed.
- **Power-ups**: Special collectible items that temporarily change the Pacman state.
- **Game Over**: If a ghost catches Pacman, the game ends and the player is returned to the main menu.

### **Menu**

- A main menu appears at the start of the game, allowing the player to either start the game or quit. The player can
  navigate the menu with the arrow keys and press Enter to make a selection.
- A pause menu that allows the player to take a break during the gameplay.

### **Game Over and Restart**

- The game will display a death animation and return to the main menu after Pacman is caught by a ghost.

### **Implementation Overview**

The system is composed of the following key components:

- **Element**: A base class representing both movable (Pacman, Ghosts) and static (PacDots, PowerPellets) elements in
  the game.
- **MovableElement & StaticElement**: Interfaces defining the behavior of objects that can move or remain stationary.
- **Pacman & Ghost**: Concrete classes that implement the movable behavior, with the Ghost class further divided into
  specific ghost types (Blinky, Pinky, Inky, Clyde).
- **Direction & Position**: Representations of the directions and coordinates in the game world.
- **Arena**: A container for all the elements in the game, including Pacman, ghosts, and collectible items.

---

## **UML schema**

![Project structure](/docs/resources/uml/structure.png "UML")

## Testing

We used unit testing with Junit 5, mocks with Mockito and mutation testing with Pitest.

### Screenshot of Test Coverage

![Test coverage](/docs/resources/testing/coverage.png "Coverage")
![Pitest report](/docs/resources/testing/pitest.png "Pitest")