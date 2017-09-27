package controllers;

import models.users.*;
import play.mvc.*;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.CompletableFuture;
/**
 * Created by Tudor on 12/04/2017.
 */
public class AuthCustomer extends Action.Simple{

    public CompletionStage<Result> call(Http.Context ctx) {


        String id = ctx.session().get("email");
        if (id != null) {
            User u = User.getUserById(id);
            if ("customer".equals(u.getRole())) {


                return delegate.call(ctx);
            }
        }

        ctx.flash().put("error", "Admin Login Required.");
        return CompletableFuture.completedFuture(redirect(routes.LoginController.login()));
    }
}
