package com.example.collecteurpoi;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * PoiAdapter est un adaptateur personnalisé qui gère l'affichage des points d'intérêt (POIs)
 * dans une ListView. Il relie les données des POIs à une interface utilisateur définie dans
 * le fichier de mise en page `list_item_poi`.
 */
public class PoiAdapter extends ArrayAdapter<Poi> {

    private Context context;
    private ArrayList<Poi> poiList;

    /**
     * Constructeur de l'adaptateur PoiAdapter.
     *
     * @param context Le contexte de l'application.
     * @param poiList La liste des POIs à afficher.
     */
    public PoiAdapter(Context context, ArrayList<Poi> poiList) {
        super(context, R.layout.list_item_poi, poiList);
        this.context = context;
        this.poiList = poiList;
    }

    /**
     * Méthode appelée pour obtenir la vue de chaque élément dans la ListView.
     * Lie les données du POI actuel à l'interface utilisateur.
     *
     * @param position    La position de l'élément dans la liste.
     * @param convertView La vue recyclée pour l'élément (si disponible).
     * @param parent      Le parent auquel cette vue sera attachée.
     * @return La vue configurée pour l'élément à afficher.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Gonfler (inflate) le layout pour chaque élément si nécessaire
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_poi, parent, false);
        }

        // Récupérer le POI actuel
        Poi currentPoi = poiList.get(position);

        // Associer les détails du POI aux vues (ex. : description)
        TextView descriptionView = convertView.findViewById(R.id.poi_description);
        descriptionView.setText(currentPoi.getDescription());

        // Gérer le clic sur un élément pour ouvrir l'écran de détails du POI
        convertView.setOnClickListener(v -> {
            Intent intent = new Intent(context, PoiDetailActivity.class);
            intent.putExtra("poi_id", currentPoi.getId());
            context.startActivity(intent);
        });

        return convertView;
    }
}
