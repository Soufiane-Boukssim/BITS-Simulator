package com.simulator.globalController.atm;

import com.simulator.entities.atm.NdcSplusBeginIccInit;
import com.simulator.entities.atm.NdcSplusBeginIccInitId;
import com.simulator.globalService.atm.NdcSplusBeginIccInitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NdcSplusBeginIccInitController {

	@Autowired
	private NdcSplusBeginIccInitService ndcSplusBeginIccInitService;

	@PostMapping("/ndcSplusBeginIccInit")
	public void saveNdcSplusBeginIccInit(@RequestBody NdcSplusBeginIccInit ndcSplusBeginIccInit) {
		ndcSplusBeginIccInitService.saveNewNdcSplusBeginIccInit(ndcSplusBeginIccInit);
	}

	@GetMapping("/ndcSplusBeginIccInit")
	public List<NdcSplusBeginIccInit> getAllNdcSplusBeginIccInit() {
		return ndcSplusBeginIccInitService.getAllNdcSplusBeginIccInit();
	}

	@PutMapping("/ndcSplusBeginIccInit/{_profileCode}/{_stateNumber}")
	public void updateNdcSplusBeginIccInit(@RequestBody NdcSplusBeginIccInit ndcSplusBeginIccInit,
			@PathVariable String _profileCode, @PathVariable String _stateNumber) {
		NdcSplusBeginIccInitId id = new NdcSplusBeginIccInitId();
		id.setProfileCode(_profileCode);
		id.setStateNumber(_stateNumber);
		ndcSplusBeginIccInit.setId(id);
		ndcSplusBeginIccInitService.updateNdcSplusBeginIccInit(ndcSplusBeginIccInit, id);
	}

	@DeleteMapping("/ndcSplusBeginIccInit")
	public String deleteNdcSplusBeginIccInit(@RequestBody NdcSplusBeginIccInitId id) {
		ndcSplusBeginIccInitService.deleteNdcSplusBeginIccInit(id);
		return "Deleted Successfully";
	}
}