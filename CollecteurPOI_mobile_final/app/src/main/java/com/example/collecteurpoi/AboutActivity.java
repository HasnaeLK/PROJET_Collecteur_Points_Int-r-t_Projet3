package com.example.collecteurpoi;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Cette classe représente l'activité "À propos" de l'application.
 * Elle affiche des informations sur l'application ou son développeur.
 */
public class AboutActivity extends AppCompatActivity {

    /**
     * Méthode appelée lors de la création de l'activité.
     * Elle initialise l'interface utilisateur de l'activité "À propos".
     *
     * @param savedInstanceState Si l'activité est recréée après avoir été détruite,
     *                           ce paramètre contient les données les plus récentes fournies par
     *                           {@link #onSaveInstanceState(Bundle)}. Sinon, il est null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about); // Définit le fichier XML pour cette activité.
    }
}