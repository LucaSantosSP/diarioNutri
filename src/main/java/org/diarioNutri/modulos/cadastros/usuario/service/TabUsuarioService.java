package org.diarioNutri.modulos.cadastros.usuario.service;

import org.diarioNutri.dao.cadastros.usuario.TabUsuarioObj;
import org.diarioNutri.dao.cadastros.usuario.repository.TabUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TabUsuarioService {

    @Autowired
    private TabUsuarioRepository tabUsuarioRepository;

    public void salvaUsuario(TabUsuarioObj tabUsuarioObj){
        validarUsuario(tabUsuarioObj);
        tabUsuarioRepository.salvar(tabUsuarioObj);
    }

    public void validarUsuario(TabUsuarioObj tabUsuarioObj){
        //apaga validações
    }
}
