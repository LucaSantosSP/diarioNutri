package org.diarioNutri.modulos.cadastros.usuario.service;

import org.diarioNutri.dao.cadastros.usuario.TabUsuarioObj;
import org.diarioNutri.dao.cadastros.usuario.repository.TabUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TabUsuarioService {

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

    public void validarUsuario(TabUsuarioObj tabUsuarioObj){
        //apaga validações
    }
}
