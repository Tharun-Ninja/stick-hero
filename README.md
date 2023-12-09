# Stick Hero Game - ReadMe

## Group Information
- **Group Number:** 16
- **Members:**
    - Chandan Sah (2022140)
    - Tharun Harish (2022541)

## Game Summary
In this project, we have developed a game named Stick Hero, where a character navigates between platforms by extending a stick. The stick must be of the correct length, or the character will fall. The game features multiple platforms of varying widths, a reviving feature using cherries, and a scoring system based on collecting rewards (cherries).

## Game Mechanics
1. **Stick Control:** Players can control the character (stick-hero) who moves between platforms by extending a stick.
2. **Platform Variety:** The game includes multiple platforms of different widths.
3. **Reviving Feature:** Players can be revived once using cherries collected during the game. Cherries and related scores are deducted after revival.
4. **Timing is Crucial:** Players must time the stick extension correctly to land on the next platform. Failure results in the character falling, ending the game.
5. **Reward Collection:** The character can collect rewards (cherries) by flipping upside down while traversing platforms, adding to the player's score.
6. **Scoring System:** The game includes a scoring system encouraging players to achieve higher scores by collecting more rewards.

## Code Overview
The main Java file for the game is `GameController.java`, which serves as the controller for the game logic. It utilizes JavaFX for the graphical user interface. The file is structured with various methods for initializing, handling user input, managing game elements (platforms, sticks, cherries), and updating the game state.

## Compilation and Execution
To compile and run the game from the command line:
1. Ensure you have Java installed on your system.
2. Navigate to the directory containing the Java files.
3. Compile the code using the following command on the project folder:
   ```bash
    mvn  clean compile package
   ```
4. Run the jar file using the following command :
   ```bash
   java -jar  .\target\Sticky_Hero-1.0-SNAPSHOT.jar
   ```

Note: Make sure you have the necessary dependencies and resources (images, sounds) in the correct directories for the game to run successfully.

## Additional Information
- The game utilizes threads for implementing key press and release functionality.
- Design patterns employed include Singleton Design Pattern (for creating a single instance of Scene and other classes) and Observer Design Pattern (triggering reallocation of game elements when the stick falls on a platform).

## Image and Sound Credits
- **Images:**
    - "wall.jpg" - [Mountain Sunset](https://www.pinterest.com/)
    - "hero.png" - [Stick Hero Game](https://play.google.com/store/apps/details?id=com.ketchapp.stickhero)
    - "cherry.png" - [How to Draw a Cherry](https://easydrawingart.com/how-to-draw-a-cherry/)
- **Sounds:**
    - [Pixabay Sound Effects](https://pixabay.com/sound-effects/)

Feel free to explore and enhance the Stick Hero game!