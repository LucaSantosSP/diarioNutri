package org.diarioNutri.dao.cadastros.receita.repository;

import org.diarioNutri.dao.cadastros.alimento.TabAlimentoObj;
import org.diarioNutri.dao.cadastros.receita.TabReceitaObj;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TabReceitaRepository extends JpaRepository<TabReceitaObj, Integer> {

    @Query("select t from TabReceitaObj t where t.tabUsuarioObj.cdUsuario = ?1")
    List<TabReceitaObj> findByCdUsuario(Integer cdUsuario );

    @Query("select t from TabReceitaObj t where t.tabUsuarioObj.cdUsuario = ?1 or t.tabUsuarioObj.cdUsuario is null order by t.txTitulo asc")
    List<TabReceitaObj> findAllDesc(Integer cdUsuario);

    @Query("select t from TabReceitaObj t where (t.tabUsuarioObj.cdUsuario = ?1 or t.tabUsuarioObj.cdUsuario is null) " +
            "and t.txTitulo like %?2% order by t.txTitulo asc")
    List<TabReceitaObj> findByCdUsuarioByPesquisa(Integer cdUsuario, String txTitulo);

}
