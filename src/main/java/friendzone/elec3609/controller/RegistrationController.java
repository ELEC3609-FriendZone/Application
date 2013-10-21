package friendzone.elec3609.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import friendzone.elec3609.model.ProgrammingLanguage;
import friendzone.elec3609.model.Role;
import friendzone.elec3609.model.SocialMedia;
import friendzone.elec3609.model.Student;
import friendzone.elec3609.model.StudyLevel;
import friendzone.elec3609.service.DatabaseHandler;

@Controller
public class RegistrationController {

	@Autowired
	DatabaseHandler dbHandler;
	
	@RequestMapping("/registration")
	public String getEnums(Map<String, Object> map){
		map.put("programmingLanguages", ProgrammingLanguage.getNames());
		map.put("roles", Role.getNames());	
		map.put("socialMediaProviders", SocialMedia.Provider.getNames());
		map.put("studyLevels", StudyLevel.getNames());
		return "registration";
	}
	
	@RequestMapping("/registration/add")
	public String addStudent(HttpServletRequest request){
		
		String[] languageStrings = request.getParameterValues("languages");
		ProgrammingLanguage[] languages = new ProgrammingLanguage[languageStrings.length];
		for (int i = 0; i != languageStrings.length; ++i){
			languages[i] = ProgrammingLanguage.findMatch(languageStrings[i]);
		}
		Student student = new Student(
										request.getParameter("sid"),
										request.getParameter("unikey"),
										request.getParameter("password"),
										request.getParameter("firstName"),
										request.getParameter("lastName"),
										request.getParameter("primaryEmail"),
										request.getParameter("mobile"),
										StudyLevel.findMatch(request.getParameter("studyLevel")),
										Boolean.parseBoolean(request.getParameter("esl")), 
										languages
									 );
		student.setExperience(request.getParameter("experience"));
		student.setCourse(request.getParameter("course"));
		student.setSecondaryEmail(request.getParameter("secondaryEmail"));
		return "redirect:/"; //return to index page
	}
}