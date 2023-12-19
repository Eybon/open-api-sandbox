# :rocket: Doc d'installation sur openshift

Cette doc est issue du POC de déploiement --> Attention tout n'est peut-être pas parfait et les liens sont liés à un env openshift qui ne sera pas accessible sans autorisation.


## :one: Tentative installation #1

Pour faire une installation complète :
```shell
helm repo add microcks https://microcks.io/helm

oc new-project microcks

helm upgrade --install microcks microcks/microcks --version 1.7.0 --set ingresses=false,microcks.url={{ microcks-url }},keycloak.url={{ keycloak-url }},keycloak.privateUrl=http://microcks-keycloak:8080

oc create route edge microcks --service=microcks

oc create route edge microcks-keycloak --service=microcks-keycloak
```

Puis il faut aller dans l'admin du Keycloak déployé pour créer des users avec les bons roles.

Si besoin de modifier le keycloak, les crédentials sont ici : https://{{ openshift-url }}/ns/microcks/secrets/microcks-keycloak-admin 

Conf par défaut :
```yaml
spec:
  sleepAt: '20:00'
  suspendCronJobs: false
  timeZone: Europe/Paris
  wakeUpAt: '08:00'
  weekdays: 1-5
```


## :two: Tentative installation #2 (plus simple)

```shell
helm repo add microcks https://microcks.io/helm

oc new-project microcks

helm upgrade --install microcks microcks/microcks --version 1.7.0 --set microcks.url={{ microcks-url }},keycloak.url={{ keycloak-url }},keycloak.privateUrl=http://microcks-keycloak:8080
```

A priori dans cette version d'Openshift, on peut créer des ingress, ca créé des Routes

Mais avec des certifs auto-signés par contre....

