package group825.vetapp2.reminders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import group825.vetapp2.database.Application_DbConnection;

/**
 * Repository that stores Reminder information
 *
 * @author Jimmy Zhu
 * @version 2.0
 * @since November 30, 2021
 */
@Repository("tempRemindersRepo")
public class RemindersRepository {
	String table_name = "REMINDERS";
	Application_DbConnection dao;
	String query;
	int Latest_reminder_id;
	
	public RemindersRepository() throws Exception {
		dao = new Application_DbConnection();
		getLatestReminderId();
	}
	
	/**
	 * Inserts a reminder in the database
	 * @param reminder new Reminder object for particular animal
	 * @return integer verifying successful code execution
	 * @throws Exception when there is an SQL Exception
	 */
	public int insertReminder(Reminder reminder) throws Exception{
		String query_begin = "INSERT INTO REMINDERS (Animal_ID, Reminder_ID, Reminder_Status, Due_Date, Date_Performed, Author_ID, Notes) VALUES";
		query = query_begin + "( '"+ reminder.getId()+"', '" + reminder.getReminderId() + "', '" +  reminder.getStatus() 
		+"', '" + reminder.getDateDue() +"', '" + reminder.getDatePerformed() + "', '" + reminder.getAuthorID() + "', '" + reminder.getNote() +"');";
//		System.out.println(query);
		try {
			int responseCheck = dao.manipulateRows(query);
		}catch(Exception e) {
			getLatestReminderId();
			query = query_begin + "( '"+ reminder.getId()+"', '" + (this.Latest_reminder_id +1) + "', '" +  reminder.getStatus() 
			+"', '" + reminder.getDateDue() +"', '" + reminder.getDatePerformed() + "', '" + reminder.getAuthorID() + "', '" + reminder.getNote() +"');";
			int responseCheck = dao.manipulateRows(query);
		}
		return 1;
	}
	
	/**
	 * Selects all reminders in the database
	 * @return ArrayList of strings for reminders for all animals
	 * @throws Exception when there is an SQL Exception
	 */
	public ArrayList<String> selectAllReminders() throws Exception {
		query = "SELECT * FROM "+this.table_name;
		query = "SELECT R.Animal_ID, R.Reminder_ID, R.Reminder_Status, R.Due_Date, R.Date_Performed, R.Author_ID, R.Notes, U.First_Name, U.Last_Name, U.User_Type FROM REMINDERS AS R, USERS AS U "
				+ "WHERE R.Author_ID = U.User_ID";
		ArrayList<String> results = dao.getResponseArrayList(query);
		return results;
	}
	
	/**
	 * Selects a reminder in the database by ID number
	 * @param id int pertaining to specific animal
	 * @return Optional<String> object which contains the reminder of particular animal or is empty
	 * @throws Exception when there is an SQL Exception
	 */
	public ArrayList<String> selectRemindersById(int id) throws Exception {
		query = "SELECT * FROM "+this.table_name +" AS R WHERE R.Animal_ID='"+id+"';";
		query = "SELECT R.Animal_ID, R.Reminder_ID, R.Reminder_Status, R.Due_Date, R.Date_Performed, R.Author_ID, R.Notes, U.First_Name, U.Last_Name, U.User_Type FROM REMINDERS AS R, USERS AS U "
				+ "WHERE R.Author_ID = U.User_ID AND R.Animal_ID='"+ id +"'";
		ArrayList<String> results = dao.getResponseArrayList(query);
		System.out.println("results from 'selectRemindersByID()'");
		for (String result: results) {
			System.out.println(result);
		}
		System.out.println("----- out of function\n");
		return results;
	}
	
	/**
	 * Deletes a reminder from the database by ID number
	 * @param id int pertaining to specific animal
	 * @return integer verifying successful code execution
	 * @throws Exception when there is an SQL Exception
	 */
	public int deleteReminderById(int id) throws Exception {
		String query = "DELETE FROM "+ table_name + " AS R WHERE R.Reminder_ID='"+id+"';";
//		System.out.println("query for update: "+query);
		int responseCheck = dao.manipulateRows(query);
		return responseCheck;
	}
	
	/**
	 * Updates a reminder from the database by ID number
	 * @param id int pertaining to specific animal
	 * @param update Reminder object containing new data members
	 * @return integer verifying successful code execution
	 */
	public int updateReminderById(int id, Reminder update) throws Exception {
		 String query = "UPDATE " + table_name + " AS C SET Animal_ID='" + update.getId() + "', Reminder_ID='" + update.getReminderId() 
		 + "', Reminder_Status='" + update.getStatus() + "', Due_Date='" + update.getDateDue() 
		 + "', Date_Performed='" + update.getDatePerformed() + "', Author_ID='" + update.getAuthorID() 
		 + "', Notes='" + update.getNote()  + "'"
		 + " WHERE C.Reminder_ID='"+update.getReminderId()+"';";

		//System.out.println("query for update: "+query);
		int responseCheck = dao.manipulateRows(query);
		return responseCheck;
	}
	
	
	/**
	 * get the latest Id for the primary key for Reminders objects from database
	 * @throws Exception when there is an SQL Exception
	 */
	private void getLatestReminderId() throws Exception{
		String queryMaxId = "SELECT MAX(R.Reminder_ID) FROM REMINDERS AS R ";
//		System.out.println(queryMaxId);
		String latestId = dao.getRows(queryMaxId).replaceAll("\\s+","");
		System.out.println("latestId ='"+latestId+"'");
		this.Latest_reminder_id = Integer.valueOf(latestId);
	}
	
	/**
     * @return the Split placeholder saved in the "Application_DbConnection.java"
     */
    public String getSplitPlaceholder() {
    	return dao.getSplitPlaceholder();
    }
}