package org.diarioNutri.modulos.cadastros.refeicao.service.impl;

import org.diarioNutri.dao.cadastros.refeicao.DTO.TabRefeicaoDTO;
import org.diarioNutri.dao.cadastros.refeicao.TabRefeicaoObj;
import org.diarioNutri.dao.cadastros.refeicao.repository.TabRefeicaoRepository;
import org.diarioNutri.dao.cadastros.refeicaotipo.TabRefeicaoTipoObj;
import org.diarioNutri.dao.cadastros.refeicaotipo.repository.TabRefeicaoTipoRepository;
import org.diarioNutri.dao.cadastros.usuario.TabUsuarioObj;
import org.diarioNutri.dao.cadastros.usuario.repository.TabUsuarioRepository;
import org.diarioNutri.modulos.cadastros.refeicao.service.TabRefeicaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TabRefeicaoServiceImp implements TabRefeicaoService {
    @Autowired
    private TabRefeicaoRepository tabRefeicaoRepository;

    @Autowired
    private TabRefeicaoTipoRepository tabRefeicaoTipoRepository;

    @Autowired
    private TabUsuarioRepository tabUsuarioRepository;

    @Override
    public TabRefeicaoObj gravar(TabRefeicaoDTO tabRefeicaoDTO) {
        TabRefeicaoObj tabRefeicaoObj = new TabRefeicaoObj();
        TabUsuarioObj tabUsuarioObj =
                tabRefeicaoDTO.getCdUsuario() != null ? tabUsuarioRepository.findByCdUsuario(tabRefeicaoDTO.getCdUsuario()) : null;
        TabRefeicaoTipoObj tabRefeicaoTipoObj =
                tabRefeicaoDTO.getCdRefeicaoTipo() != null ? tabRefeicaoTipoRepository.findByCdRefeicaoTipo(tabRefeicaoDTO.getCdRefeicaoTipo()) : null;

        tabRefeicaoObj.setTabUsuarioObj(tabUsuarioObj);
        tabRefeicaoObj.setTxRefeicao(tabRefeicaoTipoObj != null ? tabRefeicaoObj.getTxRefeicao() : tabRefeicaoDTO.getTxRefeicao());
        tabRefeicaoObj.setDtRefeicao(LocalDate.now());
        tabRefeicaoObj.setTabRefeicaoTipoObj(tabRefeicaoTipoObj);
        tabRefeicaoObj = tabRefeicaoRepository.save(tabRefeicaoObj);
        return tabRefeicaoObj;
    }

    @Override
    public Optional<TabRefeicaoObj> encontrarPorCdRefeicao(Integer cdRefeicao) {
        return tabRefeicaoRepository.findById(cdRefeicao);
    }

    @Override
    public List<TabRefeicaoObj> encontrarTodos() {
        return tabRefeicaoRepository.findAll();
    }
}
