package com.example.collecteurpoi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * La classe DatabaseHelper gère la création, la mise à jour et les opérations CRUD (Create, Read, Update, Delete)
 * sur une base de données SQLite pour stocker les points d'intérêt (POIs).
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "poi.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "points_of_interest";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_CATEGORY = "category";
    private static final String COLUMN_LATITUDE = "latitude";
    private static final String COLUMN_LONGITUDE = "longitude";

    /**
     * Constructeur de la classe DatabaseHelper.
     *
     * @param context Le contexte de l'application.
     */
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Méthode appelée lors de la création de la base de données.
     * Elle crée la table `points_of_interest`.
     *
     * @param db L'objet SQLiteDatabase.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_DESCRIPTION + " TEXT, " +
                COLUMN_CATEGORY + " TEXT, " +
                COLUMN_LATITUDE + " REAL, " +
                COLUMN_LONGITUDE + " REAL)";
        db.execSQL(createTable);
    }

    /**
     * Méthode appelée lors de la mise à jour de la base de données.
     * Elle supprime l'ancienne table et la recrée.
     *
     * @param db         L'objet SQLiteDatabase.
     * @param oldVersion L'ancienne version de la base.
     * @param newVersion La nouvelle version de la base.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    /**
     * Insère un nouveau POI dans la base de données.
     *
     * @param description La description du POI.
     * @param category    La catégorie du POI.
     * @param latitude    La latitude du POI.
     * @param longitude   La longitude du POI.
     */
    public void insertPOI(String description, String category, double latitude, double longitude) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_DESCRIPTION, description);
        values.put(COLUMN_CATEGORY, category);
        values.put(COLUMN_LATITUDE, latitude);
        values.put(COLUMN_LONGITUDE, longitude);

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    /**
     * Récupère tous les POIs de la base de données sous forme de Cursor.
     *
     * @return Un Cursor contenant tous les POIs.
     */
    public Cursor getAllData() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

    /**
     * Met à jour un POI existant dans la base de données.
     *
     * @param poi L'objet POI contenant les nouvelles informations.
     * @return true si la mise à jour a réussi, false sinon.
     */
    public boolean updatePOI(Poi poi) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_DESCRIPTION, poi.getDescription());
        values.put(COLUMN_CATEGORY, poi.getCategory());

        int rowsAffected = db.update(TABLE_NAME, values, "id = ?", new String[]{String.valueOf(poi.getId())});
        db.close();
        return rowsAffected > 0;
    }

    /**
     * Supprime un POI de la base de données.
     *
     * @param id L'identifiant du POI à supprimer.
     */
    public void deletePOI(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    /**
     * Récupère un POI spécifique à partir de son identifiant.
     *
     * @param id L'identifiant du POI.
     * @return Un objet POI contenant les informations du POI, ou null s'il n'existe pas.
     */
    public Poi getPOIById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, "id = ?", new String[]{String.valueOf(id)}, null, null, null);

        Poi poi = null;
        if (cursor != null && cursor.moveToFirst()) {
            int descriptionIndex = cursor.getColumnIndex(COLUMN_DESCRIPTION);
            int categoryIndex = cursor.getColumnIndex(COLUMN_CATEGORY);
            int latitudeIndex = cursor.getColumnIndex(COLUMN_LATITUDE);
            int longitudeIndex = cursor.getColumnIndex(COLUMN_LONGITUDE);

            if (descriptionIndex >= 0 && categoryIndex >= 0 && latitudeIndex >= 0 && longitudeIndex >= 0) {
                String description = cursor.getString(descriptionIndex);
                String category = cursor.getString(categoryIndex);
                double latitude = cursor.getDouble(latitudeIndex);
                double longitude = cursor.getDouble(longitudeIndex);
                poi = new Poi(id, description, category, latitude, longitude);
            }
            cursor.close();
        }

        return poi;
    }

    /**
     * Récupère tous les POIs de la base de données sous forme de liste.
     *
     * @return Une liste contenant tous les POIs.
     */
    public ArrayList<Poi> getAllPOIs() {
        ArrayList<Poi> poiList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(COLUMN_ID);
            int descriptionIndex = cursor.getColumnIndex(COLUMN_DESCRIPTION);
            int categoryIndex = cursor.getColumnIndex(COLUMN_CATEGORY);
            int latitudeIndex = cursor.getColumnIndex(COLUMN_LATITUDE);
            int longitudeIndex = cursor.getColumnIndex(COLUMN_LONGITUDE);

            do {
                int id = cursor.getInt(idIndex);
                String description = cursor.getString(descriptionIndex);
                String category = cursor.getString(categoryIndex);
                double latitude = cursor.getDouble(latitudeIndex);
                double longitude = cursor.getDouble(longitudeIndex);

                Poi poi = new Poi(id, description, category, latitude, longitude);
                poiList.add(poi);
            } while (cursor.moveToNext());

            cursor.close();
        }

        return poiList;
    }
}
