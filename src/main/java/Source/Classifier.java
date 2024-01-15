/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Source;

import Model.Word;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Unknown
 */
public class Classifier {
    private static Classifier classifier;
    public enum Type {SPAM, HAM}
    private Word[] pronenessWords;
    private List<Integer> lastIndexes;
    private Type lastClassification;
    
    private Classifier() {
        this.lastIndexes = new ArrayList<Integer>();
        this.pronenessWords = Wordbook.getWords();
    }
    
    public static Classifier getInstance(){
        if(classifier == null){
            classifier = new Classifier();
        }
        return classifier;
    }

    // Inicializar el vocabulario
    public void initWordbook(File words,File spamPath,File hamPath) throws Exception{
        Wordbook.init(words,spamPath, hamPath);
        this.pronenessWords = Wordbook.getWords();
    }
      
    // Clasificador ( SPAM / HAM )
    public Type classify(List<String> text){  
        if(pronenessWords == null){
            throw new Error ("the wordbook hasn't been loaded");
        }
        this.lastIndexes.clear();
        int spam = 0, ham = 0, count = 0;

        // Buscar las palabras del texto en el vocabulario
        for(String word : text) { 
            int index = findWord(word);
            if(index != -1){
                spam += pronenessWords[index].getSpamCount();
                ham += pronenessWords[index].getHamCount();
                lastIndexes.add(index);
                count++;
            }
        }      
        
        // Calcular porciento de probabilidad de ser Spam
        double k = wordPronenessPercent(spam,ham);
        double x = proneWordsPercent(count,text.size());
        double y = admissionPercent(x);
        
        // Determinar clasificación
        lastClassification = (k > y)? Type.SPAM : Type.HAM;

        return lastClassification;
    }
    
    // Porciento de probabilidad de las palabras propensas a ser Spam
    private double wordPronenessPercent(double spamWordSum, double hamWordSum){
        return (spamWordSum * 100) / (spamWordSum + hamWordSum);
    }
    
    // Porciento de palabras propensas del Email    
    private double proneWordsPercent(int proneWords, int words){
        return ((double)proneWords * 100) / (double)words;
    }
    
    // Porciento maximo de aceptación como Ham
    private double admissionPercent(double proneWordsPercent){
        return 80 - (proneWordsPercent / 2);
    } 
    
    // Buscar la palabra en el Vocabulario
    private int findWord(String word){
        for(int i = 0; i < pronenessWords.length; i++) {
            if(pronenessWords[i].getText().equalsIgnoreCase(word)){
                return i;
            }
        }
        return -1;
    }
    
    // Actualizar el vocabulario con la última clasificación
    public void updateWordbook(boolean correct){
        Type trueType = (correct)? lastClassification : opposedClassification();
        for(Integer word : lastIndexes) {
            switch(trueType){
                case SPAM: pronenessWords[word].increaseSpamCount();break;
                case HAM: pronenessWords[word].increaseHamCount();break;
            }
        }
        try {
            Wordbook.saveChanges();
        }
        catch(Exception ex) {}
    }
    
    private Type opposedClassification(){
        switch(lastClassification){
            case SPAM: return Type.HAM;
            case HAM: return Type.SPAM;
        }
        return null;
    }
    
    public boolean initialized(){
        return pronenessWords != null;
    } 

}
