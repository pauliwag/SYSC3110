# Welcome to Plants Vs Zombies!

In this document, simple instructions on how to play Group 5's Plants Vs Zombies v4.0 will be given.


## Launching the game

To launch the game, first ensure that you have the PlantsVZombies.jar file downloaded. Next, navigate to the .jar file's directory with your computer's terminal/command prompt, and enter the following command:

    java -jar PlantsVZombies.jar

If you have Java installed correctly, a window will launch and display the initial state of the game.

![](images/home.png?raw=true)

## Controls

Now that you've got the game started, it's time to learn about the different controls you can use to interact with the game.
On the left side of the window, you'll see the buttons you can use. 

To 'select' a plant that will be placed by clicking on a position on the grass grid, click the button with the plant's name.

Beside the plant buttons, a number representing the number of turns until you can place this plant is displayed, along with the cost of the plant.

Below the plant selection buttons, there is information on the current level number, wave number, and sunpoint balance. 

The next turn button is used to advance the game to the next turn.

![](images/controls.png?raw=true)

When the game is advanced to the next turn, the the grass grid and any information labels will update.

Every two turns, 25 sun points are added to the sun point balance (passive income).

![](images/actors.png?raw=true)


## The level

This version of the game comes with three levels, but more can be created using the level builder.

Each level is a grid, and in each grid cell, a plant or zombie can exist.

During your first few turns of a new level, you'll want to place some plants near the left side of the grid. After a few turns, zombies will begin to enter from the right, and your plants will attack them.

The image below has a demo of what the level grid will look like with some *peashooters* and a *sunflower* placed on the left hand side, and *zombies* attacking from the right.

![](images/wave3.png?raw=true)

If you manage to complete a level, a dialog will be displayed to congratulate you.

![](images/winner.png?raw=true)

There are three different terrain types that a level can have: normal, sand, and ice. The terrain type doesn't affect the difficulty of the level, it just changes up the sprites that are used.

## Plant types

In this version of the game there are three different plant types.

  * **Peashooter:**

   The peashooter is the basic "attack" plant.  It does **200 damage** to zombies it shoots.

   You can only place a new peashooter every **two turns**. It costs **200 sun points** to place.

  * **Sunflower:**

   The sunflower is a plant that generates more sun points while placed. It generates **50 sun points** every turn.

   You can only place a new sunflower every **two turns**. It costs **100 sun points** to place.
   
  * **Threepeater:**

   The threepeater is a stronger attack plant. It does **350 damage** to zombies it shoots.

   You can only place a new threepeater every **two turns**. It costs **300 sun points** to place.
   
## Zombies

There are currently three different types of zombies in the game.

 * **Normal Zombie:**
  The normal zombie has **500 health** and moves at a speed of **one tile per turn**
  
 * **Shield Zombie:**
  The shield zombie has **800 health** and moves at a speed of **one tile per turn**
  
 * **Fast Zombie:**
  The fast zombie has **400 health** and moves at a speed of **two tiles per turn**
  
 * **Wizard Zombie:**
  The wizard zombie has **500 health** and moves at a speed of **1 tile per turn**. The wizard zombie has the ability to teleport between rows, and will try to teleport to the row that has the least number of plants attacking it.
  
 * **Giga Zombie:**
  The giga zombie has **2000 health** and moves at a speed of **1 tile per turn**  
  
 * **Boss Zombie:**
  The boss zombie has **1500 health** and moves at a speed of **1 tile per turn**. The boss zombie also has the same teleporting power that the wizard zombie has, making it the most challenging zombie.
  
Zombies spawn on random rows to the far right of the level.

If a zombie is on the same tile as a plant (while still being alive), the plant will immediately die.

## Undo/Redo Feature

In version 3.0, an undo/redo feature was added. You can turn it on by checking the checkbox "Allow undo/redo".
When on, the game state will be saved whenever it's changed, so the player can revert back to it if they wish.

## Level Builder

New in version 4.0! The level builder allows you to create and edit game levels. To launch the level builder, select open from the level builder menu.

You should give your level a name, a number, a starting number of sunpoints, a terrain type, and some waves. Waves can be added using the "Add Waves" button. You can edit waves that have been added by clicking on them.

Here's what a sample level will look like:

![](images/levelbuilder.png?raw=true)

When you save a level from the level builder, it will be saved in the game's directory in a file titled **'levelname'.level**
You can load level files into the level builder by entering the 'levelname' into the "Load Level" dialog.

## Saving/loading the game 

Also new in version 4.0 is the ability to save/load the game world (the game world contains multiple levels). Save your current world by clicking World->Save World and entering a <worldname>. 

The world will be saved in the game directory with a file name of **'worldname'.world**.

Worlds can be loaded from World->Load World and entering <worldname> into the dialog. Enter 'default' to load back the default game world.

You can create a new empty world (world with no levels) using World->New Empty World.
After creating an empty world, you can add custom levels to it using the World->Add Level To World button and entering 'levelname'.