/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;

/**
 *
 * @author Dr
 */
public class Sample implements Serializable{
     
    private int count;
    private int greaters;
    private int greatersThan;
    private int size;
    private int subject;
    private int date;
    private String source;
    private boolean attachments;
    private static final long serialVersionUID = 1L;

    public Sample() {
        this.greaters = -1;
        this.greatersThan = -1;
        
    }

    public Sample(int count, int greaters, int greatersThan, int size, int subject, int date, String source, boolean attachments) {
        this.count = count;
        this.greaters = greaters;
        this.greatersThan = greatersThan;
        this.size = size;
        this.subject = subject;
        this.date = date;
        this.source = source;
        this.attachments = attachments;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getGreaters() {
        return greaters;
    }

    public void setGreaters(int greaters) {
        this.greaters = greaters;
    }

    public int getGreatersThan() {
        return greatersThan;
    }

    public void setGreatersThan(int greatersThan) {
        this.greatersThan = greatersThan;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSubject() {
        return subject;
    }

    public void setSubject(int subject) {
        this.subject = subject;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public boolean isAttachments() {
        return attachments;
    }

    public void setAttachments(boolean attachments) {
        this.attachments = attachments;
    }
    
    
    
    
}
