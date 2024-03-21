package org.diarioNutri.dao.cadastros.refeicao;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TabMacronutrienteObj {

    private String txProteina;
    private String txCarboidrato;
    private String txGordura;
    private String txKcal;
    private String txAgua;
}
