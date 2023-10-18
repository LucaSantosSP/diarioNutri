package org.diarioNutri.dao.cadastros.usuario;

public enum TabUsuarioRoleObj {

    ADMIN("admin"),
    USER("user");

    private String role;

    TabUsuarioRoleObj(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
