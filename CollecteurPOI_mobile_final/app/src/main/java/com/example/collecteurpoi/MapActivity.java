package com.example.collecteurpoi;

import android.os.Bundle;
import android.util.Log;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

/**
 * MapActivity affiche une carte interactive utilisant Google Maps.
 * Elle permet de visualiser tous les points d'intérêt (POIs) enregistrés sur la carte.
 */
public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private DatabaseHelper databaseHelper;

    /**
     * Méthode appelée lors de la création de l'activité.
     * Initialise le fragment de la carte et configure la base de données.
     *
     * @param savedInstanceState L'état précédemment sauvegardé de l'activité.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        // Initialisation de l'instance DatabaseHelper
        databaseHelper = new DatabaseHelper(this);

        // Configuration du fragment de la carte
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    /**
     * Méthode appelée lorsque la carte est prête à être utilisée.
     * Configure la position initiale de la carte et affiche les POIs.
     *
     * @param googleMap L'instance de GoogleMap prête à être utilisée.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Définir une position initiale (par exemple, New York City)
        LatLng initialLocation = new LatLng(40.7128, -74.0060); // Exemple : New York
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(initialLocation, 12));

        // Ajouter un marqueur pour la position initiale
        mMap.addMarker(new MarkerOptions().position(initialLocation).title("Marker in New York"));

        // Afficher les POIs sur la carte
        showPOIsOnMap();
    }

    /**
     * Affiche tous les POIs enregistrés dans la base de données sur la carte.
     * Chaque POI est représenté par un marqueur à ses coordonnées GPS.
     */
    private void showPOIsOnMap() {
        if (databaseHelper != null) {
            // Récupérer les POIs depuis la base de données
            List<Poi> pois = databaseHelper.getAllPOIs();

            // Ajouter un marqueur pour chaque POI sur la carte
            for (Poi poi : pois) {
                LatLng poiLocation = new LatLng(poi.getLatitude(), poi.getLongitude());
                mMap.addMarker(new MarkerOptions()
                        .position(poiLocation)
                        .title(poi.getDescription()));
            }
        } else {
            // Log en cas d'erreur
            Log.e("MapActivity", "DatabaseHelper is null");
        }
    }
}
