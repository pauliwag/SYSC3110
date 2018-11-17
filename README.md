# SYSC 3110 Design Project - Team 5 - Iteration 2

**Welcome to the 2nd iteration of our GUI-based Plants vs. Zombies game!**

**Check out the user guide for getting started and learning to play. On-screen instructions are also provided to help you.**

**Authors and their contributions:**

Abdillahi Nur: Worked on implementing Zombie, PeaShooter and Zombie logic from iteration one into the GUI. Ensured GameOver and NextTurn logic worked correctly and was implemented accordingly. Wrote the documentation file detailing our design decisions. Added Javadoc to methods

Paul Roode : UML class and sequence diagrams; model rearchitecting and refactoring in preparation for iteration 3; de-smelling of code; generated API documentation; javadocs; etc.

Jacob Laboissonniere: Worked on inital game model classes (world, level, actor subclasses). Wrote USERMANUAL.md. Created GUI fxml file and GUIController class and integrated with existing model. Number of small changes to clean up code.

Sameed Mohammed: Worked on test classes for all classes in project, added Javadoc comments. Reviewed/edited design decision justufication document. 


**User visible changes:**
- Game is no longer text-based! It implements a GUI built using FXML.
- Grid squares hold .png images of the various actors in the game (e.g., Sunflower, Zombie, etc.).
- Labels indicate cooldown periods, unspent sunpoints, etc.

**Known Issues:**
- None of which we are aware.

**For the next deliverable:** 
Significant rearchitecting was performed in preparation for iteration 3 (when markers start docking for smelly code) with the intention of decentralizing the core logic of the game that primarily resides in the ActionProcessor class. E.g., the Level class now has a (currently unused) field that is a priority queue of Wave objects. Waves are prioritized in order of ascending wave number (i.e., lower wave number is prioritized) to ensure zombie waves are deployed in the correct sequence (1, 2, ..., n).

Many other refactorings were performed to maximize delegation of tasks, e.g., from ActionProcessor to the other classes, thereby maximizing encapsulation and extensibility. These refactorings have yet to be melded with the core logic of the game (namely the logic in ActionProcessor).

With the introduction of different zombie types in the next iteration, the ArrayList<Zombie> field in the Wave class will be refactored into a HashMap to keep track of the numbers of different types of zombies in a given wave.
  
With the introduction of more plant types, the global CooldownManager class will be modified accordingly.
