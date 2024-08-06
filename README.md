
# Project Color Switch

**A JavaFX implementation of the popular game "Color Switch"**

## Overview

Project Color Switch is a reimagining of the classic "Color Switch" game. This project focuses on the "Endless" game mode, denoted by an infinity symbol on the game's home screen. The game is designed using Java, JavaFX, and FXML, providing a visually appealing and interactive GUI.

## Gameplay Mechanics

1. **Core Gameplay**:
   - The player controls a ball that must be kept afloat and moved upward using a single input.
   - Each input moves the ball a fixed distance upward. If no input is given, the ball falls due to simulated gravity.

2. **Objectives**:
   - **Collect Stars**: Stars are placed throughout the gameplay area and count toward the player's score. They appear at frequent intervals.
   - **Avoid Obstacles**: The game features various obstacles in different colors. The ball can only pass through sections of obstacles matching its color.

3. **Color Mechanics**:
   - **Color Switches**: These are elements that change the ball's color when passed through, making the game more challenging and varied.
   - **Obstacles**: Utilize at least four different colors to match the ball's color-switching mechanic.

4. **Difficulty Progression**:
   - The game becomes progressively harder with more complex and faster-moving obstacles.

5. **Resurrection**:
   - Players can use collected stars to resurrect the ball if it collides with an obstacle. This feature allows players to resume their game or start afresh.

5. **Power up**:
    - Players can pick up the power up in the game which allows players to skip obstacle. 

## Saving and Loading Games

- **Save Game**: The current game state, including collected stars, ball position, and obstacle layout, can be saved at any point.
- **Load Game**: Players can load previously saved games, allowing for multiple save slots.

## Installation

### Requirements

- **Java Development Kit (JDK)**: Version 13 or higher.
- **JavaFX SDK**: Version 15 or higher.

### Setup Instructions

1. **Install JDK**:
   - For Debian/Ubuntu: `sudo apt install openjdk-13-jdk`
   - For Windows/Mac: Download and install from [Oracle's JDK download page](https://www.oracle.com/java/technologies/javase-jdk13-downloads.html).

2. **Install JavaFX**:
   - Download from [Gluon](https://gluonhq.com/products/javafx/).

3. **Running the Application**:
   - Clone the repository:
     ```bash
     git clone https://github.com/manoj19058/Project_Color_Switch.git
     ```
   - Open the project in your IDE (e.g., IntelliJ IDEA).
   - Set up JavaFX in your project settings.
   - Add the following to VM options:
     ```
     --module-path <path-to-javafx-lib> --add-modules javafx.controls,javafx.fxml
     ```
   - Run `Main.java`.

