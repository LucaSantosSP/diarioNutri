package org.diarioNutri.modulos.cadastros;

import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class ApiErrors {
    private List<String> errors;

    public ApiErrors(String mensagemError){
        this.errors = Arrays.asList(mensagemError);
    }
}
