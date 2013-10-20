package friendzone.elec3609.controller;

import java.util.Map;
import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import friendzone.elec3609.model.UnitOfStudy;
import friendzone.elec3609.service.DatabaseHandler;
import friendzone.elec3609.model.Project;
import friendzone.elec3609.model.Student;
import friendzone.elec3609.model.Team;

@Controller
public class SubjectController {

	@Autowired
	DatabaseHandler dbHandler;
	
	//Dummy Data For now, so i can test the content on the page
	UnitOfStudy uos =  new UnitOfStudy("ELEC3609", "Internet Software Platforms", 500);
	Project project = new Project(uos,  new java.sql.Date(8099,10,15));
	Team team = new Team(12345, project);
	
	//Functions to be used for mapping
	//Student stu;
	//Team team;  // Need to be able to get a particular team that a student is in
	//Project project = team.getProject();
	//UnitOfStudy uos = project.getParent();
	
	
	//mapping out what will go on the page
	@RequestMapping("/subject")
	public String getEnums(Map<String, Object> map){
		//The heading of the page will include the Unit Of Study Details
		map.put("unitcode", uos.getUnitCode());
		map.put("unitname", uos.getUnitName());
		
		//The side bar of the page, includes team member details
		map.put("team", team.getMembers());
		
		//The main part contains the project and the subject description
		map.put("project-description", project.getDescription());
		map.put("subject-description", uos.getDescription());
		
		//return page
		return "subject";
	}

}