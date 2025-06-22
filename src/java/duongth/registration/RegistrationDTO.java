/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duongth.registration;

import java.io.Serializable;

/**
 *
 * @author Vu
 */
public class RegistrationDTO implements Serializable {

    private String username;
    private String password;
    private String full_name;
    private boolean roles;

    public RegistrationDTO() {
    }

    public RegistrationDTO(String username, String password, String full_name, boolean roles) {
        this.username = username;
        this.password = password;
        this.full_name = full_name;
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String lastname) {
        this.full_name = full_name;
    }

    public boolean isRoles() {
        return roles;
    }

    public void setRoles(boolean roles) {
        this.roles = roles;
    }

}
