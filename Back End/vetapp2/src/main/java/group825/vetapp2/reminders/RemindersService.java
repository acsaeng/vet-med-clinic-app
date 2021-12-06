package group825.vetapp2.reminders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


/**
 * Service that performs Reminder requests
 *
 * @author Jimmy Zhu
 * @version 2.0
 * @since November 30, 2021
 */
@Service
public class RemindersService {

	/**
	 * Reminder repository that accesses the database
	 */
	private final RemindersRepository dbReminders;
	
	/**
	 * Reminder repository that accesses the database
	 * @param dbReminders repository denoted with Qualifier annotation storing the Reminder objects
	 */
	@Autowired
	public RemindersService(@Qualifier("tempRemindersRepo") RemindersRepository dbReminders) {
		this.dbReminders = dbReminders;
	}

	/**
	 * Inserts a reminder in the database
	 * @param reminder new Reminder object to be added
	 * @return integer verifying successful code execution
	 * @throws Exception when there is an SQL Exception
	 */
	public int insertReminder(Reminder reminder ) throws Exception {
		return dbReminders.insertReminder(reminder);
	}

	/**
	 * Selects all reminders in the database
	 * @return list of all Reminder Objects
	 * @throws Exception when there is an SQL Exception
	 */
	public List<Reminder> selectAllReminders() throws Exception{
		ArrayList<String> results = dbReminders.selectAllReminders();
		List<Reminder> listResults = createListReminders(results);
		return listResults;
	}
	
	/**
	 * Selects a reminder in the database by ID number
	 * @param id int pertaining to specific animal
	 * @return List<Reminder> either containing the Reminder object for particular animal or is empty
	 * @throws Exception when there is an SQL Exception
	 */
	public List<Reminder> selectRemindersById(int id) throws Exception {
		ArrayList<String> results =  dbReminders.selectRemindersById(id);
		List<Reminder> listResults = createListReminders(results);
		return listResults;
	}
	
	/** Deletes a reminder from the database by ID number
	 * @param id int pertaining to specific animal
	 * @return integer verifying successful code execution
	 * @throws Exception when there is an SQL Exception
	 */
	public int deleteReminderById(int id) throws Exception{
		return dbReminders.deleteReminderById(id);
	}
	
	/**
	 * Updates a reminder from the database by ID number
	 * @param id int pertaining to specific animal
	 * @param reminder Reminder object with updated data members
	 * @return integer verifying successful code execution
	 * @throws Exception when there is an SQL Exception
	 */
	public int updateReminderById(int id, Reminder reminder) throws Exception{
		return dbReminders.updateReminderById(id, reminder);
	}
	
	/**
	  * Create a list of Reminder objects from ArrayList<String> returned from database query
	 * @param foundResults = ArrayList<String> preprocessed response from database of all returned tuples as an ArrayList of Strings
	 * @return ArrayList<Reminder> where each object was created from the data in each String from the ArrayList input
	 */
	public List<Reminder> createListReminders(ArrayList<String> foundResults){
		List<Reminder> listResults = new ArrayList<Reminder>(); 
		//review against Database setup
		int idx_id=0, idx_reminderId=1, idx_status=2, idx_dateDue=3, idx_datePerformed=4, idx_authorID=5, idx_note=6, idx_FirstName=7, idx_LastName=8, idx_UserType=9;
		for (String result: foundResults) {
			String[] resultSplit = result.split(dbReminders.getSplitPlaceholder());
			for(String individualInfo: resultSplit) {System.out.println(individualInfo);}
			Reminder temp =  new Reminder( Integer.valueOf(resultSplit[idx_id]), Integer.valueOf(resultSplit[idx_reminderId]), resultSplit[idx_status], 
						resultSplit[idx_dateDue], resultSplit[idx_datePerformed], Integer.valueOf(resultSplit[idx_authorID]), resultSplit[idx_note],
						resultSplit[idx_FirstName], resultSplit[idx_LastName], resultSplit[idx_UserType]);
		listResults.add(temp);
	}
	System.out.println("\nPrepared List to send as json response to API endpoint:");
	System.out.println(listResults);

	return listResults;
	}
	
	
}