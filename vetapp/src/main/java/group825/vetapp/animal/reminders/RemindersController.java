package group825.vetapp.animal.reminders;

import java.util.List;
import java.util.UUID;

import group825.vetapp.exceptions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RequestMapping("app/reminders/animal")
@RestController
public class RemindersController {
	private final RemindersService remindersService;

	/** RemindersController Constructor 
	 * @param remindersService = RemindersService object which interacts with the "RemindersRepository" Class
	 */
	@Autowired
	public RemindersController(RemindersService remindersService) {
		this.remindersService = remindersService;
	}
	
	
	
	/** addReminder function - POST MAPPING
	 * @param reminder = RequestBody json object to be passed to the Reminder class where the json keys are already mapped to specific data members
	 */
	@PostMapping
	public void addReminder(@RequestBody Reminder reminder){	
		if (reminder.anyNulls()) {
			throw new ApiRequestException("Data members cannot be null! Check the Request Body being sent.");
		}
		remindersService.insertReminder(reminder);
	}


	
	/** selectAllReminders function - GET MAPPING
	 * @return List<Reminder> object containing the Reminders of all animals by calling method from the repository
	 */
	@GetMapping
	public List<Reminder> selectAllReminders(){
		return remindersService.selectAllReminders();
	}
	
	
	
	/** selectReminderById function - GET MAPPING
	 * @param id = UUID path variable obtained by path denoted inside the GetMapping annotation
	 * @return Reminder object or throw exception
	 */
	@GetMapping(path="{id}") 
	public Reminder selectReminderById(@PathVariable("id") String id_str) {	
		try {
			UUID id = UUID.fromString(id_str);
			return remindersService.selectReminderById(id)
					.orElseThrow(ApiExceptions.invalidIdException()); //throw exception if UUID is valid but does not exist in database
		}catch(java.lang.IllegalArgumentException e) { //catch if id_str is not a valid UUID
			throw new InvalidIdException();
		}
		
	}
	

	
	/** deleteReminderById function - DELETE MAPPING
	 * @param id = UUID path variable obtained by path denoted inside the DeleteMapping annotation
	 */
	@DeleteMapping(path = "{id}")
	public void deleteReminderById(@PathVariable("id") String id_str) {
		try {
			UUID id = UUID.fromString(id_str);
			remindersService.deletRemindersById(id);
		}catch(java.lang.IllegalArgumentException e) {
			throw new InvalidIdException();
		}
		
	}
	
	
	
	/** updateReminderById function - PUT MAPPING
	 * @param id = UUID path variable obtained by path denoted inside the PutMapping annotation
	 * @param reminderToUpdate = response body from HTTP request which should contain keys for necessary data members
	 */
	@PutMapping(path = "{id}")
	public void updateReminderById(@PathVariable("id") String id_str, @RequestBody Reminder reminderToUpdate) {	
		try {
			UUID id = UUID.fromString(id_str);
			if (reminderToUpdate.anyNulls()) {
				throw new ApiRequestException("Data members cannot be null! Check the Request Body being sent.");
			}
			remindersService.updateReminderById(id, reminderToUpdate);
		}catch(java.lang.IllegalArgumentException e) {
			throw new InvalidIdException();
		}
	}
}
