package com.simulator.globalController.atm;

import com.simulator.entities.atm.NdcSmEnhancedPinEnter;
import com.simulator.entities.atm.NdcSmEnhancedPinEnterId;
import com.simulator.globalService.atm.NdcSmEnhancedPinEnterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NdcSmEnhancedPinEnterController {

	@Autowired
	private NdcSmEnhancedPinEnterService ndcSmEnhancedPinEnterService;

	@PostMapping("/ndcSmEnhancedPinEnter")
	public void saveNdcSmEnhancedPinEnter(@RequestBody NdcSmEnhancedPinEnter ndcSmEnhancedPinEnter) {
		ndcSmEnhancedPinEnterService.saveNewNdcSmEnhancedPinEnter(ndcSmEnhancedPinEnter);
	}

	@GetMapping("/ndcSmEnhancedPinEnter")
	public List<NdcSmEnhancedPinEnter> getAllNdcSmEnhancedPinEnter() {
		return ndcSmEnhancedPinEnterService.getAllNdcSmEnhancedPinEnter();
	}

	@PutMapping("/ndcSmEnhancedPinEnter/{_profileCode}/{_stateNumber}")
	public void updateNdcSmEnhancedPinEnter(@RequestBody NdcSmEnhancedPinEnter ndcSmEnhancedPinEnter,
			@PathVariable String _profileCode, @PathVariable String _stateNumber) {
		NdcSmEnhancedPinEnterId id = new NdcSmEnhancedPinEnterId();
		id.setProfileCode(_profileCode);
		id.setStateNumber(_stateNumber);
		ndcSmEnhancedPinEnter.setId(id);
		ndcSmEnhancedPinEnterService.updateNdcSmEnhancedPinEnter(ndcSmEnhancedPinEnter, id);
	}

	@DeleteMapping("/ndcSmEnhancedPinEnter")
	public String deleteNdcSmEnhancedPinEnter(@RequestBody NdcSmEnhancedPinEnterId id) {
		ndcSmEnhancedPinEnterService.deleteNdcSmEnhancedPinEnter(id);
		return "Deleted Successfully";
	}
}