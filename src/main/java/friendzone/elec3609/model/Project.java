package friendzone.elec3609.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import friendzone.elec3609.service.DatabaseHandler;


public class Project {
	
	final static DatabaseHandler dbHandler = DatabaseHandler.getInstance();
	
	private final String TABLE_NAME = "Project";
	
	Integer id;
	String parent;
	Date deadline;
	String description;
	int maxTeamSize;
	int minTeamSize;
	Timestamp lastViewed;
	
	public Project(String unitCode, String projectName, Date deadline, int minTeamSize, int maxTeamSize){
		this.parent = unitCode;
		this.deadline = deadline;
		this.minTeamSize = minTeamSize;
		this.maxTeamSize = maxTeamSize;
		id = dbHandler.addProject(parent, projectName, deadline, minTeamSize, maxTeamSize);
		lastViewed = new Timestamp(System.currentTimeMillis());
	}
	
	public Project(int id, String unitCode, Date deadline, int minTeamSize, int maxTeamSize){
		this.parent = unitCode;
		this.deadline = deadline;
		this.id = id;
		this.minTeamSize = minTeamSize;
		this.maxTeamSize = maxTeamSize;
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
	
	public void setDeadline(Date deadline) throws IllegalArgumentException{
		if (deadline == null)
			throw new IllegalArgumentException();
		this.deadline = deadline;
		dbHandler.update(TABLE_NAME, id, "DEADLINE", deadline);
	}
	
	public void setParent(String unitCode) throws IllegalArgumentException{
		if (parent == null)
			throw new IllegalArgumentException();
		this.parent = unitCode;
		dbHandler.update(TABLE_NAME, id, "DEADLINE", deadline);
	}
	
	public void setDescription(String description){
		this.description = description;
		dbHandler.update(TABLE_NAME, id, "DESCRIPTION", description);
	}

	public void copyValues(Project project) {
		this.id = project.id;
		this.parent = project.parent;
		this.deadline = project.deadline;
		this.description = project.description;
		this.lastViewed = project.lastViewed;
	}
}