package org.diarioNutri.modulos.cadastros.refeicaoalimento;

import org.diarioNutri.dao.cadastros.refeicaoalimento.TabRefeicaoAlimentoObj;
import org.diarioNutri.dao.cadastros.refeicaoalimento.repository.TabRefeicaoAlimentoRepository;
import org.diarioNutri.dao.cadastros.refeicaotipo.TabRefeicaoTipoObj;
import org.diarioNutri.dao.cadastros.refeicaotipo.repository.TabRefeicaoTipoRepository;
import org.diarioNutri.dao.cadastros.usuario.TabUsuarioObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TabRefeicaoAlimentoService {

    @Autowired
    private TabRefeicaoAlimentoRepository tabRefeicaoAlimentoRepository;

    public TabRefeicaoAlimentoObj gravar (TabRefeicaoAlimentoObj tabRefeicaoAlimentoObj){
        return tabRefeicaoAlimentoRepository.save(tabRefeicaoAlimentoObj);
    }

    public Optional<TabRefeicaoAlimentoObj> encontrarPorCdRefeicaoAlimento(Integer cdRefeicaoAlimento){
        return tabRefeicaoAlimentoRepository.findById(cdRefeicaoAlimento);
    }

    public List<TabRefeicaoAlimentoObj> encontrarTodos(){
        return tabRefeicaoAlimentoRepository.findAll();
    }

    public List<TabRefeicaoAlimentoObj> findByCdUsuario(Integer cdUsuario){
        return tabRefeicaoAlimentoRepository.findByCdUsuario(cdUsuario);
    }

    public Boolean excluir(TabRefeicaoAlimentoObj tabRefeicaoAlimentoObj){
        tabRefeicaoAlimentoRepository.delete(tabRefeicaoAlimentoObj);
        return true;
    }

    public List<TabRefeicaoAlimentoObj> findRefeicaoAlimento(Integer cdUsuario, Integer cdRefeicao){
        return tabRefeicaoAlimentoRepository.findRefeicaoAlimentoByUsuario(cdUsuario, cdRefeicao);
    }

    public List<TabRefeicaoAlimentoObj> findByCdUsuarioAndDay(Integer cdUsuario){
        return tabRefeicaoAlimentoRepository.findByCdUsuarioAndDay(cdUsuario);
    }

}
