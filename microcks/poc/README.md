# POC Microcks

Projet de test de l'outil Microcks pour du Mock et du Test/Validation de contrat d'interface (OpenAPI, AsyncAPI, GraphQL, etc ...)

:warning: L'outil fonctionne quasiment uniquement avec les exemples fournit dans les contrats d'interfaces. Si il n'y a pas d'exemple ça ne fonctionne pas très bien.


## 💻 Installation de l'outil en local

> Constat : Installation assez facile et rapide + Quickstart plutot complet

> Prérequis : Avoir Docker deja installé sur son poste (via wsl2 pour les postes windows)

La doc de Quickstart : https://microcks.io/documentation/getting-started/

Résumé des étapes : 
1. Cloner le repo microcks et se mettre dans le bon dossier
2. Démarrer les containers via docker-compose
3. Accéder à l'appli (authent keycloak, user=admin password=microcks123) : http://localhost:8080/

```shell
git clone https://github.com/microcks/microcks.git
cd microcks/install/docker-compose
sudo docker compose up -d
```



## ✅ Tests effectués avec l'outil <a name="test"></a>

### Tests de l'exemple OpenAPI mis à disposition par Microcks

> Constat : l'exemple permet de tester la partie mock + la partie validation de l'outil, c'est plutot OK

La doc de Getting Started : https://microcks.io/documentation/getting-started-tests/

**Résumé des étapes** : 
1. Dans la vue "Importers", faire "+create" et utiliser le contrat d'interface openapi fournit : https://raw.githubusercontent.com/microcks/microcks/master/samples/APIPastry-openapi.yaml
2. Récupérer l'application fournit qui implémente le contrat d'interface openapi
   * Fait via docker-compose pour plus de simplicité
```shell
sudo docker run -d -i --name pastry --network=docker-compose_default --rm -p 8282:8282 quay.io/microcks/quarkus-api-pastry:latest
```

**Lancement des tests via l'IHM** :
1. Dans la vue "APIs | Services", aller sur "API Pastry 2.0"
2. Faire "+New Test"
   * Tests Endpoint = http://pastry:8282 (c'est le nom du service docker)
   * Runner = OPEN_API_SCHEMA
On voit que ça fonctionne et que ça lance les tests vers l'application en vérifiant que les retours sont conformes aux attendus.

**Les Mocks sont aussi dispo** : 
* http://localhost:8080/rest/API+Pastry+-+2.0/2.0.0/pastry
* http://localhost:8080/rest/API+Pastry+-+2.0/2.0.0/pastry/Eclair+Cafe


### Tests avec l'exemple du projet open_api_template

Le projet est ici : https://innersource.soprasteria.com/526-sb-and-u/streamapi/open_api/open_api_template

**Résumé des étapes** : 
1. Dans la vue "Importers", faire "Upload"
2. Choisir le fichier "stream-api-formation-exemple.yml" du dossier "openapi-exemple"
3. Aller au bout et il est dispo dans l'interface

**Pour pouvoir lancer les tests de validation, il faut avoir une application qui implémente les APIs** :
* Le projet suivant implémente les APIs : https://innersource.soprasteria.com/526-sb-and-u/streamapi/open_api/open_api_java_template
* Récupérer le projet et faire les steps de Build Docker présent dans le README pour obtenir une image docker de l'application
* Lancer l'image docker
```
sudo docker run -d -i --name openapi-spring-application-template --network=docker-compose_default --rm -p 8090:8090 openapi-spring-application-template:dev
```

:warning: Il est nécessaire d'ajouter le param network pour que le container "microcks" puisse accéder au container de l'appli

**On peut ensuite lancer les tests de validation de conformité de l'API + utiliser les mocks** :
* Test Endpoint : http://openapi-spring-application-template:8090


### Tests de la partie microcks-cli

Documentation : 
* https://microcks.io/documentation/automating/cli/
* Repo Git : https://github.com/microcks/microcks-cli
* Release : https://github.com/microcks/microcks-cli/releases

En local Windows : 
* Créer un dossier microcks-cli
* Télécharger le binaire windows et le renommer microcks-cli.exe
* Ajouter le binaire au PATH

Interface Keycloak :
* http://localhost:18080/
* user/pass = admin/admin
* Aller dans le keycloak "microcks" (menu déroulant en haut à gauche)
* Récuperer le "client secret" dans "Clients" > "microcks-serviceaccount" > "Credentials"

Commande pour lancer le test "pastry" :
```shell
microcks-cli.exe test 'API Pastry - 2.0:2.0.0' http://pastry:8282 OPEN_API_SCHEMA --microcksURL=http://localhost:8080/api/ --waitFor=5sec --keycloakClientId=microcks-serviceaccount --keycloakClientSecret=ab54d329-e435-41ae-a900-ec6b3fe15c54 --verbose --insecure
```


## 💡 Intérêts

**Plusieurs fonctionnalités vraiement intéressante (à creuser plus en profondeur)** :
* Déploiement de mock automatique 
* Tests de validation de la conformité d'une API
* Conformance index/score sur les APIs : https://microcks.io/documentation/using/tests/#conformance-metrics
* Documentation intégrée (avec Redoc je pense), par : http://localhost:8080/api/documentation/Template%20API-1.0.0.yaml/OPEN_API_SPEC


## :warning: Problèmes rencontrés 

**Sur l'utilisation du contrat d'interface "stream-api-formation-exemple.yml"** :
* Visiblement il y a des problèmes lors des tests de validation --> ça semble venir d'un champ "queryParam" qui n'est pas renseigné
   * 🐞 Creuser le pourquoi ça ne fonctionne pas correctement (exemple non dispo ? outils qui ne fonctionne pas ?)
* Pareil pour les Mock :
   * Renvoit un truc alors que paramètre obligatoire non présent : http://localhost:8080/rest/Template+API/1.0.0/api/v1/formation
   * Fonctionnement OK : http://localhost:8080/rest/Template+API/1.0.0/api/v1/formation?id_formation=44

