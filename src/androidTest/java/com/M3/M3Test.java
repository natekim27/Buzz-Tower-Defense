package com.M3;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import android.widget.ImageView;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.runner.AndroidJUnitRunner;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.example.towerdefense.Config;
import com.example.towerdefense.Game;
import com.example.towerdefense.towers.BoxingBuzz;
import com.example.towerdefense.util.Difficulty;
import com.example.towerdefense.towers.Tower;
import com.example.towerdefense.towers.WizardBuzz;
import com.example.towerdefense.towers.CountryBuzz;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@RunWith(AndroidJUnit4.class)
public class M3Test extends AndroidJUnitRunner {

    @Test
    public void towerPriceChangesOnDifficulty() {
        Game easyGame = new Game("Easy");
        assertEquals(easyGame.getTowerCosts()[0], 150);
        assertEquals(easyGame.getTowerCosts()[1], 250);
        assertEquals(easyGame.getTowerCosts()[2], 400);

        Game mediumGame = new Game("Medium");
        assertEquals(mediumGame.getTowerCosts()[0], 105);
        assertEquals(mediumGame.getTowerCosts()[1], 175);
        assertEquals(mediumGame.getTowerCosts()[2], 280);

        Game hardGame = new Game("Hard");
        assertEquals(hardGame.getTowerCosts()[0], 45);
        assertEquals(hardGame.getTowerCosts()[1], 75);
        assertEquals(hardGame.getTowerCosts()[2], 120);
    }

    @Test
    public void startingMoneyIsDifferent() {
        Game easyGame = new Game("Easy");
        assertEquals(easyGame.getMoney(), 500);

        Game mediumGame = new Game("Medium");
        assertEquals(mediumGame.getMoney(), 350);

        Game hardGame = new Game("Hard");
        assertEquals(hardGame.getMoney(), 150);
    }

    @Test
    public void startingHealthIsDifferent() {
        Game easyGame = new Game("Easy");
        assertEquals(easyGame.getHealth(), 10);

        Game mediumGame = new Game("Medium");
        assertEquals(mediumGame.getHealth(), 7);

        Game hardGame = new Game("Hard");
        assertEquals(hardGame.getHealth(), 4);
    }


    @Test
    public void buyTowerOnlyWithSufficientFunds() {
        Game game = new Game("Easy");

        game.purchase(1);
        assertEquals(game.getMoney(), 350);
        game.purchase(2);

        assertNull(game.purchase(1));
        assertNull(game.purchase(2));
        assertNull(game.purchase(3));
        assertEquals(game.getMoney(), 100);
    }

    @Test
    public void testEmptyPlacement() {
        Game hardGame = new Game("Hard");

        WizardBuzz wizard = new WizardBuzz(Difficulty.HARD);
        hardGame.purchase(2);
        String wizPlace = hardGame.placeTower(0, 0, 1, null);

        CountryBuzz country = new CountryBuzz(Difficulty.HARD);
        hardGame.purchase(3);
        String cowPlace = hardGame.placeTower(0, 0, 1, null);

        assertEquals("You already have a tower placed there!", cowPlace);
    }

    @Test
    public void onlyPlaceWhenBoughtTower() {
        Game hardGame = new Game("Hard");

        String wizPlace = hardGame.placeTower(0, 0, 1, null);

        assertEquals("You don't have a tower to place!", wizPlace);
    }

    @Test
    public void testDirtPlacement() {
        Game hardGame = new Game("Hard");

        WizardBuzz wizard = new WizardBuzz(Difficulty.HARD);
        hardGame.purchase(2);
        String wizPlace = hardGame.placeTower(0, 0, 1, null);

        assertEquals(wizard.getImage(), hardGame.getPlacements()[0].getTower().getImage());
    }

    @Test
    public void testIsValidName() {
        assertEquals(false, Config.isValidName(""));
        assertEquals(false, Config.isValidName("    "));
        assertEquals(true, Config.isValidName("  name  "));
    }

    @Test
    public void testIsValidConfig() {
        assertEquals(false, Config.isValidConfig("name", null));
        assertEquals(false, Config.isValidConfig("", "Easy"));
        assertEquals(true, Config.isValidConfig("name", "Easy"));
    }


    @Test
    public void testCorrectTowerIsPurchased() {
        Game game1 = new Game("Easy");
        Tower tower1 = game1.purchase(3);
        assertThat(new CountryBuzz(Difficulty.EASY), instanceOf(tower1.getClass()));

        Game game2 = new Game("Easy");
        Tower tower2 = game2.purchase(2);
        assertThat(new WizardBuzz(Difficulty.EASY), instanceOf(tower2.getClass()));

        Game game3 = new Game("Easy");
        Tower tower3 = game3.purchase(1);
        assertThat(new BoxingBuzz(Difficulty.EASY), instanceOf(tower3.getClass()));

    }




    // Ignore

    // UI Tests
    //
    //       @Test
    //    public void startButtonLeadsToConfigScreen() {
    //        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
    //        // specify activity class
    //        Intent i = new Intent(appContext, MainActivity.class);
    //        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    //        appContext.startActivity(i);
    //
    //        // initialization click
    //        onView(withId(R.id.startBtn)).perform(click());
    //
    //        onView(withId(R.id.startBtn)).perform(click());
    //
    //        // check that new activity launched
    //        onView(withId(R.id.configGameText)).check(matches(isDisplayed()));
    //    }
    //
    //
    //
    //
    //
    //    @Test
    //    public void boxingBuzzHasCorrectCost() {
    //        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
    //
    //        // specify activity class
    //        Intent i = new Intent(appContext, GameScreen.class);
    //        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    //
    //        // start activity
    //        appContext.startActivity(i);
    //
    //        onView(withId(R.id.boxingBuzzLabel)).check(matches(isDisplayed()));
    //        onView(withId(R.id.boxingBuzzLabel)).check(matches(withText("150")));
    //    }
    //
    //    @Test
    //    public void startButtonLeadsToConfigScreen() {
    //        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
    //        // specify activity class
    //        Intent i = new Intent(appContext, MainActivity.class);
    //        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    //        appContext.startActivity(i);
    //
    //        // initialization click
    //        onView(withId(R.id.startBtn)).perform(click());
    //
    //        onView(withId(R.id.startBtn)).perform(click());
    //
    //        // check that new activity launched
    //        onView(withId(R.id.configGameText)).check(matches(isDisplayed()));
    //    }
    //
    //    @Test
    //    public void buyTowerOnlyWithSufficientFunds() {
    //        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
    //        // specify activity class
    //        Intent i = new Intent(appContext, GameScreen.class);
    //        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    //        appContext.startActivity(i);
    //
    //        // initialization click
    //        onView(withId(R.id.messages)).perform(click());
    //
    //        onView(withId(R.id.countryPurchase)).perform(click());
    //        onView(withId(R.id.messages))
    //        .check(matches(withText(startsWith("Country Buzz Tower purchased"))));
    //
    //        onView(withId(R.id.boxerPurchase)).perform(click());
    //        onView(withId(R.id.messages)).check(matches(withText(startsWith
    //        ("Insufficient funds"))));
    //
    //        onView(withId(R.id.wizardPurchase)).perform(click());
    //        onView(withId(R.id.messages)).check(matches(withText(startsWith
    //        ("Insufficient funds"))));
    //
    //        onView(withId(R.id.countryPurchase)).perform(click());
    //        onView(withId(R.id.messages)).check(matches(withText(startsWith
    //        ("Insufficient funds"))));
}