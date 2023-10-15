package org.diarioNutri.modulos.cadastros.usuario.controller;

import jakarta.validation.Valid;
import org.diarioNutri.dao.cadastros.usuario.TabUsuarioObj;
import org.diarioNutri.dao.cadastros.usuario.repository.TabUsuarioRepository;
import org.diarioNutri.modulos.cadastros.usuario.service.TabUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class TabUsuarioController {

    @Autowired
    private TabUsuarioService tabUsuarioService;

    @GetMapping("/{cdUsuario}")
    public TabUsuarioObj getTxUsuarioById (@PathVariable Integer cdUsuario){
        return tabUsuarioService.encontrarUsuario(cdUsuario)
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado!"));
    }

    @GetMapping("/pesquisa")
    public ResponseEntity encontraTodos (){
        Optional<List<TabUsuarioObj>> tabUsuarioObjList = tabUsuarioService.encontraUsuarios();
        if(tabUsuarioObjList.isPresent()){
            return ResponseEntity.ok(tabUsuarioObjList);
        }
        return ResponseEntity.notFound().build();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/gravar")
    public TabUsuarioObj save( @RequestBody @Valid TabUsuarioObj tabUsuarioObj){
        Double imc = tabUsuarioObj.getVlPeso() / Math.pow(tabUsuarioObj.getVlAltura(), 2);
        tabUsuarioObj.setVlImcAtual(imc);
        return tabUsuarioService.salvar(tabUsuarioObj);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete/{cdUsuario}")
    public void delete(@PathVariable Integer cdUsuario){
        tabUsuarioService.encontrarUsuario(cdUsuario).map(tabUsuarioObj -> tabUsuarioService.deletar(tabUsuarioObj.getCdUsuario()))
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado!"));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/salvar/{cdUsuario}")
    public void update( @PathVariable Integer cdUsuario, @RequestBody @Valid TabUsuarioObj tabUsuarioObj) {
        tabUsuarioService.encontrarUsuario(cdUsuario).
                map( tabUsuarioObjExistente -> {
                    Double imc = tabUsuarioObj.getVlPeso() / Math.pow(tabUsuarioObj.getVlAltura(), 2);
                    tabUsuarioObj.setVlImcAtual(imc);
                    tabUsuarioObj.setCdUsuario(tabUsuarioObjExistente.getCdUsuario());
                    tabUsuarioService.salvar(tabUsuarioObj);
                    return tabUsuarioObjExistente;
                }).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado!") );
    }
}
