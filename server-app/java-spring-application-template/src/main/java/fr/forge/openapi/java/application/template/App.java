package fr.forge.openapi.java.application.template;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "fr.forge.openapi")
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class);
        System.out.println("Démarrage App Server !");
    }
}
