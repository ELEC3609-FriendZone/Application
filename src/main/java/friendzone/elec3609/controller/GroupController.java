package friendzone.elec3609.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import friendzone.elec3609.model.Student;
import friendzone.elec3609.model.Team;
import friendzone.elec3609.service.DatabaseHandler;

@Controller
@SessionAttributes({"student", "dbHandler"})
public class GroupController {

	final static DatabaseHandler dbHandler = DatabaseHandler.getInstance();
	
	@RequestMapping(value = "/group", method = RequestMethod.GET)
	public String viewTeam(@ModelAttribute("student") Student student, HttpServletRequest request ){
		
		// test print out
		System.out.println(student.getFirstName().toString());
		
		Team team = dbHandler.getTeam(1);
		// Team team = dbHandler.getTeam(request.getParameter("id") team id
		
		ArrayList<Student> memberList = team.getMembers();
		for(Student stud: memberList)
			System.out.println(stud.getFirstName());
			
			
		
		return "group";
	}
	
	@RequestMapping("/group/invite")
	public String inviteFriends(@ModelAttribute("student") Student student) {
		
		return "group_invite";
	}
	

}