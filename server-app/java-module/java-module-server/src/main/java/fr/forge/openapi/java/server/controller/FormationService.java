package fr.forge.openapi.java.server.controller;


import fr.forge.openapi.specification.server.api.FormationApiDelegate;
import fr.forge.openapi.specification.server.model.Formation;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class FormationService implements FormationApiDelegate {

    private final ResourceLoader resourceLoader = new DefaultResourceLoader();

    /**
     * Reponse bouchonnee pour le service de recuperation d'une formation.
     *
     * @param idFormation Id de la formation a recuperer. Le champs est obligatoire. (required)
     */
    @Override
    public ResponseEntity<Formation> getFormation(Integer idFormation) {
        return new ResponseEntity<>(new Formation().intitule("Fake Formation"), HttpStatus.OK);
    }

    /**
     * Reponse bouchonnee pour le service de telechargement d'une formation.
     *
     * @param idFormation Id de la formation a recuperer. Le champs est obligatoire. (required)
     */
    @Override
    public ResponseEntity<org.springframework.core.io.Resource> getFormationFile(Integer idFormation) {
        try {
            Resource formationFile = loadFile(idFormation);
            return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                    "attachment; filename=\"" + formationFile.getFilename() + "\"")
                .body(formationFile);
        } catch (Throwable t) {
            throw new RuntimeException();
        }
    }

    private Resource loadFile(Integer idFormation){
        return resourceLoader.getResource("classpath:api/Formation_" + idFormation.toString() +".xml");
    }
}
