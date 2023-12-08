# OpenApi Java Template

L'objectif du module `server-app` est de tester et de proposer un template d'application Java qui implémente et utilise des API basées sur des contrats d'interface OpenAPI.


## :star: Fonctionnement du repo 

Le dossier est découpé en plusieurs modules :
* Module "java-generation" --> Module Java de generation des classes Java à partir des fichiers OpenAPI (via le plugin openapi-generator)
    * Sous-module "java-generation-server" --> Sous-module contenant le code generé pour la partie server (exposition d'API)
    * Sous-module "java-generation-client" --> Sous-module contenant le code generé pour la partie client (appel d'API externe)
* Module "java-module" --> Module Java d'utilisation des classes générees.
    * Sous-module "java-module-server" --> Sous-module java pour la partie server (exposition d'API)
    * Sous-module "java-module-client" --> Sous-module java pour la partie client (appel d'API externe)
* Module "java-spring-application-template" --> Module Java/spring de lancement d'une app de test


## :tools: Tools

### Generateur de code (via openapi-generator) 

Lien vers la liste des générateurs disponibles pour openapi-generator : https://openapi-generator.tech/docs/generators/

L'outil openapi-generator permet de génerer le code à la fois coté client et coté serveur.

La configuration se fait au niveau des fichiers maven lors de l'utilisation du plugin, exemple d'une utilisation :
```xml
  <plugin>
      <groupId>org.openapitools</groupId>
      <artifactId>openapi-generator-maven-plugin</artifactId>
      <version>${open-api-generator.version}</version>
      <executions>
          <execution>
              <id>generate-all-server</id>
              <phase>generate-sources</phase>
              <goals>
                  <goal>generate</goal>
              </goals>
              <configuration>
                  <inputSpec>${project.basedir}/../../_build/global.yml</inputSpec>
                  <!-- On valide les fichiers yaml -->
                  <!--<skipValidateSpec>true</skipValidateSpec>-->
                  <generatorName>spring</generatorName>
                  <configOptions>
                      <!-- config pour la generation de code -->
                      <delegatePattern>true</delegatePattern>
                      <dateLibrary>java8</dateLibrary>
                      <!-- config output generation -->
                      <sourceFolder>src/main/java</sourceFolder>
                      <apiPackage>fr.forge.openapi.specification.server.api</apiPackage>
                      <modelPackage>fr.forge.openapi.specification.server.model</modelPackage>
                  </configOptions>
              </configuration>
          </execution>
      </executions>
  </plugin>
```

### Configuration IDE (IntelliJ)

Plugin IntelliJ :
* [OpenAPI (Swagger) Editor](https://plugins.jetbrains.com/plugin/14837-openapi-swagger-editor)


## :whale2: Docker 

### Construction d'une image docker de l'application et utilisation en local

Le repo contient un fichier DockerFile permettant de créer une image docker de l'application java-template.
L'image est basé sur une image openjdk:17

**Commande Docker** :
```shell
# Construction de l'image docker 
sudo docker image build -t openapi-spring-application-template:dev . 

# Lancement de l'image docker (exposition sur le port 8090)
sudo docker run -d -p 8090:8090 --name openapi-spring-application-template openapi-spring-application-template:dev
```

Si tout est ok, le swagger de l'application est dispo : http://localhost:8090/swagger-ui/index.html
