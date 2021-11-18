package group825.vetapp.animal;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter @Setter
public class Animal {

    private UUID id;

    private String name;

    private String type;

    private String species;

    private char sex;

    private double weight;

    private int tattoo;

    private String cityTattoo;

    private Date dob;

    private String breed;

    private int rfid;

    private int microchip;

    private String status;

    public Animal(@JsonProperty("id") UUID id, @JsonProperty("name") String name,
                  @JsonProperty("type") String type, @JsonProperty("species") String species,
                  @JsonProperty("sex") char sex, @JsonProperty("weight") double weight,
                  @JsonProperty("tattoo") int tattoo, @JsonProperty("cityTattoo") String cityTattoo,
                  @JsonProperty("dob") Date dob, @JsonProperty("breed") String breed,
                  @JsonProperty("rfid") int rfid, @JsonProperty("microchip") int microchip,
                  @JsonProperty("status") String status) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.species = species;
        this.sex = sex;
        this.weight = weight;
        this.tattoo = tattoo;
        this.cityTattoo = cityTattoo;
        this.dob = dob;
        this.breed = breed;
        this.rfid = rfid;
        this.microchip = microchip;
        this.status = status;
    }

    public boolean anyNulls() {
        return this.id == null || this.name == null || this.species == null || this.type == null || this.sex == '\u0000';
    }
}
