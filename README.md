# POI Management App

A mobile application for managing Points of Interest (POIs). Users can add, edit, delete, and view POIs on a map. The app allows the user to provide a description and choose a category for each location and manage them easily.

## Features

- Add new Points of Interest (POIs) with descriptions.
- Edit or delete existing POIs.
- Display all POIs on a map view.
- User-friendly interface for managing POIs.

## Screenshots

**Home Page**  

![image](https://github.com/user-attachments/assets/3d0927ab-c170-4ac4-a21f-58cf51e0f2aa)

**Principal Page**  

The home page allows users to add new POIs and access additional features.
![image](https://github.com/user-attachments/assets/3c879bf8-1895-4837-9955-176a6e340d7e)

**Map View**  

POIs are displayed on a map, showing their locations with pin markers.
![image](https://github.com/user-attachments/assets/68800312-0cfe-4fce-9138-351fd66262ac)


## Installation

### Prerequisites

- Android Studio (or any other preferred IDE for Android development)
- Android 5.0 or above (for running the app on an emulator or device)

## Permissions
The app requires the following permissions:

- ACCESS_FINE_LOCATION: For obtaining precise location data.
- ACCESS_COARSE_LOCATION: For obtaining approximate location data.

These permissions are requested in the AndroidManifest.xml.

## Application Structure

The app consists of the following key activities:

- **HomeActivity:** The main launcher activity where users can see their POIs and access other features.
- **AboutActivity:** Provides information about the app.
- **ManagePoiActivity:** Allows users to manage, edit, or delete POIs.
- **PoiDetailActivity:** Displays detailed information about a selected POI.
- **MapActivity:** Displays POIs on a Google Map.

## Roadmap

- Ajouter la prise en charge des photos pour chaque POI.
- Intégrer des notifications push pour des rappels liés aux POIs.
- Améliorer la recherche avec des filtres avancés.
- Ajouter un mode hors ligne pour consulter les POIs sans connexion Internet.

## Contributions

Les contributions sont les bienvenues ! Suivez ces étapes :
1. Forkez le dépôt.
2. Créez une branche pour votre fonctionnalité (`git checkout -b feature/nom-feature`).
3. Faites vos modifications et soumettez une pull request.


