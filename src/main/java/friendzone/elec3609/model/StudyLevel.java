package friendzone.elec3609.model;

import java.util.ArrayList;
import java.util.List;

public enum StudyLevel{
	
	UNDERGRADUATE ("Undergraduate"),
	POSTGRADUATE ("Postgraduate"),
	OTHER ("Other");

	private final String name;
	private StudyLevel(String name){
		this.name = name;
	}
	public String toString(){
		return name;
	}
	
	public static List<String> getNames(){
		List<String> names = new ArrayList<String>();
		for (StudyLevel studyLevel : StudyLevel.values()){
			names.add(studyLevel.toString());
		}
		return names;
	}
	
	public static StudyLevel findMatch(String name){
		StudyLevel matchingStudyLevel = null;
		for (StudyLevel studyLevel : StudyLevel.values()){
			if (studyLevel.name.equals(name)){
				matchingStudyLevel = studyLevel;
				break;
			}
		}
		return matchingStudyLevel;
	}
}