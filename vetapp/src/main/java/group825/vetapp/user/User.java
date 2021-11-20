package group825.vetapp.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

/**
 * User of representing an admin or veterinary staff member
 *
 * @author Aron Saengchan
 * @version 1.0
 * @since November 15, 2021
 */
@Getter
@Setter
public class User {

    /**
     * ID number of the user
     */
    private UUID id;

    /**
     * Full name of the user
     */
    private String name;

    /**
     * Email address of the user
     */
    private String email;

    /**
     * Password of the user's account
     */
    private String password;

    /**
     * Account status of the user
     */
    private boolean isActive;

    /**
     * Date the user account was activated
     */
    private LocalDate activationDate;

    /**
     * Constructor that initializes the User
     * @param id user's ID
     * @param name user's name
     * @param email user's email address
     * @param password user's account password
     */
    public User(@JsonProperty("id") UUID id, @JsonProperty("name") String name,
                @JsonProperty("email") String email, @JsonProperty("password") String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.isActive = true;
        this.activationDate = LocalDate.now();
    }

    /**
     * Checks if any of the user information is null
     * @return true if id, name, email, or password is null, false otherwise
     */
    public boolean anyNulls() {
        return this.id == null || this.name == null || this.email == null || this.password == null;
    }
}