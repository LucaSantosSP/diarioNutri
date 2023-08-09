package org.diarioNutri.dao.cadastros.alimento;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tab_alimento")
public class TabAlimentoObj {

    @Column(name = "cd_alimento")
    private Integer cdAlimento;

    @Column(name = "cd_alimento")
    private String txAliemnto;

    @Column(name = "vl_kcal")
    private Integer vlKcal;

    @Column(name = "vl_proteina")
    private double vlProteina;

    @Column(name = "vl_carboidrato")
    private double vlCarboidrato;

    @Column(name = "vl_gordura")
    private double vlGordura;
}
