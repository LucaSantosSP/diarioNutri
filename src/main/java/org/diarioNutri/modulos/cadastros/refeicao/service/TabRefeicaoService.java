package org.diarioNutri.modulos.cadastros.refeicao.service;

import org.diarioNutri.dao.cadastros.refeicao.DTO.TabRefeicaoDTO;
import org.diarioNutri.dao.cadastros.refeicao.TabRefeicaoObj;

import java.util.List;
import java.util.Optional;

public interface TabRefeicaoService {

    TabRefeicaoObj gravar (TabRefeicaoDTO tabRefeicaoDTO);

    Optional<TabRefeicaoObj> encontrarPorCdRefeicao (Integer cdRefeicao);

    List<TabRefeicaoObj> encontrarTodos();

    /*

    public Boolean deletar (TabRefeicaoObj tabRefeicaoObj) {
        tabRefeicaoRepository.delete(tabRefeicaoObj);
        return true;
    }*/

}
