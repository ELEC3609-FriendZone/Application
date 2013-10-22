package friendzone.elec3609.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Array;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import friendzone.elec3609.model.*;
import friendzone.elec3609.model.SocialMedia.Provider;

public class DatabaseHandler{
	Map<String, UnitOfStudy> uosMap = new HashMap<String, UnitOfStudy>();
	Map<String, Student> studentMap = new HashMap<String, Student>();
	Map<Integer, Team> teamMap = new HashMap<Integer, Team>();
	Map<Integer, Project> projectMap = new HashMap<Integer, Project>();	
	Map<Integer, Invitation> inviteMap = new HashMap<Integer, Invitation>();
	Map<Integer, Meeting> meetingMap = new HashMap<Integer, Meeting>();
	
	Connection dbConnection;
	static DatabaseHandler instance;
	
	public static DatabaseHandler getInstance(){
		if (instance == null){
			instance = new DatabaseHandler();
			
			//reset and populate!
//			if (instance != null){
//				instance.resetDatabase();
//				instance.populate();
//			}
		}
		return instance;
	}
	
	
	//Since the DatabaseHandler is marked as a service, it acts as a singleton and the constructor gets called automatically when the application starts
	private DatabaseHandler(){
		try {
			dbConnection = getConnection();
			System.out.println("Database has the following tables:");
			for (String table : getTableNames()){
				System.out.println("\t" + table);
				for (String column : getColumnNames(table)){
					System.out.println("\t\t" + column);
				}
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static Connection getConnection() throws URISyntaxException, SQLException{
		URI dbUri = new URI("postgres://cqqcqkwluqeeav:29RAKIdozh96I7x6ptMGlWZyL-@ec2-54-227-251-13.compute-1.amazonaws.com:5432/de5q05a1htpue7");	//SHOULD use System.getenv("DATABASE_URL"), but I can't access the environment variables on my local copy

	    String username = dbUri.getUserInfo().split(":")[0];
	    String password = dbUri.getUserInfo().split(":")[1];
	    String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
	    
	    Properties props = new Properties();
	    props.setProperty("user", username);
	    props.setProperty("password", password);
	    //these properties are necessary for remote access when debugging but can be removed when the application has been pushed and is running from the Heroku server
	    props.setProperty("ssl", "true");
	    props.setProperty("sslfactory", "org.postgresql.ssl.NonValidatingFactory");
	    
	    System.out.println("Connecting to " + dbUrl + " as user " + username + " with password " + password);
	    return DriverManager.getConnection(dbUrl, props); 
	}
	
	
	public void resetDatabase(){
		String createQuery = 
				"DROP TABLE IF EXISTS Student CASCADE;"
			+	"CREATE TABLE Student ("
			+	"	SID					CHAR(9) 		NOT NULL,"
			+ 	"	UNIKEY 				CHAR(8) 		UNIQUE NOT NULL,"
			+	"	PASSWORD			CHAR(64)		NOT NULL,"
			+	"	FIRST_NAME			VARCHAR(20)		NOT NULL,"
			+	"	LAST_NAME			VARCHAR(20)		NOT NULL,"
			+	"	COURSE				VARCHAR(50),"
			+	"	PRIMARY_EMAIL		VARCHAR(50) 	NOT NULL,"
			+	"	SECONDARY_EMAIL 	VARCHAR(50),"
			+	"	MOBILE				CHAR(10)		NOT NULL,"
			+	"	SOCIAL_MEDIA_1		VARCHAR(50),"
			+	"	SOCIAL_MEDIA_2		VARCHAR(50),"
			+	"	STUDY_LEVEL			VARCHAR(20)		NOT NULL,"
			+	"	PREFERRED_ROLE		VARCHAR(15)," 					//	"Project Manager", "Programmer", "Tester"...
		//	+	"	PREFERRED_CONTACT	SMALLINT,"					
			+	"	EXPERIENCE			VARCHAR(200),"
			+	"	ESL					BOOLEAN			NOT NULL,"
			+	"	LANGUAGES			VARCHAR(50),"
			+	"	AVAILABILITY		BOOLEAN[84],"					//	7 days x 12 hours (8am-8pm) 
			+	"	LAST_MODIFIED		TIMESTAMP		DEFAULT NOW(),"
			+	"	PRIMARY KEY			(SID)"
			+ 	");"
			+	"DROP TABLE IF EXISTS UnitOfStudy CASCADE;"
			+	"CREATE TABLE UnitOfStudy ("
			+	"	UOS_ID				CHAR(8)			NOT NULL,"
			+	"	UOS_NAME			VARCHAR(50)		NOT NULL,"
			+	"	UOS_DESCRIPTION		VARCHAR(100),"
			+	"	NUM_STUDENTS		INTEGER			NOT NULL,"
			+	"	LAST_MODIFIED		TIMESTAMP 		DEFAULT NOW(),"
			+	"	PRIMARY KEY			(UOS_ID)"
			+ 	");"
			+	"DROP TABLE IF EXISTS Enrolment CASCADE;"
			+	"CREATE TABLE Enrolment ("
			+	"	UOS				CHAR(8)		REFERENCES UnitOfStudy(UOS_ID)		NOT NULL,"
			+	"	STUDENT			CHAR(9)		REFERENCES Student(SID)				NOT NULL,"
			+	"	TUTORIAL_NUM	SMALLINT	NOT NULL,"
			+	"	PRIMARY KEY		(UOS, STUDENT)" 
			+	");"
			+	"DROP TABLE IF EXISTS Project CASCADE;"
			+	"CREATE TABLE Project ("
			+	"	PROJECT_ID		SERIAL		NOT NULL,"
			+	"	UOS_ID			CHAR(8)		REFERENCES UnitOfStudy(UOS_ID)		NOT NULL,"
			+	"	NAME			VARCHAR(20)	NOT NULL,"
			+	"	DESCRIPTION		VARCHAR(100),"
			+	"	DEADLINE		DATE		NOT NULL,"
			+	"	LAST_MODIFIED	TIMESTAMP	DEFAULT NOW(),"
			+	"	PRIMARY KEY		(PROJECT_ID)"
			+	");"
			+	"DROP TABLE IF EXISTS Team CASCADE;" //could not use the name "Group" since it's a reserved word in SQL
			+	"CREATE TABLE Team ("
			+	"	TEAM_ID			SERIAL		NOT NULL,"
			+	"	NAME			VARCHAR(20)	NOT NULL,"
			+	"	PROJECT_ID		INTEGER		REFERENCES Project(PROJECT_ID)		NOT NULL,"
			+	"	PRIMARY KEY		(TEAM_ID)"
			+	");"
			+	"DROP TABLE IF EXISTS TeamMembership CASCADE;"
			+	"CREATE TABLE TeamMembership ("
			+	"	STUDENT		CHAR(9)		REFERENCES Student(SID)		NOT NULL,"
			+	"	TEAM		INTEGER		REFERENCES Team(TEAM_ID)	NOT NULL,"
			// should enforce exists ENROLMENT with STUDENT=STUDENT, UOS= TEAM -> PROJECT_ID -> UOS_ID
			+	"	PRIMARY KEY (STUDENT, TEAM)"
			+	");"
			+	"DROP TABLE IF EXISTS Meeting CASCADE;"
			+	"CREATE TABLE Meeting("
			+	"	MEETING_ID	SERIAL			NOT NULL,"
			+	"	TEAM		INTEGER			REFERENCES Team(TEAM_ID)	NOT NULL,"
			+	"	START_TIME	TIMESTAMP		NOT NULL,"
			+	"	END_TIME	TIMESTAMP		NOT NULL,"
			+	"	LOCATION	VARCHAR(20),"
			+	"	PRIMARY KEY (MEETING_ID)"
			+	");"
			+ 	"DROP TABLE IF EXISTS Administrator CASCADE;"
			+	"CREATE TABLE Administrator ("
			+	"	STAFF_NUM		CHAR(9)		NOT NULL,"	//format of a USYD staff number?	
			+	"	UOS				CHAR(8)		REFERENCES UnitOfStudy(UOS_ID)		NOT NULL,"
			+	"	PRIMARY KEY (STAFF_NUM, UOS)" // a staff member could administer multiple courses
			+	");"
			+	"DROP TABLE IF EXISTS InstantMessage CASCADE;"
			+	"CREATE TABLE InstantMessage ("
			+	"	MESSAGE_ID	SERIAL			NOT NULL,"
			+	"	SENDER		CHAR(9)			REFERENCES Student(SID)		NOT NULL,"
			+	"	TEAM		INTEGER			REFERENCES Team(TEAM_ID)	NOT NULL,"
			+	"	MESSAGE		VARCHAR(200)	NOT NULL,"
			+	"	PRIMARY KEY	(MESSAGE_ID)"
			+	");"
			+	"DROP TABLE IF EXISTS Invitation CASCADE;"
			+	"CREATE TABLE Invitation ("
			+	"	INVITE_ID	SERIAL			NOT NULL,"
			+	"	MESSAGE		VARCHAR(100),"	
			+	"	PROJECT		INTEGER			REFERENCES Project(PROJECT_ID)	NOT NULL,"
			+	"	SENDER		CHAR(9)			REFERENCES Student(SID)			NOT NULL,"
			+	"	RECIPIENT	CHAR(9)			REFERENCES Student(SID)			NOT NULL,"	
			+	"	PRIMARY KEY	(INVITE_ID)"
			+	");"
			// Now that the tables are made, we define the triggers to make LAST_MODIFIED work
			+	"DROP FUNCTION IF EXISTS UpdateLastModified();"
			+	"CREATE FUNCTION UpdateLastModified() RETURNS trigger AS $$"
			+	"	BEGIN"
			+	"		NEW.LAST_MODIFIED := NOW();"	
			+	"		RETURN NEW;"
			+	"	END;" 
			+	"$$ LANGUAGE PLPGSQL;"
			+	"DROP TRIGGER IF EXISTS UpdateStudentLastModified ON Invitation;"
			+	"CREATE TRIGGER UpdateStudentLastModified"
			+	"	BEFORE UPDATE ON Student"
			+	"	FOR EACH ROW"
			+	"	EXECUTE PROCEDURE UpdateLastModified();"
			+	"DROP TRIGGER IF EXISTS UpdateUnitOfStudyLastModified ON UnitOfStudy;"
			+	"CREATE TRIGGER UpdateUnitOfStudyLastModified"
			+	"	BEFORE UPDATE ON UnitOfStudy"
			+	"	FOR EACH ROW"
			+	"	EXECUTE PROCEDURE UpdateLastModified();"
			+	"DROP TRIGGER IF EXISTS UpdateProjectModified ON UnitOfStudy;"
			+	"CREATE TRIGGER UpdateProjectLastModified"
			+	"	BEFORE UPDATE ON Project"
			+	"	FOR EACH ROW"
			+	"	EXECUTE PROCEDURE UpdateLastModified();"			
		;

		try{
			Statement stmt = dbConnection.createStatement();
			stmt.executeUpdate(createQuery);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			//System.err.println("Could not create tables!");
		} finally {
			System.out.println("\nDatabase has been reset!\n");
		}
	}
	
	public void populate(){
		final int STUDENT_AMOUNT = 1000;
		final int UOS_AMOUNT = 10;
		final int MAX_PROJECTS = 3; // per UOS
		final int MAX_MEMBERS = 5; // students per team
		
		String[] firstNames = new String[]{"Oliver", "Lucas", "Ethan", "Tom", "Noah", "Cooper", "James", "Jackson", "Liam", "Xavier",
											"Lily", "Isabella", "Emily", "Chloe", "Charlotte", "Zoe", "Isabelle", "Olivia", "Sophie", "Amelia"};
		String[] lastNames = new String[]{"Smith", "Jones", "Williams", "Brown", "Wilson", "Taylor", "Morton", "White", "Martin", "Anderson",
											"Thompson", "Nguyen", "Thomas", "Walker", "Harris", "Lee", "Ryan", "Robinson", "Kelly", "King"};
		String[] unitCodes = new String[]{"ELEC", "INFO", "COMP", "ISYS"};
		
		HashSet<String> usedUnitCodes = new HashSet<String>();
		HashSet<String> usedSIDs = new HashSet<String>();
		HashSet<String> usedUnikeys = new HashSet<String>();
		ArrayList<Student> students = new ArrayList<Student>();
		// populate student table
		for (int i=0; i != STUDENT_AMOUNT; ++i){
			String SID = null;
			do{
				SID = String.valueOf(300000000 + (int)(Math.random() * 100000000));
			} while (usedSIDs.contains(SID));
			usedSIDs.add(SID);
			
			String firstName = firstNames[(int)(Math.random() * firstNames.length)];
			String lastName = lastNames[(int)(Math.random() * firstNames.length)];
			
			String unikey = null;
			do{
				unikey = (firstName.charAt(1) + lastName.substring(0, 3) + String.valueOf(1000 + (int)(Math.random() * 9000))).toLowerCase(); 
			} while (usedUnikeys.contains(unikey));
			usedUnikeys.add(unikey);
			
			String password = "password";

			System.out.println("Adding student " + (i+1) + "/" + STUDENT_AMOUNT + " (unikey " + unikey + ", password " + password + ")");
			
			String primaryEmail = unikey + "@uni.sydney.edu.au";
			String mobile = String.valueOf(041) + String.valueOf(100000 + (int)(Math.random() * 900000)); 
			StudyLevel studyLevel = StudyLevel.values()[(int)(Math.random() * StudyLevel.values().length)];
			boolean ESL = ((int)(Math.random()) == (int)(Math.random()));
			ProgrammingLanguage languages[] = new ProgrammingLanguage[(int)(Math.random() * ProgrammingLanguage.values().length)];
			
			HashSet<ProgrammingLanguage> usedLangs = new HashSet<ProgrammingLanguage>();
			for (int j=0; j != languages.length; ++j){
				ProgrammingLanguage newLanguage = null;
				do{
					newLanguage = ProgrammingLanguage.values()[(int)(Math.random() * ProgrammingLanguage.values().length)];

				} while (usedLangs.contains(newLanguage));
				usedLangs.add(newLanguage);
				languages[j] = newLanguage;
			}

			Student newStudent = new Student(SID, unikey, password, firstName, lastName, primaryEmail, mobile, studyLevel, ESL, languages);

			students.add(newStudent);
		}
		
		
		// populate UoS table
		for (int i=0; i != UOS_AMOUNT; ++i){
			
			String unitCode = null;
			do{
				unitCode = unitCodes[(int)(Math.random() * unitCodes.length)] + String.valueOf(1000 + (int)(Math.random() * 3000));
			} while (usedUnitCodes.contains(unitCode));
			usedUnitCodes.add(unitCode);
		
			System.out.println("Adding Unit of Study " + (i+1) + "/" + UOS_AMOUNT + "(unitCode " + unitCode + ")");
			
			String unitName = "<unit name for " + unitCode + ">";
			int numStudents = (int)(Math.random() * STUDENT_AMOUNT); 
			UnitOfStudy newUOS = new UnitOfStudy(unitCode, unitName, numStudents);
			
			// enrol students to the UOS
			ArrayList<Student> uosStudents = new ArrayList<Student>();
			HashSet<Student> enrolledStudents = new HashSet<Student>();
			for (int j=0; j != numStudents; j++){
				Student student = null;
				do{
					student = students.get((int)(Math.random() * students.size()));
				} while (enrolledStudents.contains(student));
				System.out.println("Enrolling student " + student.getSID() + " to unit of study " + unitCode + " " + (j+1) + "/" + numStudents);
				student.enrolTo(unitCode);
				enrolledStudents.add(student);
				uosStudents.add(student);
			}
			
			// add projects to this UoS
			int numProjects = (int)(Math.random() * (MAX_PROJECTS+1));
			for (int j=0; j != numProjects; j++){
				Date deadline = new Date(System.currentTimeMillis() + (long)(Math.random() * 31556926000L)); //current date + up to 1 year in milliseconds
				System.out.println("Adding Project to unit of study " + unitCode + " " + (j+1) + "/" + numProjects + "(deadline " + deadline.toGMTString() + ")");
				Project newProject = new Project(unitCode, unitCode + " project " + j, deadline);
				
				// create teams for this project
				int numTeams = (numStudents / MAX_MEMBERS);
				int projectID = newProject.getID();
				for (int k = 0; k != numTeams; ++k){
					System.out.println("Adding team to project " + projectID + (k+1) + "/" + numTeams);
					Team newTeam = new Team(projectID, "TEAM" + k);
					
					// add students to this team
					for (int l = 0; l != MAX_MEMBERS; l++){
						int teamID = newTeam.getID();
						int index = (int)(Math.random() * uosStudents.size());
						Student selectedStudent = uosStudents.remove(index);
						System.out.println("Putting student " + selectedStudent.getUnikey() + " in team " + teamID + " for project " + projectID + " " + (l+1) + "/" + MAX_MEMBERS);
					}
				}
			}
		}	
	}

			
//			//create teams for this project
//			for (int k = 0; k != TEAM_AMOUNT; ++k){
//				Team newTeam = new Team(newProject.getID(), "TEAM" + k);
//			
//			}
//			
//			//fill the team with students in the UOS
//			for (int l=0; l != TEAM_SIZE; ++l){
//				if (uosStudents.size() == 0){
//					break;
//				}
//				Student teamMember = uosStudents.remove((int)(Math.random() * uosStudents.size()));
//			}
//			
//			// enrol students to the UOS
//			HashSet<Student> enrolledStudents = new HashSet<Student>();
//			for (int j=0; j != numStudents; j++){
//				Student student = null;
//				do{
//					student = students.get((int)(Math.random() * students.size()));
//				} while (enrolledStudents.contains(student));
//				student.enrolTo(unitCode);
//				uosStudents.add(student);
//			}

	
	public boolean checkExists(String SID){
		try{
			String selectQuery = "SELECT 1"
							+	" FROM Student"
							+	" WHERE SID=?"
							;
			PreparedStatement stmt = dbConnection.prepareStatement(selectQuery);
			stmt.setString(1 , SID);
			ResultSet rs = stmt.executeQuery();
			if (rs.next())
				return true;
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return false;
	}
	
	/*
	  * Takes login and returns a hashed pw of that login if the login exists.
	  * Returns null if login doesn't exist.
	  */
	 public String getAuthentication(String unikey) {
	  String password = null;
	  String fetchQuery =
	     " SELECT PASSWORD"
	    + " FROM Student"
	    + " WHERE UNIKEY=?" 
	    ;
	  try{
	   PreparedStatement statement = dbConnection.prepareStatement(fetchQuery);
	   statement.setString(1, unikey);
	   ResultSet rs = statement.executeQuery();
	   if (rs.next()){ // using if instead of while since there can only be 1 result
		   password = rs.getString("PASSWORD");
	   }
	  } catch (SQLException e){
	   e.printStackTrace();
	  }
	  return password;
	 }
	 
	 public String getSID(String unikey){
		 String SID = null;
		 try{
			 String selectQuery = "SELECT SID"
					 		+	 " FROM Student"
					 		+	 " WHERE UNIKEY=?"
					 		;
			 PreparedStatement stmt = dbConnection.prepareStatement(selectQuery);
			 stmt.setString(1, unikey);
			 ResultSet rs = stmt.executeQuery();
			 if (rs.next()){
				 SID = rs.getString(1);
			 }
			 
		 }
		 catch (SQLException e){
			 e.printStackTrace();
		 }
		 return SID;
	 }
	 
	//	--	--	--	--	--	THE FOLLOWING METHODS ARE FOR FETCHING MODELS USING THEIR ID 	--	--	--	--	--	--	--	--	
	public Project getProject(int projectID){
		Project matchingProject = projectMap.get(projectID);
		try{
			boolean needsUpdate = false;
			if (matchingProject == null){
				needsUpdate = true;
			}
			else{
				String lastUpdateQuery = "SELECT LAST_MODIFIED"
									+	" FROM Project"
									+	" WHERE PROJECT_ID=?"
									;
				PreparedStatement lastUpdateStatement = dbConnection.prepareStatement(lastUpdateQuery);
				lastUpdateStatement.setInt(1, projectID);
				ResultSet lastUpdateRs = lastUpdateStatement.executeQuery();
				Timestamp lastUpdate = (lastUpdateRs.next()? lastUpdateRs.getTimestamp(1) : null);
				needsUpdate =  matchingProject.getLastViewed().before(lastUpdate);
			}
		
			if (needsUpdate){
				String uosQuery = "SELECT *"
							+	 " FROM Project"
							+	 " WHERE Project_ID=?"
							;
				PreparedStatement projectStatement = dbConnection.prepareStatement(uosQuery);
				projectStatement.setInt(1, projectID);
				ResultSet projectRs = projectStatement.executeQuery();
				if (projectRs.next()){
					matchingProject = new Project(projectID, projectRs.getString("UOS"), projectRs.getDate("DEADLINE"));
					if (projectMap.get(projectID) == null){
						projectMap.put(projectID, matchingProject);
					}
					else{
						projectMap.get(projectID).copyValues(matchingProject);
					}
					matchingProject = projectMap.get(projectID);
					matchingProject.setDescription(projectRs.getString("DESCRIPTION"));					
				}
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		matchingProject.setLastViewed(new Timestamp(System.currentTimeMillis()));
		return matchingProject;
	}
	
	public Team getTeam(int teamID) {
		Team matchingTeam = teamMap.get(teamID);
		try{
			String selectQuery = "SELECT *"
							+	 " FROM TEAM"
							+	 " WHERE TEAM_ID=?"
							;
			PreparedStatement stmt = dbConnection.prepareStatement(selectQuery);
			stmt.setInt(1, teamID);
			ResultSet uosRs = stmt.executeQuery();
			if (uosRs.next()){
				matchingTeam = new Team(teamID, uosRs.getInt("PROJECT_ID"), uosRs.getString("NAME"));
		
				if (teamMap.get(teamID) == null){
					teamMap.put(teamID, matchingTeam);
				}
				else{
					teamMap.get(teamID).copyValues(matchingTeam);
				}
				matchingTeam = teamMap.get(teamID);
			}
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return matchingTeam;
	}
	
	public UnitOfStudy getUnitOfStudy(String unitCode){
		UnitOfStudy matchingUOS = uosMap.get(unitCode);
		try{
			boolean needsUpdate = false;
			if (matchingUOS == null){
				needsUpdate = true;
			}
			else{
				String lastUpdateQuery = "SELECT LAST_MODIFIED"
									+	" FROM UnitOfStudy"
									+	" WHERE UOS_ID=?"
									;
				PreparedStatement lastUpdateStatement = dbConnection.prepareStatement(lastUpdateQuery);
				lastUpdateStatement.setString(1, unitCode);
				ResultSet lastUpdateRs = lastUpdateStatement.executeQuery();
				Timestamp lastUpdate = (lastUpdateRs.next()? lastUpdateRs.getTimestamp(1) : null);
				needsUpdate =  matchingUOS.getLastViewed().before(lastUpdate);
			}
		
			if (needsUpdate){
				String uosQuery = "SELECT *"
							+	 " FROM UnitOfStudy"
							+	 " WHERE UOS_ID=?"
							;
				PreparedStatement uosStatement = dbConnection.prepareStatement(uosQuery);
				uosStatement.setString(1, unitCode);
				ResultSet uosRs = uosStatement.executeQuery();
				if (uosRs.next()){
					matchingUOS = new UnitOfStudy(unitCode, uosRs.getString("UOS_NAME"), uosRs.getInt("NUM_STUDENTS"));
					if (uosMap.get(unitCode) == null){
						uosMap.put(unitCode, matchingUOS);
					}
					else{
						uosMap.get(unitCode).copyValues(matchingUOS);
					}
					matchingUOS = uosMap.get(unitCode);
					matchingUOS.setDescription(uosRs.getString("UOS_DESCRIPTION"));

				
				}
			}
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		matchingUOS.setLastViewed(new Timestamp(System.currentTimeMillis()));
		return matchingUOS;
	}
	
	public Student getStudent(String SID){
		Student matchingStudent = studentMap.get(SID);
		try{
			boolean needsUpdate = false;
			if (matchingStudent == null){
				needsUpdate = true;
			}
			else{
				String lastUpdateQuery = "SELECT LAST_MODIFIED"
									+	" FROM Student"
									+	" WHERE SID=?"
									;
				PreparedStatement lastUpdateStatement = dbConnection.prepareStatement(lastUpdateQuery);
				lastUpdateStatement.setString(1, SID);
				ResultSet lastUpdateRs = lastUpdateStatement.executeQuery();
				Timestamp lastUpdate = (lastUpdateRs.next()? lastUpdateRs.getTimestamp(1) : null);
				needsUpdate =  matchingStudent.getLastViewed().before(lastUpdate);
			}
		
			if (needsUpdate){
				String fetchQuery =	"SELECT *"
							+	"	FROM Student"
							+	"	WHERE SID=?"
							;
				PreparedStatement statement = dbConnection.prepareStatement(fetchQuery);
				statement.setString(1, SID);
				ResultSet rs = statement.executeQuery();
				if (rs.next()){ // using if instead of while since there can only be 1 result
					String languageString = rs.getString("LANGUAGES");
					String[] languageNames = languageString.split(",");
					ProgrammingLanguage[] languages = new ProgrammingLanguage[languageNames.length];
					for (int i=0; i < languages.length; ++i){
						languages[i] = ProgrammingLanguage.findMatch(languageNames[i].trim());
					}
					
					matchingStudent = new Student(SID,
							 rs.getString("UNIKEY"),
							 rs.getString("PASSWORD"),
							 rs.getString("FIRST_NAME"),
							 rs.getString("LAST_NAME"),
							 rs.getString("PRIMARY_EMAIL"),
							 rs.getString("MOBILE"),
							 StudyLevel.findMatch(rs.getString("STUDY_LEVEL")),
							 rs.getBoolean("ESL"),
							 languages);
					
					if (studentMap.get(SID) == null){
						studentMap.put(SID, matchingStudent);
					}
					else{
						studentMap.get(SID).copyValues(matchingStudent);
					}
					matchingStudent = studentMap.get(SID);
					
					//The constructor takes all the "NOT NULL" values, but there are others that can exist
					String[] socialMediaComponents = rs.getString("SOCIAL_MEDIA_1").split(":");
					Provider provider = SocialMedia.Provider.findMatch(socialMediaComponents[0]);
					String address = socialMediaComponents[1].trim();
					matchingStudent.setFirstSocialMedia(provider, address);
					
					socialMediaComponents = rs.getString("SOCIAL_MEDIA_2").split(":");
					provider = SocialMedia.Provider.findMatch(socialMediaComponents[0]);
					address = socialMediaComponents[1].trim();
					matchingStudent.setSecondSocialMedia(provider, address);
					
					Array availArray = rs.getArray("AVAILABILITY");
					boolean[][] twoDimensionalAvail = new boolean[7][12];
					Boolean[] oneDimensionalAvail = (Boolean[])availArray.getArray();
					for (int i = 0; i != twoDimensionalAvail.length; ++i){
						for (int j = 0; j != twoDimensionalAvail[i].length; ++j){
							twoDimensionalAvail[i][j] = oneDimensionalAvail[i * twoDimensionalAvail.length + j];
						}
					}
					matchingStudent.setAvailability(twoDimensionalAvail);
					
					matchingStudent.setCourse(rs.getString("COURSE"));
					matchingStudent.setSecondaryEmail(rs.getString("SECONDARY_EMAIL"));
					matchingStudent.setExperience(rs.getString("EXPERIENCE"));
					matchingStudent.setStudyLevel(StudyLevel.findMatch(rs.getString("STUDY_LEVEL")));
					matchingStudent.setPreferredRole(Role.findMatch(rs.getString("PREFERRED_ROLE")));
				}
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		matchingStudent.setLastViewed(new Timestamp(System.currentTimeMillis()));
		return matchingStudent;
	}
	
	private Meeting getMeeting(int meetingID) {
		Meeting meeting = meetingMap.get(meetingID);
		try{
			String selectQuery = "SELECT *"
							+	" FROM Meeting"
							+	" WHERE MEETING_ID=?"
							;
			PreparedStatement stmt = dbConnection.prepareStatement(selectQuery);
			stmt.setInt(1, meetingID);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()){
				meeting = new Meeting(meetingID,
									  rs.getInt("TEAM"),
									  rs.getTimestamp("START_TIME"),
									  rs.getTimestamp("END_TIME"),
									  rs.getString("LOCATION"));
				if (meetingMap.get(meetingID) == null){
					meetingMap.put(meetingID, meeting);
				}
				else{
					meetingMap.get(meetingID).copyValues(meeting);
				}
				meeting = meetingMap.get(meetingID);
			}
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return meeting;
	}
		
	private Invitation getInvitation(int inviteID) {
		Invitation invite = inviteMap.get(inviteMap);
		try{
			String selectQuery = "SELECT *"
							+	" FROM Invitation"
							+	" WHERE INVITE_ID=?"
							;
			PreparedStatement stmt = dbConnection.prepareStatement(selectQuery);
			stmt.setInt(1, inviteID);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()){
				invite = new Invitation(inviteID, rs.getString("SENDER"), rs.getString("RECIPIENT"), rs.getInt("PROJECT"), rs.getString("MESSAGE"));
				if (inviteMap.get(inviteID) == null){
					inviteMap.put(inviteID, invite);
				}
				else{
					inviteMap.get(inviteID).copyValues(invite);
				}
				invite = inviteMap.get(inviteID);
			}
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return invite;
	}
	
	// --	--	--	--	--	--	--	THE FOLLOWING METHODS ARE USED TO ADD MODELS TO THE DATABASE --	--	--	--	--	--	--	--	--
	
	public void addStudent(String SID, String unikey, String password,
			String firstName, String lastName, String primaryEmail,
			String mobile, StudyLevel studyLevel, boolean ESL, ProgrammingLanguage[] languages) {
		
		String insertQuery = "INSERT INTO Student"
						+	" (SID, UNIKEY, PASSWORD, FIRST_NAME, LAST_NAME, PRIMARY_EMAIL, MOBILE, STUDY_LEVEL, ESL, LANGUAGES)"
						+	" SELECT ?,?,?,?,?,?,?,?,?,?"
						+	" WHERE NOT EXISTS (SELECT 1 FROM Student WHERE SID=?)";
						
		try{
			PreparedStatement statement = dbConnection.prepareStatement(insertQuery);
				statement.setString(1, SID);
				statement.setString(2, unikey);
				statement.setString(3, password);
				statement.setString(4, firstName);
				statement.setString(5, lastName);
				statement.setString(6, primaryEmail);
				statement.setString(7, mobile);
				statement.setString(8, studyLevel.toString());
				statement.setBoolean(9, ESL);
				
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

				statement.setString(10, languageString);
				statement.setString(11, SID);
				statement.execute();
			} catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	public void addUnitOfStudy(String unitCode, String unitName, int num_students){
		try {
			String insertQuery = "INSERT INTO UnitOfStudy"
							+	" (UOS_ID, UOS_NAME, NUM_STUDENTS)"
							+	" SELECT ?,?,?"
							+	" WHERE NOT EXISTS (SELECT 1 FROM UnitOfStudy WHERE UOS_ID=?)"
							;
			PreparedStatement stmt = dbConnection.prepareStatement(insertQuery);
			stmt.setString(1, unitCode);
			stmt.setString(2, unitName);
			stmt.setInt(3, num_students);
			stmt.setString(4, unitCode);
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int addInvitation(int projectID, String senderSID, String recipientSID){
		Integer id = null;
		try{
			String insertQuery = "INSERT INTO Invitation"
							+	" (PROJECT, SENDER, RECIPIENT)"
							+	" VALUES (?,?,?)"
							+ 	" RETURNING INVITE_ID"
							;
			PreparedStatement stmt = dbConnection.prepareStatement(insertQuery);
			stmt.setInt(1, projectID);
			stmt.setString(2, senderSID);
			stmt.setString(3, recipientSID);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) 
				id = rs.getInt(1);
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return id;
	}
	
	public int addTeam(int projectID, String name){
		Integer id = null;
		try{
			String insertQuery = "INSERT INTO Team"
							+	" (PROJECT_ID, NAME)"
							+	" VALUES (?,?)"
							+	" RETURNING TEAM_ID"
							;
			PreparedStatement stmt = dbConnection.prepareStatement(insertQuery);
			stmt.setInt(1, projectID);
			stmt.setString(2, name);
			stmt.setInt(3, projectID);
			stmt.setString(4, name);
			ResultSet rs = stmt.executeQuery();
			if (rs.next())
				id = rs.getInt(1);
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return id;
	}
	
	public int addProject(String unitCode, String projectName, Date deadline){
		Integer id = null;
		try{
			String insertQuery = "INSERT INTO Project"
							+	" (UOS_ID, NAME, DEADLINE)"
							+	" VALUES (?,?,?)"
							+	" RETURNING PROJECT_ID"
							;
			PreparedStatement stmt = dbConnection.prepareStatement(insertQuery);
			stmt.setString(1, unitCode);
			stmt.setString(2, projectName);
			stmt.setDate(3, deadline);
			ResultSet rs = stmt.executeQuery();
			if (rs.next())
				id = rs.getInt(1);
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return id;
	}
	
	public int addMeeting(int teamID, Timestamp start, Timestamp end){
		Integer id = null;
		try{
			String insertQuery = "INSERT INTO Meeting"
							+	" (TEAM, START_TIME, END_TIME)"
							+	" VALUES (?,?,?)"
							;
			PreparedStatement stmt = dbConnection.prepareStatement(insertQuery);
			stmt.setInt(1, teamID);
			stmt.setTimestamp(2, start);
			stmt.setTimestamp(3, end);
			stmt.setInt(4, teamID);
			stmt.setTimestamp(5, start);
			ResultSet rs = stmt.executeQuery();
			if (rs.next())
				id = rs.getInt(1);
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return id;
	}
	
	public void addTeamMembership(String SID, int teamID){
		try{
			String insertQuery = "INSERT INTO TeamMembership"
							+	" (STUDENT, TEAM)"
							+	" SELECT ?,?"
							+	" WHERE NOT EXISTS (SELECT 1 FROM TeamMembership WHERE STUDENT=? AND TEAM=?)" //allows re-enrolling when already enrolled to occur without error
							
							;
			PreparedStatement stmt = dbConnection.prepareStatement(insertQuery);
			stmt.setString(1, SID);
			stmt.setInt(2, teamID);
			stmt.setString(3, SID);
			stmt.setInt(4, teamID);
			stmt.execute();
		}
		catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	public int addInstantMessage(String senderID, int teamID, String message){
		Integer id = null;
		try{
			String insertQuery = "INSERT INTO InstantMessage"
							+	" (SENDER, TEAM, MESSAGE)"
							+	" VALUES (?,?,?)"
							+	" RETURNING MESSAGE_ID"
							;
			PreparedStatement stmt = dbConnection.prepareStatement(insertQuery);
			stmt.setString(1, senderID);
			stmt.setInt(2, teamID);
			stmt.setString(3, message);
			ResultSet rs = stmt.executeQuery();
			if (rs.next())
				id = rs.getInt(1);
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return id;
	}
	
	public void addAdministrator(String staffID, String unitCode){
		try{
			String insertQuery = "INSERT INTO Administrator"
							+	" (STAFF_NUM, UOS)"
							+	" SELECT ?,?"
							+	" WHERE NOT EXISTS (SELECT 1 FROM Administrator WHERE STAFF_NUM=? AND UOS=?)" //allows re-applying as administrator when already admin without error
							
							;
			PreparedStatement stmt = dbConnection.prepareStatement(insertQuery);
			stmt.setString(1, staffID);
			stmt.setString(2, unitCode);
			stmt.setString(3, staffID);
			stmt.setString(4, unitCode);
			stmt.execute();
		}
		catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	public void addEnrolment(String unitCode, String SID, int tutorialNum){
		try{
			String insertQuery = "INSERT INTO Enrolment"
							+	" (UOS, STUDENT, TUTORIAL_NUM)"
							+	" SELECT ?,?,?"
							+	" WHERE NOT EXISTS (SELECT 1 FROM Enrolment WHERE UOS=? AND STUDENT=?)" //allows re-enrolment without throwing an error
							;
			PreparedStatement stmt = dbConnection.prepareStatement(insertQuery);
			stmt.setString(1, unitCode);
			stmt.setString(2, SID);
			stmt.setInt(3, tutorialNum);
			stmt.setString(4, unitCode);
			stmt.setString(5, SID);
			
			stmt.execute();
		}
		catch (SQLException e){
			e.printStackTrace();
		}
	}

	private String getIDFieldName(String tableName){
		String idName = "";
		if (tableName.equals("Student")){
			idName = "SID";
		}
		else if (tableName.equals("UnitOfStudy")){
			idName = "UOS_ID";
		}
		else if (tableName.equals("Administrator")){
			idName = "STAFF_NUM";
		}
		else if (tableName.equals("Project")){
			idName = "PROJECT_ID";
		}
		else if (tableName.equals("Team")){
			idName = "TEAM_ID";
		}
		else if (tableName.equals("Meeting")){
			idName = "MEETING_ID";
		}
		else if (tableName.equals("InstantMessage")){
			idName = "MESSAGE_ID";
		}
		else if (tableName.equals("Invitation")){
			idName = "INVITE_ID";
		}
		return idName;
	}
	
	//	--	--	--	--	--	--	--	--	--	THE FOLLOWING METHODS ARE USED TO UPDATE ENTRIES IN THE DATABASE --	--	--	--	--	--	--	--
	public void update(String tableName, String identifier, String fieldName, String newValue){
		try{
			String updateQuery = "UPDATE ?"
							+	" SET ?=?"
							+	" WHERE ?=?"
							;
			String idName = getIDFieldName(tableName);
			PreparedStatement stmt = dbConnection.prepareStatement(updateQuery);
			stmt.setString(1, tableName);
			stmt.setString(2, fieldName);
			stmt.setString(3, newValue);
			stmt.setString(4, idName);
			stmt.setString(5, identifier);
			stmt.execute();
		}
		catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	public void update(String tableName, String identifier, String fieldName, boolean newValue){
		try{
			String updateQuery = "UPDATE ?"
							+	" SET ?=?"
							+	" WHERE ?=?"
							;
			String idName = getIDFieldName(tableName);
			PreparedStatement stmt = dbConnection.prepareStatement(updateQuery);
			stmt.setString(1, tableName);
			stmt.setString(2, fieldName);
			stmt.setBoolean(3, newValue);
			stmt.setString(4, idName);
			stmt.setString(5, identifier);
			stmt.execute();
		}
		catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	public void update(String tableName, int identifier, String fieldName, String newValue){
		try{
			String updateQuery = "UPDATE ?"
							+	" SET ?=?"
							+	" WHERE ?=?"
							;
			String idName = getIDFieldName(tableName);
			PreparedStatement stmt = dbConnection.prepareStatement(updateQuery);
			stmt.setString(1, tableName);
			stmt.setString(2, fieldName);
			stmt.setString(3, newValue);
			stmt.setString(4, idName);
			stmt.setInt(5, identifier);
			stmt.execute();
		}
		catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	public void update(String tableName, String identifier, String fieldName, int newValue){
		try{
			String updateQuery = "UPDATE ?"
							+	" SET ?=?"
							+	" WHERE ?=?"
							;
			String idName = getIDFieldName(tableName);
			PreparedStatement stmt = dbConnection.prepareStatement(updateQuery);
			stmt.setString(1, tableName);
			stmt.setString(2, fieldName);
			stmt.setInt(3, newValue);
			stmt.setString(4, idName);
			stmt.setString(5, identifier);
			stmt.execute();
		}
		catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	public void update(String tableName, int identifier, String fieldName, int newValue){
		try{
			String updateQuery = "UPDATE ?"
							+	" SET ?=?"
							+	" WHERE ?=?"
							;

			String idName = getIDFieldName(tableName);
			PreparedStatement stmt = dbConnection.prepareStatement(updateQuery);
			stmt.setString(1, tableName);
			stmt.setString(2, fieldName);
			stmt.setInt(3, newValue);
			stmt.setString(4, idName);
			stmt.setInt(5, identifier);
			stmt.execute();
		}
		catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	public void update(String tableName, String identifier, String fieldName, Date newValue){
		try{
			String updateQuery = "UPDATE ?"
							+	" SET ?=?"
							+	" WHERE ?=?"
							;

			String idName = getIDFieldName(tableName);
			PreparedStatement stmt = dbConnection.prepareStatement(updateQuery);
			stmt.setString(1, tableName);
			stmt.setString(2, fieldName);
			stmt.setDate(3, newValue);
			stmt.setString(4, idName);
			stmt.setString(5, identifier);
			stmt.execute();
		}
		catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	public void update(String tableName, int identifier, String fieldName, Date newValue){
		try{
			String updateQuery = "UPDATE ?"
							+	" SET ?=?"
							+	" WHERE ?=?"
							;

			String idName = getIDFieldName(tableName);
			PreparedStatement stmt = dbConnection.prepareStatement(updateQuery);
			stmt.setString(1, tableName);
			stmt.setString(2, fieldName);
			stmt.setDate(3, newValue);
			stmt.setString(4, idName);
			stmt.setInt(5, identifier);
			stmt.execute();
		}
		catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	public void update(String tableName, String identifier, String fieldName, Timestamp newValue){
		try{
			String updateQuery = "UPDATE ?"
							+	" SET ?=?"
							+	" WHERE ?=?"
							;

			String idName = getIDFieldName(tableName);
			PreparedStatement stmt = dbConnection.prepareStatement(updateQuery);
			stmt.setString(1, tableName);
			stmt.setString(2, fieldName);
			stmt.setTimestamp(3, newValue);
			stmt.setString(4, idName);
			stmt.setString(5, identifier);
			stmt.execute();
		}
		catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	public void update(String tableName, int identifier, String fieldName, Timestamp newValue){
		try{
			String updateQuery = "UPDATE ?"
							+	" SET ?=?"
							+	" WHERE ?=?"
							;

			String idName = getIDFieldName(tableName);
			PreparedStatement stmt = dbConnection.prepareStatement(updateQuery);
			stmt.setString(1, tableName);
			stmt.setString(2, fieldName);
			stmt.setTimestamp(3, newValue);
			stmt.setString(4, idName);
			stmt.setInt(5, identifier);
			stmt.execute();
		}
		catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	public void update(String tableName, String identifier, String fieldName, boolean[][] newValue){
		try {
			String updateQuery = "UPDATE ?"
							+	" SET ?=?"
							+	" WHERE ?=?"
							;

			String idName = getIDFieldName(tableName);
			PreparedStatement stmt = dbConnection.prepareStatement(updateQuery);
			stmt.setString(1, tableName);
			stmt.setString(2, fieldName);
			Boolean[] oneDimensionalAvail = new Boolean[84];
			boolean[][] twoDimensionalAvail = newValue;
			for (int i = 0; i != twoDimensionalAvail.length ; ++i){
				for (int j = 0; j != twoDimensionalAvail[i].length; ++j){
					oneDimensionalAvail[i * twoDimensionalAvail.length + j] = twoDimensionalAvail[i][j];
				}
			}
			Array availArray = dbConnection.createArrayOf("boolean", oneDimensionalAvail);
			stmt.setArray(3, availArray);
			stmt.setString(4, idName);
			stmt.setString(5, identifier);
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
	
	// --	--	--	--	--	--	--	THE FOLLOWING METHODS ARE USED BY THE ACCESSOR METHODS OF OUR MODELS	--	--	--	--	--	--	--
	public ArrayList<Invitation> getInvitations(String recipientSID){
		ArrayList<Invitation> invitations = new ArrayList<Invitation>();
		try{
			String selectQuery = "SELECT INVITE_ID"
							+	" FROM Invitation"
							+	" WHERE RECIPIENT=?"
							;
			PreparedStatement stmt = dbConnection.prepareStatement(selectQuery);
			stmt.setString(1, recipientSID);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()){
				int inviteID = rs.getInt(1);
				Invitation invite = getInvitation(inviteID);
				invitations.add(invite);
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		return invitations;
	}
	
	public Integer getTutorialNum(String SID, String unitCode) {
		Integer tutNum = null;
		try{
			String selectQuery = "SELECT TUTORIAL_NUM"
		
						+	" FROM Enrolment"
						+	" WHERE STUDENT=? AND UOS=?"
						;
		PreparedStatement stmt = dbConnection.prepareStatement(selectQuery);
		stmt.setString(1, SID);
		stmt.setString(2, unitCode);
		ResultSet rs = stmt.executeQuery();
		if (rs.next())
			tutNum = rs.getInt(1);
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return tutNum;
	}
	
	public ArrayList<Meeting> getMeetings(String SID){
		ArrayList<Meeting> meetings = new ArrayList<Meeting>();
		try{
			String selectQuery = "SELECT TEAM"
							+	" FROM TeamMembership"
							+	" WHERE STUDENT=?"
							;
			PreparedStatement stmt = dbConnection.prepareStatement(selectQuery);
			stmt.setString(1, SID);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()){
				int teamID = rs.getInt(1);
				String meetingQuery = "SELECT MEETING_ID"
								+	" FROM Meeting"
								+	" WHERE TEAM_ID=?"
								;
				PreparedStatement meetingStmt = dbConnection.prepareStatement(meetingQuery);
				meetingStmt.setInt(1, teamID);
				ResultSet meetingRs = meetingStmt.executeQuery();
				while (meetingRs.next()){
					Meeting meeting = getMeeting(meetingRs.getInt(1));
					meetings.add(meeting);
				}
			}
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return meetings;
	}

	public ArrayList<Team> getTeams(int projectID) {
		ArrayList<Team> teams = new ArrayList<Team>();
		try{
			String selectQuery = "SELECT TEAM_ID"
							+	" FROM Team"
							+	" WHERE PROJECT_ID=?"
							;
			PreparedStatement stmt = dbConnection.prepareStatement(selectQuery);
			stmt.setInt(1, projectID);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()){
				int teamID = rs.getInt(1);
				Team matchingTeam = getTeam(teamID);
				teams.add(matchingTeam);
			}
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return teams;
	}

	public ArrayList<Project> getProjects(String unitCode) {
		ArrayList<Project> projects = new ArrayList<Project>();
		
		return projects;
	}	
	
	public UnitOfStudy getUnitOfStudy(int projectID) {
		UnitOfStudy matchingUOS = null;
		try{
			String selectQuery = "SELECT UOS"
							+	" FROM Project"
							+	" WHERE PROJECT_ID=?"
							;
			PreparedStatement selectStatement = dbConnection.prepareStatement(selectQuery);
			selectStatement.setInt(1, projectID);
			ResultSet selectRs = selectStatement.executeQuery();
			if (selectRs.next()){
				String unitCode = selectRs.getString(1);
				matchingUOS = getUnitOfStudy(unitCode);
			}
		} 
		catch (SQLException e){
			e.printStackTrace();
		}
		return matchingUOS;
	}

	public ArrayList<UnitOfStudy> getUnitsOfStudy(String SID) {
		ArrayList<UnitOfStudy> subjects = new ArrayList<UnitOfStudy>();
		try{
			String selectQuery = "SELECT UOS"
							+	" FROM Enrolment"
							+	" WHERE STUDENT=?"
							;
			PreparedStatement selectStatement = dbConnection.prepareStatement(selectQuery);
			selectStatement.setString(1, SID);
			ResultSet selectRs = selectStatement.executeQuery();
			while (selectRs.next()){
				String unitCode = selectRs.getString(1);
				UnitOfStudy matchingUOS = getUnitOfStudy(unitCode);
				subjects.add(matchingUOS);
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		return subjects;
	}
	
	public Project getParent(int teamID) {
		Project matchingProject = null;
		try{
			String selectQuery = "SELECT PROJECT_ID"
							+	" FROM TEAM"
							+	" WHERE TEAM_ID=?"
							;
			PreparedStatement stmt = dbConnection.prepareStatement(selectQuery);
			stmt.setInt(1, teamID);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()){
				int projectID = rs.getInt(1);
				matchingProject = getProject(projectID);
			}
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return matchingProject;
	}
	
	public ArrayList<Student> getMembers(int teamID) {
		ArrayList<Student> teamMembers = new ArrayList<Student>();
		try{
			String selectQuery = "SELECT STUDENT"
							+ 	" FROM TeamMembership"
							+	" WHERE TEAM=?"
							;
			PreparedStatement stmt = dbConnection.prepareStatement(selectQuery);
			stmt.setInt(1, teamID);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()){
				String sid = rs.getString(1);
				Student matchingStudent = getStudent(sid);
				teamMembers.add(matchingStudent);
			}
		}
		catch (SQLException e){
			e.printStackTrace();
		}	
		return teamMembers;
	}

	public ArrayList<Team> getTeams(String sid) {
		ArrayList<Team> studentsTeams = new ArrayList<Team>();
		try{
			String selectQuery = "SELECT TEAM"
							+	" FROM TeamMembership"
							+	" WHERE STUDENT=?"
							;
			PreparedStatement stmt = dbConnection.prepareStatement(selectQuery);
			stmt.setString(1, sid);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()){
				int teamID = rs.getInt(1);
				Team matchingTeam = getTeam(teamID);
				studentsTeams.add(matchingTeam);
			}
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return studentsTeams;
	}
	
	// --	--	--	--	--	--	--	THE FOLLOWING METHODS ARE ONLY USED FOR TESTING PURPOSES	--	--	--	--	--	--	--
	public ArrayList<String> getTableNames(){
		String selectQuery = 	
						"	SELECT table_name"
					+	"	FROM INFORMATION_SCHEMA.TABLES"
					+	"	WHERE TABLE_SCHEMA='public'"
					+	"	AND TABLE_TYPE='BASE TABLE'";
		ArrayList<String> tableNames = new ArrayList<String>();
		try{
			Statement stmt = dbConnection.createStatement();
			ResultSet rs = stmt.executeQuery(selectQuery);
			while (rs.next()){
				tableNames.add(rs.getString("table_name"));
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		return tableNames;
	}
	
	public ArrayList<String> getColumnNames(String tableName){
		String selectQuery = 	
					"	SELECT column_name"
				+	"	FROM INFORMATION_SCHEMA.COLUMNS"
				+	"	WHERE table_name=?";
		ArrayList<String> columnNames = new ArrayList<String>();
		try{
			PreparedStatement stmt = dbConnection.prepareStatement(selectQuery);
			stmt.setString(1, tableName);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()){
				columnNames.add(rs.getString("column_name"));
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		return columnNames;
	}

}