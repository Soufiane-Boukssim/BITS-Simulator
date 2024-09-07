package com.simulator.globalController.atm;

import com.simulator.entities.atm.NdcState;
import com.simulator.entities.atm.NdcStateId;
import com.simulator.globalService.atm.NdcStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NdcStateController {

	@Autowired
	private NdcStateService ndcStateService;

	@PostMapping("/ndcState")
	public void saveNdcState(@RequestBody NdcState ndcState) {
		ndcStateService.saveNewNdcState(ndcState);
	}

	@GetMapping("/ndcState")
	public List<NdcState> getAllNdcState() {
		return ndcStateService.getAllNdcState();
	}

	@PutMapping("/ndcState/{_profileCode}/{_stateNumber}")
	public void updateNdcState(@RequestBody NdcState ndcState, @PathVariable String _profileCode,
			@PathVariable String _stateNumber) {
		NdcStateId id = new NdcStateId();
		id.setProfileCode(_profileCode);
		id.setStateNumber(_stateNumber);
		ndcState.setId(id);
		ndcStateService.updateNdcState(ndcState, id);
	}

	@DeleteMapping("/ndcState")
	public String deleteNdcState(@RequestBody NdcStateId id) {
		ndcStateService.deleteNdcState(id);
		return "Deleted Successfully";
	}
}