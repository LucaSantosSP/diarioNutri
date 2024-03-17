package org.diarioNutri.dao.cadastros.refeicao;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.diarioNutri.dao.cadastros.refeicaotipo.TabRefeicaoTipoObj;
import org.diarioNutri.dao.cadastros.usuario.TabUsuarioObj;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table( name = "tab_refeicao")
public class TabRefeicaoObj {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cd_refeicao")
    private Integer cdRefeicao;

    @Column(name = "tx_refeicao")
    private String txRefeicao;

    @ManyToOne
    @JoinColumn(name = "cd_usuario")
    private TabUsuarioObj tabUsuarioObj;

    @Column(name = "dt_refeicao")
    private LocalDate dtRefeicao;

    @ManyToOne
    @JoinColumn(name = "cd_refeicao_tipo")
    private TabRefeicaoTipoObj tabRefeicaoTipoObj;

    @Column(name = "tx_icon")
    private String txIcon;

    @Column(name = "dt_hora_refeicao")
    private LocalTime dtHoraRefeicao;
}
