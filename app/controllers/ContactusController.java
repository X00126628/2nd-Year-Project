package controllers;

import models.Contactus;
import models.users.User;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

import javax.inject.Inject;

/**
 * Created by deni on 18/04/2017.
 */
public class ContactusController  extends Controller{

    private FormFactory formFactory;


    @Inject
    public ContactusController(FormFactory f) {


        this.formFactory = f;
    }

    public Result contactus() {

        Form<Contactus> addContactusForm = formFactory.form(Contactus.class);

        return ok(contactus.render(addContactusForm, User.getUserById(session().get("email"))));

    }

    public Result contactusSubmit(){

        Form<Contactus> newContactusForm = formFactory.form(Contactus.class).bindFromRequest();

        if(newContactusForm.hasErrors()){

            return badRequest(contactus.render(newContactusForm, User.getUserById(session().get("email"))));
        }

        Contactus newContactus = newContactusForm.get();

        newContactus.save();

        flash("success", "User " + newContactus.getName() + " has been created");

        return redirect(routes.HomeController.index());
    }

}
