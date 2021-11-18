package group825.vetapp.animal.diagnosis;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class DiagnosisService {

	private final DiagnosisRepository DB_diagnosis;
	
	// UPDATE WITH DATABASE AS NEEDED
	@Autowired
	public DiagnosisService(@Qualifier("tempDiagnosisRepo") DiagnosisRepository DB_diagnosis) {
		this.DB_diagnosis = DB_diagnosis; 
	}

	public int addDiagnosis(Diagnosis diagnosis) {
		return DB_diagnosis.insertDiagnosis(diagnosis);
	}
	
	public List<Diagnosis> selectAllDiagnosis(){
		return DB_diagnosis.selectAllDiagnosis();
	}
	
	public Optional<Diagnosis> selectDiagnosisById(UUID id){
		return DB_diagnosis.selectDiagnosisById(id);
	}
	
	public int deleteDiagnosisById(UUID id) {
		return DB_diagnosis.deleteDiagnosisById(id);
	}
	
	public int updateDiagnosisById(UUID id, Diagnosis diagnosis) {
		return DB_diagnosis.updateDiagnosisById(id, diagnosis);
	}
	
	
	
}
