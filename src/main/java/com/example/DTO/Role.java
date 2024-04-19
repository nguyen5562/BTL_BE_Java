package com.example.DTO;

public class Role {
    private Boolean isAdmin;

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Role() {
    }

    public Role(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }    
}
