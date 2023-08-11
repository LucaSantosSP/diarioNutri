package org.diarioNutri.dao.cadastros.refeicaoalimento.repository;

import org.diarioNutri.dao.cadastros.refeicaoalimento.TabRefeicaoAlimentoObj;
import org.diarioNutri.dao.cadastros.refeicaotipo.TabRefeicaoTipoObj;
import org.diarioNutri.dao.cadastros.refeicaotipo.repository.TabRefeicaoTipoRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TabRefeicaoAlimentoRepository extends JpaRepository<TabRefeicaoAlimentoObj, Integer> {
}
