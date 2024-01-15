package Model;

import java.io.Serializable;
import java.util.Date;

public class Header  implements Serializable{
    private String id;
    private Date date;
    private String subject;
    private static final long serialVersionUID = 1L;

    public Header(String id, Date date, String subject) {
        this.id = id;
        this.date = date;
        this.subject = subject;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

}
