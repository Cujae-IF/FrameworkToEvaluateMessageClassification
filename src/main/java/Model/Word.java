package Model;

import java.io.Serializable;

public class Word implements Serializable { 
    private String text;
    private int spamCount;
    private int hamCount;

    public Word(String text) {
        this.text = text;
        this.spamCount = 0;
        this.hamCount = 0;
    }
    
    public Word(String text, int spamCount, int hamCount) {
        this.text = text;
        this.spamCount = spamCount;
        this.hamCount = hamCount;
    }

    public String getText() {
        return text;
    }

    public int getSpamCount() {
        return spamCount;
    }

    public int getHamCount() {
        return hamCount;
    }
   
    public void increaseSpamCount() {
        this.spamCount++;
    }

    public void increaseHamCount() {
        this.hamCount++;
    }

}
 
