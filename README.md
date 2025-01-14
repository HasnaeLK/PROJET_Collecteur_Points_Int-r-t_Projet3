#Collecteur de Points d'Intérêt
##Description du projet
Le Collecteur de Points d'Intérêt est une application Android permettant à un utilisateur de capturer des points d'intérêt géographiques. L'application utilise les services de géolocalisation pour récupérer la position GPS actuelle et permet d'ajouter une description ainsi qu'une catégorie pour chaque point. Ces informations sont stockées dans une base de données SQLite et affichées sur une carte interactive.

##Fonctionnalités principales
1- Capturer la position GPS actuelle :
  Récupère les coordonnées de latitude et de longitude à l'aide des services Google     
  Location Services.
2- Ajouter une description et une catégorie :
  L'utilisateur peut personnaliser chaque point d'intérêt en ajoutant une description  
  textuelle et en sélectionnant une catégorie (exemple : Restaurant, Parc, Monument).
3- Sauvegarder les données :
  Les points d'intérêt sont stockés localement dans une base de données SQLite.
4- Afficher les points sur une carte :
  Les points d'intérêt enregistrés sont visualisables sur une carte interactive via 
  Google Maps.
  
##Technologies utilisées
Langage de programmation : Java
Base de données : SQLite
API de géolocalisation : Google Maps API / FusedLocationProviderClient
SDK de cartographie : Google Maps SDK
Environnement de développement : Android Studio

##Installation et configuration
1-Cloner le dépôt :
