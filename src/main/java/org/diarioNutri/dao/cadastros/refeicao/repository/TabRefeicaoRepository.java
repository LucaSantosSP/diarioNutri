package org.diarioNutri.dao.cadastros.refeicao.repository;

import org.diarioNutri.dao.cadastros.refeicao.TabRefeicaoObj;
import org.diarioNutri.dao.cadastros.refeicaoalimento.TabRefeicaoAlimentoObj;
import org.diarioNutri.dao.cadastros.refeicaotipo.TabRefeicaoTipoObj;
import org.diarioNutri.dao.cadastros.usuario.TabUsuarioObj;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TabRefeicaoRepository extends JpaRepository<TabRefeicaoObj, Integer> {

    @Query("select t from TabRefeicaoObj t where t.tabUsuarioObj.cdUsuario = ?1")
    List<TabRefeicaoObj> findByCdUsuario(Integer cdUsuario );

    @Query("select t from TabRefeicaoObj t where t.tabUsuarioObj.cdUsuario = ?1 and t.dtRefeicao = CURRENT_DATE()")
    List<TabRefeicaoObj> findByCdUsuarioAndDtAtual(Integer cdUsuario);
}
