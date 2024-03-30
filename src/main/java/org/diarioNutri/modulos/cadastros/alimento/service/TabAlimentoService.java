package org.diarioNutri.modulos.cadastros.alimento.service;

import org.diarioNutri.dao.cadastros.alimento.TabAlimentoObj;
import org.diarioNutri.dao.cadastros.alimento.repository.TabAlimentoRepository;
import org.diarioNutri.dao.cadastros.refeicaotipo.TabRefeicaoTipoObj;
import org.diarioNutri.dao.cadastros.refeicaotipo.repository.TabRefeicaoTipoRepository;
import org.diarioNutri.dao.cadastros.usuario.TabUsuarioObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TabAlimentoService {

    @Autowired
    private TabAlimentoRepository tabAlimentoRepository;

    public TabAlimentoObj gravar (TabAlimentoObj tabAlimentoObj){
        return tabAlimentoRepository.save(tabAlimentoObj);
    }

    public Optional<TabAlimentoObj> encontrarPorCdAlimento(Integer cdAlimento){
        return tabAlimentoRepository.findById(cdAlimento);
    }

    public List<TabAlimentoObj> encontrarTodos(Integer cdUsuario){
        return tabAlimentoRepository.findAllDesc(cdUsuario);
    }

    public List<TabAlimentoObj> findByCdUsuario(Integer cdUsuario){
        return tabAlimentoRepository.findByCdUsuario(cdUsuario);
    }

    public List<TabAlimentoObj> encontrarTodosByPesquisa(Integer cdUsuario, String txAlimento){
        return tabAlimentoRepository.findByCdUsuarioByPesquisa(cdUsuario, txAlimento);
    }

    public Boolean excluir(TabAlimentoObj tabAlimentoObj){
        tabAlimentoRepository.delete(tabAlimentoObj);
        return true;
    }

}
