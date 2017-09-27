package controllers;

import controllers.*;
import models.shopping.ShopOrder;
import models.users.User;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.*;
import play.db.ebean.Transactional;
import views.html.*;
import models.*;
import play.api.Environment;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import models.Product;
import models.shopping.*;
import views.html.admin.*;



/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {
        return ok(index.render(getUserFromSession()));
    }

    public Result aboutus() {
        return ok(aboutus.render(getUserFromSession()));
    }



    @Security.Authenticated(Secured.class)
    @With(AuthStaff.class)
    public Result staffView(){

        List<ShopOrder> ordersList = ShopOrder.findAll();

        return ok(staffView.render(ordersList,User.getUserById(session().get("email"))));

    }




    @Security.Authenticated(Secured.class)
    @With(AuthAdmin.class)
    public Result productsView() {

        List<Product> productsList = Product.findAll();

        return  ok(productsView.render(productsList,User.getUserById(session().get("email"))));

    }



    private User getUserFromSession(){
        return  User.getUserById(session().get("email"));
    }

    public Result menu(){
        //Get the list of products
        List<Product> productsList = Product.findAll();

        //Render the list products view, passing the parameters
        return ok(menu.render(productsList, User.getUserById(session().get("email"))));

    }

    @Security.Authenticated(Secured.class)
    @With(AuthAdmin.class)
    public Result addProduct(){
        Form<Product> addProductForm = formFactory.form(Product.class);
        return ok(addProduct.render(addProductForm, User.getUserById(session().get("email"))));
    }

    @Transactional
    @Security.Authenticated(Secured.class)
    public Result addProductSubmit() {
        Form<Product> newProductForm = formFactory.form(Product.class).bindFromRequest();

        if(newProductForm.hasErrors()) {
            //Display the form again
            return badRequest(addProduct.render(newProductForm, User.getUserById(session().get("email"))));
        }
        //Extract the product from the form object
        Product newProduct = newProductForm.get();

        if (newProduct.getId() == null) {
            newProduct.save();
        }
        else if (newProduct.getId() != null) {
            newProduct.update();
        }




        //Save to the database via Ebean
        newProduct.save();

        //Set a success message in temporary flash for display in the return view
        flash("success", "Product " + newProduct.getName() + " has been created / updated");

        //redirect to the admin page
        return redirect(controllers.routes.HomeController.menu());



    }
    @Security.Authenticated(Secured.class)
    @With(AuthAdmin.class)
    @Transactional
    public Result deleteProduct(Long id){

        Product.find.ref(id).delete();

        flash("succes", "Product has been deleted");

        return redirect(routes.HomeController.menu());
    }

    private FormFactory formFactory;

    @Inject
    public HomeController(FormFactory f){
        this.formFactory = f;
    }

    //Update product by id called when edit update button is pressed
    @Security.Authenticated(Secured.class)
    @With(AuthAdmin.class)
    @Transactional
    public Result updateProduct(Long id) {
        Product p;
        Form<Product> productForm;

        try{
            //find product by id
            p = Product.find.byId(id);

            //Create a form based on the Product class and fill using p
            productForm = formFactory.form(Product.class).fill(p);

        }catch(Exception ex) {
            //Display an error message on page
            return badRequest("error");
        }
        //Render the updateProduct view - pass form as a parameter
        return ok(addProduct.render(productForm, User.getUserById(session().get("email"))));
    }
}
