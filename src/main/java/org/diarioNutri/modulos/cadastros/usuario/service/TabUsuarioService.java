package org.diarioNutri.modulos.cadastros.usuario.service;

import org.diarioNutri.dao.cadastros.refeicao.repository.TabRefeicaoRepository;
import org.diarioNutri.dao.cadastros.refeicaotipo.repository.TabRefeicaoTipoRepository;
import org.diarioNutri.dao.cadastros.usuario.TabUsuarioObj;
import org.diarioNutri.dao.cadastros.usuario.repository.TabUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;

@Service
public class TabUsuarioService {

    @Autowired
    private TabUsuarioRepository tabUsuarioRepository;

    public TabUsuarioObj salvar(TabUsuarioObj tabUsuarioObj){
        TabUsuarioObj newTabUsuarioObj = tabUsuarioRepository.save(tabUsuarioObj);
        return newTabUsuarioObj;
    }

    public Optional<TabUsuarioObj> encontrarUsuario (Integer cdUsuario){
        return tabUsuarioRepository.findById(cdUsuario);
    }

    public Optional<List<TabUsuarioObj>> encontraUsuarios (){
        Optional<List<TabUsuarioObj>> tabUsuarioObjList = Optional.of(tabUsuarioRepository.findAll());
        return tabUsuarioObjList;
    }

    public Boolean deletar(Integer cdUsuario){
        Optional<TabUsuarioObj> tabUsuarioObj = encontrarUsuario(cdUsuario);
        if(tabUsuarioObj.isPresent()){
            tabUsuarioRepository.delete(tabUsuarioObj.get());
            return true;
        }
        return false;
    }

    public String pesoIdeal(Double vlAltura){

        Double txPesoMinimo = 18.5 * Math.pow(vlAltura, 2);
        Double txPesoMaximo = 24.9 * Math.pow(vlAltura, 2);

        DecimalFormat df = new DecimalFormat("#.##");

        String pesoMinimoFormatado = df.format(txPesoMinimo);
        String pesoMaximoFormatado = df.format(txPesoMaximo);

        return pesoMinimoFormatado + " a " + pesoMaximoFormatado + " Kg";
    }
}
