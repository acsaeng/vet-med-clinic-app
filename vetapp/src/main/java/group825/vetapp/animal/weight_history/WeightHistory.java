package group825.vetapp.animal.weight_history;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class WeightHistory {

    private UUID id;

    private LocalDate date;

    private double weight;

    private WeightHistory(@JsonProperty("id") UUID id, @JsonProperty("date") LocalDate date, @JsonProperty double weight) {
        this.id = id;
        this.date = date;
        this.weight = weight;
    }

    public boolean anyNulls() {
        return this.id == null || this.date == null || this.weight == 0.0;
    }
}
