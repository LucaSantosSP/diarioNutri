package org.diarioNutri.dao.cadastros.usuario.repository;
import org.diarioNutri.dao.cadastros.usuario.TabUsuarioObj;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;


public interface TabUsuarioRepository extends JpaRepository<TabUsuarioObj, Integer> {

    TabUsuarioObj findByCdUsuario(Integer cdUsuario);
    List<TabUsuarioObj> findByTxUsuarioLike(String txUsuario);

    @Query("select t from TabUsuarioObj t where t.txEmail like ?1")
    TabUsuarioObj encontrarPorEmail(String txEmail);

    UserDetails findByTxEmail(String txEmail);

    UserDetails findByTxUsuario(String txUsuario);
}
