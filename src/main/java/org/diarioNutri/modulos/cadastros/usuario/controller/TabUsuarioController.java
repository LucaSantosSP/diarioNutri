package org.diarioNutri.modulos.cadastros.usuario.controller;

import org.diarioNutri.dao.cadastros.usuario.TabUsuarioObj;
import org.diarioNutri.dao.cadastros.usuario.repository.TabUsuarioRepository;
import org.diarioNutri.modulos.cadastros.usuario.service.TabUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/usuario")
public class TabUsuarioController {

    @Autowired
    private TabUsuarioService tabUsuarioService;



    @ResponseBody
    @GetMapping("/encontrar/{cdUsuario}")
    public ResponseEntity getTxUsuarioById (@PathVariable Integer cdUsuario){
        Optional<TabUsuarioObj> tabUsuarioObj =  tabUsuarioService.encontrarUsuario(cdUsuario);
        if (tabUsuarioObj.isPresent()){
            return ResponseEntity.ok(tabUsuarioObj.get());
        }
        return ResponseEntity.notFound().build();
    }
    @ResponseBody
    @GetMapping("/pesquisa")
    public ResponseEntity encontraTodos (){
        Optional<List<TabUsuarioObj>> tabUsuarioObjList = tabUsuarioService.encontraUsuarios();
        if(tabUsuarioObjList.isPresent()){
            return ResponseEntity.ok(tabUsuarioObjList);
        }
        return ResponseEntity.notFound().build();
    }

    @ResponseBody
    @PostMapping("/save")
    public ResponseEntity save( @RequestBody TabUsuarioObj tabUsuarioObj){
        TabUsuarioObj newTabUsuarioObj = tabUsuarioService.salvar(tabUsuarioObj);
        return ResponseEntity.ok(newTabUsuarioObj);
    }

    @ResponseBody
    @DeleteMapping("/delete/{cdUsuario}")
    public ResponseEntity delete(@PathVariable Integer cdUsuario){
        Boolean deleteSuccess = tabUsuarioService.deletar(cdUsuario);
        if(deleteSuccess){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @ResponseBody
    @PutMapping("/update/{cdUsuario}")
    public ResponseEntity update( @PathVariable Integer cdUsuario, @RequestBody TabUsuarioObj tabUsuarioObj) {
        return tabUsuarioService.encontrarUsuario(cdUsuario).
                map( tabUsuarioObjExistente -> {
                    tabUsuarioObj.setCdUsuario(tabUsuarioObjExistente.getCdUsuario());
                    tabUsuarioService.salvar(tabUsuarioObj);
                    return ResponseEntity.noContent().build();
                }).orElseGet( () -> ResponseEntity.notFound().build() );
    }
}
