package org.diarioNutri.dao.cadastros.refeicaotipo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.diarioNutri.dao.cadastros.refeicao.TabRefeicaoObj;
import org.diarioNutri.dao.cadastros.usuario.TabUsuarioObj;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.boot.autoconfigure.web.WebProperties;

@Getter
@Setter
@Entity
@Table(name = "tab_refeicao_tipo")
public class TabRefeicaoTipoObj {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cd_refeicao_tipo")
    private Integer cdRefeicaoTipo;

    @Column(name = "tx_refeicao_tipo", length = 30)
    @NotEmpty(message = "Campo nome da refeição é obrigatório!")
    private String txRefeicaoTipo;


}
