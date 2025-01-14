package com.example.collecteurpoi;

/**
 * La classe Poi représente un point d'intérêt (POI) avec des attributs tels que
 * l'identifiant, la description, la catégorie et les coordonnées GPS.
 */
public class Poi {

    private int id;
    private String description;
    private String category;
    private double latitude;
    private double longitude;

    /**
     * Constructeur de la classe Poi.
     *
     * @param id          L'identifiant unique du POI.
     * @param description La description du POI.
     * @param category    La catégorie du POI (exemple : Restaurant, Parc).
     * @param latitude    La latitude du POI.
     * @param longitude   La longitude du POI.
     */
    public Poi(int id, String description, String category, double latitude, double longitude) {
        this.id = id;
        this.description = description;
        this.category = category;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Retourne l'identifiant unique du POI.
     *
     * @return L'identifiant du POI.
     */
    public int getId() {
        return id;
    }

    /**
     * Définit l'identifiant unique du POI.
     *
     * @param id L'identifiant du POI.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retourne la description du POI.
     *
     * @return La description du POI.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Définit la description du POI.
     *
     * @param description La nouvelle description du POI.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Retourne la catégorie du POI.
     *
     * @return La catégorie du POI.
     */
    public String getCategory() {
        return category;
    }

    /**
     * Définit la catégorie du POI.
     *
     * @param category La nouvelle catégorie du POI.
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Retourne la latitude du POI.
     *
     * @return La latitude du POI.
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Retourne la longitude du POI.
     *
     * @return La longitude du POI.
     */
    public double getLongitude() {
        return longitude;
    }
}
