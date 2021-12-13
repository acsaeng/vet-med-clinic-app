package group825.vetapp2.users;

import com.fasterxml.jackson.databind.node.ObjectNode;
import group825.vetapp2.exceptions.ApiRequestException;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    private final UserService service;

    /**
     * Constructor that initializes the UserController
     * @param service implements the request
     */
    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    /**
     * 'POST' request that verifies user username and password inputs
     * @param json node containing the username and password inputs
     * @return true if login was successful, false otherwise
     */
    @PostMapping(path = "/login")
    @ResponseBody
    public boolean loginUser(@RequestBody ObjectNode json) {
        return this.service.loginUser(json.get("username").asText(), json.get("password").asText());
    }

    /**
     * 'POST' request that adds a new user to the database
     * @param user user to be added
     */
    @PostMapping(path = "/user/add-user")
    public void addUser(@RequestBody User user) {
        // Checks if any user fields are 'null'
        if (user.anyNulls()) {
            throw new ApiRequestException("At least one user field is null");
        }

        this.service.addUser(user);
    }

    /**
     * 'GET' request that returns a user based on ID number
     * @param userID user's ID number
     * @return user if found, 'null' otherwise
     */
    @GetMapping(path = "/user/edit-user/{userID}")
    @ResponseBody
    public User getUserById(@PathVariable("userID") int userID) {
        return this.service.selectUserById(userID);
    }
    
    /**
     * 'GET' request that returns a user based on user type
     * @param userType user's type
     * @return list of users matching the user type
     */
    @GetMapping(path = "/user/userType={userType}")
    @ResponseBody
    public ArrayList<User> getUserByType(@PathVariable("userType") String userType) {
        return this.service.selectUserByType(userType);
    }

    /**
     * 'PUT' request that updates a users' information
     * @param userID user's ID number
     */
    @PutMapping(path = "/user/edit-user/{userID}")
    public void editUser(@PathVariable("userID") int userID, @RequestBody User updatedInfo) {
        this.service.editUser(userID, updatedInfo);
    }

    /**
     * 'PUT' request that blocks a user
     * @param userID user's ID number
     */
    @PutMapping(path = "/user/block-user/{userID}")
    public boolean blockUser(@PathVariable("userID") int userID) {
        return this.service.blockUser(userID);
    }
}