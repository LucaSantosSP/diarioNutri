package org.diarioNutri.dao.cadastros.usuario.repository;

import jakarta.persistence.EntityManager;
import org.diarioNutri.dao.cadastros.usuario.TabUsuarioObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TabUsuarioRepository extends TabUsuarioObj{

    private static String SELECT_ALL = "SELECT * FROM tab_usuario";
    private static String UPTDATE = "update tab_usuario set tx_usuario = ? where cd_usuario = ? ";
    private static String DELETE = "delete from tab_usuario where cd_usuario = ? ";
    private static String SELECT_BY_USUARIO = "SELECT * FROM tab_usuario where tx_usuario like ? ";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public TabUsuarioObj salvar(TabUsuarioObj tabUsuarioObj){
        entityManager.persist(tabUsuarioObj);
        return tabUsuarioObj;
    }

    public TabUsuarioObj atualizar(TabUsuarioObj tabUsuarioObj){
        jdbcTemplate.update(UPTDATE, new Object[]{
                tabUsuarioObj.getTxUsuario(), getCdUsuario() });
        return tabUsuarioObj;
    }

    public void deletar(TabUsuarioObj tabUsuarioObj){
        jdbcTemplate.update(DELETE, new Object[]{
                tabUsuarioObj.getCdUsuario()
        });
    }

    public List<TabUsuarioObj> findByTxUsuario (String txUsuario){
        return jdbcTemplate.query(SELECT_BY_USUARIO,
                new Object[]{"%" + txUsuario + "%"},
                getRowMapper());
    }

    public List<TabUsuarioObj> listar (){
        return jdbcTemplate.query(SELECT_ALL, getRowMapper());
    }

    private static RowMapper<TabUsuarioObj> getRowMapper() {
        return new RowMapper<TabUsuarioObj>() {
            @Override
            public TabUsuarioObj mapRow(ResultSet resultSet, int i) throws SQLException {
                Integer cdUsuario = resultSet.getInt("cd_usuario");
                String txUsuario = resultSet.getString("tx_usuario");
                return new TabUsuarioObj(cdUsuario, txUsuario);
            }
        };
    }
}
