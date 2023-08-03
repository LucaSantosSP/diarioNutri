package org.diarioNutri;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DiarioNutriApplication {

    public String applicationName(){
        return "Diario Nutricional";
    }

    @GetMapping("/hello")
    public String helloWorld(){
        return applicationName();
    }
    public static void main(String[] args) {
        SpringApplication.run(DiarioNutriApplication.class, args);
    }
}