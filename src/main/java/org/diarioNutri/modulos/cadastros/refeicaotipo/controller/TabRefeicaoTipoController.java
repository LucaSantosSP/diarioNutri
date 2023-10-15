package org.diarioNutri.modulos.cadastros.refeicaotipo.controller;

import jakarta.validation.Valid;
import org.diarioNutri.dao.cadastros.refeicaotipo.TabRefeicaoTipoObj;
import org.diarioNutri.dao.cadastros.usuario.TabUsuarioObj;
import org.diarioNutri.modulos.cadastros.refeicaotipo.service.TabRefeicaoTipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/refeicaotipo")
public class TabRefeicaoTipoController {

    @Autowired
    private TabRefeicaoTipoService tabRefeicaoTipoService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/gravar")
    public TabRefeicaoTipoObj save (@RequestBody @Valid TabRefeicaoTipoObj tabRefeicaoTipoObj){
        return tabRefeicaoTipoService.gravar(tabRefeicaoTipoObj);
    }

    @GetMapping("/{cdRefeicaoTipo}")
    public TabRefeicaoTipoObj encontrarPorCdRefeicaoTipo(@PathVariable Integer cdRefeicaoTipo){
        return tabRefeicaoTipoService.encontrarPorCdTipoRefeicao(cdRefeicaoTipo)
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tipo de refeição não encontrado!"));
    }

    @GetMapping("/pesquisa")
    public ResponseEntity encontraTodos (){
        List<TabRefeicaoTipoObj> tabRefeicaoTipoObjList = tabRefeicaoTipoService.encontrarTodos();
        if(!tabRefeicaoTipoObjList.isEmpty()){
            return ResponseEntity.ok(tabRefeicaoTipoObjList);
        }
        return ResponseEntity.notFound().build();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/salvar/{cdRefeicaoTipo}")
    public void update(@PathVariable Integer cdRefeicaoTipo, @RequestBody @Valid TabRefeicaoTipoObj tabRefeicaoTipoObj){
        tabRefeicaoTipoService.encontrarPorCdTipoRefeicao(cdRefeicaoTipo)
                .map( tabRefeicaoTipoObjExistente -> {
                    tabRefeicaoTipoObj.setCdRefeicaoTipo(tabRefeicaoTipoObjExistente.getCdRefeicaoTipo());
                    tabRefeicaoTipoService.gravar(tabRefeicaoTipoObj);
                    return tabRefeicaoTipoObj;
                }).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado!"));
    }
    @DeleteMapping("/excluir/{cdRefeicaoTipo}")
    public void excluir(@PathVariable Integer cdRefeicaoTipo){
        tabRefeicaoTipoService.encontrarPorCdTipoRefeicao(cdRefeicaoTipo).map(tabRefeicaoTipoObj -> tabRefeicaoTipoService.excluir(tabRefeicaoTipoObj))
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}

