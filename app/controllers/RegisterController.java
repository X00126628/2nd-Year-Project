package controllers;

/**
 * Created by Tudor on 21/03/2017.
 */
import controllers.*;
import controllers.routes;
import play.api.Environment;
import play.mvc.*;
import play.data.*;

import javax.inject.Inject;

import views.html.*;
import models.users.*;

public class RegisterController extends Controller {

    private FormFactory formFactory;


    @Inject
    public RegisterController(FormFactory f) {


        this.formFactory = f;
    }

    public Result register() {

        Form<User> addUserForm = formFactory.form(User.class);

        return ok(register.render(addUserForm, User.getUserById(session().get("email"))));

    }

    public Result registerSubmit(){

        Form<User> newRegisterForm = formFactory.form(User.class).bindFromRequest();

        if(newRegisterForm.hasErrors()){

            return badRequest(register.render(newRegisterForm, User.getUserById(session().get("email"))));
        }

        User newUser = newRegisterForm.get();

        newUser.save();

        flash("success", "User " + newUser.getName() + " has been created");

        return redirect(controllers.routes.LoginController.login());
    }

}
