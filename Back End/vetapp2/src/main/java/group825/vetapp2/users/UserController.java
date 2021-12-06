package group825.vetapp2.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectSerializer;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;

import group825.vetapp2.exceptions.ApiRequestException;
import group825.vetapp2.exceptions.InvalidIdException;
import group825.vetapp2.treatment.Treatment;

import java.util.List;
import java.util.UUID;

/**
 * Controller that handles User requests
 *
 * @author Aron Saengchan, Jimmy Zhu, Timothy Mok
 * @version 2.0
 * @since December 6, 2021
 */
@CrossOrigin
@RestController
@RequestMapping(path = "/app")
public class UserController {

    /**
     * User service that performs the request
     */
    private final UserService userService;

    /**
     * Constructor that initializes the UserController
     * @param userService implements the request
     */
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 'Post' request that verifies email and password inputs received from the front end
     * @param userName the entered username
     * @param password the entered password
     * @return a list of users if login successful
     * @throws Exception when there is an SQL Exception
     */
    @GetMapping(path = "/login")
    @ResponseBody
    public List<User> loginUser(@RequestParam String userName, @RequestParam String password) throws Exception{
        return this.userService.loginUser(userName, password);
    }

    /**
     * 'GET' request that retrieves all the stored users from the database
     * @return list of all stored users
     * @throws Exception when there is an SQL Exception
     */
    @GetMapping(path = "/admin/users")
    public List<User> selectAllUsers() throws Exception {
        return this.userService.selectAllUsers();
    }

    /**
     * 'POST' request that adds a new user to the database
     * @param user user to be added
     * @throws Exception when there is an SQL Exception
     */
    @PostMapping(path = "/admin/add-user")
    public void addUser(@RequestBody User user) throws Exception {
        // Checks if any user fields are 'null'
        if (user.anyNulls()) {
            throw new ApiRequestException("At least one user field is null");
        }
        this.userService.addUser(user);
    }

    /**
     * 'PUT' request that updates an users's information
     * @param strId user's id
     * @throws Exception when there is an SQL Exception
     */
    @PutMapping(path = "/admin/edit-user/{userID}")
    public void editUser(@PathVariable("userID") String userID, @RequestBody User userToUpdate) throws Exception {

        try {
        	int id = Integer.valueOf(userID);
        	if (userToUpdate.anyNulls()) {
    			throw new ApiRequestException("At least one user field is null");
    		}
            userService.editUser(id, userToUpdate);
        } catch(java.lang.IllegalArgumentException e) {
            throw new InvalidIdException();
        }
//        // Check if ID exists
//        if (this.userService.editUser(id, newName, newEmail) == 0) {
//            throw new InvalidIdException();
//        }
    }

    /**
     * 'PUT' request that blocks a user
     * @param strId user's id
     * @throws Exception when there is an SQL Exception
     */
    @PutMapping(path = "/admin/block-user/{userID}")
    public void blockUser(@PathVariable("userID") String strId, @RequestBody User userToBlock) throws Exception{
        try {
        	int id = Integer.valueOf(strId);
        	if (userToBlock.anyNulls()) {
    			throw new ApiRequestException("At least one user field is null");
    		}
            userService.editUser(id, userToBlock);
        } catch(java.lang.IllegalArgumentException e) {
            throw new InvalidIdException();
        }

//        // Check if ID exists
//        if(this.userService.blockUser(id) == 0) {
//            throw new InvalidIdException();
//        }
    }
}