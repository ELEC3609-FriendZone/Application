package friendzone.elec3609.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


public class Team {

	//Variables
	int id;
	Project parent;
	List<Student> members = new ArrayList<Student>();
	
	//Constructor
	public Team(int id, Project parent){
		setProject(parent);
		setID(id);
	}
	
	//Get Variable Functions
	public int getID(){
		return id;
	}
	
	public Project getProject(){
		return parent;
	}
	
	public List<Student> getMembers(){
		return members;
	}
	
	//Set Variable Functions
	public void setID(int id){
		this.id = id;
	}
	
	public void setProject(Project parent)throws IllegalArgumentException{
		if (parent == null)
			throw new IllegalArgumentException();
		this.parent = parent;
	}
	
	public void setMembers(List<Student> members){
		this.members = members;
	}
}