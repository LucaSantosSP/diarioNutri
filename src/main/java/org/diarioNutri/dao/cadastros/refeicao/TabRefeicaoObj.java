package org.diarioNutri.dao.cadastros.refeicao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.diarioNutri.dao.cadastros.usuario.TabUsuarioObj;

import java.util.Date;

@Getter
@Setter
@Entity
@Table( name = "tab_refeicao")
public class TabRefeicaoObj {

    @Column(name = "cd_refeicao")
    private Integer cdRefeicao;

    @Column(name = "cd_usuario")
    private TabUsuarioObj tabUsuarioObj;

    @Column(name = "dt_refeicao")
    private Date dtRefeicao;
}
