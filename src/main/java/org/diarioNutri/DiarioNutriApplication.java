package org.diarioNutri;

import org.diarioNutri.dao.cadastros.usuario.TabUsuarioObj;
import org.diarioNutri.dao.cadastros.usuario.repository.TabUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@SpringBootApplication
@RestController
public class DiarioNutriApplication {

    @Bean
    public CommandLineRunner init(@Autowired TabUsuarioRepository tabUsuarioRepository) {
        return args -> {
            System.out.println("SALVANDO USUARIO");
            tabUsuarioRepository.save(new TabUsuarioObj("Lucas", "lucas-santos@hotmil.com", "senh001", null, 1, new Date(), 1.80, 90.00));
            tabUsuarioRepository.save(new TabUsuarioObj("Nathalia"));


            List<TabUsuarioObj> listTabUsuarioObj = tabUsuarioRepository.findAll();
            listTabUsuarioObj.forEach(System.out::println);

            System.out.println("PESQUISANDO USUARIO");
            tabUsuarioRepository.findByTxUsuarioLike("tha").forEach(System.out::println);

            System.out.println("ATUALIZANDO USUARIO");
            listTabUsuarioObj.forEach(c -> {
                c.setTxUsuario(c.getTxUsuario() + " atualizado.");
                tabUsuarioRepository.save(c);
            });

            listTabUsuarioObj = tabUsuarioRepository.findAll();
            listTabUsuarioObj.forEach(System.out::println);

            System.out.println("DELETANDO USUARIO");
            tabUsuarioRepository.findAll().forEach(c -> {
                tabUsuarioRepository.delete(c);
            });
            listTabUsuarioObj = tabUsuarioRepository.findAll();
            if(listTabUsuarioObj.isEmpty()){
                System.out.println("Nenhum Usuario encontrado!");
            }else{
                listTabUsuarioObj.forEach(System.out::println);
            }
        };
    }
    public static void main(String[] args) {
        SpringApplication.run(DiarioNutriApplication.class, args);
    }
}