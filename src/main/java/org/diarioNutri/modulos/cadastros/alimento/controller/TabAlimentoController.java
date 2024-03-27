package org.diarioNutri.modulos.cadastros.alimento.controller;

import jakarta.validation.Valid;
import org.diarioNutri.dao.cadastros.alimento.TabAlimentoObj;
import org.diarioNutri.dao.cadastros.refeicao.DTO.TabRefeicaoDTO;
import org.diarioNutri.dao.cadastros.refeicaotipo.TabRefeicaoTipoObj;
import org.diarioNutri.modulos.cadastros.alimento.service.TabAlimentoService;
import org.diarioNutri.modulos.cadastros.refeicao.service.TabRefeicaoService;
import org.diarioNutri.modulos.cadastros.usuario.service.TabUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/alimento")
public class TabAlimentoController {

    @Autowired
    private TabAlimentoService tabAlimentoService;

    @Autowired
    private TabRefeicaoService tabRefeicaoService;

    @Autowired
    private TabUsuarioService tabUsuarioService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/gravar")
    public TabAlimentoObj save (@RequestBody @Valid TabAlimentoObj tabAlimentoObj){
        return tabAlimentoService.gravar(tabAlimentoObj);
    }

    @GetMapping("/{cdAlimento}")
    public TabAlimentoObj encontrarPorCdalimento(@PathVariable Integer cdAlimento){
        return tabAlimentoService.encontrarPorCdAlimento(cdAlimento)
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Alimento não encontrado!"));
    }

    @GetMapping("/pesquisa/{cdUsuario}")
    public ResponseEntity encontraTodos (@PathVariable Integer cdUsuario){
        List<TabAlimentoObj> tabAlimentoObjList = tabAlimentoService.encontrarTodos(cdUsuario);
        if(!tabAlimentoObjList.isEmpty()){
            return ResponseEntity.ok(tabAlimentoObjList);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/pesquisabypesquisa/{cdUsuario}/{txAlimento}")
    public ResponseEntity encontraTodos (@PathVariable Integer cdUsuario, @PathVariable String txAlimento){
        List<TabAlimentoObj> tabAlimentoObjList = tabAlimentoService.encontrarTodosByPesquisa(cdUsuario, txAlimento);
        if(!tabAlimentoObjList.isEmpty()){
            return ResponseEntity.ok(tabAlimentoObjList);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/pesquisaByUsuario/{cdUsuario}")
    public ResponseEntity findByCdUsuario (@PathVariable Integer cdUsuario){
        List<TabAlimentoObj> tabAlimentoObjList = tabAlimentoService.findByCdUsuario(cdUsuario);
        if(!tabAlimentoObjList.isEmpty()){
            return ResponseEntity.ok(tabAlimentoObjList);
        }
        return ResponseEntity.notFound().build();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/salvar/{cdAlimento}")
    public void update(@PathVariable Integer cdAlimento, @RequestBody @Valid TabAlimentoObj tabAlimentoObj){
        tabAlimentoService.encontrarPorCdAlimento(cdAlimento)
                .map( tabAlimentoObjExistente -> {
                    tabAlimentoObj.setCdAlimento(tabAlimentoObjExistente.getCdAlimento());
                    tabAlimentoService.gravar(tabAlimentoObj);
                    return tabAlimentoObj;
                }).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Alimento não encontrado!"));
    }
    @DeleteMapping("/excluir/{cdAlimento}")
    public void excluir(@PathVariable Integer cdAlimento){
        tabAlimentoService.encontrarPorCdAlimento(cdAlimento).map(tabAlimentoObj -> tabAlimentoService.excluir(tabAlimentoObj))
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}

