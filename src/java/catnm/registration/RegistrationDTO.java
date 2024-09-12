/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catnm.registration;

import java.io.Serializable;

/**
 *
 * @author DELL
 */
public class RegistrationDTO implements Serializable{
    //biến tầm vực private hoặc protected
    private String name;
    private String password;
    private String fullName;
    private boolean role;

    public RegistrationDTO() {
    }

    public RegistrationDTO(String name, String password, String fullName, boolean role) {
        this.name = name;
        this.password = password;
        this.fullName = fullName;
        this.role = role;
    }

    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * @param fullName the fullName to set
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * @return the role
     */
    public boolean isRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(boolean role) {
        this.role = role;
    }

    
    
}
