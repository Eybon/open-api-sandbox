package fr.forge.openapi.java.client.service;

import fr.forge.openapi.java.client.configuration.FormationExterneConfiguration;
import fr.forge.openapi.specification.client.api.FormationApi;
import fr.forge.openapi.specification.client.model.Formation;
import org.springframework.stereotype.Service;

@Service
public class FormationExterneService {

    private final FormationApi formationApi;
    private final FormationExterneConfiguration formationExterneConfiguration;

    private FormationExterneService(final FormationApi formationApiApi,
                                    final FormationExterneConfiguration formationExterneConfiguration) {
        this.formationApi = formationApiApi;
        this.formationExterneConfiguration = formationExterneConfiguration;
        init();
    }

    private void init() {
        // Modification de l'url de sortie
        formationApi.getApiClient().setBasePath(formationExterneConfiguration.getUrl());
    }

    public void recupererFormationViaID(Integer idFormation) {
        try {
            Formation result = formationApi.getFormation(idFormation);
            System.out.println("\nResultat de la recuperation de la formation : \n " + result.toString());
        } catch (Exception e) {
            System.out.println("\nEchec lors de l'appel au endpoint : " + formationApi.getApiClient().getBasePath());
        }
    }

}
