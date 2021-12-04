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
     * ID number of the animal
     */
    private int id;

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
     * Weight of the animal
     */
    private double weight;
    
    private String tattoo;
    private String cityTattoo;
    private String dob;
    private String rfid;
    private String microchip;
    private String status;
    private String color;
    private String moreInfo;
    private int nameLength;
    private String searchKey_Name;


    /**
     * Constructor that initializes the Animal
     * @param id animal's ID number
     * @param name animal's name
     * @param type animal's type
     * @param species animal's species
     * @param sex animal's sex
     * @param weight animal's weight
     * .......FILLIN THE REST...........................
     */
    public Animal(@JsonProperty("id") int id, @JsonProperty("name") String name,
    		 	  @JsonProperty("species") String species, @JsonProperty("type") String breed,
                  @JsonProperty("sex") String sex, @JsonProperty("weight") double weight,
                  @JsonProperty("tattoo") String tattoo, @JsonProperty("cityTattoo") String cityTattoo,
                  @JsonProperty("dob") String dob, @JsonProperty("rfid") String rfid,
                  @JsonProperty("microchip") String microchip, @JsonProperty("status") String status,
                  @JsonProperty("color") String color, @JsonProperty("moreInfo") String moreInfo,
                  @JsonProperty("nameLength") int nameLength, @JsonProperty("searchKey_Name") String searchKey_Name) {
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.species = species;
        this.sex = sex;
        this.weight = weight;

        
        this.tattoo = tattoo;
        this.cityTattoo = cityTattoo;
        this.dob = dob;
        this.rfid = rfid;
        this.microchip = microchip;
        this.status = status;
        this.color = color;
        this.moreInfo = moreInfo;
        this.nameLength = nameLength;
        this.searchKey_Name = searchKey_Name;
        
        updateSearchKey();
    }

    /**
     * Checks if any attributes are null
     * @return true if at least one attribute is null, false otherwise
     */
    public boolean anyNulls() {
        return (this.id == 0 || this.name == null || this.species == null || this.breed == null || this.sex == null || this.weight == 0.0 ||
        		this.tattoo == null || this.cityTattoo == null || this.dob == null || this.rfid == null || this.microchip == null || 
        		this.status == null || this.color == null );
    }
    
    @Override 
    public String toString() {
    	String newString = "{ id: " + id + ", name: " + name + ", breed: " + breed + ", species: " + species + ", sex: " + sex + ", weight: " 
    + weight + ", tattoo: " + tattoo + ", cityTattoo: " + cityTattoo + ", dob: " + dob 
    + ", rfid: " + rfid + ", microchip: " + microchip + ", status: " + status + ", color: " + color + ", moreInfo: " + moreInfo 
    + ", nameLength: " + nameLength + ", searchKey_Name: " + searchKey_Name + "}";
     return newString;
    }
    
    private void updateSearchKey() {
    	if (this.nameLength == 0) {this.nameLength = this.name.length();}
    	if (this.searchKey_Name == null) {this.searchKey_Name = SearchKey.generateSearchKey(this.name); }
    }
    
    
    
}