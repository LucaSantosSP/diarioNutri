package org.diarioNutri.dao.cadastros.refeicao;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.diarioNutri.dao.cadastros.usuario.TabUsuarioObj;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Entity
@Table( name = "tab_refeicao")
public class TabRefeicaoObj {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cd_refeicao")
    private Integer cdRefeicao;

    @JdbcTypeCode(SqlTypes.JSON)
    @ManyToOne
    @JoinColumn(name = "cd_usuario")
    private TabUsuarioObj tabUsuarioObj;

    @Column(name = "dt_refeicao")
    private LocalDate dtRefeicao;
}
