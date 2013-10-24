package friendzone.elec3609.controller;

import java.util.ArrayList;

import java.util.Iterator;
import java.util.Map;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import friendzone.elec3609.service.DatabaseHandler;
import friendzone.elec3609.service.MeetingsMaker;
import friendzone.elec3609.model.*;

@Controller
public class MeetingController {

	final static DatabaseHandler dbHandler = DatabaseHandler.getInstance();
	
	//dummy data
	//Student student = dbHandler.getStudent("371830314");
//	UnitOfStudy uos =  new UnitOfStudy("ELEC3609", "Internet Software Platforms", 500);
//	Project project = new Project(uos.getUnitCode(),  "aProject",new java.sql.Date(8099,10,15));
//	Team team = new Team(1234, project.getID(), "FriendZone");
	Team team = dbHandler.getTeam(2);
	//Meeting meeting = dbHandler.getMeetings("371830314").get(0);
	Student student = dbHandler.getStudent(team.getMembers().get(0).getSID());
	
	@RequestMapping("/meeting")
	public String getEnums(Map<String, Object> map){
//		//using dbHandler
//		//This worked
//		dbHandler.addUnitOfStudy("ELEC3609", "Internet Software Platforms", 500);
//		System.out.println("LOOK HERE");
//		System.out.println("LOOK HERE");
//		System.out.println("LOOK HERE");
//		System.out.println("LOOK HERE");
//		System.out.println(dbHandler.getUnitOfStudy("ELEC3609"));
//		
//		//Worked but is empty :/
//		dbHandler.addProject("ELEC3609",  "aProject",new java.sql.Date(8099,10,15));
//		System.out.println("LOOK HERE");
//		System.out.println("LOOK HERE");
//		System.out.println("LOOK HERE");
//		System.out.println("LOOK HERE");
//		System.out.println(dbHandler.getProjects("ELEC3609"));
//		
//		
//		System.out.println("LOOK HERE");
//		System.out.println("LOOK HERE");
//		System.out.println("LOOK HERE");
//		System.out.println("LOOK HERE");
//		System.out.println(dbHandler.getTeams(2));
//		
//		//works
//		student.enrolTo("ELEC3609");
//		System.out.println("LOOK HERE");
//		System.out.println("LOOK HERE");
//		System.out.println("LOOK HERE");
//		System.out.println("LOOK HERE");
//		System.out.println(dbHandler.getStudent("123456789"));
//		System.out.println(dbHandler.getStudent("123456789").getSubjects());
//		
//		
//		
//		student.joinTeam(1);
//		System.out.println("LOOK HERE");
//		System.out.println("LOOK HERE");
//		System.out.println("LOOK HERE");
//		System.out.println("LOOK HERE");
//		System.out.println(dbHandler.getStudent("123456789").getTeams());
		
		
		System.out.println("LOOK HERE");
		System.out.println("LOOK HERE");
		System.out.println("LOOK HERE");
		System.out.println("LOOK HERE");
		System.out.println(team.toString());
/**		
		//////////////////////////////////////////////////////////////////////////////////
		Student student = dbHandler.getStudent("371830314");
		
		//Make Lists!
		List<UnitOfStudy> uosList = student.getSubjects();
		ArrayList<String> uosStringList = new ArrayList<String>();
		ArrayList<String> projectUoS = new ArrayList<String>();
		
		//Loops through the Unit of study list
		//Converts it to an array list of strings
		//If it has a project add it onto projectUosList
		for(UnitOfStudy uos: uosList)
		{
			uosStringList.add(uos.toString());
			if(!uos.getProjects().isEmpty())
				projectUoS.add(uos.toString());
		}
		
	
		//New ArrayList of Ints to store indexes 
		//These are the indexes of the UoS with projects
		ArrayList<Integer> UoSindexes = new ArrayList<Integer>();
		
		
		//Loop through both array Lists and compare strings
		for(int i=0; i<uosStringList.size(); i++)
		{
			for(int j=0; j<projectUoS.size(); j++)
			{
				//if there is a match, store the index
				if(uosStringList.get(i)==projectUoS.get(j))
					UoSindexes.add(i);
				
			}
		}
		
		
		//Repeats the process to get the project with teams
		for(Integer i: UoSindexes)
		{
			List<Project> projectList = student.getSubjects().get(i).getProjects();
			ArrayList<String> projectStringList = new ArrayList<String>();
			ArrayList<String> teamProject = new ArrayList<String>();
			
			//Loops through the Project list
			//Converts it to an array list of strings
			//If it has a team add it onto teamProjectList
			for(Project p: projectList)
			{
				projectStringList.add(p.toString());
				if(!p.getTeams().isEmpty())
					teamProject.add(p.toString());
			}
			
			//New ArrayList of Ints to store indexes 
			//These are the indexes of the Projects with teams
			ArrayList<Integer> projectIndexes = new ArrayList<Integer>();
			
			
			//Loop through both array Lists and compare strings
			for(int y=0; y<projectStringList.size(); y++)
			{
				for(int z=0; z<teamProject.size(); z++)
				{
					//if there is a match, store the index
					if(projectStringList.get(y)==teamProject.get(z))
						projectIndexes.add(y);
					
				}
			}
			
			//NOW HERE I WANT TO STORE THE value of 'i' AND the projectIndexes together
		}
	
		
		/////////////////////////////////////////////////////////////////////////////////
*/		
		//dbHandler.addMeeting(1, );
		//dbHandler.addMeeting(1, new java.sql.Timestamp(2013, 12, 10, 3, 50, 20, 1), new java.sql.Timestamp(2013, 12, 10, 4, 20, 20, 1));
//		INSERT INTO meeting VALUES (1,1, timestamp '2013-09-27 23:00:00', timestamp '2013-09-27 23:30:00', 'School OF IT')
//		map.put("studentTeams",student.getTeams());
//		map.put("meetingAttendees", meeting.getAttendees());
		map.put("teamMembers", team.getMembers());
//		map.put("meetingLocation", meeting.getLocation());
		map.put("team", dbHandler.getStudent(team.getMembers().get(0).getSID()).getTeams());
		map.put("teamID", team.getID());
		map.put("teamName", team.getName());
		map.put("meetingLocation", dbHandler.getMeetings(team.getMembers().get(0).getSID()));
		//map.put("meetingLocation", dbHandler.getMeetings("371830314").get(0).getLocation());
		//map.put("team", dbHandler.getMeetings("371830314").get(0).getAttendees());
		
		return "meeting";
	}
	
	@RequestMapping("/meeting/new")
	public String editProfile(Map<String, Object> map){
		
		//Get an arrayList<String> Of team names
		List<Team> studentTeams = student.getTeams();
		ArrayList<String> teamNames = new ArrayList<String>();
		for(Team t: studentTeams)
		{
			teamNames.add(t.getName());
		}
		
		MeetingsMaker mm = new MeetingsMaker();
		
		int[][] ca = mm.countAvailability(team);
//		System.out.println("LOOK HERE");
//		System.out.println("LOOK HERE");
//		System.out.println("LOOK HERE");
		System.out.println("LOOK HERE ca:");
		System.out.println(ca);
		//If this works ^ YAY IT DOES
		
		//Converts the given 2d Boolean array into strings
		//Corresponding to their respective days
		List<String> availMondayStrings = new ArrayList<String>();
		List<String> availTuesdayStrings = new ArrayList<String>();
		List<String> availWednesdayStrings = new ArrayList<String>();
		List<String> availThursdayStrings = new ArrayList<String>();
		List<String> availFridayStrings = new ArrayList<String>();
		List<String> availSaturdayStrings = new ArrayList<String>();
		List<String> availSundayStrings = new ArrayList<String>();
		
		for(int j=0;j<12; j++)
		{
			availMondayStrings.add(String.valueOf(ca[0][j]));
			availTuesdayStrings.add(String.valueOf(ca[1][j]));
			availWednesdayStrings.add(String.valueOf(ca[2][j]));
			availThursdayStrings.add(String.valueOf(ca[3][j]));
			availFridayStrings.add(String.valueOf(ca[4][j]));
			availSaturdayStrings.add(String.valueOf(ca[5][j]));
			availSundayStrings.add(String.valueOf(ca[6][j]));
		}
			
		

		map.put("availabilityMonday", availMondayStrings);
		map.put("availabilityTuesday", availTuesdayStrings);
		map.put("availabilityWednesday", availWednesdayStrings);
		map.put("availabilityThursday", availThursdayStrings);
		map.put("availabilityFriday", availFridayStrings);
		map.put("availabilitySaturday", availSaturdayStrings);
		map.put("availabilitySunday", availSundayStrings);
		map.put("teamNames", teamNames);
		return "newMeeting";
	}

}