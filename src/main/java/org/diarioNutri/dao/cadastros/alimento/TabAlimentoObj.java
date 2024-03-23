package org.diarioNutri.dao.cadastros.alimento;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

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

    @Column(name = "vl_kcal", length = 3)
    @NotNull(message = "Campo caloria é obrigatório!")
    private Integer vlKcal;

    @Column(name = "vl_proteina", length = 11, precision = 2)
    @NotNull(message = "Campo proteina é obrigatório!")
    private double vlProteina;

    @Column(name = "vl_carboidrato", length = 11, precision = 2)
    @NotNull(message = "Campo carboidrato é obrigatório!")
    private double vlCarboidrato;

    @Column(name = "vl_gordura", length = 11, precision = 2)
    @NotNull(message = "Campo gordura é obrigatório!")
    private double vlGordura;

    @Column(name = "vl_ml_agua")
    @NotNull(message = "Campo ml água é obrigatório!")
    private String vlMlAgua;
}
