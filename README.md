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
   mvn clean compile package
   ```
4. Run the jar file using the following command:
   ```bash
   java -jar .\target\Sticky_Hero-1.0-SNAPSHOT.jar
   ```

Note: Make sure you have the necessary dependencies and resources (images, sounds) in the correct directories for the game to run successfully.

## Additional Information
- The game utilizes threads for implementing key press and release functionality.
- Design patterns employed include Singleton Design Pattern (for creating a single instance of StickApplication class)
- Observer Design Pattern (triggering reallocation of game elements (3 platforms, red mark, cherries, hero) to the left when the stick falls to be in the horizontal position).
- Factory Pattern: CherryFactory class and its method have been created to handle the creation of the Cherry object and setting the cherry in the right position. (Factory): A method or object whose primary purpose is to manage the creation of other objects (usually of a different type)

## Bonus
- Golden Cherry
- Hint line

## Assumptions
- It wasn't explicitly asked to create and extend from an abstract class. So, we have not done that.
- No need to double-press the mouse to eat the cherry; only a single press and release is enough.
- Our program saves the user's latest progress until it dies.
- If the user can save the game score just before dying, the last score is stored and will be reflected when the user reloads the progress.
- If a user manually deletes the saved.txt file, all the saved scores are set to zero.
- We increase the score due to the perfect landing of the stick and increase the cherry Count after eating the cherry, even if the user doesn't reach the end of the stick successfully.

## Image and Sound Credits
- **Images:**
    - "wall.jpg" - [Mountain Sunset](https://www.pinterest.com/)
    - "hero.png" - [Stick Hero Game](https://play.google.com/store/apps/details?id=com.ketchapp.stickhero)
    - "cherry.png" - [Easy drawing art](https://easydrawingart.com/how-to-draw-a-cherry/)
- **Sounds:**
    - [Pixabay Sound Effects](https://pixabay.com/sound-effects/)

Feel free to explore and enhance the Stick Hero game!