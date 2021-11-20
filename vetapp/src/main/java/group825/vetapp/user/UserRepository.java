package group825.vetapp.user;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository that stores User information
 *
 * @author Aron Saengchan
 * @version 1.0
 * @since November 15, 2021
 */
@Repository("tempUserRepo")
public class UserRepository {

    /**
     * Database that stores all the users
     */
    private static final List<User> database = new ArrayList<>();

    /**
     * Verifies a user's email and password information
     * @param email user's email
     * @param password user's password
     * @return 1 if login was successful, 0 otherwise
     */
     public int loginUser(String email, String password) {
        for (User user : database) {
            if (email.equals(user.getEmail()) && password.equals(user.getPassword())) {
                return 1;
            }
        }

        return 0;
    }

    /**
     * Changes a user's password
     * @param email user's email
     * @param newPassword user's new password
     * @return 1 if password change was successful, 0 otherwise
     */
    public int changeUserPassword(String email, String newPassword) {
        return selectUserByEmail(email).map(user -> {
            int accountIdx = database.indexOf(user);

            if (accountIdx >= 0) {
                database.get(accountIdx).setPassword(newPassword);
                return 1;
            }

            return 0;
        }).orElse(0);
    }

    /**
     * Finds a user in the database by email
     * @return user associated with a specified email
     */
    private Optional<User> selectUserByEmail(String email) {
        return database.stream().filter(user -> user.getEmail().equals(email)).findFirst();
    }

    /**
     * Retrieves all stored users from the database
     * @return a list of all stored users
     */
    public List<User> selectAllUsers() {
        return database;
    }

    /**
     * Adds a user to the database
     * @param user user to be added
     * @return 1 if registration was successful, 0 otherwise
     */
    public int addUser(User user) {
        database.add(user);
        return 1;
    }

    /**
     * Updates a user's information
     * @param id user's existing ID
     * @param name user's new or existing name
     * @param email user's new or existing email
     * @return 1 if updates were successful, 0 otherwise
     */
    public int editUser(UUID id, String name, String email) {
        return selectUserById(id).map(user -> {
            int accountIdx = database.indexOf(user);

            if (accountIdx >= 0) {
                database.get(accountIdx).setName(name);
                database.get(accountIdx).setEmail(email);
                return 1;
            }

            return 0;
        }).orElse(0);
    }

    /**
     * Blocks a user in the system
     * @param id user's ID
     * @return 1 if block was successful, 0 otherwise
     */
    public int blockUser(UUID id) {
        return selectUserById(id).map(user -> {
            int accountIdx = database.indexOf(user);

            if (accountIdx >= 0) {
                database.get(accountIdx).setActive(false);
                return 1;
            }

            return 0;
        }).orElse(0);
    }

    /**
     * Finds a user in the database by ID
     * @return user associated with a specified ID
     */
    private Optional<User> selectUserById(UUID id) {
        return database.stream().filter(user -> user.getId().equals(id)).findFirst();
    }
}