package org.diarioNutri.modulos.security.controller;

import jakarta.validation.Valid;
import org.diarioNutri.dao.cadastros.usuario.TabUsuarioObj;
import org.diarioNutri.dao.cadastros.usuario.dto.AuthenticationDTO;
import org.diarioNutri.dao.cadastros.usuario.dto.LoginResponseDTO;
import org.diarioNutri.dao.cadastros.usuario.dto.RegisterDTO;
import org.diarioNutri.dao.cadastros.usuario.repository.TabUsuarioRepository;
import org.diarioNutri.modulos.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TabUsuarioRepository tabUsuarioRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.txEmail(), data.txSenha());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((TabUsuarioObj) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data){
        if(this.tabUsuarioRepository.findByTxEmail(data.txEmail()) != null)
            return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.txSenha());
        TabUsuarioObj usuario = new TabUsuarioObj(data.txUsuario(), data.txEmail(), encryptedPassword);

        this.tabUsuarioRepository.save(usuario);

        return ResponseEntity.ok().build();
    }

}
