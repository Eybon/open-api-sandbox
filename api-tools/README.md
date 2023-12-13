# Outils de l'eco-systeme OpenApi


## :book: Redoc (documentation)

Lien du repo github : https://github.com/Redocly/redoc

**Installation** :
```shell
# Installation outil via npm
npm install -g http-server

# Demarrage de la doc
http-server ./ -p 8081
```
Doc dispo ici : http://127.0.0.1:8081/redoc.html



## :gear: Swagger-cli

Lien du repo : https://github.com/APIDevTools/swagger-cli

**Commande Docker** :
```shell
# Build de l'image docker
sudo docker build --tag=swagger-cli api-tools/swagger-cli

# Lancement de l'image docker
sudo docker run -v /mnt/d/Projets/sandbox/open-api-sandbox:/data swagger-cli \
        swagger-cli bundle data/api/specification/index.yml \
        --outfile data/_build/global.yml --type yaml
```



## :black_joker: Prism (mock)

Il est possible de lancer un serveur de mock directement en ligne de commande à partir de vos fichiers OpenAPI via l'outil Prism.

Par defaut le serveur de mock est dispo ici : http://127.0.0.1:4010/

```shell
# Installation outil prism via npm
npm install -g @stoplight/prism-cli

# Lancement du serveur de mock
prism mock _build/global.yml

# Lancement du serveur de mock (en mode dynamics)
prism mock _build/global.yml -d
```

Exemple de resultat suite au démarrage et à l'utilisation du serveur de mock :
![Exemple](/tools/readme/images/mock_exemple.png "Exemple de lancement Prism")



## :vertical_traffic_light: openapi-validator (linter)

Il est possible de faire tourner un linter sur les fichiers OpenAPI en utilisant l'outil openapi-validator (IBM).
Lien du repo github : https://github.com/IBM/openapi-validator

L'outil swagger-cli fournit aussi une option de validation via la commande "swagger-cli validate file.yml", mais la validation à l'air plus permissive que celle de openapi-validation.

### Utilisation en local

**Installation et utilisation en local** :
```shell
# Installation outil openapi-validator via npm
npm install -g ibm-openapi-validator

# Utilisation du linter
lint-openapi _build/global.yml
```

**Exemple de resultat d'une utilisation du linter** :

![Exemple](/tools/readme/images/openapi-validator_local_exemple.png "Exemple openapi-validator en local")

### Docker

Lien pour l'utilisation de l'image docker : https://github.com/IBM/openapi-validator#docker-container

**Commande Docker** :
```shell
# Build de l'image docker
sudo docker build --tag=openapi-linter api-tools/openapi-validator

# Lancement de l'image docker
sudo docker run -v /mnt/d/Projets/sandbox/open-api-sandbox:/data \
        openapi-linter lint-openapi _build/global.yml
```

### Utilisation dans la CI Gitlab

Un job gitlab-ci est disponible dans le repo : [openapi-linter.yml](/api-tools/openapi-validator/gitlab-ci/openapi-linter.yml)
* Ce job n'est pas bloquant mais remontera un warning si il détecte des problèmes sur vos fichiers OpenAPI

Pour l'inclure dans un pipeline gitlab-ci:
```yaml
include:
  - "/jobs/openapi-linter.yml"
```

**Le job est présent dans le pipeline Gitlab** :

![Exemple](/tools/readme/images/openapi-validator_ci_job.png "Job dans gitlab")

**Exemple de resultat dans la CI du projet** :

![Exemple](/tools/readme/images/openapi-validator_ci_exemple.png "Exemple openapi-validator dans gitlab")

### Spectral

Utilisation de Spectral :
- https://docs.stoplight.io/docs/spectral/674b27b261c3c-overview
- https://docs.stoplight.io/docs/spectral/4dec24461f3af-open-api-rules

Pour le moment les configurations openapi-validator sont surchargées par la conf du fichier `.spectral.yaml`
- https://github.com/IBM/openapi-validator/blob/main/docs/ibm-cloud-rules.md#overview-of-spectraloas-rules
