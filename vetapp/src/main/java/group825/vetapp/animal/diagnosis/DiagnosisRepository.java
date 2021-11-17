package group825.vetapp.animal.diagnosis;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;


// UPDATE DATABASE AS NEEDED
@Repository("tempDiagnosisRepo")
public class DiagnosisRepository { 
	
	private static List<Diagnosis> DB_diagnosis = new ArrayList<>();
	
	public int insertDiagnosis(Diagnosis diagnosis) {
		UUID id = UUID.randomUUID(); 
		DB_diagnosis.add(new Diagnosis(id, diagnosis.getDiagnosis()));
		return 1;
	}
	
	public List<Diagnosis> selectAllDiagnosis(){
		return DB_diagnosis;
	}
	
	public Optional<Diagnosis> selectDiagnosisById(UUID id) {
		
		return DB_diagnosis.stream()
						   .filter(diagnosis -> diagnosis.getId().equals(id))
						   .findFirst();
	}
	
	public int deleteDiagnosisById(UUID id) {
		Optional<Diagnosis> animalMaybe = selectDiagnosisById(id);
		if(animalMaybe.isEmpty()) { 
			return 0;
		}
		DB_diagnosis.remove(animalMaybe.get());
		return 1;
	}
	
	public int updateDiagnosisById(UUID id, Diagnosis update) {
		return selectDiagnosisById(id)
				.map(animalFound -> {
					int indexOfAnimalToUpdate = DB_diagnosis.indexOf(animalFound);
					if (indexOfAnimalToUpdate >= 0) { 
						DB_diagnosis.set(indexOfAnimalToUpdate, new Diagnosis(id, update.getDiagnosis())); 
						return 1;   
					}
					return 0; 
				})
				.orElse(0); 
	}
	
	
	
}
