package friendzone.elec3609.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import friendzone.elec3609.service.DatabaseHandler;

public class UnitOfStudy {

	@Autowired
	DatabaseHandler dbHandler;
	
	String unitCode;
	String unitName;
	String description;
	int numStudents;
	Timestamp lastViewed;
	
	public UnitOfStudy(String unitCode, String unitName, int numStudents){
		setUnitCode(unitCode);
		setUnitName(unitName);
		setNumberOfStudents(numStudents);
		lastViewed = new Timestamp(System.currentTimeMillis());
	}
	
	public Timestamp getLastViewed(){
		return lastViewed;
	}
	
	public void setLastViewed(Timestamp lastViewed){
		this.lastViewed = lastViewed;
	}
	
	public String getUnitCode(){
		return unitCode;
	}
	
	public String getUnitName(){
		return unitName;
	}
	
	public String getDescription(){
		return description;
	}
	
	public int getNumberOfStudents(){
		return numStudents;
	}
	
	public List<Project> getProjects(){
		return dbHandler.getProjects(unitCode);
	}
	
	public void setUnitCode(String unitCode) throws IllegalArgumentException{
		if (unitCode == null)
			throw new IllegalArgumentException();
		this.unitCode = unitCode;
	}
	
	public void setUnitName(String unitName) throws IllegalArgumentException{
		if (unitName == null)
			throw new IllegalArgumentException();
		this.unitName = unitName;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	
	public void setNumberOfStudents(int numStudents){
		this.numStudents = numStudents;
	}

	public void copyValues(UnitOfStudy uos) {
		this.unitCode = uos.unitCode;
		this.unitName = uos.unitName;
		this.description = uos.description;
		this.numStudents = uos.numStudents;
		this.lastViewed = uos.lastViewed;
		
	}
}