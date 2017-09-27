
package models;

import java.util.*;
import javax.persistence.*;
import play.data.format.*;
import play.data.validation.*;
import com.avaje.ebean.*;

@Entity
public class Product extends Model
{
    //Variables
    @Id
    private Long id;

    @Constraints.Required
    private String name;

    @Constraints.Required
    private String description;

    @Constraints.Required
    private double price;

    @Constraints.Required
    private String type;

    @Constraints.Required
    private boolean inStock;

    public Product(){


    }
    public Product(Long id, String name, String description, double price, String type, boolean inStock)
    {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.type = type;
        this.inStock = inStock;
    }

    public Long getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public String getDescription()
    {
        return description;
    }

    public double getPrice()
    {
        return price;
    }

    public String getType()
    {
        return type;
    }

    public boolean getInStock() { return inStock; }

    public boolean isInStock()
    {
        return inStock;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public void setInStock(boolean inStock)
    {
        this.inStock = inStock;
    }

    //Generic query helper for entity Computer with id Long
    public static Finder<Long,Product> find = new Finder<Long,Product>(Product.class);

    //Find all Products in the database
    //Filter product name
    public static List<Product> findAll()
    {
        return Product.find.all();
    }
}
