package group825.vetapp.treatment.protocol;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;


// UPDATE DATABASE AS NEEDED
@Repository("tempProtocolRepo")
public class ProtocolRepository { 
	
	private static final List<Protocol> DB_protocol = new ArrayList<>();
	
	/**
	 * Adding a protocol to the system
	 * @param protocol - the treatment protocol associated with an animal
	 * @return 1 if successfully added
	 */
	public int addProtocol(Protocol protocol) {
		UUID id = UUID.randomUUID(); 
		DB_protocol.add(new Protocol(id, protocol.getAnimalName(),
										 protocol.getDiagnosis(),
										 protocol.getPrescription(),
		 		   						 protocol.getProtocol())); 
		return 1;
	}
	
	/**
	 * Returns all protocols in the system associated with this animal
	 * @return all associated treatment protocols
	 */
	public List<Protocol> selectAllProtocol(){
		return DB_protocol;
	}
	
	/**
	 * View a specific treatment protocol selected by id
	 * @param id - the id of the selected protocol
	 * @return the treatment protocol with the requested id
	 */
	public Optional<Protocol> selectProtocolById(UUID id) {
		return DB_protocol.stream()
						   .filter(protocol -> protocol.getId().equals(id))
						   .findFirst();
	}
	
	/**
	 * Remove a specific protocol from the system
	 * @param id - the id of the treatment protocol to be removed
	 * @return 1 if removed successfully, otherwise 0
	 */
	public int deleteProtocolById(UUID id) {
		Optional<Protocol> protocolMaybe = selectProtocolById(id);
		if(protocolMaybe.isEmpty()) { 
			return 0;
		}
		DB_protocol.remove(protocolMaybe.get());
		return 1;
	}
	
	/**
	 * Update an existing treatment protocol
	 * @param id - the id of the protocol to be updated
	 * @param newProtocol - the Protocol object with the updated information
	 * @return 1 if successfully updated, otherwise 0
	 */
	public int updateProtocolById(UUID id, Protocol newProtocol) {
		return selectProtocolById(id)
				.map(protocolFound -> {
					int indexOfAnimalToUpdate = DB_protocol.indexOf(protocolFound);
					if (indexOfAnimalToUpdate >= 0) { 
						DB_protocol.set(indexOfAnimalToUpdate, 
										 new Protocol(id, newProtocol.getAnimalName(),
												 		  newProtocol.getDiagnosis(),
												 		  newProtocol.getPrescription(),
												 		  newProtocol.getProtocol())); 
						return 1;   
					}
					return 0; 
				}).orElse(0); 
	}
	
	
	
}
