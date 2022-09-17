package com.example.towerdefense.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.towerdefense.EnemyAnimator;
import com.example.towerdefense.EnemyPath;
import com.example.towerdefense.Game;
import com.example.towerdefense.R;
import com.example.towerdefense.enemies.Enemy;
import com.example.towerdefense.towers.Tower;
import com.example.towerdefense.util.DrawableHelper;

public class GameScreen extends AppCompatActivity {
    private Game game;
    private ConstraintLayout mainLayout;
    private LayoutInflater inflater;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e) {

        }

        setContentView(R.layout.main_game);

        Bundle b = getIntent().getExtras();
        String name = "Player 1";
        String difficulty = "Easy";
        if (b != null) {
            name = b.getString("name");
            difficulty = b.getString("difficulty");
        }

        TextView nameText = findViewById(R.id.nameDisplay);
        if (name.equals(" Name")) {
            name = "Player 1";
        }
        nameText.setText(name);
        game = new Game(difficulty);
        game.setListener(new Game.GameListener() {
            @Override
            public void updateValues() {
                updateStatus();
            }

            @Override
            public void levelOver() {
                endLevel();
            }

            @Override
            public void gameOver() {
                launchGameOver();
            }

            @Override
            public void winScreen() {
                launchWinScreen();
            }
        });

        updateStatus();
        TextView boxing = findViewById(R.id.boxingBuzzLabel);
        int[] towerCosts = game.getTowerCosts();
        setTowerCostDisplays(towerCosts);
        boxing.setText(Integer.toString(towerCosts[0]));

        View startCombat = findViewById(R.id.combatbutton);
        startCombat.setOnClickListener(view -> {
            spawnEnemies();
            view.setVisibility(View.INVISIBLE);
            setMessage("Level " + game.getLevel() + " started!");
        });

        mainLayout = findViewById(R.id.main_game_layout);
        inflater = (LayoutInflater) getApplicationContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    private void setTowerCostDisplays(int[] towerCosts) {
        TextView boxing = findViewById(R.id.boxingBuzzLabel);
        boxing.setText(Integer.toString(towerCosts[0]));

        TextView wizard = findViewById(R.id.wizardBuzzLabel);
        wizard.setText(Integer.toString(towerCosts[1]));

        TextView country = findViewById(R.id.countryBuzzLabel);
        country.setText(Integer.toString(towerCosts[2]));
    }

    public void purchase(View view) {
        int option;
        switch (view.getId()) {
        case R.id.boxerPurchase: option = 1;
            break;
        case R.id.wizardPurchase: option = 2;
            break;
        default: option = 3;
            break;
        }

        Tower tower = game.purchase(option);
        if (tower == null) {
            setMessage("Insufficient funds to purchase tower");
        } else {
            updateStatus();
            setMessage(tower + " purchased successfully. Click on an open circle to place it.");
        }
    }

    private void setMessage(String message) {
        System.out.println(message);
        ConstraintLayout layout = findViewById(R.id.main_game_layout);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(1100, 120);
        LinearLayout.LayoutParams txtParams = new LinearLayout.LayoutParams(1000, 120);

        View banner = new View(this);
        banner.setBackgroundResource(R.drawable.message_banner);

        TextView messageText = new TextView(this);
        messageText.setText("      " + message);
        messageText.setTextColor(Color.BLACK);
        messageText.setGravity(Gravity.CENTER);
        messageText.setLayoutParams(txtParams);

        ConstraintLayout constraintLayout = new ConstraintLayout(this);
        constraintLayout.addView(banner);
        constraintLayout.addView(messageText);
        constraintLayout.setLayoutParams(params);

        layout.addView(constraintLayout);
        constraintLayout.setX(500);
        constraintLayout.setY(0);
        constraintLayout.animate().alpha(0.0f).setDuration(7000);
    }

    private void updateStatus() {
        TextView moneyText = findViewById(R.id.moneyDisplay);
        TextView monumentHealthText = findViewById(R.id.healthDisplay);
        moneyText.setText(Integer.toString(game.getMoney()));
        monumentHealthText.setText(Integer.toString(game.getHealth()));
    }

    private void launchGameOver() {
        Intent intent = new Intent(this, GameOverScreen.class);
        Bundle b = new Bundle();

        b.putInt("money", game.getMoneySpent());
        b.putInt("health", game.getStartingHealth() - game.getHealth());
        b.putInt("enemies", game.getEnemiesKilled());

        intent.putExtras(b);

        finish();
        startActivity(intent);

    }

    private void launchWinScreen() {
        Intent intent = new Intent(this, WinScreen.class);
        Bundle b = new Bundle();

        b.putInt("money", game.getMoneySpent());
        b.putInt("health", game.getStartingHealth() - game.getHealth());
        b.putInt("enemies", game.getEnemiesKilled());

        intent.putExtras(b);

        finish();
        startActivity(intent);

    }

    public void placeTower(View view) {
        int spot;
        ImageButton patch;

        switch (view.getId()) {
        case R.id.patch1: spot = 1;
            break;
        case R.id.patch2: spot = 2;
            break;
        case R.id.patch3: spot = 3;
            break;
        case R.id.patch4: spot = 4;
            break;
        case R.id.patch5: spot = 5;
            break;
        case R.id.patch6: spot = 6;
            break;
        case R.id.patch7: spot = 7;
            break;
        default:
            throw new IllegalStateException("Unexpected value: " + view.getId());
        }

        // determine center of patch
        float x = (float) (view.getX() + view.getWidth() / 2.0);
        float y = (float) (view.getY() + view.getHeight() / 2.0);

        System.out.println("patch " + spot);
        patch = (ImageButton) findViewById(view.getId());
        setMessage(game.placeTower(spot, x, y, null));
        displayUpgradeCosts();

        if (game.getPlacements()[spot - 1] != null) {
            patch.setImageResource(game.getPlacements()[spot - 1].getTower().getImage());
            generateTowerProximityCircle(game.getPlacements()[spot - 1].getTower(),
                    (ImageView) view);
        }

    }
  
    private void spawnEnemies() {
        spawnEnemies(-1);
    }

    private void spawnEnemies(int num) {
        Enemy[] enemies = game.startLevel(num);
        int width = 80;
        int height = 120;
        if (enemies.length == 1) {
            // final boss
            width = 160;
            height = 240;
        }

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);

        for (int i = 0; i < enemies.length; i++) {
            Enemy enemy = enemies[i];

            View enemyView = inflater.inflate(R.layout.enemy_view, null);
            ImageView enemyImage = enemyView.findViewById(R.id.enemyPicture);

            enemyImage.setImageResource(enemy.getImage());
            enemyImage.setLayoutParams(params);
            enemyView.setVisibility(View.GONE);

            mainLayout.addView(enemyView);
            ProgressBar healthBar = enemyView.findViewById(R.id.enemyHealthBar);
            healthBar.setMax(enemy.getHealth());
            healthBar.setProgress(enemy.getHealth());

            EnemyAnimator enemyAnimator = new EnemyAnimator(enemyView, EnemyPath.makePath(),
                    enemy, game);
            enemyAnimator.start(enemy.getSpeed(), i * 1500);
        }
    }

    private void endLevel() {
        updateStatus();
        Button view = findViewById(R.id.combatbutton);
        view.setText("Start level " + game.getLevel());
        view.setVisibility(View.VISIBLE);
        // display start combat button again
    }

    public void upgrade(View view) {
        String message = game.upgrade((ImageView) view);
        System.out.println(message);
        setMessage(message);

        displayUpgradeCosts();
        updateStatus();
    }

    public void displayUpgradeCosts() {
        TextView damage = findViewById(R.id.damageUpgradeCost);
        TextView attackSpeed = findViewById(R.id.firingRateUpgradeCost);
        TextView range = findViewById(R.id.rangeUpgradeCost);
        int[] costs = {50, 50, 50};
        if (game.getCurrentTower() != null) {
            costs = game.getCurrentTower().getTower().getUpgradeCosts();
        }
        damage.setText(Integer.toString(costs[0]));
        attackSpeed.setText(Integer.toString(costs[1]));
        range.setText(Integer.toString(costs[2]));

    }

    private void generateTowerProximityCircle(Tower tower, ImageView patch) {
        ConstraintLayout layout = findViewById(R.id.main_game_layout);
        GradientDrawable shape = DrawableHelper.buildCircle();

        ImageView imageView = new ImageView(this);
        imageView.setBackground(shape);
        imageView.setVisibility(View.VISIBLE);
        imageView.setX(patch.getX() - tower.getRange() + patch.getWidth() / 2);
        imageView.setY(patch.getY() - tower.getRange() + patch.getHeight() / 2);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(tower.getRange() * 2,
                tower.getRange() * 2);
        imageView.setLayoutParams(params);
        imageView.setTranslationZ(-2);

        layout.addView(imageView);
        imageView.animate().alpha(0.0f).setDuration(3000);
    }
}
