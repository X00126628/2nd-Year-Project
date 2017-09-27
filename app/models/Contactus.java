package models;

import com.avaje.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;

/**
 * Created by deni on 11/04/2017.
 */

@Entity
public class Contactus extends Model{

    @Id
    @Constraints.Required
    private String email;

    @Constraints.Required
    private String name;

    @Constraints.Required
    private String comment;

    public Contactus() {
        this.email = null;
        this.name = null;
        this.comment = null;

    }

    public Contactus(String email, String name, String comment) {
        this.email = email;
        this.name = name;
        this.comment = comment;

    }

    public static Finder<String, Contactus> find = new Finder<String, Contactus>(Contactus.class);

    public static List<Contactus> findAll() {

        return Contactus.find.all();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }



}
