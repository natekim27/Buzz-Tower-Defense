### upgradeDamage()
This tests if the damage of a tower is upgraded properly. It ensures that after the player chooses
to pay to upgrade the damage level of a tower, the damage is increased by 1.5 and the cost for the
next upgrade to the damage is increased by 1.5.

### upgradeAttackSpeed()
This tests if the attack speed of a tower is upgraded properly. It ensures that after the player
chooses to pay to upgrade the attack speed of a tower, the attack speed is decreased to 75% of the
original and the cost for the next upgrade to the attack speed is increased by 1.5.

### upgradeRange()
This method tests if the upgrading range correctly. This method ensures that the range of the tower
get updated when the user updates the tower by paying for the update. This feature is essential as
it adds more features to the game, making users more engaged. This method gets the old
range before the update and the new range after the update and checks if the new range is 1.5 larger
than the old range.


### checkFinalBoss()
This method Creation of final boss and check if it has correct health, damage, money, and speed.
Final boss is important to the game as it builds up suspense and challenges the users. This method
is important as it ensures that the final boss is working as it should(its health, damage, money,
and speed) and gives the exciting challenge to the users.

### finalBossAppearsOnLastLevel()
After the user has survived a sufficient number of levels, the final boss is supposed to appear
for the user to defeat.  This method tests that when the levels completed equal the number of levels
the final boss is the only enemy spawned for the user to defeat.

### preventsUpgradesWithInsufficientMoney()
An important part of the upgrades for this game is ensuring that users have enough money to pay
for the improvement to their towers.  This method tests that functionality by ensuring that upgrades
initiated when there is not enough money fail and upgrades where there is enough proceed.

### killFinalBossWinsGame()
In order to win our game, the final boss must be killed at the final round. When this occurs,
the gameOver flag should be set along with the screen being changed to a win screen. This method
tests if the gameOver flag is set to true when the finalBoss object in the enemies array is removed.

### statisticsCheck()
A part of our game is that it shows three statistics (money spent, damage taken, and enemies killed)
at the game over and win screens. This method checks to see if each statistic is updated correctly
when an upgrade is bought, the tower takes damage, and when an enemy is killed.

### reduceGameMoney()
Upgrading the towers is a crucial progressive nature of our game, so checking its functionality
can ensure the scalability of difficulty, allowing players for a fair but competitive aspect when
considering where to place their finite resources.

### initialUpgradeCosts()
Along with the previous test, this ensure that there is a correct starting value for tower upgrade
as their upgrade costs are increased by a mltiplier, making sure they start at the right value
will ensure that the costs are realistic.



