# SYSC 3110 Design Project - Team 5 - Iteration 2

**Welcome to the 2nd iteration of our text-based Plants vs. Zombies game!**

**Check out the user's guide for getting started and learning to play. On-screen instructions are also provided to help you.**

**Authors and their contributions:**

Abdillahi Nur: Worked on implementing Zombie, PeaShooter and Zombie logic from iteration one into the GUI. Ensured GameOver and NextTurn logic worked correctly and was implemented accordingly. Wrote the documentation file detailing our design decisions. Added Javadoc to methods

Paul Roode : UML class and sequence diagrams; some class architecting and refactoring; added Javadoc comments; generated API documentation; changed variable/method names to ensure all naming follows convention; made changes to code to correct issues when converting to javafx gui application; finalized design decision justification document

Jacob Laboissonniere: Worked on inital game model classes (world, level, actor subclasses). Wrote USERMANUAL.md. Created GUI fxml file and GUIController class and integrated with existing model. Number of small changes to clean up code.

Sameed Mohammed: Worked on test classes for all classes in project, added Javadoc comments. Reviewed/edited design decision justufication document. 



**User visible changes:**
- Game is no longer a text-based game! Currently it is a GUI that when run allows the user to play the first level
- Plants/Zombies are now represented by pictures representing each one of them respectively
- Labels that actively tell the user cooldown periods, and the amount of sunpoints they have are viewable at all times

**Known Issues:**
- None that we know of 

**For the next deliverable:** 
- Implement wave class more vigorously, as different zombie types will be implemented
- As different plants are added, adjust cooldown accordingly to contain cooldown value for each respective plant
