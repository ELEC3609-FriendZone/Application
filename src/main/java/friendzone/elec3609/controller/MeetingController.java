package friendzone.elec3609.controller;

import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import friendzone.elec3609.service.DatabaseHandler;
import friendzone.elec3609.model.*;

@Controller
public class MeetingController {

	final static DatabaseHandler dbHandler = DatabaseHandler.getInstance();
	
	//dummy data
	Student student = dbHandler.getStudent("123456789");
	UnitOfStudy uos =  new UnitOfStudy("ELEC3609", "Internet Software Platforms", 500);
	Project project = new Project(uos.getUnitCode(),  "aProject",new java.sql.Date(8099,10,15));
	Team team = new Team(1234, project.getID(), "FriendZone");
	Meeting meeting = new Meeting(1234, 1234, null, null, "School Of IT");
	
	
	@RequestMapping("/meeting")
	public String getEnums(Map<String, Object> map){
		//using dbHandler
		//This worked
		dbHandler.addUnitOfStudy("ELEC3609", "Internet Software Platforms", 500);
		System.out.println("LOOK HERE");
		System.out.println("LOOK HERE");
		System.out.println("LOOK HERE");
		System.out.println("LOOK HERE");
		System.out.println(dbHandler.getUnitOfStudy("ELEC3609"));
		
		//Worked but is empty :/
		dbHandler.addProject("ELEC3609",  "aProject",new java.sql.Date(8099,10,15));
		System.out.println("LOOK HERE");
		System.out.println("LOOK HERE");
		System.out.println("LOOK HERE");
		System.out.println("LOOK HERE");
		System.out.println(dbHandler.getProjects("ELEC3609"));
		
		
		System.out.println("LOOK HERE");
		System.out.println("LOOK HERE");
		System.out.println("LOOK HERE");
		System.out.println("LOOK HERE");
		System.out.println(dbHandler.getTeams(2));
		
		//works
		student.enrolTo("ELEC3609");
		System.out.println("LOOK HERE");
		System.out.println("LOOK HERE");
		System.out.println("LOOK HERE");
		System.out.println("LOOK HERE");
		System.out.println(dbHandler.getStudent("123456789"));
		System.out.println(dbHandler.getStudent("123456789").getSubjects());
		
		
		
		student.joinTeam(1);
		System.out.println("LOOK HERE");
		System.out.println("LOOK HERE");
		System.out.println("LOOK HERE");
		System.out.println("LOOK HERE");
		System.out.println(dbHandler.getStudent("123456789").getTeams());
		
		
		map.put("studentTeams",student.getTeams());
		map.put("meetingAttendees", meeting.getAttendees());
		map.put("teamMembers", team.getMembers());
		map.put("meetingLocation", meeting.getLocation());
		
		return "meeting";
	}
	

}