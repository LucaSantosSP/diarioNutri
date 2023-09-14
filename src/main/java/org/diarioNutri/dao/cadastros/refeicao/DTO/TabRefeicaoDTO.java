package org.diarioNutri.dao.cadastros.refeicao.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.diarioNutri.dao.cadastros.refeicaotipo.TabRefeicaoTipoObj;
import org.diarioNutri.dao.cadastros.usuario.TabUsuarioObj;
import java.time.LocalDate;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TabRefeicaoDTO {
    private Integer cdUsuario;
    private Integer cdRefeicaoTipo;
    private String txRefeicao;
    private LocalDate dtRefeicao;
}
