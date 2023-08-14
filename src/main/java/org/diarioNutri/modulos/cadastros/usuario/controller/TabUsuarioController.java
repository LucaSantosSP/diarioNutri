package org.diarioNutri.modulos.cadastros.usuario.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/usuario")
public class TabUsuarioController {

    @RequestMapping(value = "/hello/{txUsuario}", method = RequestMethod.GET)
    @ResponseBody
    public String helloUsuario ( @PathVariable("txUsuario") String txUsuario){
        return String.format("Hello %s ", txUsuario);
    }

}
