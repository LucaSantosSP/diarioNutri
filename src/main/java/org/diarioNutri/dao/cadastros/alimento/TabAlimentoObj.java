package org.diarioNutri.dao.cadastros.alimento;

import jakarta.persistence.*;
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
    private String txAliemnto;

    @Column(name = "vl_kcal", length = 3)
    private Integer vlKcal;

    @Column(name = "vl_proteina", length = 11, precision = 2)
    private double vlProteina;

    @Column(name = "vl_carboidrato", length = 11, precision = 2)
    private double vlCarboidrato;

    @Column(name = "vl_gordura", length = 11, precision = 2)
    private double vlGordura;
}
