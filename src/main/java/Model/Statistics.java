/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
 

import java.io.Serializable;
import javax.swing.Timer;



/**
 *
 * @author Dr
 */
public class Statistics implements Serializable{
    private int timer;
    private float TP;
    private float FP;
    private float TN;
    private float FN;
    private float accuracy;
    private float BalancedAccuracy;
    private float recall;
    private float precision;
    private float TrueNegativeRate;
    private float TruePositiveRate;
    private String source;
    private static final long serialVersionUID = 1L;
    private String clasifierName;

    public String getClasifierName() {
        return clasifierName;
    }

    public void setClasifierName(String clasifierName) {
        this.clasifierName = clasifierName;
    }
    
    

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }

    public Statistics(int timer, float TP, float FP, float TN, float FN, float accuracy, float BalancedAccuracy, float recall, float precision, float TrueNegativeRate, float TruePositiveRate, String source) {
        this.timer = timer;
        this.TP = TP;
        this.FP = FP;
        this.TN = TN;
        this.FN = FN;
        this.accuracy = accuracy;
        this.BalancedAccuracy = BalancedAccuracy;
        this.recall = recall;
        this.precision = precision;
        this.TrueNegativeRate = TrueNegativeRate;
        this.TruePositiveRate = TruePositiveRate;
        this.source = source;
    }

  

    public Statistics() {
        
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    
    public float getTP() {
        return TP;
    }

    public void setTP(float TP) {
        this.TP = TP;
    }

    public float getFP() {
        return FP;
    }

    public void setFP(float FP) {
        this.FP = FP;
    }

    public float getTN() {
        return TN;
    }

    public void setTN(float TN) {
        this.TN = TN;
    }

    public float getFN() {
        return FN;
    }

    public void setFN(float FN) {
        this.FN = FN;
    }

    public float getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(float accuracy) {
        this.accuracy = accuracy;
    }

    public float getBalancedAccuracy() {
        return BalancedAccuracy;
    }

    public void setBalancedAccuracy(float BalancedAccuracy) {
        this.BalancedAccuracy = BalancedAccuracy;
    }

    public float getRecall() {
        return recall;
    }

    public void setRecall(float recall) {
        this.recall = recall;
    }

    public float getPrecision() {
        return precision;
    }

    public void setPrecision(float precision) {
        this.precision = precision;
    }

    public float getTrueNegativeRate() {
        return TrueNegativeRate;
    }

    public void setTrueNegativeRate(float TrueNegativeRate) {
        this.TrueNegativeRate = TrueNegativeRate;
    }

    public float getTruePositiveRate() {
        return TruePositiveRate;
    }

    public void setTruePositiveRate(float TruePositiveRate) {
        this.TruePositiveRate = TruePositiveRate;
    }
    
    
}
