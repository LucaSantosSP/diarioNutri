package org.diarioNutri.dao.cadastros.refeicaoalimento;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.diarioNutri.dao.cadastros.alimento.TabAlimentoObj;
import org.diarioNutri.dao.cadastros.refeicao.TabRefeicaoObj;
import org.diarioNutri.dao.cadastros.usuario.TabUsuarioObj;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Setter
@Getter
@Entity
@Table(name = "tab_refeicao_alimento")
public class TabRefeicaoAlimentoObj {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cd_refeicao_alimento")
    private Integer cdRefeicaoAlimento;

    @JdbcTypeCode(SqlTypes.JSON)
    @ManyToOne
    @JoinColumn(name = "cd_alimento")
    private TabAlimentoObj tabAlimentoObj;

    @JdbcTypeCode(SqlTypes.JSON)
    @ManyToOne
    @JoinColumn(name = "cd_refeicao")
    private TabRefeicaoObj tabRefeicaoObj;

    @JdbcTypeCode(SqlTypes.JSON)
    @ManyToOne
    @JoinColumn(name = "cd_usuario")
    private TabUsuarioObj tabUsuarioObj;
}
