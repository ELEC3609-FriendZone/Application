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
	Date inviteDeadline;
	Date start;
	Date deadline;
	String description;
	int maxTeamSize;
	int minTeamSize;
	Timestamp lastViewed;
	
	public Project(String unitCode, String projectName, Date inviteDeadline, Date start, Date deadline, int minTeamSize, int maxTeamSize){
		this.parent = unitCode;
		this.inviteDeadline = inviteDeadline;
		this.start = start;
		this.deadline = deadline;
		this.minTeamSize = minTeamSize;
		this.maxTeamSize = maxTeamSize;
		id = dbHandler.addProject(parent, projectName, inviteDeadline, start, deadline, minTeamSize, maxTeamSize);
		lastViewed = new Timestamp(System.currentTimeMillis());
	}
	
	public Project(int id, String unitCode, Date deadline, int minTeamSize, int maxTeamSize){
		if (deadline.before(new Date(System.currentTimeMillis()))){
			throw new IllegalArgumentException("Cannot create a project with a deadline before this date.");
		}
		
		if (minTeamSize > maxTeamSize){
			throw new IllegalArgumentException("Maximum team size must be greater than or equal to minimum team size");
		}
		
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
	
	public int getMinTeamSize(){
		return minTeamSize;
	}
	
	public int getMaxTeamSize(){
		return maxTeamSize;
	}
	
	public Date getDeadline(){
		return deadline;
	}
	
	public Date getStart(){
		return start;
	}
	
	public Date getInviteDeadline(){
		return inviteDeadline;
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
	
	public void setStat(Date start) throws IllegalArgumentException{
		if (start == null)
			throw new IllegalArgumentException();
		this.start = start;
		dbHandler.update(TABLE_NAME, id, "START", start);
	}
	
	public void setInviteDeadline(Date inviteDeadline) throws IllegalArgumentException{
		if (inviteDeadline == null)
			throw new IllegalArgumentException();
		this.inviteDeadline = inviteDeadline;
		dbHandler.update(TABLE_NAME, id, "INVITE_DEADLINE", inviteDeadline);
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