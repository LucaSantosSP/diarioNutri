package org.diarioNutri.modulos.cadastros;

import org.diarioNutri.exception.RegraNegocioException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(RegraNegocioException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleRegraNegocioExcepcion(RegraNegocioException ex) {
        String mensagemError = ex.getMessage();
        return new ApiErrors(mensagemError);
    }
}
