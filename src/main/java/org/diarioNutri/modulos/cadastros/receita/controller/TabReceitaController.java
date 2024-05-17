package org.diarioNutri.modulos.cadastros.receita.controller;

import jakarta.validation.Valid;
import org.diarioNutri.dao.cadastros.alimento.TabAlimentoObj;
import org.diarioNutri.dao.cadastros.receita.TabReceitaObj;
import org.diarioNutri.dao.cadastros.refeicao.TabRefeicaoObj;
import org.diarioNutri.dao.cadastros.refeicaoalimento.TabRefeicaoAlimentoObj;
import org.diarioNutri.modulos.cadastros.receita.service.TabReceitaService;
import org.diarioNutri.modulos.cadastros.refeicao.service.TabRefeicaoService;
import org.diarioNutri.modulos.cadastros.refeicaoalimento.TabRefeicaoAlimentoService;
import org.diarioNutri.modulos.cadastros.usuario.service.TabUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/receita")
public class TabReceitaController {

    @Autowired
    private TabReceitaService tabReceitaService;

    @Autowired
    private TabRefeicaoService tabRefeicaoService;

    @Autowired
    private TabUsuarioService tabUsuarioService;

    @Autowired
    private TabRefeicaoAlimentoService tabRefeicaoAlimentoService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/gravar")
    public TabReceitaObj save (@RequestBody @Valid TabReceitaObj tabReceitaObj){
        return tabReceitaService.gravar(tabReceitaObj);
    }

    @GetMapping("/{cdReceita}")
    public TabReceitaObj encontrarPorCdReceita(@PathVariable Integer cdReceita){
        return tabReceitaService.encontrarPorCdReceita(cdReceita)
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Receita não encontrado!"));
    }

    @GetMapping("/pesquisa/{cdUsuario}")
    public ResponseEntity encontraTodos (@PathVariable Integer cdUsuario){
        List<TabReceitaObj> tabReceitaObjList = tabReceitaService.encontrarTodos(cdUsuario);
        if(!tabReceitaObjList.isEmpty()){
            return ResponseEntity.ok(tabReceitaObjList);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/pesquisabypesquisa/{cdUsuario}/{txTitulo}")
    public ResponseEntity encontraTodos (@PathVariable Integer cdUsuario, @PathVariable String txTitulo){
        List<TabReceitaObj> tabReceitaObjList = tabReceitaService.encontrarTodosByPesquisa(cdUsuario, txTitulo);
        if(!tabReceitaObjList.isEmpty()){
            return ResponseEntity.ok(tabReceitaObjList);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/pesquisaByUsuario/{cdUsuario}")
    public ResponseEntity findByCdUsuario (@PathVariable Integer cdUsuario){
        List<TabReceitaObj> tabReceitaObjList = tabReceitaService.findByCdUsuario(cdUsuario);
        if(!tabReceitaObjList.isEmpty()){
            return ResponseEntity.ok(tabReceitaObjList);
        }
        return ResponseEntity.notFound().build();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/salvar/{cdReceita}")
    public void update(@PathVariable Integer cdReceita, @RequestBody @Valid TabReceitaObj tabReceitaObj){
        tabReceitaService.encontrarPorCdReceita(cdReceita)
                .map( tabReceitaObjExistente -> {
                    tabReceitaObj.setCdReceita(tabReceitaObjExistente.getCdReceita());
                    tabReceitaService.gravar(tabReceitaObj);
                    return tabReceitaObj;
                }).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Receita não encontrado!"));
    }
    @DeleteMapping("/excluir/{cdReceita}")
    public void excluir(@PathVariable Integer cdReceita){
        tabReceitaService.encontrarPorCdReceita(cdReceita).map(tabReceitaObj -> tabReceitaService.excluir(tabReceitaObj))
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

}

