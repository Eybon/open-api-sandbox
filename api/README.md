# Gestion des fichiers OpenAPI

## :clipboard: Fonctionnement du module specification (fichier OpenAPI) <a name="repo-specs"></a>

Dans le module "specification" les fichiers OpenAPI sont découpés par section :
* Le fichier "index.yml" à la racine est le point d'entrée
* Un dossier "parameters" contenant la définition des paramètres des API
* Un dossier "paths" contenant la définition des API
* Un dossier "responses" contenant la définition des responses des API
* Un dossier "schemas" contenant les objets des API
  Chaque dossier contient un fichier "_index.yml" qui est l'annuaire du dossier.

