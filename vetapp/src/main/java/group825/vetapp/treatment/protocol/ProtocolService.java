package group825.vetapp.treatment.protocol;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Service that performs treatment Protocol request
 *
 * @author Timothy Mok
 * @version 1.0
 * @since November 15, 2021
 */
@Service
public class ProtocolService {

	/**
	 * Treatment protocol repository that accesses the database
	 */
	private final ProtocolRepository repo;

	/**
	 * Constructor for the ProtocolService to communicate between the Repository and Controller
	 * @param repo the ProtocolRepository
	 */
	public ProtocolService(@Qualifier("tempProtocolRepo") ProtocolRepository repo) {
		// UPDATE WITH DATABASE AS NEEDED
		this.repo = repo;
	}

	/**
	 * Service for adding a protocol to the system
	 * @param protocol protocol to be added
	 * @return whether the protocol was successfully added to the Repository
	 */
	public int addProtocol(Protocol protocol) {
		return repo.addProtocol(protocol);
	}
	
	/**
	 * List all protocols in the system
	 * @return a list of all protocols associated with an animal
	 */
	public List<Protocol> selectAllProtocol() {
		return repo.selectAllProtocol();
	}
	
	/**
	 * Search for a specific treatment protocol in the Repository
	 * @param id id of the requested protocol
	 * @return the protocol from the repository
	 */
	public Optional<Protocol> selectProtocolById(UUID id) {
		return repo.selectProtocolById(id);
	}
	
	/**
	 * Delete a protocol from the Repository
	 * @param id protocol to be deleted
	 * @return whether the protocol was successfully deleted from the Repository
	 */
	public int deleteProtocolById(UUID id) {
		return repo.deleteProtocolById(id);
	}
	
	/**
	 * Update an existing protocol in the Repository
	 * @param id protocol to be updated
	 * @param protocol protocol object containing new information
	 * @return whether the protocol was successfully updated the Repository
	 */
	public int updateProtocolById(UUID id, Protocol protocol) {
		return repo.updateProtocolById(id, protocol);
	}
}