package friendzone.elec3609.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import friendzone.elec3609.model.*;
import friendzone.elec3609.service.DatabaseHandler;

@Controller
@SessionAttributes({"student", "dbHandler"})
public class MainHomeController {

	final static DatabaseHandler dbHandler = DatabaseHandler.getInstance();
	// test login sbro3659 password
	@RequestMapping(value ="/mainHome", method = RequestMethod.POST)
	public String loginValidation(HttpServletRequest request, 
			Model model, Map<String, Object> map){
		
/** This log-in code should work but I'll leave the dummy data for now		
*/
		String unikey = request.getParameter("username");
		String enteredPassword = dbHandler.hashPassword(request.getParameter("password"));
		System.out.println("Hashed input length=" + enteredPassword.length() + ", value=" + enteredPassword);
		String actualPassword = null;
		
		if(unikey == null || enteredPassword == null){
			//should tell user to fill in all fields
			System.out.println("Fields left empty");
			return "redirect:/";
		}
		
		if ((actualPassword = dbHandler.getAuthentication(unikey)) == null){
			//should tell user that an account doesn't exist for this unikey
			System.out.println("User doesn't exist");
			return "redirect:/";
		}
		
		
		
		if (!enteredPassword.matches(actualPassword)) {
			//should tell the user the password is incorrect
			System.out.println("Wrong password: entered " + "\"" + enteredPassword + "\"" + " expected " + "\"" + actualPassword + "\"");
			return "redirect:/";
		}
		
		// if we reach this point, we know student exists and password is correct so just grab the student object and add it to the session attributes
		Student student = dbHandler.getStudent(dbHandler.getSID(unikey));
		
		// now we need to load in this students list of subjects and the projects in those subjects to that the view can see them
		
		// checks if the student is currently enrolled in subjects. this is for the list for students to choose
		// if they have not yet entered a group or with to join a group.
		List<UnitOfStudy> subjectList = student.getSubjects();
		
		// within each unit of study there are list of projects
		List<List<Project>> projects = new ArrayList<List<Project>>(); 
		
		for (UnitOfStudy uos: subjectList){
			System.out.println(uos.getUnitCode());
			projects.add(uos.getProjects());
		}
		
		map.put("units", projects);
		
		
		if(projects.size() != 0 ) {
			for (List<Project> il: projects) {
				
				System.out.println();
			}
		}
		
		// use it like this when u link to group form action=post name="id"
		//SUBJECT
		//	PROJECT
		//form action=post name="id" value = projects.get(0).get(0).getId()> group 1
		model.addAttribute("student", student);
		return "mainHome";
	}

}