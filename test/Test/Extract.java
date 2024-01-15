package Test;

import Model.Email;
import Source.FeaturesExtractor;

public class Extract {

    public static void main(String[] args) {
        
        Email email = new FeaturesExtractor().extractEML("C:\\Users\\hhernandez\\Desktop\\Proyecto SPAM\\Workspace\\SpamDetection\\data\\email.eml");

        System.out.println("Message ID: " + email.getHeader().getId());
        System.out.println("Date: " + email.getHeader().getDate());
        System.out.println("Subject: " + email.getHeader().getSubject());
        System.out.println("---------------------------------------------");
        System.out.println("From: " + email.getBody().getEmailAddresses()[0]);
        System.out.println("Content: " + email.getBody().getContent());
        System.out.println("---------------------------------------------");

        if(email.getAttachments() != null) {
            System.out.println("Parts: " + email.getAttachments().size());
        }

    }

}
