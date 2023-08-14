package org.diarioNutri.modulos.cadastros.usuario.service;

import org.diarioNutri.dao.cadastros.refeicao.TabRefeicaoObj;
import org.diarioNutri.dao.cadastros.refeicao.repository.TabRefeicaoRepository;
import org.diarioNutri.dao.cadastros.refeicaotipo.TabRefeicaoTipoObj;
import org.diarioNutri.dao.cadastros.refeicaotipo.repository.TabRefeicaoTipoRepository;
import org.diarioNutri.dao.cadastros.usuario.TabUsuarioObj;
import org.diarioNutri.dao.cadastros.usuario.repository.TabUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cglib.core.Local;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class TabUsuarioService {

    @Autowired
    private TabUsuarioRepository tabUsuarioRepository;
    @Autowired
    private TabRefeicaoTipoRepository tabRefeicaoTipoRepository;
    @Autowired
    private TabRefeicaoRepository tabRefeicaoRepository;
    @Bean
    public CommandLineRunner init() {
        return args -> {
            System.out.println("SALVANDO USUARIO");
            TabUsuarioObj lucasUsuario = new TabUsuarioObj();
            lucasUsuario.setTxUsuario("Lucas");
            lucasUsuario.setTxEmail("lucas-santos@hotmil.com");
            lucasUsuario.setTxSenha("senh001");
            lucasUsuario.setTxFoto(null);
            lucasUsuario.setCkSexo(1);
            lucasUsuario.setDtNascimento(new Date());
            lucasUsuario.setVlAltura(1.80);
            lucasUsuario.setVlPeso(86.00);
            tabUsuarioRepository.save(lucasUsuario);

            TabRefeicaoTipoObj tipoRefeicao = new TabRefeicaoTipoObj();
            tipoRefeicao.setTxRefeicaoTipo("Café da manhã");
            tabRefeicaoTipoRepository.save(tipoRefeicao);

            TabRefeicaoObj refeicao = new TabRefeicaoObj();
            refeicao.setTabRefeicaoTipoObj(tipoRefeicao);
            refeicao.setTxRefeicao(tipoRefeicao.getTxRefeicaoTipo());
            refeicao.setTabUsuarioObj(lucasUsuario);
            refeicao.setDtRefeicao(LocalDate.now());
            tabRefeicaoRepository.save(refeicao);

            List<TabUsuarioObj> listTabUsuarioObj = tabUsuarioRepository.findAll();
            listTabUsuarioObj.forEach(System.out::println);


        };
    }

    public void validarUsuario(TabUsuarioObj tabUsuarioObj){
        //apaga validações
    }
}
