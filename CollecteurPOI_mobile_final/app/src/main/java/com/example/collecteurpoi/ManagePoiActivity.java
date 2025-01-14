package com.example.collecteurpoi;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * ManagePoiActivity permet à l'utilisateur de visualiser et de gérer tous les points d'intérêt (POIs) enregistrés.
 * Elle affiche les POIs dans une liste interactive et permet de naviguer vers l'écran de détails d'un POI.
 */
public class ManagePoiActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private ListView poiListView;
    private ArrayList<Poi> poiList; // Liste des POIs chargés depuis la base de données
    private PoiAdapter adapter;

    /**
     * Méthode appelée lors de la création de l'activité.
     * Initialise l'interface utilisateur, charge les POIs depuis la base de données et configure les interactions utilisateur.
     *
     * @param savedInstanceState L'état précédemment sauvegardé de l'activité.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_poi);

        // Initialisation des composants de l'interface utilisateur
        poiListView = findViewById(R.id.poi_list_view);

        // Initialisation du DatabaseHelper
        databaseHelper = new DatabaseHelper(this);

        // Chargement des POIs depuis la base de données
        poiList = loadPOIsFromDatabase();

        // Configuration de l'adaptateur pour afficher les POIs dans la ListView
        adapter = new PoiAdapter(this, poiList);
        poiListView.setAdapter(adapter);

        // Gestion des clics sur les éléments de la liste
        poiListView.setOnItemClickListener((parent, view, position, id) -> {
            Poi selectedPoi = poiList.get(position);
            Intent intent = new Intent(ManagePoiActivity.this, PoiDetailActivity.class);
            intent.putExtra("poi_id", selectedPoi.getId()); // Passe l'ID du POI à l'écran de détails
            startActivity(intent);
        });

        // Gestion du bouton Retour
        Button backButton = findViewById(R.id.btn_back);
        backButton.setOnClickListener(v -> {
            // Retourne à l'écran principal
            Intent intent = new Intent(ManagePoiActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }

    /**
     * Charge tous les POIs depuis la base de données SQLite.
     *
     * @return Une liste de POIs contenant toutes les données récupérées de la base.
     */
    private ArrayList<Poi> loadPOIsFromDatabase() {
        ArrayList<Poi> poiList = new ArrayList<>();

        // Obtention d'une instance de la base de données en lecture
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.query("points_of_interest", null, null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            // Indices des colonnes dans la base de données
            int idIndex = cursor.getColumnIndex("id");
            int descriptionIndex = cursor.getColumnIndex("description");
            int categoryIndex = cursor.getColumnIndex("category");
            int latitudeIndex = cursor.getColumnIndex("latitude");
            int longitudeIndex = cursor.getColumnIndex("longitude");

            // Vérifie que toutes les colonnes nécessaires sont présentes
            if (idIndex >= 0 && descriptionIndex >= 0 && categoryIndex >= 0 && latitudeIndex >= 0 && longitudeIndex >= 0) {
                do {
                    // Récupération des données du POI
                    int id = cursor.getInt(idIndex);
                    String description = cursor.getString(descriptionIndex);
                    String category = cursor.getString(categoryIndex);
                    double latitude = cursor.getDouble(latitudeIndex);
                    double longitude = cursor.getDouble(longitudeIndex);

                    // Création d'un objet POI
                    Poi poi = new Poi(id, description, category, latitude, longitude);
                    poiList.add(poi);
                } while (cursor.moveToNext());
            } else {
                // Log en cas de colonnes manquantes
                Log.e("Database Error", "One or more columns are missing.");
            }

            cursor.close();
        }

        return poiList;
    }
}
