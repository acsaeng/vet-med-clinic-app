package group825.vetapp2.reminders;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import group825.vetapp2.database.OldDatabaseConnection;

/**
 * Repository that stores Reminder information
 *
 * @author Jimmy Zhu
 * @version 2.0
 * @since November 30, 2021
 */
@Repository("tempRemindersRepo")
public class RemindersRepository {
	/**
	 * Table name from database
	 */
	String tableName = "REMINDERS";
	
	/**
	 * Database connection boundary class
	 */
	OldDatabaseConnection dao;
	
	/**
	 * Any query that is sent to the database
	 */
	String query;
	
	/**
	 * Max Reminder ID currently recorded on the database
	 */
	int latestID;
	
	public RemindersRepository() throws Exception {
		dao = new OldDatabaseConnection();
		getLatestReminderID();
	}
	
	/**
	 * Inserts a reminder in the database
	 * @param reminder = new Reminder object for particular animal
	 * @return integer verifying successful code execution
	 * @throws Exception when there is an SQL Exception
	 */
	public int insertReminder(Reminder reminder) throws Exception{
		String queryBegin = "INSERT INTO REMINDERS (Animal_ID, Reminder_ID, Reminder_Status, Due_Date, Date_Performed, Author_ID, Notes) VALUES";
		query = queryBegin + "( '"+ reminder.getAnimalID()+"', '" + reminder.getReminderID() + "', '" +  reminder.getStatus() 
		+"', '" + reminder.getDateDue() +"', '" + reminder.getDatePerformed() + "', '" + reminder.getAuthorID() + "', '" + reminder.getNote() +"');";
		System.out.println("query = "+query);
		try {
			int responseCheck = dao.manipulateRows(query);
		}catch(Exception e) {
			getLatestReminderID();
			query = queryBegin + "( '"+ reminder.getAnimalID()+"', '" + (this.latestID +1) + "', '" +  reminder.getStatus() 
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
		query = "SELECT * FROM "+this.tableName;
		query = "SELECT R.Animal_ID, R.Reminder_ID, R.Reminder_Status, R.Due_Date, R.Date_Performed, R.Author_ID, R.Notes, U.First_Name, U.Last_Name, U.User_Type FROM REMINDERS AS R, USERS AS U "
				+ "WHERE R.Author_ID = U.User_ID";
		System.out.println("query = "+query);
		ArrayList<String> results = dao.getResponseArrayList(query);
		return results;
	}
	
	/**
	 * Selects a reminder in the database by animal ID number
	 * @param id = id pertaining to specific animal
	 * @return ArrayList<String> object which contains the reminders of particular animal or is empty
	 * @throws Exception when there is an SQL Exception
	 */
	public ArrayList<String> selectRemindersByID(int animalID) throws Exception {
		query = "SELECT R.Animal_ID, R.Reminder_ID, R.Reminder_Status, R.Due_Date, R.Date_Performed, R.Author_ID, R.Notes, U.First_Name, U.Last_Name, U.User_Type FROM REMINDERS AS R, USERS AS U "
				+ "WHERE R.Author_ID = U.User_ID AND R.Animal_ID='"+ animalID +"'";
		System.out.println("query = "+query);
		ArrayList<String> results = dao.getResponseArrayList(query);
//		System.out.println("results from 'selectRemindersByID()'");
//		for (String result: results) {
//			System.out.println(result);
//		}
//		System.out.println("----- out of function\n");
		return results;
	}
	
	/**
	 * Deletes a reminder from the database by reminder ID number
	 * @param id = id pertaining to specific reminder
	 * @return integer verifying successful code execution
	 * @throws Exception when there is an SQL Exception
	 */
	public int deleteReminderByID(int reminderID) throws Exception {
		String query = "DELETE FROM "+ tableName + " AS R WHERE R.Reminder_ID='"+reminderID+"';";
		System.out.println("query for update: "+query);
		int responseCheck = dao.manipulateRows(query);
		return responseCheck;
	}
	
	/**
	 * Updates a reminder from the database by ID number
	 * @param id = reminder ID pertaining to specific reminder
	 * @param update = Reminder object containing updated data members
	 * @return integer verifying successful code execution
	 */
	public int updateReminderByID(int reminderID, Reminder update) throws Exception {
		 String query = "UPDATE " + tableName + " AS C SET Animal_ID='" + update.getAnimalID() 
		 + "', Reminder_ID='" + reminderID
		 + "', Reminder_Status='" + update.getStatus() + "', Due_Date='" + update.getDateDue() 
		 + "', Date_Performed='" + update.getDatePerformed() + "', Author_ID='" + update.getAuthorID() 
		 + "', Notes='" + update.getNote()  + "'"
		 + " WHERE C.Reminder_ID='"+reminderID+"';";

		System.out.println("query for update: "+query);
		int responseCheck = dao.manipulateRows(query);
		return responseCheck;
	}
	
	
	/**
	 * get the latest ID for the primary key for Reminders objects from database
	 * @throws Exception when there is an SQL Exception
	 */
	private void getLatestReminderID() throws Exception{
		String queryMaxID = "SELECT MAX(R.Reminder_ID) FROM REMINDERS AS R ";
//		System.out.println(queryMaxID);
		String latestID = dao.getRows(queryMaxID).replaceAll("\\s+","");
//		System.out.println("latestID ='"+latestID+"'");
		this.latestID = Integer.valueOf(latestID);
	}
	
	/**
     * @return the Split placeholder saved in the "DatabaseConnection.java"
     */
    public String getSplitPlaceholder() {
    	return dao.getSplitPlaceholder();
    }
}