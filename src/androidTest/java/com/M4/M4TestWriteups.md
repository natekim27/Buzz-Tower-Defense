# M4 Test Writeup

### returnsVarietyOfPaths()
A key part of our game is the variety of paths enemies can take to the monument. Because we determine
their paths randomly, an appropriate unit test just ensures that we are correctly returning each of the possible
paths.  This test ensures that in a small sample size we have each path present, so the game is interesting for players;

### gameOverFlagSetCorrectly()
After the monument health reaches 0, we end the game and set a gameOver variable to true in the game object.
This variable is a key component of our logic and allows us to adjust methods when the game ends.  This test ensures
that the gameOver variable is correctly set to true when the monument health reaches 0 (and not before then).

### enemyHasCorrectAttributes()
This test creates three different enemies(Bulldog, Football Bulldog, Prison Bulldog) with different
values for three attributes (speed, health, damage). It then checks if the the values of attributes
for each enemies matches the values as the programmer designed. This test ensures that the speed,
health, and damage are correct for each enemy types and therefore give variations for each enemy's
function.

### enemyHasCorrectString()
This test creates three different enemies(Bulldog, Football Bulldog, Prison Bulldog) that will
return different springs based on the type of the enemy. This test is important as it ensures that
the code returns the correct string that shows the type of the enemy and its speed, damage, and health.

### startLevelReturnsParameterEnemy()
When starting the level, our code allows a specific number of enemies to be manually set.  This 
provides the framework for custom levels or easier testing.  This test ensures that the method respects
this parametrized setting and returns the correct number of enemies.

### startLevelReturnsLevelMultiplierEnemy()
Typically, when starting the level, we spawn a number of enemies that is dependent on the level
and a multiplier.  This test ensures that incrementing level results in the correctly calculated 
number of enemies being returned.

### cantPlaceTowerAfterGameOver()
When the game ends, we want to ensure that the player cannot do any unwanted functionality.  This
test makes sure this is achieved by testing if the player can place a tower after the game is over.
It also ensures that the game returns the correct error message.

### enemiesDamageMonument()
A key component of this game is the ability of the enemy to attack the monument.  To ensure that
the attack is working correctly, this test checks if the monument health decreases by the amount
of the enemy damage.  This ensures the game remains interesting and playable.

### testDamageMonumentEndsGame()
The way the end games is if the monument health falls below 0 as a result of enemies damaging
it.  To ensure that the game ends correctly, this test tests whether enemies damaging the monument
so its health is below 0 end the game.

### removeEnemy()
The game only progresses when the enemies have been removed, and to check that the correct reward
is been given, it must be the case that the player is rewarded appropriately, this tests that
the correct amount of money has been allocated regardless of current level. Multiplier is 50 * level
of new currency.

### startLevelReturnsLevelMultiplierEnemy()
Typically, when starting the level, we spawn a number of enemies that is dependent on the level
and a multiplier.  This test ensures that incrementing level results in the correctly calculated 
number of enemies being returned.
