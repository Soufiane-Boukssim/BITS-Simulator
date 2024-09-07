package com.simulator.globalController.atm;

import com.simulator.entities.atm.NdcSpvergIccReInitialise;
import com.simulator.entities.atm.NdcSpvergIccReInitialiseId;
import com.simulator.globalService.atm.NdcSpvergIccReInitialiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NdcSpvergIccReInitialiseController {

	@Autowired
	private NdcSpvergIccReInitialiseService ndcSpvergIccReInitialiseService;

	@PostMapping("/ndcSpvergIccReInitialise")
	public void saveNdcSpvergIccReInitialise(@RequestBody NdcSpvergIccReInitialise ndcSpvergIccReInitialise) {
		ndcSpvergIccReInitialiseService.saveNewNdcSpvergIccReInitialise(ndcSpvergIccReInitialise);
	}

	@GetMapping("/ndcSpvergIccReInitialise")
	public List<NdcSpvergIccReInitialise> getAllNdcSpvergIccReInitialise() {
		return ndcSpvergIccReInitialiseService.getAllNdcSpvergIccReInitialise();
	}

	@PutMapping("/ndcSpvergIccReInitialise/{_profileCode}/{_stateNumber}")
	public void updateNdcSpvergIccReInitialise(@RequestBody NdcSpvergIccReInitialise ndcSpvergIccReInitialise,
			@PathVariable String _profileCode, @PathVariable String _stateNumber) {
		NdcSpvergIccReInitialiseId id = new NdcSpvergIccReInitialiseId();
		id.setProfileCode(_profileCode);
		id.setStateNumber(_stateNumber);
		ndcSpvergIccReInitialise.setId(id);
		ndcSpvergIccReInitialiseService.updateNdcSpvergIccReInitialise(ndcSpvergIccReInitialise, id);
	}

	@DeleteMapping("/ndcSpvergIccReInitialise")
	public String deleteNdcSpvergIccReInitialise(@RequestBody NdcSpvergIccReInitialiseId id) {
		ndcSpvergIccReInitialiseService.deleteNdcSpvergIccReInitialise(id);
		return "Deleted Successfully";
	}
}