package org.diarioNutri.modulos.cadastros.refeicaotipo.controller;

import jakarta.validation.Valid;
import jakarta.validation.Validator;
import org.diarioNutri.dao.cadastros.refeicao.DTO.TabRefeicaoDTO;
import org.diarioNutri.dao.cadastros.refeicaotipo.TabRefeicaoTipoObj;
import org.diarioNutri.dao.cadastros.usuario.TabUsuarioObj;
import org.diarioNutri.modulos.cadastros.refeicao.service.TabRefeicaoService;
import org.diarioNutri.modulos.cadastros.refeicaotipo.service.TabRefeicaoTipoService;
import org.diarioNutri.modulos.cadastros.usuario.service.TabUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/refeicaotipo")
public class TabRefeicaoTipoController {

    @Autowired
    private TabRefeicaoTipoService tabRefeicaoTipoService;

    @Autowired
    private TabRefeicaoService tabRefeicaoService;

    @Autowired
    private TabUsuarioService tabUsuarioService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/gravar")
    public TabRefeicaoTipoObj save (@RequestBody @Valid TabRefeicaoTipoObj tabRefeicaoTipoObj){
        LocalTime hrSeisManha = LocalTime.of(6, 0); // 06:00
        LocalTime hrMeioDia = LocalTime.NOON; // Meio-dia
        LocalTime hrSeteNoite = LocalTime.of(19, 0); // 06:00
        LocalTime hrOnzeNoite = LocalTime.of(23, 59); // 06:00

        if (tabRefeicaoTipoObj.getTxIcon() == null) {
            tabRefeicaoTipoObj.setTxIcon("baguette");
            if (tabRefeicaoTipoObj.getDtHoraRefeicaoTipo().isAfter(hrSeisManha) && tabRefeicaoTipoObj.getDtHoraRefeicaoTipo().isBefore(hrMeioDia)) {
                tabRefeicaoTipoObj.setTxIcon("food-apple");
            }
            if (tabRefeicaoTipoObj.getDtHoraRefeicaoTipo().isAfter(hrMeioDia) && tabRefeicaoTipoObj.getDtHoraRefeicaoTipo().isBefore(hrSeteNoite)) {
                tabRefeicaoTipoObj.setTxIcon("food-croissant");
            }
            if (tabRefeicaoTipoObj.getDtHoraRefeicaoTipo().isAfter(hrSeteNoite) && tabRefeicaoTipoObj.getDtHoraRefeicaoTipo().isBefore(hrOnzeNoite)) {
                tabRefeicaoTipoObj.setTxIcon("pizza");
            }
        }

        TabRefeicaoTipoObj newTabRefeicaoTipoObj = tabRefeicaoTipoService.gravar(tabRefeicaoTipoObj);

        TabRefeicaoDTO tabRefeicaoDTO = new TabRefeicaoDTO();
        tabRefeicaoDTO.setTxRefeicao(newTabRefeicaoTipoObj.getTxRefeicaoTipo());
        tabRefeicaoDTO.setCdUsuario(newTabRefeicaoTipoObj.getTabUsuarioObj().getCdUsuario());
        tabRefeicaoDTO.setDtRefeicao(LocalDate.now());
        tabRefeicaoDTO.setCdRefeicaoTipo(newTabRefeicaoTipoObj.getCdRefeicaoTipo());
        tabRefeicaoDTO.setTxIcon(newTabRefeicaoTipoObj.getTxIcon());
        tabRefeicaoDTO.setDtHoraRefeicao(newTabRefeicaoTipoObj.getDtHoraRefeicaoTipo());

        tabRefeicaoService.gravar(tabRefeicaoDTO);

        return newTabRefeicaoTipoObj;
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

    @GetMapping("/pesquisaByUsuario/{cdUsuario}")
    public ResponseEntity findByCdUsuario (@PathVariable Integer cdUsuario){
        List<TabRefeicaoTipoObj> tabRefeicaoTipoObjList = tabRefeicaoTipoService.findByCdUsuario(cdUsuario);
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

