package org.diarioNutri.dao.cadastros.usuario;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tab_usuario")
public class TabUsuarioObj implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cd_usuario")
    private Integer cdUsuario;

    @Column(name = "tx_usuario", length = 30)
    @NotEmpty(message = "Campo nome é obrigatório!")
    private String txUsuario;

    @Column(name = "tx_email", length = 50)
    @NotEmpty(message = "Campo e-mail é obrigatório!")
    private String txEmail;

    @Column(name = "tx_senha", length = 999)
    @NotEmpty(message = "Campo senha é obrigatório!")
    private String txSenha;

    @Column(name = "tx_foto", length = 999)
    private String txFoto;

    @Column(name = "ck_sexo")
    //@NotEmpty(message = "Campo sexo é obrigatório!")
    private Integer ckSexo;

    @Column(name = "dt_nascimento")
    //@NotNull(message = "Campo data de nascimento é obrigatório!")
    private Date dtNascimento;

    @Column(name = "vl_altura", length = 11, precision = 2)
    //@NotNull(message = "Campo altura é obrigatório!")
    private Double vlAltura;

    @Column(name = "vl_peso", length = 11, precision = 2)
    //@NotNull(message = "Campo peso é obrigatório!")
    private Double vlPeso;

    @Column(name = "vl_peso_ideal", length = 11, precision = 2)
    private String vlPesoIdeal;

    @Column(name = "vl_imc_atual", length = 11, precision = 2)
    private Double vlImcAtual;

    @Column(name = "vl_imc_ideal", length = 11, precision = 2)
    private String vlImcIdeal;

    //private TabUsuarioRoleObj role;

    public TabUsuarioObj() {
    }

    public TabUsuarioObj(Integer cdUsuario, String txUsuario) {
        this.cdUsuario = cdUsuario;
        this.txUsuario = txUsuario;
    }

    public TabUsuarioObj( String txEmail, String txSenha) {
        this.txEmail = txEmail;
        this.txSenha = txSenha;
    }

    public TabUsuarioObj( String txUsuario, String txEmail, String txSenha, Double vlAltura, Double vlPeso) {
        this.txUsuario = txUsuario;
        this.txEmail = txEmail;
        this.txSenha = txSenha;
        this.vlAltura = vlAltura;
        this.vlPeso = vlPeso;
    }

    @Override
    public String toString() {
        return "TabUsuarioObj{" +
                "cdUsuario=" + cdUsuario +
                ", txUsuario='" + txUsuario + '\'' +
                '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
        /*if(this.role == TabUsuarioRoleObj.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));*/
    }

    @Override
    public String getPassword() {
        return txSenha;
    }

    @Override
    public String getUsername() {
        return txEmail;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
