# [SYSC 3110 F18] Software Design Project
## Plants vs. Zombies v3.0
### Team 5 – Jacob Laboissonniere, Sameed Mohammed, Abdillahi Nur, Paul Roode

---

**Welcome to the 3rd iteration of our GUI-based Plants vs. Zombies game!**

**Check out the user guide for getting started and learning how to play.**

---

***Authors and their contributions***

Jacob Laboissonniere
> Worked on inital game model classes (world, level, actor subclasses). Wrote USERMANUAL.md. Created GUI fxml file and GUIController class and integrated with existing model. Number of small changes to clean up code.

<br>

Sameed Mohammed
> Worked on test classes for all classes in project, added Javadoc comments. Reviewed/edited design decision justufication document.

<br>

Abdillahi Nur
> Worked on implementing Zombie, PeaShooter and Zombie logic from iteration one into the GUI. Ensured GameOver and NextTurn logic worked correctly and was implemented accordingly. Wrote the documentation file detailing our design decisions. Added Javadoc to methods.

<br>

Paul Roode
> UML class and sequence diagrams; model rearchitecting and refactoring in preparation for iteration 3; de-smelling of code; generated API documentation; javadocs; etc.

---

***A note about the UML sequence diagram***

The sequence diagram features the sequences entailing:

1) launching and initializing the game
2) pushing a deep copy of the game world onto the undo stack via serialization
3) loading the GUI controller
4) selecting a plant type and placing it in an available cell
5) undoing operation 4

---

***User-visible changes***

**`TODO : Update ...`**

- Game is no longer text-based! It implements a GUI built using FXML.
- Grid squares hold .png images of the various actors in the game (e.g., Sunflower, Zombie, etc.).
- Labels indicate cooldown periods, unspent sunpoints, etc.

---

***Known issues***

None.

---

***For the next deliverable...***

**`TODO : Update ...`**

Significant rearchitecting was performed in preparation for iteration 3 (when markers start docking for smelly code) with the intention of decentralizing the core logic of the game that primarily resides in the ActionProcessor class. E.g., the Level class now has a (currently unused) field that is a priority queue of Wave objects. Waves are prioritized in order of ascending wave number (i.e., lower wave number is prioritized) to ensure zombie waves are deployed in the correct sequence (1, 2, ..., n).

Many other refactorings were performed to maximize delegation of tasks, e.g., from ActionProcessor to the other classes, thereby maximizing encapsulation and extensibility. These refactorings have yet to be melded with the core logic of the game (namely the logic in ActionProcessor).

With the introduction of different zombie types in the next iteration, the ArrayList<Zombie> field in the Wave class will be refactored into a HashMap to keep track of the numbers of different types of zombies in a given wave.
  
With the introduction of more plant types, the global CooldownManager class will be modified accordingly.
