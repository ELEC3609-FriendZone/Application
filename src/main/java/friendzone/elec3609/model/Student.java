package friendzone.elec3609.model;


import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Autowired;


import friendzone.elec3609.model.SocialMedia.Provider;
import friendzone.elec3609.service.DatabaseHandler;

public class Student{

	final static DatabaseHandler dbHandler = DatabaseHandler.getInstance();
	
	private final int MAX_SOCIAL_MEDIA = 2;
	private final String TABLE_NAME = "Student";
	
	String SID;
	String unikey, firstName, lastName, course, primaryEmail, secondaryEmail, mobile, experience;
	String password;
	StudyLevel studyLevel;
	Role preferredRole;
	ProgrammingLanguage[] languages;
	SocialMedia[] socialMedia = new SocialMedia[MAX_SOCIAL_MEDIA];
	boolean ESL;
	boolean[][] availability = new boolean[7][12];
	Timestamp lastViewed;
	
	public Student(String SID, String unikey, String password, String firstName, String lastName, String primaryEmail, String mobile, StudyLevel studyLevel, boolean ESL, ProgrammingLanguage[] languages){ //takes all the NOT NULL attributes as arguments
		this.SID = SID;
		this.unikey = unikey;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.primaryEmail = primaryEmail;
		this.mobile = mobile;
		this.studyLevel = studyLevel;
		this.ESL = ESL;
		this.languages = languages;
		dbHandler.addStudent(SID, unikey, password, firstName, lastName, primaryEmail, mobile, studyLevel, ESL, languages);
	}
	
	public Timestamp getLastViewed(){
		return lastViewed;
	}
	
	public void setLastViewed(Timestamp lastViewed){
		this.lastViewed = lastViewed;
	}
	
	public List<UnitOfStudy> getSubjects(){
		return dbHandler.getUnitsOfStudy(SID);
	}
	
	public void setESL(boolean ESL) {
		this.ESL = ESL;
		dbHandler.update(TABLE_NAME, SID, "ESL", ESL);
	}

	public void setSID(String SID){
		
	}
	
	public void setStudyLevel(StudyLevel studyLevel) throws IllegalArgumentException{
		if (studyLevel == null)
			throw new IllegalArgumentException();
		this.studyLevel = studyLevel;
		dbHandler.update(TABLE_NAME, SID, "STUDY_LEVEL", studyLevel.toString());
	}

	public void setMobile(String mobile) throws IllegalArgumentException{
		if (mobile == null)
			throw new IllegalArgumentException();
		this.mobile = mobile;
		dbHandler.update(TABLE_NAME, SID, "MOBILE", mobile);
	}

	public void setSecondaryEmail(String secondaryEmail){
		this.secondaryEmail = secondaryEmail;
		dbHandler.update(TABLE_NAME, SID, "SECONDARY_EMAIL", secondaryEmail);
	}

	public void setPrimaryEmail(String primaryEmail) throws IllegalArgumentException{
		if (primaryEmail == null)
			throw new IllegalArgumentException("Please enter a value for 'Primary Email'");
		this.primaryEmail = primaryEmail;
		dbHandler.update(TABLE_NAME, SID, "PRIMARY_EMAIL", primaryEmail);
	}

	public void setLastName(String lastName) throws IllegalArgumentException{
		if (lastName == null)
			throw new IllegalArgumentException();
		this.lastName = lastName;
		dbHandler.update(TABLE_NAME, SID, "LAST_NAME", lastName);
	}

	public void setFirstName(String firstName) throws IllegalArgumentException{
		if (firstName == null)
			throw new IllegalArgumentException();
		this.firstName = firstName;
		dbHandler.update(TABLE_NAME, SID, "FIRST_NAME", firstName);
	
	}
	public void setProgrammingLanguages(ProgrammingLanguage[] languages)
	{
		this.languages = languages;
		String languageString = "";
		boolean firstPassed = false;
		for (ProgrammingLanguage language : languages){
			if (!firstPassed){
				firstPassed = true;
			}
			else{
				languageString += ", ";
			}
			languageString += language.toString();
		}
		dbHandler.update(TABLE_NAME, SID, "LANGUAGES", languageString);
	}
	public void setUnikey(String unikey) throws IllegalArgumentException{
		if (unikey == null)
			throw new IllegalArgumentException();
		this.unikey = unikey;
		dbHandler.update(TABLE_NAME, SID, "UNIKEY", unikey);
	}
	
	public void setCourse(String course){
		this.course = course;
		dbHandler.update(TABLE_NAME, SID, "COURSE", course);
	}
	
	public void setExperience(String experience){
		this.experience = experience;
		dbHandler.update(TABLE_NAME, SID, "EXPERIENCE", experience);
	}
	
	public void setPreferredRole(Role role){
		this.preferredRole = role;
		dbHandler.update(TABLE_NAME, SID, "PREFERRED_ROLE", (role == null? null : role.toString()));
	}
	
	public ProgrammingLanguage getLanguage(int i){
		return languages[i];
	}
	
	public void setAvailability(boolean[][] availability) {
		this.availability = availability;
		dbHandler.update(TABLE_NAME, SID, "AVAILABILITY", availability);
	}
	
	public void setFirstSocialMedia(Provider provider, String address){
		socialMedia[0] = new SocialMedia(provider, address);
		dbHandler.update(TABLE_NAME, SID, "SOCIAL_MEDIA_1", (socialMedia[0] == null? null : socialMedia[0].toString()));
	}
	
	public void setSecondSocialMedia(Provider provider, String address){
		socialMedia[1] = new SocialMedia(provider, address);
		dbHandler.update(TABLE_NAME, SID, "SOCIAL_MEDIA_2", (socialMedia[1] == null? null : socialMedia[0].toString()));
	}
	
	public SocialMedia getFirstSocialMedia(){
		return socialMedia[0];
	}
	
	public SocialMedia getSecondSocialMedia(){
		return socialMedia[1];
	}
	
	public String getSID(){
		return SID;
	}
	
	public String getUnikey(){
		return unikey;
	}
	
	public String getFirstName(){
		return firstName;
	}
	
	public String getLastName(){
		return lastName;
	}
	
	public String getCourse(){
		return course;
	}
	
	public String getPrimaryEmail(){
		return primaryEmail;
	}
	
	public String getSecondaryEmail(){
		return secondaryEmail;
	}
	
	public String getMobile(){
		return mobile;
	}
	
	public StudyLevel getStudyLevel(){
		return studyLevel;
	}
	
	public Role getPreferredRole(){
		return preferredRole;
	}
	
	public String getExperience(){
		return experience;
	}
	
	public boolean getESL(){
		return ESL;
	}

	public ProgrammingLanguage[] getLanguages(){
		return languages;
	}
	
	public boolean[][] getAvailability(){
		return availability;
	}

	public List<Team> getTeams(){
		return dbHandler.getTeams(SID);
	}
	
	
	public Team getTeam(int projectID) {
		return dbHandler.getStudentsTeam(SID, projectID);
	}

	public void copyValues(Student student){
		this.SID = student.SID;
		this.unikey = student.unikey;
		this.firstName = student.firstName;
		this.lastName = student.lastName;
		this.course = student.course;
		this.primaryEmail = student.primaryEmail;
		this.secondaryEmail = student.secondaryEmail;
		this.mobile = student.mobile;
		this.experience = student.mobile;
		this.studyLevel = student.studyLevel;
		this.preferredRole = student.preferredRole;
		this.languages = student.languages;
		this.socialMedia = student.socialMedia;
		this.ESL = student.ESL;
		this.availability = student.availability;
		this.lastViewed = student.lastViewed;
	}

	public void enrolTo(String unitCode) {
		dbHandler.addEnrolment(unitCode, SID, (int)(Math.random() * 10));
	}
	
	public void joinTeam(int teamID){
		dbHandler.addTeamMembership(SID, teamID);
	}
	
	public int getTutorialNum(String unitCode){
		return dbHandler.getTutorialNum(SID, unitCode);
	}
	

}