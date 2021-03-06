package friendzone.elec3609.service;

import org.springframework.stereotype.Service;


import friendzone.elec3609.model.Team;
import friendzone.elec3609.model.Student;
import java.util.List;
import java.util.ArrayList;

@Service
public class MeetingsMaker {
	//DatabaseHandler dbHandler;
	
//	ArrayList<String> times, days,  members = new ArrayList<String>();
//	int[][] availCount = new int[7][12];
	int maxCount = 0;
	ArrayList<String>[][] members = new ArrayList[7][12];
	
	public MeetingsMaker()
	{
		//supposed constructor :/ 
		//I forgot How To Java! HAHAHAHA
	}
	//This function will return a list of the timestamps
	public ArrayList<String> getTimes(ArrayList<ArrayList<Integer>> values)
	{
		ArrayList<String> times = new ArrayList<String>();
		//Goes through each time slot
		for(ArrayList<Integer> slot: values)
		{
			int slotTime = slot.get(1);
			String time;
			
			if(slotTime == 0)
			{
				time = "8am";
			}
			else if(slotTime == 1)
			{
				time = "9am";
			}
			else if(slotTime == 2)
			{
				time = "10am";
			}
			else if(slotTime == 3)
			{
				time = "11am";
			}
			else if(slotTime == 4)
			{
				time = "12pm";
			}
			else if(slotTime == 5)
			{
				time = "1pm";
			}
			else if(slotTime == 6)
			{
				time = "2pm";
			}
			else if(slotTime == 7)
			{
				time = "3pm";
			}
			else if(slotTime == 8)
			{
				time = "4pm";
			}
			else if(slotTime == 9)
			{
				time = "5pm";
			}
			else if(slotTime == 10)
			{
				time = "6pm";
			}
			else
			{
				time = "7pm";
			}
			//Add it to the arrayList of strings for the times
			times.add(time);
				
		}
		
		return times;
	}
	
	//This function will return a list of the Days
	public ArrayList<String> getDays(ArrayList<ArrayList<Integer>> values)
	{
		ArrayList<String> days = new ArrayList<String>();
		//Goes through each day slot
		for(ArrayList<Integer> slot: values)
		{
			int slotTime = slot.get(0);
			String day;
			
			if(slotTime == 0)
			{
				day = "Monday";
			}
			else if(slotTime == 1)
			{
				day = "Tuesday";
			}
			else if(slotTime == 2)
			{
				day = "Wednesday";
			}
			else if(slotTime == 3)
			{
				day = "Thursday";
			}
			else if(slotTime == 4)
			{
				day = "Friday";
			}
			else if(slotTime == 5)
			{
				day = "Saturday";
			}
			else 
			{
				day = "Sunday";
			}
			
			//Add it to the arrayList of strings for the times
			days.add(day);
				
		}
		return days;
	}
	
	//This function will return a list of the available team members at that time
	public ArrayList<String> getAvailableMembers(int i, int j, Team team)
	{
		ArrayList<String> members = new ArrayList<String>();
		List<Student> teamMembers = team.getMembers();
		boolean[][] studAvail = new boolean[7][12];
		
		for(Student s : teamMembers)
		{
			studAvail = s.getAvailability();
			if(studAvail[i][j] == true)
			{
				members.add(s.getUnikey());
			}
		}
		
		return members;
	}
	
	//This function will return the count of people attend who can attend
	//at a particular time
	public int getCount(int[][] availCount, int i, int j)
	{
		int count = 0;
		for(int row = 0; row <7; row ++)
		{
			for(int col =0; col<12; col++)
			{
				if(row == i && col ==j)
					count = availCount[i][j];
			}
		}
		return count;
	}
	
	/**This finds if there is an available time for the group to meet as a whole */
	public int[][] countAvailability(Team team){
		
		//initializing variables
		int[][] availCount = new int[7][12];
		boolean[][] studAvail = new boolean[7][12];
		
		List<Student> teamMembers = team.getMembers();
		
		//for each team member get their availability
		for(Student s: teamMembers)
		{
			studAvail = s.getAvailability();
			//Go through the 2D array List
			for(int i=0; i<7; i++)
			{
				for(int j=0; j<12; j++)
				{
					//If any value is true
					if(studAvail[i][j] == true)
					{
						//Increment the count in that particular spot of the array
						availCount[i][j] += 1;
						//System.out.println(String.valueOf(s.getUnikey()));
						if(availCount[i][j] > maxCount)
							maxCount = availCount[i][j];
					}
				}
			}
		}
		
		return availCount;
	}
	
	/**Gets the maxCount variable*/
	public int getMaxCount()
	{
		return maxCount;
	}
	
	public ArrayList<String>[][] getMembers()
	{
		return members;
	}
	
	//This stores the best possible meeting times
	public ArrayList<ArrayList<Integer>> getTopMeetingTimes(int[][] availCount, Team team)
	{
		ArrayList<ArrayList<Integer>> topMeetingTimes = new ArrayList<ArrayList<Integer>>();
		//Loops through the 2D array and adds the value of i and j if everyone is available.
		for(int i=0; i<7; i++)
		{
			for(int j=0; j<12; j++)
			{
				if(availCount[i][j] == team.getMembers().size())
				{
					ArrayList<Integer> timeSlot= new ArrayList<Integer>();
					//Store i and j in list where
					// index 0 = i
					// index 1 = j
					timeSlot.add(i);
					timeSlot.add(j);
					//Add the arraylist to the topMeetingTims array List
					topMeetingTimes.add(timeSlot);
				}
			}
		}
		
		//Makes sure there is at least 5 times shown in the list
		//Adds times with one person less
		if(topMeetingTimes.size() < 5)
		{
			if(team.getMembers().size() > 1)
			{
				for(int i=0; i<7; i++)
				{
					for(int j=0; j<12; j++)
					{
						int countSize = team.getMembers().size() - 1;
						if(availCount[i][j] == countSize)
						{
							ArrayList<Integer> timeSlot= new ArrayList<Integer>();
							//Store i and j in list where
							// index 0 = i
							// index 1 = j
							timeSlot.add(i);
							timeSlot.add(j);
							
							//Add the arraylist to the topMeetingTims array List
							topMeetingTimes.add(timeSlot);
						}
					}
				}
			}
		}
		
		//Makes sure there is at least 5 times shown in the list
		//Adds times with two person less
		if(topMeetingTimes.size() < 5)
		{
			if(team.getMembers().size() > 2)
			{
				for(int i=0; i<7; i++)
				{
					for(int j=0; j<12; j++)
					{
						int countSize = team.getMembers().size() - 2;
						if(availCount[i][j] == countSize)
						{
							ArrayList<Integer> timeSlot= new ArrayList<Integer>();
							//Store i and j in list where
							// index 0 = i
							// index 1 = j
							timeSlot.add(i);
							timeSlot.add(j);
							
							//Add the arraylist to the topMeetingTims array List
							topMeetingTimes.add(timeSlot);
						}
					}
				}
			}
		}
		
		return topMeetingTimes;
	}
	
	public String covertDay(int i)
	{
		String m ="";
		String d ="";
		
		switch(i)
		{
		case '0':
			m="10";
			d="04";
			break;
		case '1':
			m="10";
			d="05";
			break;
		case '2':
			m="10";
			d="06";
			break;
		case '3':
			m="10";
			d="07";
			break;
		case '4':
			m="10";
			d="08";
			break;
		case '5':
			m="10";
			d="09";
			break;
		case '6':
			m="10";
			d="10";
			break;
		}
		
		String date = "2013-" + m + "-" + d;
		return date;
	}

	
}