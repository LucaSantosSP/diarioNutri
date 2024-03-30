package org.diarioNutri.dao.cadastros.alimento.repository;

import org.diarioNutri.dao.cadastros.alimento.TabAlimentoObj;
import org.diarioNutri.dao.cadastros.refeicao.TabRefeicaoObj;
import org.diarioNutri.dao.cadastros.refeicaoalimento.TabRefeicaoAlimentoObj;
import org.diarioNutri.dao.cadastros.refeicaotipo.TabRefeicaoTipoObj;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TabAlimentoRepository extends JpaRepository<TabAlimentoObj, Integer> {

    @Query("select t from TabAlimentoObj t where t.tabUsuarioObj.cdUsuario = ?1")
    List<TabAlimentoObj> findByCdUsuario(Integer cdUsuario );

    @Query("select t from TabAlimentoObj t where t.tabUsuarioObj.cdUsuario = ?1 or t.tabUsuarioObj.cdUsuario is null order by t.txAliemnto asc")
    List<TabAlimentoObj> findAllDesc(Integer cdUsuario);

    @Query("select t from TabAlimentoObj t where (t.tabUsuarioObj.cdUsuario = ?1 or t.tabUsuarioObj.cdUsuario is null) " +
            "and t.txAliemnto like %?2% order by t.txAliemnto asc")
    List<TabAlimentoObj> findByCdUsuarioByPesquisa(Integer cdUsuario, String txAlimento);

}
