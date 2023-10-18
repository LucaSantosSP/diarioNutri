package org.diarioNutri.modulos.security;

import org.diarioNutri.dao.cadastros.usuario.repository.TabUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements UserDetailsService {
    @Autowired
    TabUsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String txEmail) throws UsernameNotFoundException {
        return repository.findByTxEmail(txEmail);
    }
}
