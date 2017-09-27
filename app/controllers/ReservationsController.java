package controllers;

import controllers.*;
import controllers.routes;
import play.api.Environment;

import models.*;
import play.data.*;
import play.mvc.*;
import views.html.*;
import models.users.User;

import javax.inject.Inject;

/**
 * Created by deni on 07/04/2017.
 */
public class ReservationsController extends Controller {

    private FormFactory formFactory;


    @Inject
    public ReservationsController(FormFactory f) {


        this.formFactory = f;
    }

    public Result reservations() {

        Form<Reservations> addReservationsForm = formFactory.form(Reservations.class);

        return ok(reservations.render(addReservationsForm, User.getUserById(session().get("email"))));

    }

    public Result reservationsSubmit(){

        Form<Reservations> newReservationsForm = formFactory.form(Reservations.class).bindFromRequest();

        if(newReservationsForm.hasErrors()){

            return badRequest(reservations.render(newReservationsForm, User.getUserById(session().get("email"))));
        }

        Reservations newReservations = newReservationsForm.get();

       newReservations.save();

        flash("success", "User " + newReservations.getName() + " has been created");

        return redirect(controllers.routes.HomeController.index());
    }

    public Result deleteReservation(Long id){
        Reservations.find.ref(id).delete();

        flash("succes", "Product has been deleted");

        return redirect(routes.HomeController.staffView());
    }
}
