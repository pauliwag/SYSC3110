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
> UML class and sequence diagrams. Added new Plant and Zombie subclasses. Added zombies hash table to Wave. Revamped moveZombie() and shootZombie(). Created/implemented ActionProcessor::spawnZombie. Added Difficulty enum to Wave and melded w/ logic for zombie spawn rate control. Refactored/de-smelled model, especially ActionProcessor. Playtested, debugged, failsafed. Reformatted and contributed to README. Wrote *Data structs employed in zombie wave and spawn logic* and *About the global CooldownManager class* sections in the Design Decisions Justifications document.

---

***Contents of .zip***

- runnable .jar (the PvZ game)
- README
- API documentation
- user guide
- UML class diagram
- UML sequence diagram
- .pdf justifying design decisions

---

***A note about the UML sequence diagram***

The sequence diagram features the sequences entailing:

1) launching and initializing the game
2) pushing a deep copy of the game world onto the undo stack via serialization
3) loading the GUI controller
4) selecting a plant type and placing it in a cell
5) undoing operation 4

---

***Changelog (v2.0 → 3.0)***

- added waves priority queue to Level
- added new Plant and Zombie subtypes
- added zombies hash table to Wave
- added Difficulty enum to Wave for zombie spawn rate control
- revamped/refactored ActionProcessor::moveZombie and ActionProcessor::shootZombie
- created/implemented ActionProcessor::spawnZombie
- refactored/de-smelled entire model
- de-centralized ActionProcessor logic; maximized abstraction, delegation and extensibility
- added various failsafes
- added/modified test classes to match changes relative to iteration 3
- zombie health and speed, peashooter damage, and sunpoint accumulation were tuned in order to promote balance
- turn undo/redo features were implemented via serialization

---

***User-visible changes***

- Level 1 map width extended to 8 tiles (from 5) for balance purposes.
- New sprites added for the new plant and zombie types.
- Undo and redo buttons, which can be toggled on or off, provide the option of undoing and redoing turns ad infinitum.
- Zombie spawn rate visibly increases or decreases depending on the difficulty setting of the active wave.

---

***Known issues***

None.

---

***Roadmap for iteration 4***

The augmentation of save/load features will be expedited as our project already uses serialization (for undo/redo).

**`TODO : Add more ...`**
