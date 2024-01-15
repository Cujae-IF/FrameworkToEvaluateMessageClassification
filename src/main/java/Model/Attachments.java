package Model;

import javax.mail.BodyPart;

public class Attachments {
    private int size;
    private BodyPart[] parts;

    public Attachments(int size, BodyPart[] parts) {
        this.size = size;
        this.parts = parts;
    }

    public BodyPart[] getParts() {
        return parts;
    }

    public void setParts(BodyPart[] parts) {
        this.parts = parts;
    }

    public int size() {
        return size;
    }

}
