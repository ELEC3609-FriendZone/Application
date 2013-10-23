package friendzone.elec3609.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import org.springframework.stereotype.Service;

import friendzone.elec3609.model.Project;
import friendzone.elec3609.model.Team;

@Service
public class GroupFormer{
	
	/**
	 * This class is used to run the group forming algorithm. As a <code>Runnable</code> object, it can run in it's own <code>Thread</code> and will update the database when it completes.
	 * Since students access their teams through a database query, they will receive the updates next time they load any view with team-relevant info.
	 * @author CJR
	 */
	private class GroupFormingAlgorithm implements Runnable{

		/**
		 * Node class used by the algorithm, contains an SID of the student it represents and a compatibility score with every other node  (null for dummy nodes) 
		 * @author CJR
		 */
		private class Node{
			String SID;
			
			public Node(String SID){
				this.SID = SID;		
			}
		}
		
		/**
		 * Links refer to the edges, which connect the Nodes to eachother with a value.
		 * This value refers to the compatibility of the two students, and boolean is used to ensure we don't try to switch students in the same group, or calculate the viability of a switch that we've already considered
		 * @author CJR
		 */
		private class Link{
			boolean considered;
			Node start;
			Node end;
			int value; // value is the compatibility between two students, which is constant and based on the student attributes
			int score; // the score of a switch is the value the algorithm uses to determine what the best possible switch, and is basically the profit gained from performing a switch
			
			public Link(Node start, Node end, int value){
				this.start = start;
				this.end = end;
				this.value = value;
			}

			public boolean getConsidered(){
				return considered;
			}
		}
		
		int projectID;
		int minTeamSize;
		int maxTeamSize;
		ArrayList<ArrayList<Node>> groups; //we have a list of groups, and each group has a list of nodes (its current members) 
		
		public GroupFormingAlgorithm(int projectID){
			this.projectID = projectID;
			Project project = dbHandler.getProject(projectID);
			this.minTeamSize = project.getMinTeamSize();
			this.maxTeamSize = project.getMaxTeamSize();
			
		}
		
		public void run() {
			System.out.println("Starting group forming for " + projectID);
			
			//TODO: group forming algorithm runs here
			
			System.out.println("Group forming for " + projectID + " completed!");
		}
	}
	
	final static DatabaseHandler dbHandler = DatabaseHandler.getInstance();
	
	
	public void createGroups(int projectID) {
		Thread thread = new Thread(new GroupFormingAlgorithm(projectID));
		thread.run();
		return;
	}
	
	
}