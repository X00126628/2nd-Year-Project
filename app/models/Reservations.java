package models;

import com.avaje.ebean.Finder;
import com.avaje.ebean.Model;
import com.sun.org.apache.regexp.internal.RE;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.*;
import javax.persistence.*;

import com.avaje.ebean.Model;
import play.data.format.*;
import play.data.validation.*;
/**
 * Created by deni on 07/04/2017.
 */

@Entity
public class Reservations extends Model{

    @Id
    private Long id;

    @Constraints.Required
    private String email;

    @Constraints.Required
    private String name;

    @Constraints.Required
    private String phone;

    @Constraints.Required
    private  Date date;

    @Constraints.Required
    private int number_People;


    public Reservations() {
        this.id = null;
        this.email = null;
        this.name = null;
        this.phone = null;
        this.date = null;
        this.number_People = 0;
    }

    public Reservations(Long id, String email, String name, String phone, Date date, int numberPeople) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.date = date;
        this.number_People = numberPeople;
    }

    public String validate() {

        if (!getEmail().contains("@")) {
            return "Invalid email address";

        }
        return null;
    }

    public static Finder<Long,Reservations> find = new Finder<Long,Reservations>(Reservations.class);

    public static List<Reservations> findAll() {

        return Reservations.find.all();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getNumber_People() {
        return number_People;
    }

    public void setNumber_People(int number_People) {
        this.number_People = number_People;
    }
}
