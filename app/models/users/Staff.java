package models.users;

import java.util.*;
import javax.persistence.*;
import play.data.format.*;
import play.data.validation.*;
import com.avaje.ebean.*;

@Entity
// This is a User of type manager

@DiscriminatorValue("staff")

// Staff inherits from the User class
public class Staff extends User {
	
	private String department;

	public Staff() {

	}
	public Staff(String email,  String name, String password, String department)
	{
		super(email, "staff", name, password);
		this.department = department;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
}