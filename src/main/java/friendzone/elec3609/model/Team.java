package friendzone.elec3609.model;

import java.sql.Date;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import friendzone.elec3609.service.DatabaseHandler;


public class Team {

	final static DatabaseHandler dbHandler = DatabaseHandler.getInstance();
	
	private final String TABLE_NAME = "Team";
	
	int id;
	int projectID;
	String name;
	Date lastViewed;
	
	public Team(int projectID, String name){
		this.name = name;
		this.projectID = projectID;
		id = dbHandler.addTeam(projectID, name);
		lastViewed = new Date(System.currentTimeMillis());
	}
	
	public Team(int id, int projectID, String name){
		this.name = name;
		this.projectID = projectID;
		this.id = id;
		lastViewed = new Date(System.currentTimeMillis());
	}
	
	public int getID(){
		return id;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
		dbHandler.update(TABLE_NAME, id, "NAME", name);
	}
	
	public ArrayList<Student> getMembers(){
		return dbHandler.getMembers(id);
	}
	
	public Project getProject(){
		return dbHandler.getParent(id);
	}

	public void copyValues(Team team) {
		this.id = team.id;
		this.name = team.name;
		this.projectID = team.projectID;
		this.lastViewed = team.lastViewed;
	}
	
}