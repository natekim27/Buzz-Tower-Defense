package com.example.towerdefense.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.towerdefense.R;

public class GameOverScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e) {

        }

        setContentView(R.layout.gameover);

        Bundle b = getIntent().getExtras();

        TextView health = findViewById(R.id.health);
        TextView money = findViewById(R.id.money);
        TextView enemies = findViewById(R.id.enemies);

        health.setText(Integer.toString(Math.min(b.getInt("health"), 100)));
        money.setText(Integer.toString(b.getInt("money")));
        enemies.setText(Integer.toString(b.getInt("enemies")));

    }

    public void quit(View view) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }

    public void openWelcomeScreen(View view) {
        Intent intent = new Intent(this, WelcomeScreen.class);
        startActivity(intent);
    }
}
