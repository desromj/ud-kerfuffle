# ud-kerfuffle
Project 2 of Udacity's 2D Mobile Game Developer Nanodegree - top-down shooter game

A-Pollen

What is it?
===========
A top-down shooter in which you control an innocent little bee trying to figure
out a mysterious problem related to flowers and pollen. A-Pollen is a bullet hell
shooter in which you must dodge through dense waves of bullets and attack enemies
to reach the end of the level.

How To Play
===========
In order to run the project, do the following

1. Import the project into Android Studio
2. Open the terminal
3. Type in "gradlew desktop:run" and press enter
4. The project will build and launch

On Desktop:
  - Use ARROW KEYS to move around the game screen
  - Hold Z to shoot bullets forward
  - Press X to launch a bomb which clears bullets and damages enemies ahead of you
  - Hold SHIFT to slow your movement speed and also view your hitbox
  
On Mobile:
  - (To Bee Implemented)

How To Win
==========
Reach the end of the level without losing all your lives

Scoring
=======
A big part of the game is trying to maximize the score you receive. You score points by:

- Destroying enemies
- Collecting pickups
- 'Grazing' bullets
  - This is when bullets get very near to your hitbox, but don't actually touch it

Power Level
===========
You start at 1.00 power and can grab red powerup blocks in order to increase this.

- Small Red Pickup: increase of 0.075
- Large Red Pickup: increase of 0.500

Every new whole number you reach (2.00, 3.00) will upgrade your default shot power,
to a maximum value of 5.00. At that point, each red pickup is instead worth a certain
amount of extra points.

Point Amounts
=======
- Small Blue Point: 5 points
- Large Blue Point: 75 points
- Massive Blue Point: 1000 points
- Small Red Pickup (when at max power): 2 points
- Large Red Pickup (when at max power): 24 points
- Grazing: 1 point per bullet grazed

Bombs
=====
You start with 2 bombs and can pick up more as green pickups through the level.
A bomb will make you invulnerable for a short time, damage everything it touches
onscreen, and clear all bullets it touches as well. Bombs are reset when you lose
a life.

The more bombs you use, the less bullets you have to graze. Maximum scores will
require you to use as few bombs as possible, but if you're in a pinch, bombs
will help save your lives and earn more bonus points at the end.
