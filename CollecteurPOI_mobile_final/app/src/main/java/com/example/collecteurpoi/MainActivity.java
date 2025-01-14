package com.example.collecteurpoi;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import java.util.ArrayList;
import java.util.List;

/**
 * MainActivity est l'écran principal de l'application.
 * Elle permet à l'utilisateur de capturer un point d'intérêt (POI) avec une description,
 * une catégorie et sa position GPS. Elle offre également une navigation vers d'autres
 * fonctionnalités comme l'affichage des POIs sur une carte ou leur gestion.
 */
public class MainActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private FusedLocationProviderClient fusedLocationClient;
    private EditText descriptionInput;
    private Spinner categorySpinner;
    private Button getLocationButton, saveButton;
    private double latitude, longitude;

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    /**
     * Méthode appelée lors de la création de l'activité.
     * Initialise l'interface utilisateur, configure les boutons et met en place
     * les interactions utilisateur.
     *
     * @param savedInstanceState L'état précédemment sauvegardé de l'activité.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialisation des composants de l'interface utilisateur
        descriptionInput = findViewById(R.id.descriptionInput);
        categorySpinner = findViewById(R.id.categorySpinner);
        getLocationButton = findViewById(R.id.getLocationButton);
        saveButton = findViewById(R.id.saveButton);

        // Initialisation du DatabaseHelper et du client de géolocalisation
        databaseHelper = new DatabaseHelper(this);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        // Configuration du spinner avec les catégories
        setupCategorySpinner();

        // Bouton pour capturer la position GPS actuelle
        getLocationButton.setOnClickListener(v -> captureLocation());

        // Bouton pour enregistrer un POI
        saveButton.setOnClickListener(v -> savePOI());

        // Bouton pour naviguer vers MapActivity
        Button displayMapButton = findViewById(R.id.displayMapButton);
        displayMapButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MapActivity.class);
            startActivity(intent);
        });

        // Bouton pour naviguer vers ManagePoiActivity
        Button btnManagePoi = findViewById(R.id.btn_manage_poi);
        btnManagePoi.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ManagePoiActivity.class);
            startActivity(intent);
        });
    }

    /**
     * Configure le spinner avec une liste de catégories prédéfinies.
     */
    private void setupCategorySpinner() {
        List<String> categories = new ArrayList<>();
        categories.add("Choose category"); // Option par défaut
        categories.add("Restaurant");
        categories.add("Park");
        categories.add("Museum");
        categories.add("Shop");
        categories.add("Other");

        // Crée un adaptateur pour afficher les catégories dans le spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);
    }

    /**
     * Capture la position GPS actuelle de l'utilisateur.
     * Vérifie les permissions et utilise FusedLocationProviderClient pour obtenir la position.
     * Affiche un message Toast pour indiquer si la position a été capturée ou non.
     */
    private void captureLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
            return;
        }

        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, location -> {
                    if (location != null) {
                        latitude = location.getLatitude();
                        longitude = location.getLongitude();
                        Toast.makeText(MainActivity.this, "Location Captured: Lat: " + latitude + ", Long: " + longitude, Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Location not found", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    /**
     * Enregistre un nouveau point d'intérêt (POI) dans la base de données.
     * Valide les entrées utilisateur et affiche un message Toast en cas de succès ou d'échec.
     */
    private void savePOI() {
        String description = descriptionInput.getText().toString();
        String category = categorySpinner.getSelectedItem().toString();

        if (description.isEmpty() || category.isEmpty() || category.equals("Choose category")) {
            Toast.makeText(this, "Please fill in all fields and choose a valid category", Toast.LENGTH_SHORT).show();
            return;
        }

        // Insère le POI dans la base de données
        databaseHelper.insertPOI(description, category, latitude, longitude);

        // Réinitialise les champs de saisie
        descriptionInput.setText("");
        categorySpinner.setSelection(0);

        Toast.makeText(this, "POI saved successfully!", Toast.LENGTH_SHORT).show();
    }
}
