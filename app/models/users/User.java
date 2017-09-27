package models.users;

import com.avaje.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.*;
import javax.persistence.*;

import com.avaje.ebean.Model;
import play.data.format.*;
import play.data.validation.*;

import static play.mvc.Controller.flash;

/**
 * Created by Tudor on 18/03/2017.
 */
@Entity

@Table(name = "user")

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)

@DiscriminatorColumn(name = "role")

@DiscriminatorValue("customer")
public class User extends Model {

    @Id
    @Constraints.Required
    private String email;

    @Column(insertable=false, updatable=false)
    private String role;

    @Constraints.Required
    private String name;

    @Constraints.Required
    private String password;

    @Constraints.Required
    private String confirmPassword;


    public String validate() {

        if(!getEmail().contains("@")){
            return "Invalid email address";

        }

        if (User.authenticate(getEmail(), getName()) != null) {

            return "Account already exist";
        }

        if(!getPassword().equals(getConfirmPassword())) {
            return "Password mismatch";
        }

        return null;

    }


    public User() {
        this.role = "customer";
    }

    public User(String email, String role, String name, String password) {
        this.email = email;
        this.role = "customer";
        this.name = name;
        this.password = password;


        flash("error", "password doesn't match");
    }




    public static Finder<String, User> find = new Finder<String, User>(User.class);

    public static List<User> findAll() {

        return User.find.all();
    }

    public static User authenticate(String email, String password) {

        return find.where().eq("email", email).eq("password", password).findUnique();

    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public static User getUserById(String id) {
        if (id == null)
            return null;
        else
            return find.byId(id);

    }

}
