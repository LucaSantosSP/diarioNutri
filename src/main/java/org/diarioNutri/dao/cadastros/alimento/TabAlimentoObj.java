package org.diarioNutri.dao.cadastros.alimento;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TabAlimentoObj {

    private Integer cdalimento;
    private String txAliemnto;
    private Integer vlKcal;
    private double vlProteina;
    private double vlCarboidrato;
    private double vlGordura;
}
