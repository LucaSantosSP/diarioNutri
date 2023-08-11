package org.diarioNutri.dao.cadastros.refeicaotipo.repository;

import org.diarioNutri.dao.cadastros.refeicaotipo.TabRefeicaoTipoObj;
import org.diarioNutri.dao.cadastros.usuario.TabUsuarioObj;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TabRefeicaoTipoRepository extends JpaRepository<TabRefeicaoTipoObj, Integer> {
}
