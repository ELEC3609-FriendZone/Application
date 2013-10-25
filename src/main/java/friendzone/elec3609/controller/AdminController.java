package friendzone.elec3609.controller;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import friendzone.elec3609.model.*;

import java.util.ArrayList;
import java.util.List;
import friendzone.elec3609.service.DatabaseHandler;
import friendzone.elec3609.service.MeetingsMaker;

@Controller
public class AdminController {

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
	public String newMeeting(Map<String, Object> map){
		

		return "groupDrafter";
	}

}