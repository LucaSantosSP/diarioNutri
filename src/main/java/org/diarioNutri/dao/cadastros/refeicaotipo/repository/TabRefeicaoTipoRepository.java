package org.diarioNutri.dao.cadastros.refeicaotipo.repository;

import org.diarioNutri.dao.cadastros.refeicaotipo.TabRefeicaoTipoObj;
import org.diarioNutri.dao.cadastros.usuario.TabUsuarioObj;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TabRefeicaoTipoRepository extends JpaRepository<TabRefeicaoTipoObj, Integer> {
    TabRefeicaoTipoObj findByCdRefeicaoTipo( Integer cdRefeicaoTipo );

    @Query("select t from TabRefeicaoTipoObj t where t.tabUsuarioObj.cdUsuario = ?1")
    List<TabRefeicaoTipoObj> findByCdUsuario(Integer cdUsuario );
}
