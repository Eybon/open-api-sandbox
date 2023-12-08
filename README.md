# open-api-sandbox
Projet sandbox pour test des outils de l'écosystème open-api


## :gear: Stack technique 

| Framework/Tools     | Version |
|---------------------|---------|
| Java                | 17      |
| SpringBoot          | 2.7.8   |
| Angular             |         |
| OpenApi Generator   | 6.2.0   | 


## :package: Construction du fichier OpenApi via swagger-cli <a name="tools-build"></a>

Pour le moment il est nécessaire de builder "à la main" le fichier "global.yml" et de le pousser dans le repo gitlab.
Pour cela on utilise l'outil swagger-cli : https://github.com/APIDevTools/swagger-cli
```
# Installation outil swagger-cli via npm
npm install -g swagger-cli

# Construction du fichier global.yml
swagger-cli bundle api/specification/index.yml --outfile _build/global.yml --type yaml
```
