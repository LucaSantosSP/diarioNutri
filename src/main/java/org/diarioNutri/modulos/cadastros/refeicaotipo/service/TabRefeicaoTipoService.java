package org.diarioNutri.modulos.cadastros.refeicaotipo.service;

import org.diarioNutri.dao.cadastros.refeicaotipo.TabRefeicaoTipoObj;
import org.diarioNutri.dao.cadastros.refeicaotipo.repository.TabRefeicaoTipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TabRefeicaoTipoService {

    @Autowired
    private TabRefeicaoTipoRepository tabRefeicaoTipoRepository;

    public TabRefeicaoTipoObj gravar (TabRefeicaoTipoObj tabRefeicaoTipoObj){
        return tabRefeicaoTipoRepository.save(tabRefeicaoTipoObj);
    }

    public Optional<TabRefeicaoTipoObj> encontrarPorCdTipoRefeicao(Integer cdRefeicaoTipo){
        return tabRefeicaoTipoRepository.findById(cdRefeicaoTipo);
    }

    public List<TabRefeicaoTipoObj> encontrarTodos(){
        return tabRefeicaoTipoRepository.findAll();
    }

    public Boolean excluir(TabRefeicaoTipoObj tabRefeicaoTipoObj){
        tabRefeicaoTipoRepository.delete(tabRefeicaoTipoObj);
        return true;
    }
}
