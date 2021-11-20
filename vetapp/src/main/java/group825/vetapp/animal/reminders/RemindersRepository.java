package group825.vetapp.animal.reminders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

/**
 * Repository that stores Reminder information
 *
 * @author Jimmy Zhu
 * @version 1.0
 * @since November 15, 2021
 */
@Repository("tempRemindersRepo")
public class RemindersRepository {

	/**
	 * Database that stores all the reminders
	 */
	private static final List<Reminder> dbReminder = new ArrayList<>();
	
	/**
	 * Inserts a reminder in the database
	 * @param reminder new Reminder object for particular animal
	 * @return integer verifying successful code execution
	 */
	public int insertReminder(Reminder reminder) {
		UUID id = UUID.randomUUID();
		dbReminder.add(new Reminder(id, reminder.getReminderId(), reminder.getStatus(), reminder.getDateDue(),
				reminder.getDatePerformed(), reminder.getNote()));

		return 1;
	}
	
	/**
	 * Selects all reminders in the database
	 * @return database of reminders for all animals
	 */
	public List<Reminder> selectAllReminders() {
		return dbReminder;
	}
	
	/**
	 * Selects a reminder in the database by ID number
	 * @param id UUID pertaining to specific animal
	 * @return Optional<Reminder> object which contains the reminder of particular animal or is empty
	 */
	public Optional<Reminder> selectReminderById(UUID id) {
		return dbReminder.stream().filter(reminder -> reminder.getId().equals(id)).findFirst();
	}
	
	/**
	 * Deletes a reminder from the database by ID number
	 * @param id UUID pertaining to specific animal
	 * @return integer verifying successful code execution
	 */
	public int deleteReminderById(UUID id) {
		Optional<Reminder> animalMaybe = selectReminderById(id);

		if (animalMaybe.isEmpty()) {
			return 0;
		}

		dbReminder.remove(animalMaybe.get());
		return 1;
	}
	
	/**
	 * Updates a reminder from the database by ID number
	 * @param id UUID pertaining to specific animal
	 * @param update Reminder object containing new data members
	 * @return integer verifying successful code execution
	 */
	public int updateReminderById(UUID id, Reminder update) {
		return selectReminderById(id).map(animalFound -> {
			int indexOfAnimalToUpdate = dbReminder.indexOf(animalFound);

			// Index was found
			if (indexOfAnimalToUpdate >= 0) {
				dbReminder.set(indexOfAnimalToUpdate, new Reminder(id, update.getReminderId(),
						update.getStatus(), update.getDateDue(),
						update.getDatePerformed(), update.getNote()));
				return 1;
			}

			return 0;
				}).orElse(0); // If no animal found by the id then return 0
	}
}