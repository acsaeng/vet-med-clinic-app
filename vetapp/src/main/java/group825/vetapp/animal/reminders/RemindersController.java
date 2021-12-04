package group825.vetapp.animal.reminders;

import java.util.List;
import java.util.UUID;

import group825.vetapp.exceptions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller that handles Reminder requests
 *
 * @author Jimmy Zhu
 * @version 2.0
 * @since November 30, 2021
 */
@RequestMapping("app/reminders/animal")
@RestController
public class RemindersController {

	/**
	 * Reminder service that performs the request
	 */
	private final RemindersService remindersService;

	/**
	 * Constructor that initializes the ReminderController
	 * @param remindersService RemindersService object which interacts with the "RemindersRepository" Class
	 */
	@Autowired
	public RemindersController(RemindersService remindersService) {
		this.remindersService = remindersService;
	}

	/**
	 * 'POST' mapping that adds a reminder to the database
	 * @param reminder RequestBody JSON object to be passed to the Reminder class where the JSON keys are already mapped to specific data members
	 * @throws Exception when there is an SQL Exception
	 */
	@PostMapping
	public void addReminder(@RequestBody Reminder reminder) throws Exception{
		if (reminder.anyNulls()) {
			throw new ApiRequestException("Data members cannot be null! Check the Request Body being sent.");
		}
		remindersService.insertReminder(reminder);
	}

	/**
	 * 'GET' mapping that retrieves all remoders from the database
	 * @return List<Reminder> object containing the Reminders of all animals by calling method from the repository
	 * @throws Exception when there is an SQL Exception
	 */
	@GetMapping
	public List<Reminder> selectAllReminders() throws Exception {
		return remindersService.selectAllReminders();
	}

	/**
	 * 'GET' mapping that selects a photo by ID number from the database
	 * @param idStr = String path variable obtained by path denoted inside the GetMapping annotation
	 * @return Reminder object or throw exception
	 */
	@GetMapping(path="{id}") 
	public List<Reminder> selectRemindersById(@PathVariable("id") String idStr) {
		try {
			//id of animal
			int id = Integer.valueOf(idStr);
			return remindersService.selectRemindersById(id);
		} catch (Exception e) {
			// Catch if id is not a valid Animal ID from Database
			throw new InvalidIdException();
		}
	}

	/**
	 * 'DELETE' mapping that deletes a reminder by ID number from the database
	 * @param idStr String path variable obtained by path denoted inside the DeleteMapping annotation
	 */
	@DeleteMapping(path = "{id}")
	public void deleteReminderById(@PathVariable("id") String reminderID) throws Exception{
		//id of a comment
		int id = Integer.valueOf(reminderID);
		int numRowsAffected = remindersService.deleteReminderById(id);
		if (numRowsAffected == 0) {throw new InvalidIdException();}
	}

	/**
	 * 'PUT' mapping that updates a reminder's information
	 * @param idStr UUID path variable obtained by path denoted inside the PutMapping annotation
	 * @param reminderToUpdate response body from HTTP request which should contain keys for necessary data members
	 */
	@PutMapping(path = "{id}")
	public void updateReminderById(@PathVariable("id") String reminderID, @RequestBody Reminder reminderToUpdate) throws Exception{
		//id of a comment
				int id = Integer.valueOf(reminderID);
				if (reminderToUpdate.anyNulls()) {
					throw new ApiRequestException("Data members cannot be null! Check the Request Body being sent.");
				}
				int numRowsAffected = remindersService.updateReminderById(id, reminderToUpdate);	
				if (numRowsAffected == 0) {throw new InvalidIdException();}
	}
}