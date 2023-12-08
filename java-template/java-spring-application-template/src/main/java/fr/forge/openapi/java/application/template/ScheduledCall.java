package fr.forge.openapi.java.application.template;

import fr.forge.openapi.java.client.service.FormationExterneService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class ScheduledCall {

    @Value("${externe.scheduler.enable}")
    private boolean isEnabled;

    private final FormationExterneService formationExterneService;

    private ScheduledCall(final FormationExterneService formationExterneService) {
        this.formationExterneService = formationExterneService;
    }

    @Scheduled(fixedDelayString = "${externe.scheduler.delay}")
    public void recuperationFormationProgrammee() {
        if(isEnabled) {
            formationExterneService.recupererFormationViaID(10);
        }
    }
}
