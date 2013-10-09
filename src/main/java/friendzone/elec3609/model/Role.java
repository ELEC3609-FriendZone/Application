package friendzone.elec3609.model;

import java.util.ArrayList;
import java.util.List;

public enum Role{
	MANAGER ("Project Manager"), 
	PROGRAMMER ("Programmer"),
	DESIGNER ("Designer");
	//NEED MORE
	
	private final String name;
	private Role(String name){
		this.name = name;
	}
	public String toString(){
		return name;
	}
	
	public static List<String> getNames(){
		List<String> names = new ArrayList<String>();
		for (Role role : Role.values()){
			names.add(role.toString());
		}
		return names;
	}
	
	public static Role findMatch(String name){
		Role matchingRole = null;
		for (Role role : Role.values()){
			if (role.name.equals(name)){
				matchingRole = role;
				break;
			}
		}
		return matchingRole;
	}
}