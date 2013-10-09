package friendzone.elec3609.model;


import java.util.ArrayList;
import java.util.List;


import friendzone.elec3609.model.SocialMedia.Provider;

public class Student{
	private final int MAX_SOCIAL_MEDIA = 2;
	
	String SID;
	List<Team> teams = new ArrayList<Team>();
	String unikey, firstName, lastName, course, primaryEmail, secondaryEmail, mobile, experience;
	StudyLevel studyLevel;
	Role preferredRole;
	ProgrammingLanguage[] languages;
	SocialMedia[] socialMedia = new SocialMedia[MAX_SOCIAL_MEDIA];
	boolean ESL;
	boolean[][] availability = new boolean[7][12];
	
	public Student(String SID, String unikey, String firstName, String lastName, String primaryEmail, String mobile, StudyLevel studyLevel, boolean ESL){ //takes all the NOT NULL attributes as arguments
		setSID(SID);
		setUnikey(unikey);
		setFirstName(firstName);
		setLastName(lastName);
		setPrimaryEmail(primaryEmail);
		setMobile(mobile);
		setStudyLevel(studyLevel);
		setESL(ESL);
	}
	
	public void setESL(boolean ESL) {
		this.ESL = ESL;
	}

	public void setStudyLevel(StudyLevel studyLevel) throws IllegalArgumentException{
		if (studyLevel == null)
			throw new IllegalArgumentException();
		this.studyLevel = studyLevel;
	}

	public void setMobile(String mobile) throws IllegalArgumentException{
		if (mobile == null)
			throw new IllegalArgumentException();
		this.mobile = mobile;
	}

	public void setSecondaryEmail(String secondaryEmail){
		this.secondaryEmail = secondaryEmail;
	}

	public void setPrimaryEmail(String primaryEmail) throws IllegalArgumentException{
		if (primaryEmail == null)
			throw new IllegalArgumentException();
		this.primaryEmail = primaryEmail;
	}

	public void setLastName(String lastName) throws IllegalArgumentException{
		if (lastName == null)
			throw new IllegalArgumentException();
		this.lastName = lastName;
	}

	public void setFirstName(String firstName) throws IllegalArgumentException{
		if (firstName == null)
			throw new IllegalArgumentException();
		this.firstName = firstName;
	}

	public void setUnikey(String unikey) throws IllegalArgumentException{
		if (unikey == null)
			throw new IllegalArgumentException();
		this.unikey = unikey;
	}

	public void setSID(String SID) throws IllegalArgumentException{
		if (SID == null)
			throw new IllegalArgumentException();
		this.SID = SID;
	}
	
	public void setCourse(String course){
		this.course = course;
	}
	
	public void setExperience(String experience){
		this.experience = experience;
	}
	
	public void setPreferredRole(Role role){
		this.preferredRole = role;
	}
	
	public void setLanguages(ProgrammingLanguage[] languages) throws IllegalArgumentException{
		for (ProgrammingLanguage language : languages){
			if (language == null) //all provided languages must be valid
				throw new IllegalArgumentException();
		}
		this.languages = languages;
	}
	
	public void setAvailability(boolean[][] availability) {
		this.availability = availability;
	}
	
	public void setFirstSocialMedia(Provider provider, String address){
		socialMedia[0] = new SocialMedia(provider, address);
	}
	
	public void setSecondSocialMedia(Provider provider, String address){
		socialMedia[1] = new SocialMedia(provider, address);
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
		return teams;
	}
}