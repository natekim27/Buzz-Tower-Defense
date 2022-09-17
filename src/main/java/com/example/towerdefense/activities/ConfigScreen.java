package com.example.towerdefense.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.towerdefense.Config;
import com.example.towerdefense.R;

public class ConfigScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e) {

        }

        setContentView(R.layout.config);

        // Create name input field
        EditText name = findViewById(R.id.nameInput);

        // input validation
        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (name.getText().toString().trim().length() == 0) {
                    name.setError("Name is required!");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }

    public void openGameScreen(View view) {
        EditText name = findViewById(R.id.nameInput);
        Spinner spinner = findViewById(R.id.difficultyInput);

        System.out.println(spinner.getSelectedItem());


        if (!Config.isValidName(name.getText().toString())) {
            // display dialog
            System.out.println("Select Name");
        } else if (!Config.isValidConfig(name.getText().toString(),
                spinner.getSelectedItem().toString())) {
            System.out.println("Invalid config");
        } else {
            Intent intent = new Intent(getApplicationContext(), GameScreen.class);

            Bundle b = new Bundle();
            b.putString("name", name.getText().toString());
            b.putString("difficulty", spinner.getSelectedItem().toString()); //Your id
            intent.putExtras(b); //Put your id to your next Intent

            startActivity(intent);
        }

    }
}
