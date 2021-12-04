package group825.vetapp.user;

import group825.vetapp.animal.comments.Comment;
import group825.vetapp.exceptions.ApiRequestException;
import group825.vetapp.exceptions.InvalidIdException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectSerializer;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import com.google.gson.Gson;

import java.util.List;
import java.util.UUID;

/**
 * Controller that handles User requests
 *
 * @author Aron Saengchan
 * @version 1.0
 * @since November 15, 2021
 */
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
     * 'GET' request that verifies email and password inputs
     */
    @GetMapping(path = "/login")
    @ResponseBody
    public List<User> loginUser(HttpEntity<String> httpEntity) throws Exception{
    	//@RequestParam String inputtedEmail, @RequestParam String inputtedPassword
    	String jsonString = httpEntity.getBody();
    	Gson g = new Gson();  
    	User u = g.fromJson(jsonString, User.class);
    	
    	System.out.println("User u = "+u);
    	  	
    	System.out.println("within loginUser in UserController, httpEntity.body = ");
    	System.out.println("username = "+u.getUserName());
    	System.out.println("password = "+u.getPassword());
    	String inputtedEmail = u.getUserName();
    	String inputtedPassword = u.getPassword();
//    	System.out.println(json);
//    	String userName = json.replace("{", "").replace("}", "").replace("\"", "").replace("//s+", "");
//    	System.out.println("'"+userName+"'");
    	
    	//        // User inputs this information
//        String inputtedEmail = "test@example.com";
//        String inputtedPassword = "password123";

        if (inputtedEmail.equals("") || inputtedPassword.equals("")) {
            throw new ApiRequestException("Input fields are empty");
        } 
//        else {
//            if (this.userService.loginUser(inputtedEmail, inputtedPassword).size() == 0) {
//                throw new ApiRequestException("Incorrect email or password.");
//            }
//        }
        return this.userService.loginUser(inputtedEmail, inputtedPassword);
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