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

import friendzone.elec3609.model.Invitation;
import friendzone.elec3609.model.Project;
import friendzone.elec3609.model.Student;
import friendzone.elec3609.model.Team;
import friendzone.elec3609.model.UnitOfStudy;
import friendzone.elec3609.service.DatabaseHandler;

@Controller
@SessionAttributes({"student", "dbHandler"})
public class GroupController {

	final static DatabaseHandler dbHandler = DatabaseHandler.getInstance();
	
	@RequestMapping(value = "/group", method = RequestMethod.GET)
	public String viewProject(@ModelAttribute("student") Student student, 
							HttpServletRequest request, Map<String, Object> map ){
		
		// test print out
		System.out.println(student.getFirstName().toString());
		System.out.println(request.getParameter("projID"));
		
		boolean hasTeamInCurrentProj;
		ArrayList<Student> memberList;
		
		if(request.getParameter("projID") != null) { // if belongs to a project
			Team team = dbHandler.getTeam(Integer.parseInt(request.getParameter("projId")));
			System.out.println(team.getName().toString());
			hasTeamInCurrentProj = true;
			
			// make member list
			 memberList = team.getMembers();
			for(Student stud: memberList)
				System.out.println(stud.getFirstName());
			
		} else {
			// there are no projects under this unit of study
			hasTeamInCurrentProj = false;
			return "redirect:/group/join";
		}
		
		
		
		
			
			
		
		return "group";
	}
	
	@RequestMapping("/group/join")
	public String needsAGroup(@ModelAttribute("student") Student student, HttpServletRequest request) {
		
		return "group_invite";
	}
	
	
	@RequestMapping("/group/invite")
	public String inviteFriends(@ModelAttribute("student") Student student, HttpServletRequest request) {
		Team currentTeam = student.getTeam(Integer.parseInt(request.getParameter("projID")));
		boolean isInTeam;
		if(currentTeam == null)
			isInTeam = false;
		else
			isInTeam = true;
		
		// makes a uos object by first getting a project Object using proj id param
		UnitOfStudy currentUoS = dbHandler.getProject(Integer.parseInt(request.getParameter("projID"))).getParent();
		// this includes the user student as well so omit themself in the view when printing
		List<Student> studentListInSelectedProj = dbHandler.getStudentsInUoS(currentUoS.getUnitCode());
		
		return "group_invite";
	}
	
	@RequestMapping(value = "/group/invite", method = RequestMethod.POST)
	public String createInvitation(@ModelAttribute("student") Student student, HttpServletRequest request) {
		Team currentTeam = student.getTeam(Integer.parseInt(request.getParameter("projID")));
		//Invitation invite = new Invitation()
		return "group_invite";
	}
	

}