package org.diarioNutri.dao.cadastros.alimento;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.diarioNutri.dao.cadastros.usuario.TabUsuarioObj;

@Getter
@Setter
@Entity
@Table(name = "tab_alimento")
public class TabAlimentoObj {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cd_alimento")
    private Integer cdAlimento;

    @Column(name = "tx_alimento")
    @NotEmpty(message = "Campo nome do alimento é obrigatório!")
    private String txAliemnto;

    @Column(name = "tx_grupo")
    @NotEmpty(message = "Campo nome do alimento é obrigatório!")
    private String txGrupo;

    @Column(name = "vl_kcal", length = 3)
    @NotNull(message = "Campo caloria é obrigatório!")
    private Integer vlKcal;

    @Column(name = "vl_proteina", length = 11, precision = 2)
    @NotNull(message = "Campo proteina é obrigatório!")
    private String vlProteina;

    @Column(name = "vl_carboidrato", length = 11, precision = 2)
    @NotNull(message = "Campo carboidrato é obrigatório!")
    private String vlCarboidrato;

    @Column(name = "vl_gordura", length = 11, precision = 2)
    @NotNull(message = "Campo gordura é obrigatório!")
    private String vlGordura;

    @Column(name = "vl_umidade")
    @NotNull(message = "Campo ml água é obrigatório!")
    private String vlUmidade;

    @ManyToOne
    @JoinColumn(name = "cd_usuario")
    private TabUsuarioObj tabUsuarioObj;

    @Column(name = "vl_fibra_alimentar")
    @NotNull(message = "Campo fibra alimentar é obrigatório!")
    private String vlFibraAlimentar;

    @Column(name = "vl_colesterol")
    @NotNull(message = "Campo colesterol é obrigatório!")
    private String vlColesterol;

    @Column(name = "vl_sodio")
    @NotNull(message = "Campo sódio é obrigatório!")
    private String vlSodio;
}
