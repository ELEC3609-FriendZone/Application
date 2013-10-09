package friendzone.elec3609.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import friendzone.elec3609.model.*;
import friendzone.elec3609.model.SocialMedia.Provider;

import org.springframework.stereotype.Service;

@Service
public class DatabaseHandler{
	static Connection dbConnection;
	
	//Since the DatabaseHandler is marked as a service, it acts as a singleton and the constructor gets called automatically when the application starts
	//We probably won't need this constructor at all when 
	public DatabaseHandler(){
		try {
			dbConnection = getConnection();
			resetDatabase();
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
	
	public Student getStudent(String SID){
		Student student = null;
		String fetchQuery =
					"	SELECT *"
				+	"	FROM Student"
				+	"	WHERE SID=?"
				;
		try{
			PreparedStatement statement = dbConnection.prepareStatement(fetchQuery);
			statement.setString(1, SID);
			ResultSet rs = statement.executeQuery();
			if (rs.next()){ // using if instead of while since there can only be 1 result
				student = new Student	(rs.getString("SID"),
										 rs.getString("UNIKEY"),
										 rs.getString("FIRST_NAME"),
										 rs.getString("LAST_NAME"),
										 rs.getString("PRIMARY_EMAIL"),
										 rs.getString("MOBILE"),
										 StudyLevel.findMatch(rs.getString("STUDY_LEVEL")),
										 rs.getBoolean("ESL"));
				//The constructor takes all the "NOT NULL" values, but there are others that can exist
				String[] socialMediaComponents = rs.getString("SOCIAL_MEDIA_1").split(":");
				Provider provider = SocialMedia.Provider.findMatch(socialMediaComponents[0]);
				String address = socialMediaComponents[1].trim();
				student.setFirstSocialMedia(provider, address);
				
				socialMediaComponents = rs.getString("SOCIAL_MEDIA_2").split(":");
				provider = SocialMedia.Provider.findMatch(socialMediaComponents[0]);
				address = socialMediaComponents[1].trim();
				student.setSecondSocialMedia(provider, address);
				
				String[] stringLanguages = rs.getString("LANGUAGES").split(",");
				ProgrammingLanguage[] languages = new ProgrammingLanguage[stringLanguages.length];
				for (int i = 0; i != languages.length; ++i){
					languages[i] = ProgrammingLanguage.findMatch(stringLanguages[i].trim());
				}
				student.setLanguages(languages);
				
				Array availArray = rs.getArray("AVAILABILITY");
				boolean[][] twoDimensionalAvail = new boolean[7][12];
				Boolean[] oneDimensionalAvail = (Boolean[])availArray.getArray();
				for (int i = 0; i != twoDimensionalAvail.length; ++i){
					for (int j = 0; j != twoDimensionalAvail[i].length; ++j){
						twoDimensionalAvail[i][j] = oneDimensionalAvail[i * twoDimensionalAvail.length + j];
					}
				}
				student.setAvailability(twoDimensionalAvail);
				
				student.setCourse(rs.getString("COURSE"));
				student.setSecondaryEmail(rs.getString("SECONDARY_EMAIL"));
				student.setExperience(rs.getString("EXPERIENCE"));
				student.setStudyLevel(StudyLevel.findMatch(rs.getString("STUDY_LEVEL")));
				student.setPreferredRole(Role.findMatch(rs.getString("PREFERRED_ROLE")));
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		return student;
	}
	
	public void addStudent(Student newMember){
		String insertQuery = 
				"	INSERT INTO Student"
			+	"	VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try{
			PreparedStatement statement = dbConnection.prepareStatement(insertQuery);
				statement.setString(1, newMember.getSID());
				statement.setString(2, newMember.getUnikey());
				statement.setString(3, newMember.getFirstName());
				statement.setString(4, newMember.getLastName());
				statement.setString(5, newMember.getCourse());
				statement.setString(6, newMember.getPrimaryEmail());
				statement.setString(7, newMember.getSecondaryEmail());
				statement.setString(8, newMember.getMobile());
				
				String firstSocialMedia = (newMember.getFirstSocialMedia() == null? null : newMember.getFirstSocialMedia().toString());
				statement.setString(9, firstSocialMedia); 
				
				String secondSocialMedia = (newMember.getSecondSocialMedia() == null? null : newMember.getSecondSocialMedia().toString());
				statement.setString(10, secondSocialMedia);
				
				String studyLevel = (newMember.getStudyLevel() == null? null : newMember.getStudyLevel().toString());
				statement.setString(11, studyLevel);
				
				String preferredRole = (newMember.getPreferredRole() == null? null : newMember.getPreferredRole().toString());
				statement.setString(12, preferredRole);
				statement.setString(14, newMember.getExperience());
				statement.setBoolean(15, newMember.getESL());
				
				String languageString = "";
				boolean firstPassed = false;
				for (ProgrammingLanguage language : newMember.getLanguages()){
					if (!firstPassed){
						firstPassed = true;
					}
					else{
						languageString += ", ";
					}
					languageString += language.toString();
				}
				statement.setString(16, languageString);
				
				Boolean[] oneDimensionalAvail = new Boolean[84];
				boolean[][] twoDimensionalAvail = newMember.getAvailability();
				for (int i = 0; i != twoDimensionalAvail.length ; ++i){
					for (int j = 0; j != twoDimensionalAvail[i].length; ++j){
						oneDimensionalAvail[i * twoDimensionalAvail.length + j] = twoDimensionalAvail[i][j];
					}
				}
				Array availArray = dbConnection.createArrayOf("boolean", oneDimensionalAvail);
				
				statement.setArray(17, null);
				statement.execute();
		} catch (SQLException e){
			e.printStackTrace();
		} 
	}

	public void resetDatabase(){
		String createQuery = 
				"DROP TABLE IF EXISTS Student CASCADE;"
			+	"CREATE TABLE Student ("
			+	"	SID					CHAR(9) 		NOT NULL,"
			+ 	"	UNIKEY 				CHAR(8) 		NOT NULL,"
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
			+	"	AVAILABILITY		BOOLEAN[84],"		//	7 days x 12 hours (8am-8pm) 
			+	"	PRIMARY KEY			(SID)"
			+ 	");"
			+	"DROP TABLE IF EXISTS UnitOfStudy CASCADE;"
			+	"CREATE TABLE UnitOfStudy ("
			+	"	UOS_ID			CHAR(8)			NOT NULL,"
			+	"	UOS_NAME			VARCHAR(50)		NOT NULL,"
			+	"	UOS_DESCRIPTION	VARCHAR(100),"
			+	"	NUM_STUDENTS		INTEGER		NOT NULL,"
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
			+	"	PROJECT_ID		INTEGER		NOT NULL,"
			+	"	UOS_ID			CHAR(8)		REFERENCES UnitOfStudy(UOS_ID)		NOT NULL,"
			+	"	DESCRIPTION		VARCHAR(100),"
			+	"	DEADLINE		DATE		NOT NULL,"
			+	"	PRIMARY KEY		(PROJECT_ID)"
			+	");"
			+	"DROP TABLE IF EXISTS Team CASCADE;" //could not use the name "Group" since it's a reserved word in SQL
			+	"CREATE TABLE Team ("
			+	"	TEAM_ID			INTEGER		NOT NULL,"
			+	"	PROJECT_ID		INTEGER		REFERENCES Project(PROJECT_ID)		NOT NULL,"
			+	"	PRIMARY KEY		(TEAM_ID)"
			+	");"
			+	"DROP TABLE IF EXISTS TeamMembership CASCADE;"
			+	"CREATE TABLE TeamMembership ("
			+	"	STUDENT		CHAR(9)		REFERENCES Student(SID)		NOT NULL,"
			+	"	TEAM		INTEGER		REFERENCES Team(TEAM_ID)	NOT NULL,"
			+	"	PRIMARY KEY (STUDENT, TEAM)"
			+	");"
			+ 	"DROP TABLE IF EXISTS Administrator CASCADE;"
			+	"CREATE TABLE Administrator ("
			+	"	STAFF_NUM		CHAR(9)		NOT NULL,"	//format of a USYD staff number?	
			+	"	UOS				CHAR(8)		REFERENCES UnitOfStudy(UOS_ID)		NOT NULL,"
			+	"	PRIMARY KEY (STAFF_NUM, UOS)" // a staff member could administer multiple courses
			+	");"
			+	"DROP TABLE IF EXISTS InstantMessage CASCADE;"
			+	"CREATE TABLE InstantMessage ("
			+	"	MESSAGE_ID	INTEGER			NOT NULL,"
			+	"	SENDER		CHAR(9)			REFERENCES Student(SID)		NOT NULL,"
			+	"	TEAM		INTEGER			REFERENCES Team(TEAM_ID)	NOT NULL,"
			+	"	MESSAGE		VARCHAR(200)	NOT NULL,"
			+	"	PRIMARY KEY	(MESSAGE_ID)"
			+	");"
		;
		Statement stmt = null;
		try{
			stmt = dbConnection.createStatement();
			stmt.executeUpdate(createQuery);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			//System.err.println("Could not create tables!");
		} finally {
			System.out.println("\nDatabase has been reset!\n");
		}
	}
	
	// this is mainly used for testing, to ensure the resetDatabase() method worked
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