package org.diarioNutri.dao.cadastros.receita;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.diarioNutri.dao.cadastros.usuario.TabUsuarioObj;

@Getter
@Setter
@Entity
@Table(name = "tab_receita")
public class TabReceitaObj {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cd_receita")
    private Integer cdReceita;

    @ManyToOne
    @JoinColumn(name = "cd_usuario")
    private TabUsuarioObj tabUsuarioObj;

    @Column(name = "tx_titulo")
    @NotEmpty(message = "Campo título é obrigatório!")
    private String txTitulo;

    @Column(name = "tx_tipo")
    @NotEmpty(message = "Campo tipo é obrigatório!")
    private String txTipo;

    @Column(name = "tx_foto")
    @NotEmpty(message = "Campo Foto é obrigatório!")
    private String txFoto;

    @Column(name = "tx_ingredientes")
    @NotEmpty(message = "Campo Ingrediente é obrigatório!")
    private String tx_Ingredientes;

    @Column(name = "tx_preparo")
    @NotEmpty(message = "Campo Preparo é obrigatório!")
    private String txPreparo;

    @Column(name = "vl_kcal", length = 3)
    @NotNull(message = "Campo caloria é obrigatório!")
    private Integer vlKcal;

}
