package Model;

import java.io.Serializable;
import javax.mail.Address;

public class Body  implements Serializable{
    private Object content;
    private Address[] emailAddresses;
    private static final long serialVersionUID = 1L;

    public Body(Object body, Address[] emailAddresses) {
        this.content = body;
        this.emailAddresses = emailAddresses;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public Address[] getEmailAddresses() {
        return emailAddresses;
    }

    public void setEmailAddresses(Address[] emailAddresses) {
        this.emailAddresses = emailAddresses;
    }
}
