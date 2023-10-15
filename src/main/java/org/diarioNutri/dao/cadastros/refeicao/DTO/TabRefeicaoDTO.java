package org.diarioNutri.dao.cadastros.refeicao.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.diarioNutri.dao.cadastros.refeicaotipo.TabRefeicaoTipoObj;
import org.diarioNutri.dao.cadastros.usuario.TabUsuarioObj;
import java.time.LocalDate;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TabRefeicaoDTO {

    @NotNull(message = "Campo usuário é obrigatório!")
    private Integer cdUsuario;
    @NotNull(message = "Campo tipo de refeição é obrigatório!")
    private Integer cdRefeicaoTipo;
    private String txRefeicao;
    private LocalDate dtRefeicao;
}
