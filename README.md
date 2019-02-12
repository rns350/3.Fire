# 3.Fire
Helper program for running dungeons and dragons 3.5 edition

## Current Functionality

Initiative rolls on players and any number of enemies

To compile and run, in the source folder in command line type:
```
javac Fire.java
java Fire [Player]
```
where there can be as many players as you like. 3.Fire will roll initiative for enemies and present player/NPC order.

This program is eventually intended to implement a variety of features that will make convenient quality of life improvements to the 3.5 experience.  It is meant as a helper to dungeon masters for quick tracking of player, npc, and enemy data, as well as a battle simulator for easy tracking of initiative roles and AC checks.  In the future, I hope to add a database of spell and weapon information, class and monster statistics, and a varaiety of other information for quick lookup, as finding the information in books can be time consuming in the middle of play time.  I also hope to implement a dungeon helper that can keep track of room based events and handle DC checks that the player has to make.  I also hope to implement options to make automatic skill rolls for all players to provide an easy way to get DC check results quickly and ithout having to notify the players that they are happening.
