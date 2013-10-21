package friendzone.elec3609.model;

import java.sql.Timestamp;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import friendzone.elec3609.service.DatabaseHandler;

public class Meeting {
	
	@Autowired
	DatabaseHandler dbHandler;
	
	int id;
	int teamID;
	Timestamp start, end;
	String location;
	
	public Meeting(int meetingID, int teamID, Timestamp start, Timestamp end, String location){
		this.id = meetingID;
		this.teamID = teamID;
		this.start = start;
		this.end = end;
		this.location = location;
		//insert into db
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
