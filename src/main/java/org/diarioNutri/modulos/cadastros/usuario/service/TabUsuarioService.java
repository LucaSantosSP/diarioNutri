package org.diarioNutri.modulos.cadastros.usuario.service;

import org.diarioNutri.dao.cadastros.refeicao.repository.TabRefeicaoRepository;
import org.diarioNutri.dao.cadastros.refeicaotipo.repository.TabRefeicaoTipoRepository;
import org.diarioNutri.dao.cadastros.usuario.TabUsuarioObj;
import org.diarioNutri.dao.cadastros.usuario.repository.TabUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TabUsuarioService {

    @Autowired
    private TabUsuarioRepository tabUsuarioRepository;
    @Autowired
    private TabRefeicaoTipoRepository tabRefeicaoTipoRepository;
    @Autowired
    private TabRefeicaoRepository tabRefeicaoRepository;

    public TabUsuarioObj salvar(TabUsuarioObj tabUsuarioObj){
        TabUsuarioObj newTabUsuarioObj = tabUsuarioRepository.save(tabUsuarioObj);
        return newTabUsuarioObj;
    }

    public Optional<TabUsuarioObj> encontrarUsuario (Integer cdUsuario){
        Optional<TabUsuarioObj> tabUsuarioObj = tabUsuarioRepository.findById(cdUsuario);
        return tabUsuarioObj;
    }

    public Optional<List<TabUsuarioObj>> encontraUsuarios (){
        Optional<List<TabUsuarioObj>> tabUsuarioObjList = Optional.of(tabUsuarioRepository.findAll());
        return tabUsuarioObjList;
    }
}
