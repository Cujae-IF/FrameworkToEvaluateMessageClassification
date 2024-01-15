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
public class Clasifier {
    private String name;
    private String URL;
    private String trainURL;
    private String autoUpdateURL;

    public Clasifier() {
    }

    public Clasifier(String name, String URL, String trainURL) {
        this.name = name;
        this.URL = URL;
        this.trainURL = trainURL;
    }

    public String getAutoUpdateURL() {
        return autoUpdateURL;
    }

    public void setAutoUpdateURL(String autoUpdateURL) {
        this.autoUpdateURL = autoUpdateURL;
    }
        

    public String getTrainURL() {
        return trainURL;
    }

    public void setTrainURL(String trainURL) {
        this.trainURL = trainURL;
    }
        

    public String getName() {
        return name;
    }

    public String getURL() {
        return URL;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }
    
    
}
