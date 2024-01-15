/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Source;

import Model.Header;
import Model.Attachments;
import Model.Body;
import Model.Email;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import javax.mail.BodyPart;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Unknown
 */
public class FeaturesExtractor {

    //para correos con extension EML
    public Email extractEML(String path) {
        Email email = new Email();

        try {
            Properties props = new Properties();
            Session mailSession = Session.getDefaultInstance(props, null);
            InputStream source = new FileInputStream(path);
            MimeMessage message = new MimeMessage(mailSession,source);

            email.setHeader(new Header(
                    message.getMessageID(),
                    message.getSentDate(),
                    message.getSubject()
            ));

            email.setBody(new Body(
                    message.getContent(),
                    message.getFrom()
            ));

            String contentType = message.getContentType();

            if(contentType.contains("multipart")) {
//                System.out.println("Multipart EMail File");
                Multipart multiPart = (Multipart) message.getContent();
                BodyPart[] bodyParts = new BodyPart[multiPart.getCount()];
                for(int i = 0; i < multiPart.getCount(); i ++) {
                    bodyParts[i] = multiPart.getBodyPart(i);
                }

                email.setAttachments(new Attachments(
                        multiPart.getCount(),
                        bodyParts
                ));
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return email;
    }
    
    //para correos con extension ISO_8859_1
    public String extractISO_8859_1(File file) {
        String text = "";
        InputStream input = null;
        try {
            input = new FileInputStream(file);
            byte[] buffer = new byte[4096];
            while(true) {
                int byteCount = input.read(buffer, 0, buffer.length);
                if(byteCount <= 0) {
                    break;
                }
                text += new String(buffer, 0, byteCount, "ISO-8859-1") + " ";
            }
            input.close();
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }

        return text;
    }

}
