package group825.vetapp2.weighthistory;

import group825.vetapp2.exceptions.ApiRequestException;
import group825.vetapp2.exceptions.InvalidIdException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * Controller that handles WeightHistoryRequests requests
 *
 * @author Aron Saengchan
 * @version 1.0
 * @since December 6, 2021
 */
@CrossOrigin
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
     * @throws Exception error when accessing the database
     */
    @GetMapping(path = "/{animalID}")
    public ArrayList<Weight> selectWeight(@PathVariable("animalID") int animalID) throws Exception {
        return this.weightHistoryService.selectWeightHistoryByID(animalID);
    }

    /**
     * 'POST' request that adds a weight entry to the database
     * @param weight weight entry to be added
     * @throws Exception error when accessing the database
     */
    @PostMapping
    public void addWeight(@RequestBody Weight weight) throws Exception {
        // Checks if any user fields are 'null'
        if (weight.anyNulls()) {
            throw new ApiRequestException("Fields cannot be null");
        }

        this.weightHistoryService.addWeight(weight);
    }

    /**
     * 'DELETE' request that deletes a weight entry
     * @param weight an animal's weight entry
     * @throws Exception error when accessing the database
     */
    @DeleteMapping()
    public void deleteWeight(@RequestBody Weight weight) throws Exception {
        this.weightHistoryService.deleteWeight(weight);
    }
}