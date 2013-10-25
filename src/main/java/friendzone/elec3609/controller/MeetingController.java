package friendzone.elec3609.controller;

import java.sql.Timestamp;
import java.util.ArrayList;

import java.util.Iterator;
import java.util.Map;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import friendzone.elec3609.service.DatabaseHandler;
import friendzone.elec3609.service.MeetingsMaker;
import friendzone.elec3609.model.*;

@Controller
@SessionAttributes({"student", "dbHandler"})
public class MeetingController {

	final static DatabaseHandler dbHandler = DatabaseHandler.getInstance();
	
	//dummy data
	//Student student = dbHandler.getStudent("123456789");
//	UnitOfStudy uos =  new UnitOfStudy("ELEC3609", "Internet Software Platforms", 500);
//	Project project = new Project(uos.getUnitCode(),  "aProject",new java.sql.Date(8099,10,15));
//	Team team = new Team(1234, project.getID(), "FriendZone");
	Team team = dbHandler.getTeam(1);
	//Meeting meeting = dbHandler.getMeetings("371830314").get(0);
	//Student student = dbHandler.getStudent("325516396");
	Student student = dbHandler.getStudent(team.getMembers().get(0).getSID());
	
	@RequestMapping("/meeting")
	public String getEnums(Map<String, Object> map){

		
		List<Meeting> meetings = student.getMeetings();

		ArrayList<String> meetingID = new ArrayList<String>();
		ArrayList<String> meetingStart = new ArrayList<String>();
		ArrayList<String> meetingEnd = new ArrayList<String>();
		ArrayList<String> meetingLocations = new ArrayList<String>();
		for(Meeting m: meetings)
		{
			meetingLocations.add(m.getLocation());
			meetingID.add(String.valueOf(m.getID()));
			meetingStart.add(String.valueOf(m.getStart()));
			meetingEnd.add(String.valueOf(m.getEnd()));
		}
		//map the meetings
		map.put("meetingLocation", meetingLocations);
		map.put("meetingID", meetingID);
		map.put("meetingStart", meetingStart);
		map.put("meetingEnd", meetingEnd);
		map.put("teamID", team.getID());
		map.put("teamName", team.getName());
		map.put("meetings", meetings);
		
		return "meeting";
	}
	
	@RequestMapping("/meeting/new")
	public String newMeeting(Map<String, Object> map){
		
		//Get an arrayList<String> Of team names
		List<Team> studentTeams = student.getTeams();
		ArrayList<String> teamNames = new ArrayList<String>();
		ArrayList<String> teamIDs = new ArrayList<String>();
		for(Team t: studentTeams)
		{
			teamNames.add(t.getName());
			teamIDs.add(String.valueOf(t.getID()));
		}
		
		//the availability
		boolean[][] avail = student.getAvailability();
		List<String> availStrings = new ArrayList<String>();
		for(int i=0; i<7; i++){
			for(int j=0; j<12; j++){
				availStrings.add(String.valueOf(avail[i][j]));
			}
		}
		
		
		MeetingsMaker mm = new MeetingsMaker();
		int[][] ca = mm.countAvailability(team);
		
		//Converts the given 2d Boolean array into strings
		//Corresponding to their respective days
		ArrayList<String> availMondayStrings = new ArrayList<String>();
		ArrayList<String> availTuesdayStrings = new ArrayList<String>();
		ArrayList<String> availWednesdayStrings = new ArrayList<String>();
		ArrayList<String> availThursdayStrings = new ArrayList<String>();
		ArrayList<String> availFridayStrings = new ArrayList<String>();
		ArrayList<String> availSaturdayStrings = new ArrayList<String>();
		ArrayList<String> availSundayStrings = new ArrayList<String>();
		
		for(int j=0;j<12; j++)
		{
			//availMondayStrings.add(String.)
			availMondayStrings.add(String.valueOf(ca[0][j]));
			availTuesdayStrings.add(String.valueOf(ca[1][j]));
			availWednesdayStrings.add(String.valueOf(ca[2][j]));
			availThursdayStrings.add(String.valueOf(ca[3][j]));
			availFridayStrings.add(String.valueOf(ca[4][j]));
			availSaturdayStrings.add(String.valueOf(ca[5][j]));
			availSundayStrings.add(String.valueOf(ca[6][j]));
		}
		//ArrayList<String>teamNames2 =teamNames;
		//ArrayList<String>[][] members= mm.getMembers();
		ArrayList<String> availMembers = mm.getAvailableMembers(0, 0, team);
		map.put("availabilityMonday", availMondayStrings);
		map.put("availabilityTuesday", availTuesdayStrings);
		map.put("availabilityWednesday", availWednesdayStrings);
		map.put("availabilityThursday", availThursdayStrings);
		map.put("availabilityFriday", availFridayStrings);
		map.put("availabilitySaturday", availSaturdayStrings);
		map.put("availabilitySunday", availSundayStrings);
		map.put("teamNames", teamNames);
		map.put("teamID", teamIDs);
		map.put("maxCount", mm.getMaxCount());
		map.put("teamSize", team.getMembers().size());
		//map.put("availMembers", );
		
		// Header details 
		map.put("firstname", student.getFirstName());
		map.put("lastname", student.getLastName());
		
		return "newMeeting";
	}
	
	@RequestMapping("/meeting/create")
	public String makeMeeting(HttpServletRequest request, @ModelAttribute("student") Student student){
		
		int teamID = 1;
		String meetinglocation = "";
		String s ="";
		int day= 0;
		String date ="2013-09-27 22:00:00";
		String endDate = "2013-09-27 23:00:00";
		MeetingsMaker mm = new MeetingsMaker();
		
		//Sets form data
		if(request.getParameterMap().containsKey("teamChosen"))
		{
			teamID = Integer.parseInt(request.getParameter("teamChosen"));
			
		}
		if(request.getParameterMap().containsKey("location"))
		{
			meetinglocation = request.getParameter("location");
		}
		
		for (int i=0; i<7; i++){
		   for (int j=0; j < 12; j++){
			   s = ("avail" + i + j);
			   if(request.getParameterMap().containsKey(s))
			   {
				   //Gives the Date
				   String m ="";
					String d ="";
					String time ="";
					
					switch(i)
					{
					case '0':
						m="10";
						d="04";
						break;
					case '1':
						m="10";
						d="05";
						break;
					case '2':
						m="10";
						d="06";
						break;
					case '3':
						m="10";
						d="07";
						break;
					case '4':
						m="10";
						d="08";
						break;
					case '5':
						m="10";
						d="09";
						break;
					case '6':
						m="10";
						d="10";
						break;
					}
					
					//Switch Case time
					switch(j)
					{
					case 0:
						time="08:00:00";
						break;
					case 1:
						time="09:00:00";
						break;
					case 2:
						time="10:00:00";
						break;
					case 3:
						time="11:00:00";
						break;
					case 4:
						time="12:00:00";
						break;
					case 5:
						time="13:00:00";
						break;
					case 6:
						time="14:00:00";
						break;
					case 7:
						time="15:00:00";
						break;
					case 8:
						time="16:00:00";;
						break;
					case 9:
						time="17:00:00";
						break;
					case 10:
						time="18:00:00";
						break;
					case 11:
						time="19:00:00";
						break;
					}
					System.out.println(m);
					System.out.println(d);
					System.out.println(time);
					date = "2013-" + m + "-" + d + " " + time;
					System.out.println("date:");
					System.out.println(date);
			   }
//			   else
//			   {
//				   
//			   }
		   }
		}
		
		//student
		// Meeting(int id, int teamID, Timestamp start, Timestamp end, String location)
		//new java.sql.Timestamp(2013, 12, 10, 4, 20, 20, 1
		//Timestamp start = new java.sql.Timestamp()
		new Meeting(teamID, Timestamp.valueOf(date), Timestamp.valueOf(endDate),meetinglocation);
		//dbHandler.addMeeting(teamID,Timestamp.valueOf(date), Timestamp.valueOf(endDate));
		//timestamp '2013-09-27 23:00:00'
		return "redirect:/meeting/"; //redirect to main meeting page
	}

}