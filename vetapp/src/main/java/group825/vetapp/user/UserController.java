package group825.vetapp.user;

import group825.vetapp.exceptions.ApiRequestException;
import group825.vetapp.exceptions.InvalidIdException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Controller that handles the HTTP requests from the client
 */
@RestController
@RequestMapping(path = "/app")
public class UserController {

    /**
     * User service that implements the request
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
     * 'POST' request to verify email and password inputs
     */
    @PostMapping(path = "/login")
    public void loginUser() {
        // User inputs this information
        String inputtedEmail = "test@example.com";
        String inputtedPassword = "password123";

        if (inputtedEmail.equals("") || inputtedPassword.equals("")) {
            throw new ApiRequestException("Input fields are empty");
        }
    }

    /**
     * 'PUT' request to change a user's password
     * @param email user's email
     */
    @PutMapping(path = "/password-recovery/{email}")
    public void changeUserPassword(@PathVariable("email") String email) {
        // User inputs this information
        String newPassword = "new_password456";

        this.userService.changeUserPassword(email, newPassword);
    }

    /**
     * 'GET' request to retrieve all the stored users
     * @return list of all stored users
     */
    @GetMapping(path = "/admin/users")
    public List<User> selectAllUsers() {
        return this.userService.selectAllUsers();
    }

    /**
     * 'POST' request to add a new user
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
     * 'PUT' request to edit a user's information
     * @param strId user's id
     */
    @PutMapping(path = "/admin/edit-user/{id}")
    public void editUser(@PathVariable("id") String strId) {
        // User inputs this information
        UUID newId = UUID.randomUUID();
        String newName = "New Name";
        String newEmail = "new.test@example.com";

        UUID id;

        // Check if ID is a valid UUID
        try {
            id = UUID.fromString(strId);
            userService.blockUser(id);
        } catch(java.lang.IllegalArgumentException e) {
            throw new InvalidIdException();
        }

        // Check if ID exists
        if (this.userService.editUser(id, newId, newName, newEmail) == 0) {
            throw new InvalidIdException();
        }
    }

    /**
     * 'PUT' request to block a user
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