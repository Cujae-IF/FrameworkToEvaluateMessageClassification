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
public class FilesSend {
    
    private byte[] array;
    private String label;

    public byte[] getArray() {
        return array;
    }

    public String getLabel() {
        return label;
    }

    public void setArray(byte[] array) {
        this.array = array;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public FilesSend() {
    }

    public FilesSend(byte[] array, String label) {
        this.array = array;
        this.label = label;
    }
    
               
    
}
