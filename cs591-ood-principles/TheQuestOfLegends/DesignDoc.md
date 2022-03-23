Farheen Rahman and Elton Cheung
U67393642

For this assignment, we chose to go with Farheen's codebase as our natural structure to build off of. 

Quickly wanted to explain some design choices we made:
- Teleportation in between the lanes: We interpreted the teleportation to mean any lane aside from the one you're in if there are 6 lanes, not 3. This is visible on my board design. We handle the case of the hero teleporting close to the Monster by making sure it is unable to pass the Monster without defeating it.
- We do not explicitly say when the Hero is on a special cell, but it's effects are visible on pressing (L/l) for the statistics. 
- We choose to respawn monsters after 15 rounds because both our initial heroes and initial monsters are pretty strong and take a while to wipe out. 
- We don't have a "Buyable" interface for our items, but this is because we maintain buy/sell as methods of our parent MarketRequirements file.

AdditionalDamage: This interface is the same as Farheen's previous Quest submission.
AdditionalSkills: This interface is the same as Farheen's previous Quest submission.
AnsiColors: This class is the essentially the same, we added some more colors for color coding menus/inventories since QOL is much more complicated.
Armor: This class is the same as Farheen's previous Quest submission.
BoardRequirements: This class is the same as Farheen's previous Quest submission.
Cell: This cell class has a lot of functionality added, especially with specific cells manipulating player's attributes. We also needed to keep track of whether or not a Hero/Monster Object was in a given cell vs. previously when we just tracked the current marker in the cell, which we realized was necessary in order to make future boards scalable.
ChooseGame: This class was a new necessary addition in order to allow the user to select which version of The Quest to play. This is essential so that we are able to add endless amounts of game options for a user and makes for an easily scalable class.
Dragon: This class is the same as Farheen's previous Quest submission.
Exoskeleton: This class is the same as Farheen's previous Quest submission.
FireSpell: This class is the same as Farheen's previous Quest submission.
GameRequirements: This class is the same as Farheen's previous Quest submission.
Hero: In this class, we added, primarily, more identifying strings and structures for the Hero. We also touched up on our equip() method so that it could apply to any type of Hero and any type of Item. It also returns a boolean, so that if you are able to equip, a turn is made, else you are able to select another action. We did this because we previously had issues with visibility for 
IceSpell: This class is the same as Farheen's previous Quest submission.
IndividualFight: This class is completely new, it is meant to simulate the fights between individual players rather than those on a team, like my previous Quest class did (now called TeamFight). While we could have restructured the original fight class, we thought it beneficial and much easier to prepare two separate types of fight for teams, since there will be games that prefer team play over individual play and vice versa, therefore allowing future games to have either functionality instead of a generic one. 
Inventory: The inventory class we completely remodeled as well, we made this an instance class because we noticed our static class was completely the wrong move (which I should have caught before). The purpose of this reconstruction was mostly better functionality in itself, that could extend to multiple Player classes that utilize inventories as well.
Item: This class is the same as Farheen's previous Quest submission.
Lanes: This class is also completely new, we found it extremely helpful in maintaining data for teleporting. The lanes can be modified, and the contructor is based primarily on the number of columns there are. This file is definitely a bit more specific to whatever type of board is initialized, so any board can immediately have lanes. 
LightningSpell: This class is the same as Farheen's previous Quest submission.
Main: The only change to the main file is that it now calls a game selection java file rather than selecting the game itself. We did this to avoid overcrowding the main function since no real logic should exist there, and therefore makes this codebase slightly more readable.
Marker: This class is the same as Farheen's previous Quest submission.
MarketRequirements: We chose to break up my previous Market.java file from Quest I because we were using the exact same functions for both the QOL market and the original Market, but the display and handling of information in the marketplaceHelper() functions were very different, so an entirely new marketplace was still necessary. We decided to put all of our similar functions (sell, buy, menu) into this parent file. This is going to help alot with the additions of marketplaces, if added functionality is necessary for future games. 
Monster: This class is the same as Farheen's previous Quest submission.
MonsterTeam: We chose to make two different team files for Hero and Monster instead of one generic Player team file because we weren't utilizing the same methods for either type of team and so it appeared a lot more sensible and organized this way. Also, our team assignments happen in these files and it would be a mess to have them all happen in one file. Again, we foresee this allowing extendability in that each type of team can have methods differing in functionality. 
Nexus: This class is completely new, we found it helpful making "return to nexus" decisions. The Lanes.java file works hand-in-hand with this one, so that depending on how many lanes there are, there are that many available cells for the Nexus. We simply thought this was a good way to organize the board information.
Paladin: This class is the same as Farheen's previous Quest submission.
Player: This class is the same as Farheen's previous Quest submission.
Potion: This class is the same as Farheen's previous Quest submission.
QuestBoard: This class is the same as Farheen's previous Quest submission.
QuestGame: This class is largely the same as the previous submission, only with an added true/false switch for the game to be replayed. Again, this ability to replay the game and also switch between games adds to the longevity of the program.
QuestMarket: As we mentioned before with the parent MarketRequirements file, this is a subclass for a market specific to the original quest world. Again, the original quest is more team based. That being said, this market functions more for the team than for individual players. Similarly to the fight files, I see this being essential in the distinction between team-based or individual-based games, and also able to be used by future team games (but not individual games).
QuestOfLegendsBoard: This class is absolutely new, although it extends our BoardRequirements. We remodeled the entire board, and had to make a number of changes to our boolean functions for movement. This was just necessary to the new game and its functionality. 
QuestOfLegendsGame: This class largely mirrors our QuestGame.java, but of course, has a semi-different navigational system. Similar to the previous description, the game play is just a necessity.
QuestOfLegendsMarket: As we mentioned before with the parent MarketRequirements file, this is a subclass for a market specific to the QOL. The team based maret of our original quest wouldn't work for how individual the heroes are in QOL. That being said, this market functions more for individual players. Similarly to the fight files, I see this being essential in the distinction between team-based or individual-based games, and also able to be used by future individual games (but not team games).
Sorcerer: This class is the same as Farheen's previous Quest submission.
Spell: This class is the same as Farheen's previous Quest submission.
Spirit: This class is the same as Farheen's previous Quest submission.
Team: This is our HeroTeam and largely mirrors our previous submission, we didn't rename the file because we make a number of static references to Team.java already. The functionality of a team of monsters wasn't the same for the methods needed in a team of heroes, which is why we choose to separate them out. We see this being extremely helpful for future team distinctions. 
TeamFight: This class is essentially my previous Quest Fight.java class. Again, with this theme of individual vs. team play, we decided to make this team fight a separate file for reasons I've mentioned already. 
TeamsAllowed: This class is the same as Farheen's previous Quest submission.
TextFiles: We added some cool new graphics for QOL! 
Warrior: This class is the same as Farheen's previous Quest submission.
Weapon: This class is the same as Farheen's previous Quest submission.





