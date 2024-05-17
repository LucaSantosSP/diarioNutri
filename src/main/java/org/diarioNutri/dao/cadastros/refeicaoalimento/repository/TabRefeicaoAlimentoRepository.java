package org.diarioNutri.dao.cadastros.refeicaoalimento.repository;

import org.diarioNutri.dao.cadastros.refeicaoalimento.TabRefeicaoAlimentoObj;
import org.diarioNutri.dao.cadastros.refeicaotipo.TabRefeicaoTipoObj;
import org.diarioNutri.dao.cadastros.refeicaotipo.repository.TabRefeicaoTipoRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TabRefeicaoAlimentoRepository extends JpaRepository<TabRefeicaoAlimentoObj, Integer> {

    @Query("select t from TabRefeicaoAlimentoObj t where t.tabUsuarioObj.cdUsuario = ?1")
    List<TabRefeicaoAlimentoObj> findByCdUsuario(Integer cdUsuario );

    @Query("select t from TabRefeicaoAlimentoObj t where t.tabUsuarioObj.cdUsuario = ?1 and t.tabRefeicaoObj.cdRefeicao = ?2")
    List<TabRefeicaoAlimentoObj> findRefeicaoAlimentoByUsuario(Integer cdUsuario, Integer cdRefeicao);

    @Query("select t from TabRefeicaoAlimentoObj t where t.tabUsuarioObj.cdUsuario = ?1 and t.tabRefeicaoObj.dtRefeicao = CURRENT_DATE()")
    List<TabRefeicaoAlimentoObj> findByCdUsuarioAndDay(Integer cdUsuario);

    @Query("select t from TabRefeicaoAlimentoObj t where t.tabUsuarioObj.cdUsuario = ?1 and t.tabRefeicaoObj.dtRefeicao = ?2")
    List<TabRefeicaoAlimentoObj> findByCdUsuarioAndDate(Integer cdUsuario, LocalDate dtRefeicao);
}
