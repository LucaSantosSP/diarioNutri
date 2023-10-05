package org.diarioNutri.dao.cadastros.usuario;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "tab_usuario")
public class TabUsuarioObj {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cd_usuario")
    private Integer cdUsuario;

    @Column(name = "tx_usuario", length = 30)
    private String txUsuario;

    @Column(name = "tx_email", length = 50)
    private String txEmail;

    @Column(name = "tx_senha", length = 999)
    private String txSenha;

    @Column(name = "tx_foto", length = 999)
    private String txFoto;

    @Column(name = "ck_sexo")
    private Integer ckSexo;

    @Column(name = "dt_nascimento")
    private Date dtNascimento;

    @Column(name = "vl_altura", length = 11, precision = 2)
    private Double vlAltura;

    @Column(name = "vl_peso", length = 11, precision = 2)
    private Double vlPeso;

    @Column(name = "vl_imc_atual", length = 11, precision = 2)
    private Double vlImcAtual;

    /*private Double vlPesoIdeal;

    private Double vlImcIdeal;*/

    public TabUsuarioObj() {
    }

    public TabUsuarioObj(Integer cdUsuario, String txUsuario) {
        this.cdUsuario = cdUsuario;
        this.txUsuario = txUsuario;
    }

    @Override
    public String toString() {
        return "TabUsuarioObj{" +
                "cdUsuario=" + cdUsuario +
                ", txUsuario='" + txUsuario + '\'' +
                '}';
    }
}
