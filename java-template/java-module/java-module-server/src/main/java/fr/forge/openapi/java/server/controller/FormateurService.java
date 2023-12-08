package fr.forge.openapi.java.server.controller;


import fr.forge.openapi.specification.server.api.FormateurApiDelegate;
import fr.forge.openapi.specification.server.api.FormationApiDelegate;
import fr.forge.openapi.specification.server.model.Formateur;
import fr.forge.openapi.specification.server.model.Formation;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class FormateurService implements FormateurApiDelegate {

    /**
     * Reponse bouchonnee pour le service de recuperation d'un formateur'.
     *
     * @param idFormateur Id du formateur Le champs est obligatoire
     */
    @Override
    public ResponseEntity<Formateur> getFormateur(Integer idFormateur) {
        return new ResponseEntity<>(new Formateur().nom("Dumont").prenom("Michel"), HttpStatus.OK);
    }
}
