# Plants vs. Zombies v3.0
## SYSC 3110 F18 – Software Design Project
### Team 5 – Jacob Laboissonniere, Sameed Mohammed, Abdillahi Nur, Paul Roode

---

***Welcome!***

We hope that you enjoy the 3rd iteration of our GUI-based Plants vs. Zombies game.

Check out the user guide for getting started and learning how to play.

---

***Authors and their contributions***

Jacob Laboissonniere
> Worked on inital game model classes (world, level, actor subclasses). Wrote USERMANUAL.md. Created GUI fxml file and GUIController class and integrated with existing model. Implemented undo/redo feature using serialization. Number of small changes to clean up code. Playtesting.

<br>

Sameed Mohammed
> Worked on test classes for all classes in project, in particular, worked on the testing logic and structure for GUIController. Added testing classes required as a result from changes for iteration 3. Added Javadoc comments to testing classes. Reviewed/edited design decision justification document.

<br>

Abdillahi Nur
> Worked on implementing new test classes for new zombie/plant subtypes. Also modified existing test classes to reflect changes for iteration 3. Searched for and repaired bugs in program where applicable. Wrote the documentation file detailing our design decisions. Added Javadoc to methods.

<br>

Paul Roode
> UML class and sequence diagrams. Added new Plant and Zombie subclasses. Added zombies hash table to Wave. Revamped moveZombie() and shootZombie(). Created/implemented ActionProcessor::spawnZombie. Added Difficulty enum to Wave and melded w/ logic for zombie spawn rate control. Refactored/de-smelled model, especially ActionProcessor. Playtested, debugged, failsafed. Reformatted and contributed to README. Wrote *Data structs employed in zombie wave and spawn logic* and *About the global CooldownManager class* sections in the *Design Decisions Justifications* document.

---

***Contents of .zip***

- runnable .jar (the PvZ game)
- README
- API documentation
- user guide
- UML class diagram
- UML sequence diagram
- doc outlining justifications for design decisions

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
- created/updated test classes in accordance with the new architecture
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

***Performance***

There is a linear increase in memory usage if the *allow undo/redo* checkbox is selected. This is because we are storing a deep copy of the game world whenever it is changed. If playing on a computer with less than 4 GB of RAM, you may not want to use the undo/redo feature.

---

***Roadmap for iteration 4***

Our project already implements serialization (for undo/redo), thereby expediting the augmentation of save/load features in iteration 4. World::copy will be utilized to create deep copies of the game world; said copies of game states will be stored in XML data files. As for level building, the user will be able to specify level dimensions (width by height) and perhaps more.
