package group825.vetapp2.animal;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Animal registered in the veterinary department
 *
 * @author Aron Saengchan, Jimmy Zhu
 * @version 2.0
 * @since November 30, 2021
 */
@Getter
@Setter
public class Animal {

    /**
     * ID number of the animal for the database
     */
    private int animalID;

    /**
     * Name of the animal
     */
    private String name;

    /**
     * Breed of animal
     */
    private String breed;

    /**
     * Species of the animal
     */
    private String species;

    /**
     * Sex of the animal
     */
    private String sex;

    
    /**
     * Tattoo on animal
     */
    private String tattoo;
    
    /**
     * Tattoo on animal assigned by the city
     */
    private String cityTattoo;
    
    /**
     * Birthday of the animal
     */
    private String dob;
    
    /**
     * RFID for the Animal
     */
    private String rfid;
    
    /**
     * Microchip information on animal
     */
    private String microchip;
    
    /**
     * Status of animal 
     */
    private String status;
    
    /**
     * Availability of animal 
     */
    private String availability;
    
    /**
     * Color of animal 
     */
    private String color;
    
    /**
     * More information recorded to visually idnetify the animal
     */
    private String moreInfo;
    
    /**
     * Length of the animal's name 
     */
    private int nameLength;
    
    /**
     * Search Key for the name used in search algorithm
     */
    private String searchKeyName;


    /**
     * Constructor that initializes the Animal
     * @param animalID = animal's ID number
     * @param name = animal's name
     * @param species =  animal's species
     * @param breed =  animal's breed
     * @param sex =  animal's sex
     * @param weight =  animal's current weight
     * @param tattoo =  describes a tattoo on the animal
     * @param cityTattoo =  describes a tattoo on the animal given by the city
     * @param dob =  animal's date of birth
     * @param rfid =  animal's rfid
     * @param microchip =  animal's microchip number
     * @param availability =  animal's current availability
     * @param status =  animal's current status
     * @param color =  animal's skin/fur/hair color
     * @param moreInfo =  gives more details identifying the animal
     * @param nameLength =  the length of the animal's name
     * @param searchKeyName = search key for the animal's name used in the search algorithm 
     */
    public Animal(@JsonProperty("animalID") int animalID, @JsonProperty("name") String name,
    		 	  @JsonProperty("species") String species, @JsonProperty("type") String breed,
                  @JsonProperty("sex") String sex,
                  @JsonProperty("tattoo") String tattoo, @JsonProperty("cityTattoo") String cityTattoo,
                  @JsonProperty("dob") String dob, @JsonProperty("rfid") String rfid,
                  @JsonProperty("microchip") String microchip, @JsonProperty("availability") String availability, @JsonProperty("status") String status,
                  @JsonProperty("color") String color, @JsonProperty("moreInfo") String moreInfo,
                  @JsonProperty("nameLength") int nameLength, @JsonProperty("searchKeyName") String searchKeyName) {
        this.animalID = animalID;
        this.name = name;
        this.breed = breed;
        this.species = species;
        this.sex = sex;
        this.tattoo = tattoo;
        this.cityTattoo = cityTattoo;
        this.dob = dob;
        this.rfid = rfid;
        this.microchip = microchip;
        this.availability = availability;
        this.status = status;
        this.color = color;
        this.moreInfo = moreInfo;
        this.nameLength = nameLength;
        this.searchKeyName = searchKeyName;
        
        updateSearchKey();
    }

    /**
     * Checks if any required attributes are null
     * @return true if at least one attribute is null, false otherwise
     */
    public boolean anyNulls() {
        return (this.animalID == 0 || this.name == null || this.species == null || this.breed == null || this.sex == null || 
        		this.tattoo == null || this.cityTattoo == null || this.dob == null || this.rfid == null || this.microchip == null || 
        		this.status == null || this.color == null );
    }
    
    @Override 
    public String toString() {
    	String newString = "{ animalID: " + animalID + ", name: " + name + ", breed: " + breed + ", species: " + species + ", sex: " + sex 
    			+ ", tattoo: " + tattoo + ", cityTattoo: " + cityTattoo + ", dob: " + dob 
			    + ", rfid: " + rfid + ", microchip: " + microchip + ", status: " + status + ", color: " + color + ", moreInfo: " + moreInfo 
			    + ", nameLength: " + nameLength + ", searchKeyName: " + searchKeyName + "}";
     return newString;
    }
    
    private void updateSearchKey() {
    	if (this.nameLength == 0) {this.nameLength = this.name.length();}
    	if (this.searchKeyName == null) {this.searchKeyName = SearchKey.generateSearchKey(this.name); }
    }
    
    
    
}