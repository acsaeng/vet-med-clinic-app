package group825.vetapp.animal.diagnosis;

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

@RequestMapping("app/diagnosis/animal")
@RestController
public class DiagnosisController {
	private final DiagnosisRepository diagnosisRepo;

	// UPDATE DATABASE AS NEEDED
	@Autowired
	public DiagnosisController(@Qualifier("tempDiagnosisRepo") DiagnosisRepository diagnosisRepo) {
		this.diagnosisRepo = diagnosisRepo;
	}
	
	@PostMapping
//	public void addDiagnosis(@Valid @NonNull @RequestBody Person person) {
	public void addDiagnosis(@RequestBody Diagnosis diagnosis) {
		diagnosisRepo.insertDiagnosis(diagnosis);
	}


	@GetMapping
	public List<Diagnosis> selectAllDiagnosis(){
		return diagnosisRepo.selectAllDiagnosis();
	}
	
	@GetMapping(path = "{id}") 
	public Diagnosis selectDiagnosisById(@PathVariable("id") UUID id) {
		return diagnosisRepo.selectDiagnosisById(id)
				.orElse(null); //here you could return the error 404 that nothing was received
	}
	//ADD EXCEPTION?????????????????????????????????????????
	
	@DeleteMapping(path = "{id}")
	public void deleteDiagnosisById(@PathVariable("id") UUID id) {
		diagnosisRepo.deleteDiagnosisById(id);
	}
	
	@PutMapping(path = "{id}")
//	public void updatePerson(@PathVariable("id") UUID id, @Valid @NonNull @RequestBody Person personToUpdate) {
	public void updateDiagnosisById(@PathVariable("id") UUID id, @RequestBody Diagnosis diagnosisToUpdate) {	
		diagnosisRepo.updateDiagnosisById(id, diagnosisToUpdate);
	}
}
