package org.diarioNutri.modulos.cadastros;

import org.diarioNutri.exception.RegraNegocioException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(RegraNegocioException.class)
    @ResponseBody
    public ApiErrors handleRegraNegocioExcepcion(RegraNegocioException ex) {
        String mensagemError = ex.getMessage();
        return new ApiErrors(mensagemError);
    }
}
