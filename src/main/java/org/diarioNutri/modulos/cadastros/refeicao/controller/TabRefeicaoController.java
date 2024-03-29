package org.diarioNutri.modulos.cadastros.refeicao.controller;

import jakarta.validation.Valid;
import jakarta.validation.Validator;
import org.diarioNutri.dao.cadastros.refeicao.DTO.TabRefeicaoDTO;
import org.diarioNutri.dao.cadastros.refeicao.TabMacronutrienteObj;
import org.diarioNutri.dao.cadastros.refeicao.TabRefeicaoObj;
import org.diarioNutri.dao.cadastros.refeicaoalimento.TabRefeicaoAlimentoObj;
import org.diarioNutri.dao.cadastros.refeicaotipo.TabRefeicaoTipoObj;
import org.diarioNutri.dao.cadastros.usuario.TabUsuarioObj;
import org.diarioNutri.modulos.cadastros.refeicao.service.TabRefeicaoService;
import org.diarioNutri.modulos.cadastros.refeicaoalimento.TabRefeicaoAlimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/refeicao")
public class TabRefeicaoController {

    @Autowired
    private TabRefeicaoService tabRefeicaoService;

    @Autowired
    private TabRefeicaoAlimentoService tabRefeicaoAlimentoService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/gravar")
    public TabRefeicaoObj gravar (@RequestBody @Valid TabRefeicaoDTO tabRefeicaoDTO){
        TabRefeicaoObj tabRefeicaoObj = tabRefeicaoService.gravar(tabRefeicaoDTO);
        return tabRefeicaoObj;
    }

    @GetMapping("/{cdRefeicao}")
    public TabRefeicaoObj encontrarPorCdRefeicao (@PathVariable Integer cdRefeicao){
        return tabRefeicaoService.encontrarPorCdRefeicao(cdRefeicao)
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Refeição não encontrada!"));
    }

    @GetMapping("/pesquisa")
    public ResponseEntity encontrarTodos (){
        List<TabRefeicaoObj> tabRefeicaoObjList =  tabRefeicaoService.encontrarTodos();
        if(!tabRefeicaoObjList.isEmpty()){
            return ResponseEntity.ok(tabRefeicaoObjList);
        }
        return ResponseEntity.notFound().build();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/salvar/{cdRefeicao}")
    public void salvar (@PathVariable Integer cdRefeicao, @RequestBody @Valid TabRefeicaoObj tabRefeicaoObj){
        tabRefeicaoService.encontrarPorCdRefeicao(cdRefeicao)
                .map( tabRefeicaoObjExistente -> {
                    tabRefeicaoObj.setCdRefeicao(tabRefeicaoObjExistente.getCdRefeicao());
                    tabRefeicaoService.salvar(tabRefeicaoObj);
                    return tabRefeicaoObj;
                }).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Refeição não encontrada!"));
    }

    @DeleteMapping("/excluir/{cdRefeicao}")
    public void excluir (@PathVariable Integer cdRefeicao){
        tabRefeicaoService.encontrarPorCdRefeicao(cdRefeicao).map(tabRefeicaoObj -> tabRefeicaoService.deletar(tabRefeicaoObj))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Refeição não encontrada!"));
    }

    @GetMapping("/refeicaodia/{cdUsuario}")
    public ResponseEntity refeicaoDia (@PathVariable Integer cdUsuario){
        List<TabRefeicaoObj> tabRefeicaoObjList = tabRefeicaoService.refeicaoDiaria(cdUsuario);
        if(!tabRefeicaoObjList.isEmpty()){
            return ResponseEntity.ok(tabRefeicaoObjList);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/refeicaoalimento/{cdUsuario}/{cdRefeicao}")
    public ResponseEntity refeicaoAlimento (@PathVariable Integer cdUsuario, @PathVariable Integer cdRefeicao){
        List<TabRefeicaoAlimentoObj> tabRefeicaoAlimentoObjList = tabRefeicaoAlimentoService.findRefeicaoAlimento(cdUsuario, cdRefeicao);
        if(!tabRefeicaoAlimentoObjList.isEmpty()){
            return ResponseEntity.ok(tabRefeicaoAlimentoObjList);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/macronutrientes/{cdUsuario}")
    public ResponseEntity macronutrientes (@PathVariable Integer cdUsuario){
        List<TabRefeicaoAlimentoObj> tabRefeicaoAlimentoObjList = tabRefeicaoAlimentoService.findByCdUsuarioAndDay(cdUsuario);
        TabMacronutrienteObj tabMacronutrienteObj = new TabMacronutrienteObj();

        Double txProteinaTotal = 0.0;
        Double txCarboidratoTotal = 0.0;
        Double txGorduraTotal = 0.0;
        Integer txKcalTotal = 0;
        Double txAguaTotal = 0.0;

        for (TabRefeicaoAlimentoObj tabRefeicaoAlimentoObj : tabRefeicaoAlimentoObjList){
            txProteinaTotal = txProteinaTotal + Double.parseDouble(tabRefeicaoAlimentoObj.getTabAlimentoObj().getVlProteina());
            txCarboidratoTotal = txCarboidratoTotal + Double.parseDouble(tabRefeicaoAlimentoObj.getTabAlimentoObj().getVlCarboidrato());
            txGorduraTotal = txGorduraTotal + Double.parseDouble(tabRefeicaoAlimentoObj.getTabAlimentoObj().getVlGordura());
            txKcalTotal = txKcalTotal + tabRefeicaoAlimentoObj.getTabAlimentoObj().getVlKcal();
        }

        tabMacronutrienteObj.setTxProteina(txProteinaTotal.toString());
        tabMacronutrienteObj.setTxCarboidrato(txCarboidratoTotal.toString());
        tabMacronutrienteObj.setTxGordura(txGorduraTotal.toString());
        tabMacronutrienteObj.setTxKcal(txKcalTotal.toString());
        tabMacronutrienteObj.setTxAgua(txAguaTotal.toString());

        return ResponseEntity.ok(tabMacronutrienteObj);
    }

    @GetMapping("/nutrientesAlimento/{cdUsuario}/{cdRefeicao}")
    public ResponseEntity nutrientesAlimento (@PathVariable Integer cdUsuario, @PathVariable Integer cdRefeicao){
        List<TabRefeicaoAlimentoObj> tabRefeicaoAlimentoObjList = tabRefeicaoAlimentoService.findRefeicaoAlimento(cdUsuario, cdRefeicao);
        TabMacronutrienteObj tabMacronutrienteObj = new TabMacronutrienteObj();

        Double txProteinaTotal = 0.0;
        Double txCarboidratoTotal = 0.0;
        Double txGorduraTotal = 0.0;
        Integer txKcalTotal = 0;
        Double txAguaTotal = 0.0;

        for (TabRefeicaoAlimentoObj tabRefeicaoAlimentoObj : tabRefeicaoAlimentoObjList){
            txProteinaTotal = txProteinaTotal + Double.parseDouble(tabRefeicaoAlimentoObj.getTabAlimentoObj().getVlProteina());
            txCarboidratoTotal = txCarboidratoTotal + Double.parseDouble(tabRefeicaoAlimentoObj.getTabAlimentoObj().getVlCarboidrato());
            txGorduraTotal = txGorduraTotal + Double.parseDouble(tabRefeicaoAlimentoObj.getTabAlimentoObj().getVlGordura());
            txKcalTotal = txKcalTotal + tabRefeicaoAlimentoObj.getTabAlimentoObj().getVlKcal();
        }

        tabMacronutrienteObj.setTxProteina(txProteinaTotal.toString());
        tabMacronutrienteObj.setTxCarboidrato(txCarboidratoTotal.toString());
        tabMacronutrienteObj.setTxGordura(txGorduraTotal.toString());
        tabMacronutrienteObj.setTxKcal(txKcalTotal.toString());
        tabMacronutrienteObj.setTxAgua(txAguaTotal.toString());

        return ResponseEntity.ok(tabMacronutrienteObj);
    }
}
