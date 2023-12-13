# Notes

## Comment organiser/découper ses fichiers OpenApi ? 

On peut regrouper l'utilisation d'un contrat d'interface OpenApi en deux grands cas de figure : 
- Une utilisation pour une exposition d'API (coté server)
- Une utilisation pour consommer des API (coté client)
- Dans la majorité des cas, ces fichiers sont produits et maintenus par l'équipe qui exposera les API

Il y a ensuite plusieurs manières de rédiger un contrat d'interface OpenApi :
- Un seul fichier qui contient l'ensemble des API/ressources exposés par une application
- Un découpage par catégorie d'exposition des API/ressources (API Interne, API Externe, API mixtes)
- Un découpage en un fichier par API/ressource de l'application
- Un découpage en plusieurs fichiers par API/ressource de l'application (apis/schemas/parameters/responses) avec un fichier "racine" 

Les différentes solutions ont des avantages et des inconvénients qu'il faut prendre en compte.

**Cependant il est plus simple de refusionner des fichiers que de les redécouper.**

### Un fichier unique pour l'application 

Avantages :
- Utile dans le cas ou vous avez besoin de fournir l'ensemble de vos API/ressources à un partenaire (par exemple une brique de sécurité qui fait du whitelisting ...)
- Permet d'éviter la duplication des components/responses/parameters
- En cas d'utilisation d'openapi-generator (coté server), permet de ne pas avoir à multiplier les configurations maven par fichier

Inconvénients :
- Impossible de versionner les API/ressources indépendamment les unes des autres
- Assez peu lisible et maintenable dans la durée si vous avez plusieurs API/ressources
- Impossible de fournir uniquement une des API/ressources sana fournir l'ensemble pour un partenaire par exemple (ou alors actions manuelles)
- En cas d'utilisation d'openapi-generator (coté client), génération de code inutile (toutes les apis de votre partenaire ...)

Tips/Conseils :
- **Il est possible de refusionner tout vos fichiers en un seul si les fichiers sont correctements rédigés** (voir openapi-validator dans ce repo)

### Un fichier par API/ressource

Avantages :
- Utile pour échanger sur une API/ressource avec vos partenaires
- Permet de versionner les API/ressources indépendamment 
- En cas d'utilisation d'openapi-generator (coté client), génération du code strictement nécessaire

Inconvénients :
- Attention à la duplication !
- En cas d'utilisation d'openapi-generator (coté serveur), multiplication des configurations maven par fichier

### Plusieurs fichiers par API/ressource avec un fichier racine

Avantages :
- Utile pour échanger sur une API/ressource avec vos partenaires
- Permet de versionner les API/ressources indépendamment
- Permet de découper les fichiers comme on découpe du code et permet donc la réutilisation de components/responses/parameters
- En cas d'utilisation d'openapi-generator (coté server), permet de ne pas avoir à multiplier les configurations maven par fichier

Inconvénients :
- En cas d'utilisation d'openapi-generator (coté client), génération de code inutile (toutes les apis de votre partenaire ...)
