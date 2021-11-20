package group825.vetapp.treatment.protocol;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

/**
 * Repository that stores treatment Protocol information
 *
 * @author Timothy Mok
 * @version 1.0
 * @since November 15, 2021
 */
// UPDATE DATABASE AS NEEDED
@Repository("tempProtocolRepo")
public class ProtocolRepository {

	/**
	 * Database that stores all the treatment protocol
	 */
	private static final List<Protocol> dbProtocol = new ArrayList<>();
	
	/**
	 * Adds a protocol to the system
	 * @param protocol the treatment protocol associated with an animal
	 * @return 1 if successfully added, 0 otherwise
	 */
	public int addProtocol(Protocol protocol) {
		UUID id = UUID.randomUUID();
		dbProtocol.add(new Protocol(id, protocol.getAnimalName(), protocol.getDiagnosis(), protocol.getPrescription(), protocol.getProtocol()));
		return 1;
	}
	
	/**
	 * Returns all protocols in the system associated with this animal
	 * @return all associated treatment protocols
	 */
	public List<Protocol> selectAllProtocol() {
		return dbProtocol;
	}
	
	/**
	 * View a specific treatment protocol selected by id
	 * @param id the id of the selected protocol
	 * @return the treatment protocol with the requested id
	 */
	public Optional<Protocol> selectProtocolById(UUID id) {
		return dbProtocol.stream().filter(protocol -> protocol.getId().equals(id)).findFirst();
	}
	
	/**
	 * Remove a specific protocol from the system
	 * @param id the id of the treatment protocol to be removed
	 * @return 1 if removed successfully, 0 otherwise
	 */
	public int deleteProtocolById(UUID id) {
		Optional<Protocol> protocolMaybe = selectProtocolById(id);

		if(protocolMaybe.isEmpty()) {
			return 0;
		}

		dbProtocol.remove(protocolMaybe.get());
		return 1;
	}
	
	/**
	 * Update an existing treatment protocol
	 * @param id the id of the protocol to be updated
	 * @param newProtocol the Protocol object with the updated information
	 * @return 1 if successfully updated, 0 otherwise
	 */
	public int updateProtocolById(UUID id, Protocol newProtocol) {
		return selectProtocolById(id).map(protocolFound -> {
			int indexOfAnimalToUpdate = dbProtocol.indexOf(protocolFound);
			if (indexOfAnimalToUpdate >= 0) {
				dbProtocol.set(indexOfAnimalToUpdate,
								 new Protocol(id, newProtocol.getAnimalName(), newProtocol.getDiagnosis(),
										 newProtocol.getPrescription(), newProtocol.getProtocol()));
				return 1;
			}

			return 0;
		}).orElse(0);
	}
}