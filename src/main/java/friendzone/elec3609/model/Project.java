package friendzone.elec3609.model;

import java.sql.Date;
import java.util.List;


public class Project {
	
	int id;
	Date deadline;
	UnitOfStudy parent;
	String description;
	List<Team> teams;
	
	public Project(UnitOfStudy parent, Date deadline){
		setParent(parent);
		setDeadline(deadline);
	}
	
	public int getID(){
		return id;
	}
	
	public Date getDeadline(){
		return deadline;
	}
	
	public UnitOfStudy getParent(){
		return parent;
	}
	
	public String getDescription(){
		return description;
	}
	
	public List<Team>getTeams(){
		return teams;
	}
	
	public void setID(int id){
		this.id = id;
	}
	
	public void setDeadline(Date deadline) throws IllegalArgumentException{
		if (deadline == null)
			throw new IllegalArgumentException();
		this.deadline = deadline;
	}
	
	public void setParent(UnitOfStudy parent) throws IllegalArgumentException{
		if (parent == null)
			throw new IllegalArgumentException();
		this.parent = parent;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
}