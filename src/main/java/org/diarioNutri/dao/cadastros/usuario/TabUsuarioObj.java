package org.diarioNutri.dao.cadastros.usuario;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TabUsuarioObj {

    private Integer cdUsuario;
    private String txNome;
    private String txEmail;
    private String txSenha;
    private String txFoto;
    private Integer ckSexo;
    private Date dtNascimento;
    private Double vlAltura;
    private Double vlPeso;

}
