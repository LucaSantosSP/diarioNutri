package org.diarioNutri.modulos.cadastros.refeicaotipo.service;

import org.diarioNutri.dao.cadastros.refeicaotipo.TabRefeicaoTipoObj;
import org.diarioNutri.dao.cadastros.refeicaotipo.repository.TabRefeicaoTipoRepository;
import org.diarioNutri.dao.cadastros.usuario.TabUsuarioObj;
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

    public List<TabRefeicaoTipoObj> findByCdUsuario(Integer cdUsuario){
        return tabRefeicaoTipoRepository.findByCdUsuario(cdUsuario);
    }

    public Boolean excluir(TabRefeicaoTipoObj tabRefeicaoTipoObj){
        tabRefeicaoTipoRepository.delete(tabRefeicaoTipoObj);
        return true;
    }

    public void refeicoesPadrao(TabUsuarioObj tabUsuarioObj){
        TabRefeicaoTipoObj tabCafeDaManhaObj = new TabRefeicaoTipoObj();
        tabCafeDaManhaObj.setTxRefeicaoTipo("Café da manhã");
        tabCafeDaManhaObj.setTabUsuarioObj(tabUsuarioObj);

        tabRefeicaoTipoRepository.save(tabCafeDaManhaObj);

        TabRefeicaoTipoObj tabAlmocoObj = new TabRefeicaoTipoObj();
        tabAlmocoObj.setTxRefeicaoTipo("Almoço");
        tabAlmocoObj.setTabUsuarioObj(tabUsuarioObj);

        tabRefeicaoTipoRepository.save(tabAlmocoObj);

        TabRefeicaoTipoObj tabJantaObj = new TabRefeicaoTipoObj();
        tabJantaObj.setTxRefeicaoTipo("Janta");
        tabJantaObj.setTabUsuarioObj(tabUsuarioObj);

        tabRefeicaoTipoRepository.save(tabJantaObj);

    }
}
