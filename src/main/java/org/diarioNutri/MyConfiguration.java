package org.diarioNutri;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class MyConfiguration {

    @Value("${application.name}")
    private String ambiente;

    @Bean
    public CommandLineRunner executar(){
        return args -> {
            System.out.println("---------------------------Rodando " + ambiente + "!---------------------------");
        };
    }


}
