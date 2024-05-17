package org.diarioNutri.modulos.cadastros.refeicao.service;

import org.diarioNutri.dao.cadastros.refeicao.DTO.TabRefeicaoDTO;
import org.diarioNutri.dao.cadastros.refeicao.TabRefeicaoObj;
import org.diarioNutri.dao.cadastros.refeicaotipo.TabRefeicaoTipoObj;
import org.diarioNutri.dao.cadastros.usuario.TabUsuarioObj;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TabRefeicaoService {

    TabRefeicaoObj gravar (TabRefeicaoDTO tabRefeicaoDTO);

    Optional<TabRefeicaoObj> encontrarPorCdRefeicao (Integer cdRefeicao);

    List<TabRefeicaoObj> encontrarTodos();

    TabRefeicaoObj salvar (TabRefeicaoObj tabRefeicaoObj);

    Boolean deletar (TabRefeicaoObj tabRefeicaoObj);

    Boolean criarNovaRefeicao(TabUsuarioObj tabUsuarioObj);

    List<TabRefeicaoObj> refeicaoDiaria (Integer cdUsuario);

    List<TabRefeicaoObj> findRefeicaoByData (Integer cdUsuario, LocalDate dtRefeicao);
}
