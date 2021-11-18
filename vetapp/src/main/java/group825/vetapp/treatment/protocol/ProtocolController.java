package group825.vetapp.treatment.protocol;

import java.util.List;
import java.util.UUID;


import group825.vetapp.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("app/treatment/protocol")
@RestController
public class ProtocolController {
	
	private final ProtocolService protocolService;

	/**
	 * Constructor for ProtocolController
	 * @param protocolService - service to be used to send updates to Repository
	 */
	@Autowired
	public ProtocolController(ProtocolService protocolService) {
		this.protocolService = protocolService;
	}
	
	/**
	 * Send request to add a treatment protocol for an animal.
	 * Checks that all necessary fields are filled out.
	 * @param protocol - the treatment required
	 */
	@PostMapping
	public void addProtocol(@RequestBody Protocol protocol) {
		if(protocol.anyNulls()) {
			throw new ApiRequestException("At least one protocol field is null");
		}
		protocolService.addProtocol(protocol);
	}


	/**
	 * Displays all the treatment protocols associated with an animal
	 * @return a list of all the treatment protocols
	 */
	@GetMapping
	public List<Protocol> selectAllProtocol(){
		return protocolService.selectAllProtocol();
	}
	
	/**
	 * Requests a specific treatment protocol.
	 * Checks that the ID is valid. 
	 * @param strId - the ID of the requested protocol
	 * @return the requested protocol
	 */
	@GetMapping(path = "{id}") 
	public Protocol selectProtocolById(@PathVariable("id") String strId) {
		try {
            UUID id = UUID.fromString(strId);
            return protocolService.selectProtocolById(id)
					.orElseThrow(ApiExceptions.invalidIdException());
        } catch(java.lang.IllegalArgumentException e) {
            throw new InvalidIdException();
        }
	}
	
	/**
	 * Delete an existing treatment protocol in the database.
	 * Checks that the treatment ID is valid.
	 * @param strId - the ID of the protocol to be deleted
	 */
	@DeleteMapping(path = "{id}")
	public void deleteProtocolById(@PathVariable("id") String strId) {
		try {
            UUID id = UUID.fromString(strId);
            protocolService.deleteProtocolById(id);
        } catch(java.lang.IllegalArgumentException e) {
            throw new InvalidIdException();
        }
	}
	
	/**
	 * Update an existing treatment protocol in the database.
	 * Checks that the treatment ID is valid.
	 * @param strId - the ID of the protocol to be updated
	 * @param protocolToUpdate - protocol object with updated information
	 */
	@PutMapping(path = "{id}")
	public void updateProtocolById(@PathVariable("id") String strId, @RequestBody Protocol protocolToUpdate) {	
		try {
            UUID id = UUID.fromString(strId);
            if(protocolToUpdate.anyNulls()) {
    			throw new ApiRequestException("At least one protocol field is null");
    		}
            protocolService.updateProtocolById(id, protocolToUpdate);
        } catch(java.lang.IllegalArgumentException e) {
            throw new InvalidIdException();
        }
	}
}
