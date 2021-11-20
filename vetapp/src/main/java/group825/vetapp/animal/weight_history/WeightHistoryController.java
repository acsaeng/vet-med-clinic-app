package group825.vetapp.animal.weight_history;

import group825.vetapp.exceptions.ApiRequestException;
import group825.vetapp.exceptions.InvalidIdException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * Controller that handles WeightHistoryRequests requests
 *
 * @author Aron Saengchan
 * @version 1.0
 * @since November 15, 2021
 */
@RestController
@RequestMapping(path = "/app/animal/weight-history")
public class WeightHistoryController {

    /**
     * Weight history service that performs the request
     */
    private final WeightHistoryService weightHistoryService;

    /**
     * Constructor that initializes the weightController
     * @param weightService service that performs the request
     */
    @Autowired
    public WeightHistoryController(WeightHistoryService weightService) {
        this.weightHistoryService = weightService;
    }

    /**
     * 'GET' request that retrieves all weight histories from the database
     * @return a list of weight histories for all animals
     */
    @GetMapping
    public List<Weight> selectWeight() {
        return this.weightHistoryService.selectWeightHistory();
    }

    /**
     * 'POST' request that adds a weight entry to the database
     * @param weight weight entry to be added
     */
    @PostMapping
    public void addWeight(@RequestBody Weight weight) {
        // Checks if any user fields are 'null'
        if (weight.anyNulls()) {
            throw new ApiRequestException("Fields cannot be null");
        }

        this.weightHistoryService.addWeight(weight);
    }

    /**
     * 'PUT' request that updates weight history information
     * @param strId animal's ID number
     */
    @PutMapping(path = "/{id}")
    public void editWeight(@PathVariable("id") String strId) {
        // User inputs this information
        LocalDate newDate = LocalDate.of(2021, 1, 1);
        double newWeight = 10.0;

        UUID id;

        // Check if ID is a valid UUID
        try {
            id = UUID.fromString(strId);
            weightHistoryService.editWeight(id, newDate, newWeight);
        } catch(java.lang.IllegalArgumentException e) {
            throw new InvalidIdException();
        }

        // Check if ID exists
        if (this.weightHistoryService.editWeight(id, newDate, newWeight) == 0) {
            throw new InvalidIdException();
        }
    }

    /**
     * 'DELETE' request that deletes a weight entry
     * @param strId animal's ID number
     */
    @DeleteMapping(path = "/{id}")
    public void deleteWeight(@PathVariable("id") String strId) {
        // User inputs this information
        UUID id;
        LocalDate date = LocalDate.of(2021, 1, 1);
        int status;

        // Check if ID is a valid UUID
        try {
            id = UUID.fromString(strId);
            status = weightHistoryService.deleteWeight(id, date);
        } catch(java.lang.IllegalArgumentException e) {
            throw new InvalidIdException();
        }

        // Check if ID exists
        if (status == 0) {
            throw new InvalidIdException();
        }
    }
}