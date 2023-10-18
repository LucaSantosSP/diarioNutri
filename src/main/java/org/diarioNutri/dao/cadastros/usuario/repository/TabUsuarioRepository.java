package org.diarioNutri.dao.cadastros.usuario.repository;
import org.diarioNutri.dao.cadastros.usuario.TabUsuarioObj;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;


public interface TabUsuarioRepository extends JpaRepository<TabUsuarioObj, Integer> {

    TabUsuarioObj findByCdUsuario(Integer cdUsuario);
    List<TabUsuarioObj> findByTxUsuarioLike(String txUsuario);

    UserDetails findByTxEmail(String txEmail);

    UserDetails findByTxUsuario(String txUsuario);
}
