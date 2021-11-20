package group825.vetapp.animal.weight_history;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Weight of an animal
 *
 * @author Aron Saengchan
 * @version 1.0
 * @since November 15, 2021
 */
@Getter
@Setter
public class Weight {

    /**
     * ID number of the animal
     */
    private UUID id;

    /**
     * Date the weight was logged
     */
    private LocalDate date;

    /**
     * Weight of the animal
     */
    private double weight;

    /**
     * Constructor that initializes the WeightHistory
     * @param id animal's ID number
     * @param date date weight was logged
     * @param weight animal's weight
     */
    private Weight(@JsonProperty("id") UUID id, @JsonProperty("date") LocalDate date, @JsonProperty double weight) {
        this.id = id;
        this.date = date;
        this.weight = weight;
    }

    /**
     * Checks if any attributes are null
     * @return true if at least one attribute is null, false otherwise
     */
    public boolean anyNulls() {
        return this.id == null || this.date == null || this.weight == 0.0;
    }
}