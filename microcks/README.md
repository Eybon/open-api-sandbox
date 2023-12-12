# Microcks




## :gear: microcks-cli

### Lancement en local

**Commande** :
```shell
# Run test commande
microcks-cli.exe test 'Template API:1.0.0' http://fake-api-a-corriger:8282 \
		OPEN_API_SCHEMA --microcksURL={{ URL_MICROCKS }}/api/ \
		--waitFor=5sec --keycloakClientId=microcks-serviceaccount \
		--keycloakClientSecret=ab54d329-e435-41ae-a900-ec6b3fe15c54 \
		--verbose --insecure

# Run import commande
microcks-cli.exe import '_build/global.yml:true' \
		--microcksURL={{ URL_MICROCKS }}/api/ \
		--keycloakClientId=microcks-serviceaccount \
 		--keycloakClientSecret=ab54d329-e435-41ae-a900-ec6b3fe15c54 \
 		--verbose --insecure 
```

### Docker

Lien pour l'utilisation de l'image docker : https://microcks.io/documentation/automating/cli/

Attention pour corriger des problèmes d'intégrations dans gitlab-ci, on se basera sur la version nightly de l'image microcks-cli (dispo ici : https://quay.io/repository/microcks/microcks-cli?tab=tags).
L'objectif est de corriger le problème de plateforme en utilisant une image avec `architecture = x86_64`.

**Commande docker** :
```shell
# Build de l'image docker
sudo docker build --tag=microcks-cli:custom microcks/microcks-cli

# Run test commande
sudo docker run microcks-cli:custom \
			microcks-cli test 'Template API:1.0.0' http://fake-api-a-corriger:8282 OPEN_API_SCHEMA \
			--microcksURL={{ URL_MICROCKS }}/api/ --waitFor=5sec --keycloakClientId=microcks-serviceaccount \
			--keycloakClientSecret=ab54d329-e435-41ae-a900-ec6b3fe15c54 --verbose --insecure

# Run import commande
sudo docker run microcks-cli:custom microcks-cli import '_build/global.yml:true' \
 			--microcksURL={{ URL_MICROCKS }}/api/ --keycloakClientId=microcks-serviceaccount \
 			--keycloakClientSecret=ab54d329-e435-41ae-a900-ec6b3fe15c54 --verbose --insecure 
```

### Utilisation dans la CI Gitlab

Un fichier YAML gitlab-ci est disponible dans le repo : [microcks.yml](/microcks/gitlab-ci/microcks.yml)

Il contient deux Jobs : 
* Un job de déploiement du contrat d'interface : microcks-import
* Un job de lancement des tests du contrat d'interface : microcks-test
	* Ces jobs ne sont pas bloquants mais remonteront un warning si certains tests sont KOs

Pour l'inclure dans un pipeline gitlab-ci:
```yaml
include:
  - "/jobs/microcks.yml"
```
