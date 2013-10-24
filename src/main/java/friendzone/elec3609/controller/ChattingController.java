package friendzone.elec3609.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import friendzone.elec3609.model.*;
import friendzone.elec3609.service.DatabaseHandler;

@Controller
@SessionAttributes({"student", "dbHandler"})
public class ChattingController {
	final static DatabaseHandler dbHandler = DatabaseHandler.getInstance();
	
	// chatting for a team so should pass on groupID to here as form
	@RequestMapping(value = "/chatting", method = RequestMethod.GET)
	public String createInvitation(@ModelAttribute("student") Student student, HttpServletRequest request) {
		ArrayList<Message> message = new ArrayList<Message>();
		int hasNewMessage = 0;
		student.getSID();
		
		
		
		return "group_invite";
	}
}
