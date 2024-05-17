package org.diarioNutri.modulos.cadastros.receita.service;

import org.diarioNutri.dao.cadastros.receita.TabReceitaObj;
import org.diarioNutri.dao.cadastros.receita.repository.TabReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TabReceitaService {

    @Autowired
    private TabReceitaRepository tabReceitaRepository;

    public TabReceitaObj gravar (TabReceitaObj tabReceitaObj){
        return tabReceitaRepository.save(tabReceitaObj);
    }

    public Optional<TabReceitaObj> encontrarPorCdReceita(Integer cdReceita){
        return tabReceitaRepository.findById(cdReceita);
    }

    public List<TabReceitaObj> encontrarTodos(Integer cdReceita){
        return tabReceitaRepository.findAllDesc(cdReceita);
    }

    public List<TabReceitaObj> findByCdUsuario(Integer cdUsuario){
        return tabReceitaRepository.findByCdUsuario(cdUsuario);
    }

    public List<TabReceitaObj> encontrarTodosByPesquisa(Integer cdUsuario, String txTitulo){
        return tabReceitaRepository.findByCdUsuarioByPesquisa(cdUsuario, txTitulo);
    }

    public Boolean excluir(TabReceitaObj tabReceitaObj){
        tabReceitaRepository.delete(tabReceitaObj);
        return true;
    }

}
