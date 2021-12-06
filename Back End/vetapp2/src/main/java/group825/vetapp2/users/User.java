package group825.vetapp2.users;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

/**
 * User of representing an admin or veterinary staff member
 *
 * @author Aron Saengchan, Jimmy Zhu, Timothy Mok
 * @version 2.0
 * @since December 6, 2021
 */
@Getter
@Setter
public class User {

    /**
     * ID number of the user
     */
    private int id;

    /**
     * First name of the user
     */
    private String firstName;
    
    /**
     * Last name of the user
     */
    private String lastName;

    /**
     * The role of the user at the vet
     */
    private String userType;
    
    /**
     * The username of the user
     */
    private String userName;
    
    /**
     * Email address of the user
     */
    private String email;
    
    /**
     * The user's phone number
     */
    private String phoneNum;

    /**
     * Password of the user's account
     */
    private String password;

    /**
     * Date the user account was activated
     */
    private String startDate;
    
    /**
     * The user's status - whether they are active or blocked
     */
    private String userStatus; 
    
    /**
     * Constructor that initializes the User
     * 
     * @param the user's ID
     * @param firstName user's first name
     * @param lastName user's last name
     * @param userType user's role at the vet clinic
     * @param userName user's username
     * @param email user's email
     * @param phoneNum user's phone number
     * @param password user's password
     * @param startDate user's start date
     * @param userStatus whether user is active or blocked
     */
    public User(@JsonProperty("id") int id, @JsonProperty("firstName") String firstName, @JsonProperty("lastName") String lastName,
    		 @JsonProperty("userType") String userType, @JsonProperty("userName") String userName, @JsonProperty("email") String email, 
    		 @JsonProperty("phoneNum") String phoneNum,   @JsonProperty("password") String password,
                @JsonProperty("startDate") String startDate, @JsonProperty("userStatus") String userStatus) {
        this.id = id;
        this.firstName =firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.userType = userType;
        this.email = email;
        this.phoneNum = phoneNum;
        this.password = password;
        this.startDate = startDate;
        this.userStatus = userStatus;
    }

    /**
     * Checks if any of the user information is null
     * @return true if id, name, email, or password is null, false otherwise
     */
    public boolean anyNulls() {
        return this.id == 0 || this.firstName == null || this.firstName == null || this.lastName == null || this.userType == null 
        		|| this.email == null || this.phoneNum == null || this.password == null || this.startDate == null || this.userStatus ==null;
    }
    
    
    /**
     * Creates a string of all the user's information
     */
    @Override 
    public String toString() {
    	String newString = "{ id: " + id + ", firstName: " + firstName + ", lastName: " + lastName + ", userName: " + userName + ", userType: " + userType 
    			+ ", email: " + email + ", phoneNum: " + phoneNum + ", password: " + password + ", startDate: " + startDate + ", userStatus: " + userStatus + "}";
     return newString;
    }
}