package group825.vetapp.animal.status;

import java.util.List;
import java.util.UUID;

//import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("app/status/animal")
@RestController
public class StatusController {
	private final StatusRepository statusRepo;

	@Autowired
	public StatusController(@Qualifier("tempStatusRepo") StatusRepository statusRepo) {
		this.statusRepo = statusRepo;
	}
	
	@PostMapping
//	public void addStatus(@Valid @NonNull @RequestBody Person person) {
	public void addStatus(@RequestBody Status status) {
		//take the request body from the endpoint
		//put that into Status object
		//where the ctor already associates individual json 
		//components with the appropriate data members
		statusRepo.insertStatus(status);
	}


	@GetMapping
	public List<Status> selectAllStatus(){
		return statusRepo.selectAllStatus();
	}
	
	@GetMapping(path="{id}") //so path is "app/status/animal/UUID....."
	public Status selectStatusById(@PathVariable("id") UUID id) {
		return statusRepo.selectStatusById(id)
				.orElse(null); //here you could return the error 404 that nothing was received
	}
	//ADD EXCEPTION?????????????????????????????????????????
	
	
	
	
	
	@DeleteMapping(path = "{id}")
	public void deleteStatusById(@PathVariable("id") UUID id) {
		statusRepo.deleteStatusById(id);
	}
	
	@PutMapping(path = "{id}")
//	public void updatePerson(@PathVariable("id") UUID id, @Valid @NonNull @RequestBody Person personToUpdate) {
	public void updateStatusById(@PathVariable("id") UUID id, @RequestBody Status statusToUpdate) {	
		statusRepo.updateStatusById(id, statusToUpdate);
	}
}
