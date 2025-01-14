package com.example.collecteurpoi;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

/**
 * HomeActivity est l'écran d'accueil de l'application.
 * Elle sert de point d'entrée et permet à l'utilisateur de naviguer vers d'autres fonctionnalités,
 * notamment l'ajout d'un nouveau point d'intérêt (POI) via MainActivity.
 */
public class HomeActivity extends AppCompatActivity {

    /**
     * Méthode appelée lors de la création de l'activité.
     * Initialise l'interface utilisateur et configure les interactions du bouton de navigation.
     *
     * @param savedInstanceState L'état précédemment sauvegardé de l'activité.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Initialisation du bouton pour naviguer vers MainActivity
        Button goToMainButton = findViewById(R.id.goToMainButton);

        // Configuration du clic sur le bouton
        goToMainButton.setOnClickListener(v -> {
            // Création d'un Intent pour ouvrir MainActivity
            Intent intent = new Intent(HomeActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }
}
