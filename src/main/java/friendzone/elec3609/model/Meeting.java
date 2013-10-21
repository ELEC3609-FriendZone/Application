package friendzone.elec3609.model;

import java.sql.Timestamp;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import friendzone.elec3609.service.DatabaseHandler;

public class Meeting {
	
	@Autowired
	DatabaseHandler dbHandler;
	
	private final String TABLE_NAME = "Meeting";
	
	int id;
	int teamID;
	Timestamp start, end;
	String location;
	
	public Meeting(int teamID, Timestamp start, Timestamp end, String location){
		this.teamID = teamID;
		this.start = start;
		this.end = end;
		id = dbHandler.addMeeting(teamID, start, end);
		setLocation(location);
	}
	
	public Meeting(int id, int teamID, Timestamp start, Timestamp end, String location){
		this.teamID = teamID;
		this.start = start;
		this.end = end;
		this.id = id;
		setLocation(location);
	}
	
	
	public void setLocation(String location){
		this.location = location;
		dbHandler.update(TABLE_NAME, teamID, "LOCATION", location);
	}
	
	public ArrayList<Student> getAttendees(){
		return dbHandler.getMembers(teamID);
	}
	
	public Team getTeam(){
		return dbHandler.getTeam(teamID);
	}
	
	public void copyValues(Meeting meeting){
		this.id = meeting.id;
		this.teamID = meeting.teamID;
		this.start = meeting.start;
		this.end = meeting.end;
		this.location = meeting.location;
	}
}
