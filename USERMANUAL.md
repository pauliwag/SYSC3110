# Welcome to Plants Vs Zombies!

In this document, simple instructions on how to play Group 5's Plants Vs Zombies v3.0 will be given.


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

This version of the game comes with only one level.

The level is a grid, and in each grid cell, a plant or zombie can exist.

During your first few turns of a new level, you'll want to place some plants near the left side of the grid. After a few turns, zombies will begin to enter from the right, and your plants will attack them.

The image below has a demo of what the level grid will look like with some *peashooters* and a *sunflower* placed on the left hand side, and *zombies* attacking from the right.

![](images/wave3.png?raw=true)

If you manage to complete the level, a dialog will be displayed to congratulate you.

![](images/winner.png?raw=true)

## Plant types

In this early version of the game there are two different plant types.

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

 * **Normal Zombie: **
  The normal zombie has **500 health** and moves at a speed of *one tile per turn**
  
 **Shield Zombie: **
  The shield zombie has **500 health** and moves at a speed of *one tile per turn**
  
 * **Fast Zombie: **
  The fast zombie has **400 health** and moves at a speed of *two tiles per turn**
  
Zombies spawn on random rows to the far right of the level.

If a zombie is on the same tile as a plant (while still being alive), the plant will immediately die.

## Undo/Redo Feature

New in version 3.0 is an undo/redo feature. You can turn it on by checking the checkbox "Allow undo/redo".
When on, the game state will be saved whenever it's changed, so the player can revert back to it if they wish.
