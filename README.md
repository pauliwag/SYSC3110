# Plants vs. Zombies v3.0
## SYSC 3110 F18 – Software Design Project
### Team 5 – Jacob Laboissonniere, Sameed Mohammed, Abdillahi Nur, Paul Roode

---

***Welcome!***

We hope you enjoy the 3rd iteration of our GUI-based Plants vs. Zombies game.

Check out the user guide for getting started and learning how to play.

---

***Authors and their contributions***

Jacob Laboissonniere
> Worked on inital game model classes (world, level, actor subclasses). Wrote USERMANUAL.md. Created GUI fxml file and GUIController class and integrated with existing model. Number of small changes to clean up code.

<br>

Sameed Mohammed
> Worked on test classes for all classes in project, added Javadoc comments. Reviewed/edited design decision justufication document.

<br>

Abdillahi Nur
> Worked on implementing new test classes for new zombie/plant subtypes. Also modified existing test classes to reflect changes for iteration 3. Searched for and repaired bugs in program where applicable. Wrote the documentation file detailing our design decisions. Added Javadoc to methods.

<br>

Paul Roode
> UML class and sequence diagrams. Added new Plant and Zombie subclasses. Added zombies hash table to Wave. Revamped moveZombie() and shootZombie(). Created/implemented ActionProcessor::spawnZombie. Added Difficulty enum to Wave and melded w/ logic for zombie spawn rate control. Refactored/de-smelled model, especially ActionProcessor. Playtested, debugged, failsafed. Reformatted and contributed to README. Contributed to design decisions justifications doc.

---

***Contents of .zip***

- runnable .jar (the PvZ game)
- README
- API documentation
- user guide
- UML class diagram
- UML sequence diagram
- .docx justifying design decisions

---

***A note about the UML sequence diagram***

The sequence diagram features the sequences entailing:

1) launching and initializing the game
2) pushing a deep copy of the game world onto the undo stack via serialization
3) loading the GUI controller
4) selecting a plant type and placing it in a cell
5) undoing operation 4

---

***Changelog***

- added waves priority queue to Level
- added new Plant and Zombie subtypes
- added zombies hash table to Wave
- added Difficulty enum to Wave for zombie spawn rate control
- revamped/refactored ActionProcessor::moveZombie and ActionProcessor::shootZombie
- created/implemented ActionProcessor::spawnZombie
- refactored/de-smelled entire model
- de-centralized ActionProcessor logic; maximized abstraction, delegation and extensibility
- added various failsafes

**`TODO : Add more ...`**

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

***Roadmap for iteration 4***

**`TODO : Update ...`**

Significant rearchitecting was performed in preparation for iteration 3 (when markers start docking for smelly code) with the intention of decentralizing the core logic of the game that primarily resides in the ActionProcessor class; e.g., the Level class now has a field that is a priority queue of Wave objects. Waves are prioritized in order of ascending wave number (i.e., lower wave number is prioritized) to ensure zombie waves are deployed in the correct sequence (1, 2, ... , *n*).

Many other refactorings were performed to maximize delegation of tasks, e.g., from ActionProcessor to other classes, thereby maximizing abstraction, encapsulation and extensibility. These refactorings have yet to be melded with the core logic of the game (namely the logic in ActionProcessor).

With the introduction of different zombie types in the next iteration, the ArrayList<Zombie> field in the Wave class will be refactored into a HashMap<? extends Zombie, Integer> to keep track of the numbers of different types of zombies in a given wave.
  
With the introduction of more plant types, the global CooldownManager class will be updated accordingly.
