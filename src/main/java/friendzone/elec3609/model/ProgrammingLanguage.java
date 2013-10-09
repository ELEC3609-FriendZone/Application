package friendzone.elec3609.model;

import java.util.ArrayList;
import java.util.List;

public enum ProgrammingLanguage{
	
	C ("C"),
	CPLUSPLUS ("C++"),
	CSHARP ("C#"),
	VISUALBASIC ("Visual Basic"),
	JAVA ("Java"),
	PYTHON ("Python");
	//NEED MORE!
	
	private final String name;
	private ProgrammingLanguage(String name){
		this.name = name;
	}
	public String toString(){
		return name;
	}
	
	public static List<String> getNames(){ 
		List<String> names = new ArrayList<String>();
		for (ProgrammingLanguage language : ProgrammingLanguage.values()){
			names.add(language.toString());
		}
		return names;
	}
	
	public static ProgrammingLanguage findMatch(String name){
		ProgrammingLanguage matchingProgrammingLanguage = null;
		for (ProgrammingLanguage language : ProgrammingLanguage.values()){
			if (language.name.equals(name)){
				matchingProgrammingLanguage = language;
				break;
			}
		}
		return matchingProgrammingLanguage;
	}
}
