package group825.vetapp.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/app")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Sends POST request to verify email and password information
    @PostMapping(path = "/login")
    public void loginUser() {
        // User inputs this information
        String inputtedEmail = "test@example.com";
        String inputtedPassword = "password123";

        if (inputtedEmail.equals("") || inputtedPassword.equals("")) {
            // Raise empty string exception
            return;
        } else if(this.userService.loginUser(inputtedEmail, inputtedPassword) == 0) {
            // Raise account exception
            return;
        }
    }

    @PutMapping(path = "/password-recovery/{email}")
    public void changeUserPassword(@PathVariable("email") String email) {
        // User inputs this information
        String newPassword = "new_password456";

        this.userService.changeUserPassword(email, newPassword);
    }








    @GetMapping(path = "/admin/users")
    public List<User> selectAllUsers() {
        return this.userService.selectAllUsers();
    }

    @PostMapping(path = "/admin/add-user")
    public void addUser(@RequestBody User user) {
        // Add error handling if email is already registered!
        this.userService.addUser(user);
    }

    @PutMapping(path = "/admin/edit-user/{id}")
    public void editUser(@PathVariable("id") UUID id) {
        UUID newId = UUID.randomUUID();
        String newName = "New Name";
        String newEmail = "new.test@example.com";

        this.userService.editUser(id, newId, newName, newEmail);
    }

    @PutMapping(path = "/admin/block-user/{id}")
    public void blockUser(@PathVariable("id") UUID id) {
        this.userService.blockUser(id);
    }










}
