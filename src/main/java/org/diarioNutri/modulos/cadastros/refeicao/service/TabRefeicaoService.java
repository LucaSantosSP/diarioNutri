package org.diarioNutri.modulos.cadastros.refeicao.service;

import org.diarioNutri.dao.cadastros.refeicao.DTO.TabRefeicaoDTO;
import org.diarioNutri.dao.cadastros.refeicao.TabRefeicaoObj;
import org.diarioNutri.dao.cadastros.refeicao.repository.TabRefeicaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface TabRefeicaoService {

    TabRefeicaoObj gravar (TabRefeicaoDTO tabRefeicaoDTO);

    /*public Optional<TabRefeicaoObj> encontrarPorCdRefeicao (Integer cdRefeicao){
        return tabRefeicaoRepository.findById(cdRefeicao);
    }

    public List<TabRefeicaoObj> encontrarTodos (){
        return tabRefeicaoRepository.findAll();
    }

    public Boolean deletar (TabRefeicaoObj tabRefeicaoObj) {
        tabRefeicaoRepository.delete(tabRefeicaoObj);
        return true;
    }*/

}
