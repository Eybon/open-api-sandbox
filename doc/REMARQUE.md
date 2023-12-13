# Nommage des classes avec les générateurs openapi-generator

Les noms des interfaces/controllers/client générées se basent sur les Tags renseignés dans le fichier open-api :
- Pour openapi-generator client (generateur Java) -> Ok par defaut pour le nommage
- Pour openapi-generator client (generateur Spring) :
  - Par defaut, il prend le endpoint path pour nommer la classe (/formation = FormationApi.java, /api/v1/formation = ApiApi.java)
  - Pour corriger ça, il faut ajouter une properties dans le plugin maven (<useTags>true</useTags>)y



# En local : Probleme de CORS sur le front

Voir : https://stackoverflow.com/questions/46522749/how-to-solve-redirect-has-been-blocked-by-cors-policy-no-access-control-allow

Solution temporaire -> Installer le plugin "Moesif Origin & CORS Changer"


```xml
 <configOptions>
     <additionalModelTypeAnnotations>@lombok.Builder @lombok.NoArgsConstructor @lombok.AllArgsConstructor</additionalModelTypeAnnotations>
 </configOptions>
```
