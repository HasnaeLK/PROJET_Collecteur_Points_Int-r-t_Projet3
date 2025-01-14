package com.example.collecteurpoi;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

/**
 * PoiDetailActivity permet d'afficher les détails d'un point d'intérêt (POI),
 * ainsi que de modifier ou supprimer ce POI depuis la base de données.
 */
public class PoiDetailActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private Poi currentPoi;

    /**
     * Méthode appelée lors de la création de l'activité.
     * Initialise l'interface utilisateur, récupère les détails du POI depuis la base de données,
     * et configure les interactions utilisateur (mise à jour, suppression, retour).
     *
     * @param savedInstanceState L'état précédemment sauvegardé de l'activité.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poi_detail);

        // Initialisation du DatabaseHelper
        databaseHelper = new DatabaseHelper(this);

        // Récupération de l'identifiant du POI à partir de l'intent
        int poiId = getIntent().getIntExtra("poi_id", -1);
        if (poiId != -1) {
            currentPoi = databaseHelper.getPOIById(poiId);
        }

        // Liaison des détails du POI à l'interface utilisateur
        if (currentPoi != null) {
            TextView descriptionView = findViewById(R.id.poi_description);
            descriptionView.setText(currentPoi.getDescription());
        }

        // Configuration du bouton de mise à jour
        Button updateButton = findViewById(R.id.btn_update);
        updateButton.setOnClickListener(v -> updatePOI());

        // Configuration du bouton de suppression
        Button deleteButton = findViewById(R.id.btn_delete);
        deleteButton.setOnClickListener(v -> deletePOI());

        // Configuration du bouton d'annulation
        Button cancelButton = findViewById(R.id.btn_cancel);
        cancelButton.setOnClickListener(v -> {
            // Retourne à l'écran ManagePoiActivity
            Intent intent = new Intent(PoiDetailActivity.this, ManagePoiActivity.class);
            startActivity(intent);
        });
    }

    /**
     * Met à jour les informations du POI dans la base de données.
     * Récupère les nouvelles valeurs saisies par l'utilisateur et les enregistre dans la base.
     * Affiche un message Toast pour indiquer le succès ou l'échec de l'opération.
     */
    private void updatePOI() {
        // Récupération de la nouvelle description depuis l'interface utilisateur
        EditText descriptionView = findViewById(R.id.poi_description);
        String newDescription = descriptionView.getText().toString();

        // Mise à jour du POI dans la base de données
        if (currentPoi != null) {
            currentPoi.setDescription(newDescription);
            boolean isUpdated = databaseHelper.updatePOI(currentPoi);

            if (isUpdated) {
                Toast.makeText(this, "POI updated", Toast.LENGTH_SHORT).show();
                descriptionView.setText(newDescription);
            } else {
                Toast.makeText(this, "Failed to update POI", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * Supprime le POI actuel de la base de données.
     * Affiche un message Toast pour confirmer la suppression
     * et ferme l'activité pour revenir à l'écran précédent.
     */
    private void deletePOI() {
        if (currentPoi != null) {
            // Suppression du POI de la base de données
            databaseHelper.deletePOI(currentPoi.getId());

            // Affichage d'un message de confirmation
            Toast.makeText(this, "POI deleted", Toast.LENGTH_SHORT).show();

            // Fermeture de l'activité
            finish();
        }
    }
}
