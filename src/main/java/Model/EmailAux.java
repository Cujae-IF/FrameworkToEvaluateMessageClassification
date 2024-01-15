/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author andy
 */
public class EmailAux {
    private Email email;
    private String realClasification;
    private String givenClasification;
    private String clasifierName;

    public EmailAux() {
    }

    public EmailAux(Email email, String realClasification, String givenClasification) {
        this.email = email;
        this.realClasification = realClasification;
        this.givenClasification = givenClasification;
    }

    public String getClasifierName() {
        return clasifierName;
    }

    public void setClasifierName(String clasifierName) {
        this.clasifierName = clasifierName;
    }
        

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public String getRealClasification() {
        return realClasification;
    }

    public void setRealClasification(String realClasification) {
        this.realClasification = realClasification;
    }

    public String getGivenClasification() {
        return givenClasification;
    }

    public void setGivenClasification(String givenClasification) {
        this.givenClasification = givenClasification;
    }
    
}
