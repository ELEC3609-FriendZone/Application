package friendzone.elec3609.model;

import java.util.ArrayList;
import java.util.List;

public class UnitOfStudy {

	String unitCode;
	String unitName;
	String description;
	int numStudents;
	List<Project> projects = new ArrayList<Project>();
	
	public UnitOfStudy(String unitCode, String unitName, int numStudents){
		setUnitCode(unitCode);
		setUnitName(unitName);
		setNumberOfStudents(numStudents);
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
		return projects;
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
}