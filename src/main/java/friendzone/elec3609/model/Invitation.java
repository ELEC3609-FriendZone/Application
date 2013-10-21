package friendzone.elec3609.model;

import org.springframework.beans.factory.annotation.Autowired;

import friendzone.elec3609.service.DatabaseHandler;


public class Invitation {
	 
	@Autowired
	DatabaseHandler dbHandler;
	
	int id;
	private String senderID;
	private String recipientID;
	private String message;
	private int projectID;
	
	 
	 public Invitation(int inviteID, String senderSID, String recipientSID, int projectID) {
		 this.id = inviteID;
		 this.senderID = senderSID;
		 this.recipientID = recipientSID;
		 this.projectID = projectID;
	 }
	 
	 public String getMessage(){
		 return message;
	 }
	 
	 public void accept(){
		 
	 }
	 
	 public void decline(){
		 
	 }
	 
	 public void setMessage(String message){
		 this.message = message;
	 }
	 
	 public Student getSender(){
		 return dbHandler.getStudent(senderID);
	 }
	 
	 public Student getRecipient(){
		 return dbHandler.getStudent(recipientID);
	 }
	 
	 public Project getProject(){
		 return dbHandler.getProject(projectID);
	 }
	 
	 public void copyValues(Invitation invite) {
		 this.id = invite.id;
		 this.senderID = invite.senderID;
		 this.recipientID = invite.recipientID;
		 this.message = invite.message;
		 this.projectID = invite.projectID;
	 }
}
