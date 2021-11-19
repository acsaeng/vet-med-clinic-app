package group825.vetapp.animal.reminders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;



@Repository("tempRemindersRepo")
public class RemindersRepository { 
	
	private static List<Reminder> DB_Reminder = new ArrayList<>();
	
	/** insertReminder function
	 * @param reminder = new Reminder object for particular animal
	 * @return integer verifying successful code execution
	 */
	public int insertReminder(Reminder reminder) {
		UUID id = UUID.randomUUID(); 
		DB_Reminder.add(new Reminder(id, reminder.getReminderId(), 
				reminder.getStatus(), reminder.getDateDue(),
				reminder.getDatePerformed(), reminder.getNote()));
		return 1;
	}
	
	/** selectAllReminder function
	 * @return DB of Reminders for all animals
	 */
	public List<Reminder> selectAllReminders(){
		return DB_Reminder;
	}
	
	/** selectReminderById function
	 * @param id = UUID pertaining to specific animal
	 * @return Optional<Reminder> object which contains the Reminder of particular animal or is empty
	 */
	public Optional<Reminder> selectReminderById(UUID id) {
		
		return DB_Reminder.stream()
				.filter(reminder -> reminder.getId().equals(id))
				.findFirst();
	}
	
	/** deleteReminderById function
	 * @param id = UUID pertaining to specific animal
	 * @return integer verifying successful code execution
	 */
	public int deleteReminderById(UUID id) {
		Optional<Reminder> animalMaybe = selectReminderById(id);
		if (animalMaybe.isEmpty()) { return 0; }
		DB_Reminder.remove(animalMaybe.get());
		return 1;
	}
	
	/**updateReminderById function
	 * @param id = UUID pertaining to specific animal 
	 * @param update = Reminder object containing new data members
	 * @return integer verifying successful code execution
	 */
	public int updateReminderById(UUID id, Reminder update) {
		return selectReminderById(id)
				.map(animalFound -> {
					int indexOfAnimalToUpdate = DB_Reminder.indexOf(animalFound);
					if (indexOfAnimalToUpdate >= 0) { //index was found, 
						DB_Reminder.set(indexOfAnimalToUpdate, new Reminder(id, update.getReminderId(), 
								update.getStatus(), update.getDateDue(),
								update.getDatePerformed(), update.getNote()));
						return 1;   
					}
					return 0; 
				})
				.orElse(0); //if no animal found by the id then return 0
	}
	
	
	
}
