package group825.vetapp.animal.status;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class StatusService {
	private final StatusRepository dB_status;
	
	/** Constructor 
	 * @param dB_status = Repository denoted with Qualifier annotation storing the Status objects
	 */
	@Autowired
	public StatusService(@Qualifier("tempStatusRepo") StatusRepository dB_status) {
		this.dB_status = dB_status; 
	}


	/** addStatus function 
	 * @param status = new Status object to be added
	 * @return integer verifying successful code execution
	 */
	public int addStatus(Status status) {
		return dB_status.insertStatus(status);
	}
	

	/** selectAllStatus function 
	 * @return List of all Status Objects
	 */
	public List<Status> selectAllStatus(){
		return dB_status.selectAllStatus();
	}
	
	/** selectStatusById function
	 * @param id = UUID pertaining to specific animal
	 * @return Optional<Status> either containing the Status object for particular animal or is empty
	 */
	public Optional<Status> selectStatusById(UUID id){
		return dB_status.selectStatusById(id);
	}
	
	/** deleteStatusById function
	 * @param id = UUID pertaining to specific animal
	 * @return integer verifying successful code execution
	 */
	public int deleteStatusById(UUID id) {
		return dB_status.deleteStatusById(id);
	}
	
	/** updateStatusById function
	 * @param id = UUID pertaining to specific animal
	 * @param status = Status object with updated data members
	 * @return integer verifying successful code execution
	 */
	public int updateStatusById(UUID id, Status status) {
		return dB_status.updateStatusById(id, status);
	}
	
	
	
}
