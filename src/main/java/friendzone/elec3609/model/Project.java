package friendzone.elec3609.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import friendzone.elec3609.service.DatabaseHandler;


public class Project {
	
	@Autowired
	DatabaseHandler dbHandler;
	
	int id;
	String parent;
	Date deadline;
	String description;
	Timestamp lastViewed;
	
	public Project(int id, String unitCode, Date deadline){
		setID(id);
		setParent(unitCode);
		setDeadline(deadline);
		lastViewed = new Timestamp(System.currentTimeMillis());
	}
	
	public Timestamp getLastViewed(){
		return lastViewed;
	}
	
	public void setLastViewed(Timestamp lastViewed){
		this.lastViewed = lastViewed;
	}
	
	public int getID(){
		return id;
	}
	
	public Date getDeadline(){
		return deadline;
	}
	
	public UnitOfStudy getParent(){
		return dbHandler.getUnitOfStudy(parent);
		
	}
	
	public String getDescription(){
		return description;
	}
	
	public List<Team> getTeams(){
		return dbHandler.getTeams(id);
	}
	
	public void setID(int id){
		this.id = id;
	}
	
	public void setDeadline(Date deadline) throws IllegalArgumentException{
		if (deadline == null)
			throw new IllegalArgumentException();
		this.deadline = deadline;
	}
	
	public void setParent(String parent) throws IllegalArgumentException{
		if (parent == null)
			throw new IllegalArgumentException();
		this.parent = parent;
	}
	
	public void setDescription(String description){
		this.description = description;
	}

	public void copyValues(Project project) {
		this.id = project.id;
		this.parent = project.parent;
		this.deadline = project.deadline;
		this.description = project.description;
		this.lastViewed = project.lastViewed;
	}
}