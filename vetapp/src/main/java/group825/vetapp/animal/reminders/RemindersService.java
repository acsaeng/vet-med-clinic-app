package group825.vetapp.animal.reminders;

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
 * @version 1.0
 * @since November 15, 2021
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
	 */
	public int insertReminder(Reminder reminder ) {
		return dbReminders.insertReminder(reminder);
	}

	/**
	 * Selects all reminders in the database
	 * @return list of all Reminder Objects
	 */
	public List<Reminder> selectAllReminders() {
		return dbReminders.selectAllReminders();
	}
	
	/**
	 * Selects a reminder in the database by ID number
	 * @param id UUID pertaining to specific animal
	 * @return Optional<Reminder> either containing the Reminder object for particular animal or is empty
	 */
	public Optional<Reminder> selectReminderById(UUID id) {
		return dbReminders.selectReminderById(id);
	}
	
	/** Deletes a reminder from the database by ID number
	 * @param id UUID pertaining to specific animal
	 * @return integer verifying successful code execution
	 */
	public int deletRemindersById(UUID id) {
		return dbReminders.deleteReminderById(id);
	}
	
	/**
	 * Updates a reminder from the database by ID number
	 * @param id UUID pertaining to specific animal
	 * @param reminder Reminder object with updated data members
	 * @return integer verifying successful code execution
	 */
	public int updateReminderById(UUID id, Reminder reminder) {
		return dbReminders.updateReminderById(id, reminder);
	}
}