/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visual;

/**
 *
 * @author andy
 */
public class GeneralMeasures {
    
    private String source;
    private int cantEmails;
    private int cantTP;
    private int cantTN;
    private int cantFP;
    private int cantFN;
    private float precision;
    private float recall;
    private float accuracy;
    private String clasifierName;

    public GeneralMeasures(String source, int cantEmails, int cantTP, int cantTN, int cantFP, int cantFN, float precision, float recall, float accuracy, String clasifierName) {
        this.source = source;
        this.cantEmails = cantEmails;
        this.cantTP = cantTP;
        this.cantTN = cantTN;
        this.cantFP = cantFP;
        this.cantFN = cantFN;
        this.precision = precision;
        this.recall = recall;
        this.accuracy = accuracy;
        this.clasifierName = clasifierName;
    }

    public GeneralMeasures() {
    }

    public String getClasifierName() {
        return clasifierName;
    }

    public void setClasifierName(String clasifierName) {
        this.clasifierName = clasifierName;
    }
    
    

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getCantEmails() {
        return cantEmails;
    }

    public void setCantEmails(int cantEmails) {
        this.cantEmails = cantEmails;
    }

    public int getCantTP() {
        return cantTP;
    }

    public void setCantTP(int cantTP) {
        this.cantTP = cantTP;
    }

    public int getCantTN() {
        return cantTN;
    }

    public void setCantTN(int cantTN) {
        this.cantTN = cantTN;
    }

    public int getCantFP() {
        return cantFP;
    }

    public void setCantFP(int cantFP) {
        this.cantFP = cantFP;
    }

    public int getCantFN() {
        return cantFN;
    }

    public void setCantFN(int cantFN) {
        this.cantFN = cantFN;
    }

    public float getPrecision() {
        return precision;
    }

    public void setPrecision(float precision) {
        this.precision = precision;
    }

    public float getRecall() {
        return recall;
    }

    public void setRecall(float recall) {
        this.recall = recall;
    }

    public float getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(float accuracy) {
        this.accuracy = accuracy;
    }
    
    
    
    
}
