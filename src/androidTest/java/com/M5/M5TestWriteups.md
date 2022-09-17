# M4 Test Writeup

1. test if Game.isWithinProximity() correctly returns true if (x, y) is within range of tower
2. check if purchasing works after combat is started



### checkEnemyLoseHealth()
As part of combat, the enemy needs to lose health when attacked by a tower. If the enemy comes
within range of a tower, it should lose some of its health which is determined by the type of
tower and its attack "strength". This test ensures that the enemy's health is properly
reduced by the attacking towers attack amount.

### checkTowerAttack()
As part of combat, each tower needs to attack the enemies. Each tower does this with a different
damage amount inflicted on the enemies. This test ensures that the three types of towers attack
the enemies with their respective damage amount.

### checkTowerRanges
Since towers have different abilities and descriptions, it is also sensible thye must have different
tanges, as such, we test that each tower has a different range when we declare them.

### checkEnemyGameplay
It is important that there is variety in the enemy gameplay so that the player may have
an enjoying challenging experience, as such we must make sure that the stats of certain enemies
are different when declared, hence what is being measured here when comparing health and damage of
the three different types of enemies.

### enemiesGiveDifferentMoney
To facilitate further gameplay, the player gets money after killing each enemy.  However, in order
to fit into the differing attributes for each enemy, the enemies result in different amounts of money
added upon their death.  This test ensures that the amount of money is different.

### enemiesDisappear()
After an enemy's health is below 0 and has been "killed" we don't want the user to see the enemy or
the game logic to continue tracking it. Thus, this test ensures that when an enemy is killed it
disappears from our backing logic and consequently the screen.

### checkStartLevel
Start Game creates more enemies each level to ensure that depending on the level, the game would be
harder. This gives excitement and challenges to the player. This test ensures that the number of
enemies increases as level increases.

### checkLevelOverMoneyGain
During the game, whenever the level is over, money has to be gained to reward the players. This
will motivate the players to complete the level and continue playing the game. This test ensures
that the money is gained when the level is over.

### drawableCircleTest
One of the ways we communicate tower range to users is through a temporary gray circle surrounding
towers.  To create this gray circle, we developed a DrawableHelper class that handles the creation
and management of the range indicator.  This test ensures that the circle is created correctly and has 
a color set for its fill.

### testTowerPurchaseDuringCombat
One of the key parts of the tower defense game is the player's ability to bolster their defenses 
while the game is continuing.  This test ensures that even after combat has started, players can continue
to purchase towers to strengthen their defense against incoming enemies.
