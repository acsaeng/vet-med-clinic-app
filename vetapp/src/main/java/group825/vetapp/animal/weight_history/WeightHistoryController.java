package group825.vetapp.animal.weight_history;

import group825.vetapp.exceptions.ApiRequestException;
import group825.vetapp.exceptions.InvalidIdException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/app/animal/weight-history")
public class WeightHistoryController {

    private final WeightHistoryService weightHistoryService;

    @Autowired
    public WeightHistoryController(WeightHistoryService weightHistoryService) {
        this.weightHistoryService = weightHistoryService;
    }

    @GetMapping
    public List<WeightHistory> selectWeightHistory() {
        return this.weightHistoryService.selectWeightHistory();
    }

    @PostMapping
    public void addUser(@RequestBody WeightHistory weightHistory) {
        // Checks if any user fields are 'null'
        if (weightHistory.anyNulls()) {
            throw new ApiRequestException("Fields cannot be null");
        }

        this.weightHistoryService.addWeightHistory(weightHistory);
    }

    @PutMapping(path = "/{id}")
    public void editUser(@PathVariable("id") String strId) {
        // User inputs this information
        LocalDate newDate = LocalDate.of(2021, 1, 1);
        double newWeight = 10.0;

        UUID id;

        // Check if ID is a valid UUID
        try {
            id = UUID.fromString(strId);
            weightHistoryService.editWeightHistory(id, newDate, newWeight);
        } catch(java.lang.IllegalArgumentException e) {
            throw new InvalidIdException();
        }

        // Check if ID exists
        if (this.weightHistoryService.editWeightHistory(id, newDate, newWeight) == 0) {
            throw new InvalidIdException();
        }
    }

    @DeleteMapping(path = "/{id}")
    public void deleteWeightHistory(@PathVariable("id") String strId) {
        UUID id;
        int status;

        // Check if ID is a valid UUID
        try {
            id = UUID.fromString(strId);
            status = weightHistoryService.deleteWeightHistory(id);
        } catch(java.lang.IllegalArgumentException e) {
            throw new InvalidIdException();
        }

        // Check if ID exists
        if (status == 0) {
            throw new InvalidIdException();
        }
    }




}
