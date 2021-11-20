package group825.vetapp.animal;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * Animal registered in the veterinary department
 *
 * @author Aron Saengchan
 * @version 1.0
 * @since November 15, 2021
 */
@Getter
@Setter
public class Animal {

    /**
     * ID number of the animal
     */
    private UUID id;

    /**
     * Name of the animal
     */
    private String name;

    /**
     * Type of animal
     */
    private String type;

    /**
     * Species of the animal
     */
    private String species;

    /**
     * Sex of the animal
     */
    private char sex;

    /**
     * Weight of the animal
     */
    private double weight;

    // Omitted for testing purposes
    /*
    private int tattoo;
    private String cityTattoo;
    private Date dob;
    private String breed;
    private int rfid;
    private int microchip;
    private String status;
    */

    /**
     * Constructor that initializes the Animal
     * @param id animal's ID number
     * @param name animal's name
     * @param type animal's type
     * @param species animal's species
     * @param sex animal's sex
     * @param weight animal's weight
     */
    public Animal(@JsonProperty("id") UUID id, @JsonProperty("name") String name,
                  @JsonProperty("type") String type, @JsonProperty("species") String species,
                  @JsonProperty("sex") char sex, @JsonProperty("weight") double weight) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.species = species;
        this.sex = sex;
        this.weight = weight;

        /*
        this.tattoo = tattoo;
        this.cityTattoo = cityTattoo;
        this.dob = dob;
        this.breed = breed;
        this.rfid = rfid;
        this.microchip = microchip;
        this.status = status;
         */
    }

    /**
     * Checks if any attributes are null
     * @return true if at least one attribute is null, false otherwise
     */
    public boolean anyNulls() {
        return this.id == null || this.name == null || this.species == null || this.type == null || this.sex == '\u0000' || this.weight == 0.0;
    }
}