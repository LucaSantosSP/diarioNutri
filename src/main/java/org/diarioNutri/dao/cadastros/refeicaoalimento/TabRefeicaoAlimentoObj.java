package org.diarioNutri.dao.cadastros.refeicaoalimento;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.diarioNutri.dao.cadastros.alimento.TabAlimentoObj;
import org.diarioNutri.dao.cadastros.refeicao.TabRefeicaoObj;
import org.diarioNutri.dao.cadastros.usuario.TabUsuarioObj;

@Setter
@Getter
@Entity
@Table(name = "tab_refeicao_alimento")
public class TabRefeicaoAlimentoObj {

    @Column(name = "cd_refeicao_alimento")
    private Integer cdRefeicaoAlimento;

    @Column(name = "cd_alimento")
    private TabAlimentoObj tabAlimentoObj;

    @Column(name = "cd_refeicao")
    private TabRefeicaoObj tabRefeicaoObj;

    @Column(name = "cd_usuario")
    private TabUsuarioObj tabUsuarioObj;
}
