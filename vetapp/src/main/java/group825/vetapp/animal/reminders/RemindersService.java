package group825.vetapp.animal.reminders;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class RemindersService {
	private final RemindersRepository dB_Reminders;
	
	/** Constructor 
	 * @param dB_Reminders = Repository denoted with Qualifier annotation storing the Reminder objects
	 */
	@Autowired
	public RemindersService(@Qualifier("tempRemindersRepo") RemindersRepository dB_Reminders) {
		this.dB_Reminders = dB_Reminders; 
	}


	/** insertReminder function 
	 * @param reminder = new Reminder object to be added
	 * @return integer verifying successful code execution
	 */
	public int insertReminder(Reminder reminder ) {
		return dB_Reminders.insertReminder(reminder);
	}
	

	/** selectAllReminder function 
	 * @return List of all Reminder Objects
	 */
	public List<Reminder> selectAllReminders(){
		return dB_Reminders.selectAllReminders();
	}
	
	/** selectRemindersById function
	 * @param id = UUID pertaining to specific animal
	 * @return Optional<Reminder> either containing the Reminder object for particular animal or is empty
	 */
	public Optional<Reminder> selectReminderById(UUID id){
		return dB_Reminders.selectReminderById(id);
	}
	
	/** deleteReminderById function
	 * @param id = UUID pertaining to specific animal
	 * @return integer verifying successful code execution
	 */
	public int deletRemindersById(UUID id) {
		return dB_Reminders.deleteReminderById(id);
	}
	
	/** updateReminderById function
	 * @param id = UUID pertaining to specific animal
	 * @param reminder = Reminder object with updated data members
	 * @return integer verifying successful code execution
	 */
	public int updateReminderById(UUID id, Reminder reminder) {
		return dB_Reminders.updateReminderById(id, reminder);
	}
	
	
	
}
