package group825.vetapp2.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectSerializer;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;

import group825.vetapp2.exceptions.ApiRequestException;
import group825.vetapp2.exceptions.InvalidIdException;

import java.util.List;
import java.util.UUID;

/**
 * Controller that handles User requests
 *
 * @author Aron Saengchan, Jimmy Zhu
 * @version 2.0
 * @since December 02, 2021
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
     */
    @GetMapping(path = "/login")
    @ResponseBody
    public List<User> loginUser(@RequestParam String userName, @RequestParam String password) throws Exception{
//    public List<User> loginUser(@RequestBody UserLogin userlogin) throws Exception{
//    public List<User> loginUser(@JsonProperty("userName") String userName, @JsonProperty("password") String password) throws Exception{
    	//@RequestParam String inputtedEmail, @RequestParam String inputtedPassword
//    	String jsonString = httpEntity.getBody();
//    	Gson g = new Gson();  
//    	User u = g.fromJson(jsonString, User.class);
//    	
//    	System.out.println("UserLogin u = "+userlogin);
//    	
//    	  	
//    	System.out.println("within loginUser in UserController, httpEntity.body = ");
//    	System.out.println("username = "+userName);
//    	System.out.println("password = "+password);
//    	String inputtedEmail = userlogin.getUserName();
//    	String inputtedPassword = userlogin.getPassword();
//    	System.out.println(json);
//    	String userName = json.replace("{", "").replace("}", "").replace("\"", "").replace("//s+", "");
//    	System.out.println("'"+userName+"'");
    	
    	//        // User inputs this information
//        String inputtedEmail = "test@example.com";
//        String inputtedPassword = "password123";

//        if (inputtedEmail.equals("") || inputtedPassword.equals("")) {
//            throw new ApiRequestException("Input fields are empty");
//        } 
//        else {
//            if (this.userService.loginUser(inputtedEmail, inputtedPassword).size() == 0) {
//                throw new ApiRequestException("Incorrect email or password.");
//            }
//        }
        return this.userService.loginUser(userName, password);
    }

    /**
     * 'PUT' request that changes a user's password
     * @param email user's email
     */
    @PutMapping(path = "/password-recovery/{email}")
    public void changeUserPassword(@PathVariable("email") String email) {
        // User inputs this information
        String newPassword = "new_password456";

        this.userService.changeUserPassword(email, newPassword);
    }

    /**
     * 'GET' request that retrieves all the stored users from the database
     * @return list of all stored users
     */
    @GetMapping(path = "/admin/users")
    public List<User> selectAllUsers() {
        return this.userService.selectAllUsers();
    }

    /**
     * 'POST' request that adds a new user to the database
     * @param user user to be added
     */
    @PostMapping(path = "/admin/add-user")
    public void addUser(@RequestBody User user) {
        // Checks if any user fields are 'null'
        if (user.anyNulls()) {
            throw new ApiRequestException("At least one user field is null");
        }

        this.userService.addUser(user);
    }

    /**
     * 'PUT' request that updates an users's information
     * @param strId user's id
     */
    @PutMapping(path = "/admin/edit-user/{id}")
    public void editUser(@PathVariable("id") String strId) {
        // User inputs this information
        String newName = "New Name";
        String newEmail = "new.test@example.com";

        UUID id;

        // Check if ID is a valid UUID
        try {
            id = UUID.fromString(strId);
            userService.editUser(id, newName, newEmail);
        } catch(java.lang.IllegalArgumentException e) {
            throw new InvalidIdException();
        }

        // Check if ID exists
        if (this.userService.editUser(id, newName, newEmail) == 0) {
            throw new InvalidIdException();
        }
    }

    /**
     * 'PUT' request that blocks a user
     * @param strId user's id
     */
    @PutMapping(path = "/admin/block-user/{id}")
    public void blockUser(@PathVariable("id") String strId) {
        UUID id;

        // Check if ID is a valid UUID
        try {
            id = UUID.fromString(strId);
            userService.blockUser(id);
        } catch(java.lang.IllegalArgumentException e) {
            throw new InvalidIdException();
        }

        // Check if ID exists
        if(this.userService.blockUser(id) == 0) {
            throw new InvalidIdException();
        }
    }
}