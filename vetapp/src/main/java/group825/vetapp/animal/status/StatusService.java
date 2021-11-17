package group825.vetapp.animal.status;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class StatusService {

	private final StatusRepository dB_status;
	
	@Autowired
	public StatusService(@Qualifier("tempStatusRepo") StatusRepository dB_status) {
		this.dB_status = dB_status; //So this qualifier links to a repository (tempStatusRepo)
	}//so if we want to link our service to a different database service then we simply created a new repository class 
	//and replace this qualifier name with the new repository name


	public int addStatus(Status status) {
		return dB_status.insertStatus(status);
	}
	
	public List<Status> selectAllStatus(){
		return dB_status.selectAllStatus();
	}
	
	public Optional<Status> selectStatusById(UUID id){
		return dB_status.selectStatusById(id);
	}
	
	public int deleteStatusById(UUID id) {
		return dB_status.deleteStatusById(id);
	}
	
	public int updateStatusById(UUID id, Status status) {
		return dB_status.updateStatusById(id, status);
	}
	
	
	
}
