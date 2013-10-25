package friendzone.elec3609.controller;

import java.sql.Date;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import friendzone.elec3609.model.*;

import java.util.ArrayList;
import java.util.List;
import friendzone.elec3609.service.DatabaseHandler;
import friendzone.elec3609.service.GroupFormer;
import friendzone.elec3609.service.MeetingsMaker;



@Controller
public class AdminController {

	@Autowired
	GroupFormer groupFormer;
	
	final static DatabaseHandler dbHandler = DatabaseHandler.getInstance();
	Admin admin = dbHandler.getAdministrator("maxd1234");
	
	@RequestMapping("/admin")
	public String getAdminStuff(Map<String, Object> map){
	
		System.out.println(admin);
		System.out.println(admin);
		System.out.println(admin);
		System.out.println(admin);
		System.out.println(admin);
		System.out.println(admin);
		System.out.println(admin);
		//dbHandler.getUnitsOfStudy(SID);
		//-----> returns null for nowArrayList<UnitOfStudy> units = dbHandler.getAdminUnitsOfStudy("maxd1234");
//		ArrayList<String> unitNames = new ArrayList<String>();
//		
//		for(UnitOfStudy u: units)
//		{
//			unitNames.add(u.getUnitName());
//		}
//		
//		map.put("unitNames", unitNames);
		return "admin";
	
	}
	
	@RequestMapping("/admin/form")
	public String newGroups(HttpServletRequest request, Map<String, Object> map){
		
		System.out.println("Called group form");
		String projectId = "";
		if (request == null || (projectId = request.getParameter("projectId")) != null){
			groupFormer.createGroups(Integer.parseInt(projectId));
		}
		else{
			Date inviteDeadline = new Date(System.currentTimeMillis());
			Date start = new Date(inviteDeadline.getTime() + 10000000L);
			Date deadline = new Date(start.getTime() + 1000000000L);
			Project newProject = new Project("ISYS1092", "DUMMY_PROJECT", inviteDeadline, start, deadline, 3, 6);
			groupFormer.createGroups(newProject.getID());
		}
		System.out.println("Group forming is running");
		return "admin";
	}

}