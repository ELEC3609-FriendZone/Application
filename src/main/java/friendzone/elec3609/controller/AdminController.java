package friendzone.elec3609.controller;

import java.sql.Date;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import friendzone.elec3609.model.*;

import java.util.Date;

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
		
		//Gets the units for the administrator
		ArrayList<UnitOfStudy> units = admin.getUnits();
		System.out.println("units:");
		System.out.println(units);
		
		//puts uni Names into an arraylist of strings
		ArrayList<String> unitNames = new ArrayList<String>();
		for(UnitOfStudy u: units)
		{
			unitNames.add(String.valueOf(u.getUnitName()));
			System.out.println(String.valueOf(u.getUnitName()));
		}
		
		System.out.println(unitNames);
		//maps
		map.put("admin", admin);
		map.put("units", units);
		map.put("unitNames", unitNames);
		
		return "admin";
	
	}
	
	@RequestMapping("/admin/makeProject")
	public String makeProject(HttpServletRequest request){
		Date inviteDeadline = new Date(System.currentTimeMillis());
		Date start = new Date(inviteDeadline.getTime() + 10000000L);
		Date deadline = new Date(start.getTime() + 1000000000L);
		
		int max = 0;
		int min = 0;
		String projectName ="";
		String unitCode="";
		
		//Sets the ucode
		if(request.getParameterMap().containsKey("ucode"))
		{
			//checks it is not empty
			if(request.getParameter("ucode") != "")
				unitCode = request.getParameter("ucode");
		}
		
		//sets the pname
		if(request.getParameterMap().containsKey("pname"))
		{
			//checks it is not empty
			if(request.getParameter("pname") != "")
				projectName = request.getParameter("pname");
		}
		
		//sets the min
		if(request.getParameterMap().containsKey("mingm"))
		{
			//checks it is not empty
			if(request.getParameter("mingm") != "")
				min = Integer.valueOf(request.getParameter("mingm"));
		}
		
		//sets the max
		if(request.getParameterMap().containsKey("maxgm"))
		{
			//checks it is not empty
			if(request.getParameter("maxgm") != "")
				max = Integer.valueOf(request.getParameter("maxgm"));
		}
		
		//Project p = new Project();
		return "redirect:/admin/";
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