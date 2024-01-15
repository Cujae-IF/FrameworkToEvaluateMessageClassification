package Model;

import java.io.Serializable;

public class Email implements Serializable{
    private Header header;
    private Body body;
    private Attachments attachments;
    private String realClasification;
    private String givenClasification;
    private static final long serialVersionUID = 1L;

    public String getGivenClasification() {
        return givenClasification;
    }

    public String getRealClasification() {
        return realClasification;
    }

    public void setGivenClasification(String givenClasification) {
        this.givenClasification = givenClasification;
    }

    public void setRealClasification(String realClasification) {
        this.realClasification = realClasification;
    } 

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public Attachments getAttachments() {
        return attachments;
    }

    public void setAttachments(Attachments attachments) {
        this.attachments = attachments;
    }

}
