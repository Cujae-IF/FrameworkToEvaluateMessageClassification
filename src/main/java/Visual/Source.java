/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visual;

/**
 *
 * @author Miguel
 */
public class Source {
    private String name;
    private int configuration;

    public Source(String name, int configuration) {
        this.name = name;
        this.configuration = configuration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getConfiguration() {
        return configuration;
    }

    public void setConfiguration(int configuration) {
        this.configuration = configuration;
    }
    
    
}
