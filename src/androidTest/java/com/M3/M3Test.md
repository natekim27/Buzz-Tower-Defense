# M3 Test Writeup

### towerPriceChangesOnDifficulty()
This test creates a new game with different difficulties(easy, medium, hard) and check if the three
different prices of each difficulty matches the expected price as the programmer designed.

### startingMoneyIsDifferent()
This test checks that each game mode (Easy, Medium, and Hard) results in different
starting money for the player.

### startingHealthIsDifferent()
This test checks that each game mode (Easy, Medium, and Hard) results in different
starting health for the player.

### buyTowerOnlyWithSufficientFunds()
This test purchases towers until money has run out.  It checks that both the towers purchased
before the money is exhausted are bought correctly and that any attempts to purchase towers
with insufficient funds fail.

### testEmptyPlacement()
This tests creates a new game and adds in a wizard tower into the first dirt patch. It then attempts
to add another tower in the same spot and checks to see that the returned String is the one that
states that a tower is already in that spot. This checks to see if towers can only be placed on
empty dirt patches, and not ones that are already occupied.

### onlyPlaceWhenBoughtTower()
This test ensures that players can not place a tower that they have not yet bought.

### testDirtPlacement()
This tests places a wizard tower in the first dirt patch. It then checks to see if the image of the
object at the first dirt patch is equal to the image of the wizard tower. This checks to see if
towers are placed only on dirt spots.

### testIsValidName()
This test checks to ensure that names with whitespace or empty strings
are invalid and all other names are valid.

### testIsValidConfig()
This test checks to ensure that the user can not continue until the name
and difficulty are filled in appropriately. 