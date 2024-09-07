package com.simulator.globalController.atm;

import com.simulator.entities.atm.NdcSvergCompleteIccInit;
import com.simulator.entities.atm.NdcSvergCompleteIccInitId;
import com.simulator.globalService.atm.NdcSvergCompleteIccInitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NdcSvergCompleteIccInitController {

	@Autowired
	private NdcSvergCompleteIccInitService ndcSvergCompleteIccInitService;

	@PostMapping("/ndcSvergCompleteIccInit")
	public void saveNdcSvergCompleteIccInit(@RequestBody NdcSvergCompleteIccInit ndcSvergCompleteIccInit) {
		ndcSvergCompleteIccInitService.saveNewNdcSvergCompleteIccInit(ndcSvergCompleteIccInit);
	}

	@GetMapping("/ndcSvergCompleteIccInit")
	public List<NdcSvergCompleteIccInit> getAllNdcSvergCompleteIccInit() {
		return ndcSvergCompleteIccInitService.getAllNdcSvergCompleteIccInit();
	}

	@PutMapping("/ndcSvergCompleteIccInit/{_profileCode}/{_stateNumber}")
	public void updateNdcSvergCompleteIccInit(@RequestBody NdcSvergCompleteIccInit ndcSvergCompleteIccInit,
			@PathVariable Character _profileCode, @PathVariable Character _stateNumber) {
		NdcSvergCompleteIccInitId id = new NdcSvergCompleteIccInitId();
		id.setProfileCode(_profileCode);
		id.setStateNumber(_stateNumber);
		ndcSvergCompleteIccInit.setId(id);
		ndcSvergCompleteIccInitService.updateNdcSvergCompleteIccInit(ndcSvergCompleteIccInit, id);
	}

	@DeleteMapping("/ndcSvergCompleteIccInit")
	public String deleteNdcSvergCompleteIccInit(@RequestBody NdcSvergCompleteIccInitId id) {
		ndcSvergCompleteIccInitService.deleteNdcSvergCompleteIccInit(id);
		return "Deleted Successfully";
	}
}