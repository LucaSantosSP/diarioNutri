package org.diarioNutri.modulos.cadastros.usuario.controller;

import org.diarioNutri.dao.cadastros.usuario.TabUsuarioObj;
import org.diarioNutri.dao.cadastros.usuario.repository.TabUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/usuario")
public class TabUsuarioController {

    @Autowired
    private TabUsuarioRepository tabUsuarioRepository;



    @ResponseBody
    @GetMapping("/encontrar/{cdUsuario}")
    public ResponseEntity getTxUsuarioById (@PathVariable Integer cdUsuario){
        Optional<TabUsuarioObj> usuario =  tabUsuarioRepository.findById(cdUsuario);
        if (usuario.isPresent()){
            return ResponseEntity.ok(usuario.get());
        }
        return ResponseEntity.notFound().build();
    }

}
