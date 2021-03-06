package friendzone.elec3609.controller;

import java.util.Map;




import javax.servlet.http.HttpServletRequest;




import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import friendzone.elec3609.model.*;

import java.util.ArrayList;
import java.util.List;

import friendzone.elec3609.service.DatabaseHandler;

@Controller
@SessionAttributes({"student", "dbHandler"})
public class AvailabilityController {

	

	final static DatabaseHandler dbHandler = DatabaseHandler.getInstance();

	//Dummy Data For now, so i can test the content on the page
//	Student student = dbHandler.getStudent("390076323");

	//Student student = new Student("123456789", "abcd1234", "password", "aFirstName", "aLastname", "abcd1234@uni.sydney.edu.au", "0412345678", StudyLevel.UNDERGRADUATE, true, new ProgrammingLanguage[] {ProgrammingLanguage.C, ProgrammingLanguage.JAVA});
//	UnitOfStudy uos =  new UnitOfStudy("ELEC3609", "Internet Software Platforms", 500);
//	Project project = new Project(uos.getUnitCode(),  "aProject",new java.sql.Date(8099,10,15));
//	Team team = new Team(project.getID(), "FriendZone");
//	
	//Dummy boolean availability data
	boolean[][] availability = new boolean[][]{{true, true, false, false, true, false, true, false, false, false, true, false},
												{true, true, false, false, true, true, false, true, true, false, true, false},
												{true, true, false, false, false, false, true, true, false, false, true, false},
												{false, true, false, true, true, false, true, true, false, false, true, false},
												{true, false, false, true, false, false, false, true, true, false, true, false},
												{false, true, false, false, true, false, true, false, false, false, true, false},
												{true, true, false, true, false, false, true, true, false, false, true, false}};									

	
	@RequestMapping("/availability")
	public String getEnums(Map<String, Object> map, @ModelAttribute("student") Student student){
		//storing the data of availability
		//availability information

		//student.setAvailability(availability);
		
		
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
			availMondayStrings.add(String.valueOf(student.getAvailability()[0][j]));
			availTuesdayStrings.add(String.valueOf(student.getAvailability()[1][j]));
			availWednesdayStrings.add(String.valueOf(student.getAvailability()[2][j]));
			availThursdayStrings.add(String.valueOf(student.getAvailability()[3][j]));
			availFridayStrings.add(String.valueOf(student.getAvailability()[4][j]));
			availSaturdayStrings.add(String.valueOf(student.getAvailability()[5][j]));
			availSundayStrings.add(String.valueOf(student.getAvailability()[6][j]));
		}
			


//		//The heading of the page will include the Unit Of Study Details
//		map.put("unitcode", uos.getUnitCode());
//		map.put("unitname", uos.getUnitName());
//		
//		//The side bar of the page, includes team member details
//		map.put("team", team.getMembers());
		
		//The availability Stuff for the body information
		//Corresponding to their respective days
		map.put("availabilityMonday", availMondayStrings);
		map.put("availabilityTuesday", availTuesdayStrings);
		map.put("availabilityWednesday", availWednesdayStrings);
		map.put("availabilityThursday", availThursdayStrings);
		map.put("availabilityFriday", availFridayStrings);
		map.put("availabilitySaturday", availSaturdayStrings);
		map.put("availabilitySunday", availSundayStrings);
		
		
		// Header details 
		map.put("firstname", student.getFirstName());
		map.put("lastname", student.getLastName());
				
		//return page		 		
		return "availability";
		
	}
	
	@RequestMapping("/availability/change")
	public String changeAvailability(HttpServletRequest request, @ModelAttribute("student") Student student){
		//New 2D boolean array for the availability
		boolean[][] newAvailability = new boolean[7][12];
		String s;
		
		//Checks if the parameter exists
		//If it exists it will make it true
		//else false
		for (int i=0; i<7; i++){
		   for (int j=0; j < 12; j++){
			   s = ("avail" + i + j);
			   if(request.getParameterMap().containsKey(s))
			   {
				   newAvailability[i][j] = true;
			   }
			   else
			   {
				   newAvailability[i][j] = false;
			   }
		   }
		}

		//Sets the students availability to the new availability
		student.setAvailability(newAvailability);
		
		return "redirect:/availability/"; //return to index page
	}

}