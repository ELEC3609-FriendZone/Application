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



@Controller

public class AdminController {



// need a subject service, to get all the subjects 



@RequestMapping("/admin")
public String index(Model model, HttpServletRequest request) {


return "admin";



}



}