package org.diarioNutri.modulos.security.controller;

import jakarta.validation.Valid;
import org.diarioNutri.dao.cadastros.usuario.TabUsuarioObj;
import org.diarioNutri.dao.cadastros.usuario.dto.AuthenticationDTO;
import org.diarioNutri.dao.cadastros.usuario.dto.LoginResponseDTO;
import org.diarioNutri.dao.cadastros.usuario.dto.RegisterDTO;
import org.diarioNutri.dao.cadastros.usuario.repository.TabUsuarioRepository;
import org.diarioNutri.modulos.cadastros.refeicao.service.TabRefeicaoService;
import org.diarioNutri.modulos.cadastros.refeicaotipo.service.TabRefeicaoTipoService;
import org.diarioNutri.modulos.cadastros.usuario.service.TabUsuarioService;
import org.diarioNutri.modulos.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TabUsuarioRepository tabUsuarioRepository;

    @Autowired
    private TabUsuarioService tabUsuarioService;

    @Autowired
    private TabRefeicaoTipoService tabRefeicaoTipoService;

    @Autowired
    private TabRefeicaoService tabRefeicaoService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.txEmail(), data.txSenha());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((TabUsuarioObj) auth.getPrincipal());

        TabUsuarioObj tabUsuarioObj = tabUsuarioRepository.encontrarPorEmail(data.txEmail());

        tabRefeicaoService.criarNovaRefeicao(tabUsuarioObj);

        return ResponseEntity.ok(new LoginResponseDTO(token, tabUsuarioObj.getCdUsuario()));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data){
        if(this.tabUsuarioRepository.findByTxEmail(data.txEmail()) != null)
            return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.txSenha());
        TabUsuarioObj usuario = new TabUsuarioObj(data.txUsuario(), data.txEmail(), encryptedPassword, data.vlAltura(), data.vlPeso());

        Double imc = usuario.getVlPeso() / Math.pow(usuario.getVlAltura(), 2);
        usuario.setVlImcAtual(imc);
        usuario.setVlImcIdeal("18.5 a 24.9");
        usuario.setVlPesoIdeal(tabUsuarioService.pesoIdeal(usuario.getVlAltura()));

        this.tabUsuarioRepository.save(usuario);

        Optional<TabUsuarioObj> tabUsuarioObjOptional  = tabUsuarioService.encontrarUsuario(usuario.getCdUsuario());
        if (tabUsuarioObjOptional.isPresent()) {
            TabUsuarioObj tabUsuarioObj = tabUsuarioObjOptional.get();
            tabRefeicaoTipoService.refeicoesPadrao(tabUsuarioObj);
        } else {
            System.out.println("Usuário não encontrado e refeições padrões não criadas!");
        }

        return ResponseEntity.ok().build();
    }

}
