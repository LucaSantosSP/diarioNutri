package org.diarioNutri.dao.cadastros.refeicao;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TabRefeicaoObj {
    private Integer cdRefeicao;
    private Integer cdUsuario;
    private Date dtRefeicao;
}
