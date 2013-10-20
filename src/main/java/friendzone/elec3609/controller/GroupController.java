package friendzone.elec3609.controller;

import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import friendzone.elec3609.service.DatabaseHandler;

@Controller
public class GroupController {

	@Autowired
	DatabaseHandler dbHandler;
	
	@RequestMapping("/group")
	public String getEnums(Map<String, Object> map){

		return "group";
	}
	

}