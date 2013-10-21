package friendzone.elec3609.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import friendzone.elec3609.service.DatabaseHandler;


public class Team {

	@Autowired
	DatabaseHandler dbHandler;
	
	int id;
	String name;
	Date lastViewed;
	
	public Team(int id, String name){
		this.id = id;
		this.name = name;
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
		this.lastViewed = team.lastViewed;
	}
	
}