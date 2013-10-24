package friendzone.elec3609.model;

import java.util.ArrayList;

import friendzone.elec3609.service.DatabaseHandler;

public class Admin{

	final static DatabaseHandler dbHandler = DatabaseHandler.getInstance();
	
	String staffID;
	String firstName;
	String lastName;
	String password;
	
	public Admin(String staffID, String password, String firstName, String lastName){
		this.staffID = staffID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		dbHandler.addAdministrator(staffID, password, firstName, lastName);
	}
	
	public void addUnitOfStudy(String unitCode){
		dbHandler.addAdministration(staffID, unitCode);
	}
	
	public ArrayList<UnitOfStudy> getUnits(){
		return dbHandler.getAdminUnitsOfStudy(staffID);
	}

	public void copyValues(Admin matchingAdmin) {
		this.staffID = matchingAdmin.staffID;
		this.firstName = matchingAdmin.firstName;
		this.lastName = matchingAdmin.lastName;
	}
}