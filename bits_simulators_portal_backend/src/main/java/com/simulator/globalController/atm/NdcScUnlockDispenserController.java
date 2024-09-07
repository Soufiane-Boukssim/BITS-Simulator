package com.simulator.globalController.atm;

import com.simulator.entities.atm.NdcScUnlockDispenser;
import com.simulator.entities.atm.NdcScUnlockDispenserId;
import com.simulator.globalService.atm.NdcScUnlockDispenserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NdcScUnlockDispenserController {

	@Autowired
	private NdcScUnlockDispenserService ndcScUnlockDispenserService;

	@PostMapping("/ndcScUnlockDispenser")
	public void saveNdcScUnlockDispenser(@RequestBody NdcScUnlockDispenser ndcScUnlockDispenser) {
		ndcScUnlockDispenserService.saveNewNdcScUnlockDispenser(ndcScUnlockDispenser);
	}

	@GetMapping("/ndcScUnlockDispenser")
	public List<NdcScUnlockDispenser> getAllNdcScUnlockDispenser() {
		return ndcScUnlockDispenserService.getAllNdcScUnlockDispenser();
	}

	@PutMapping("/ndcScUnlockDispenser/{_profileCode}/{_stateNumber}")
	public void updateNdcScUnlockDispenser(@RequestBody NdcScUnlockDispenser ndcScUnlockDispenser,
			@PathVariable String _profileCode, @PathVariable String _stateNumber) {
		NdcScUnlockDispenserId id = new NdcScUnlockDispenserId();
		id.setProfileCode(_profileCode);
		id.setStateNumber(_stateNumber);
		ndcScUnlockDispenser.setId(id);
		ndcScUnlockDispenserService.updateNdcScUnlockDispenser(ndcScUnlockDispenser, id);
	}

	@DeleteMapping("/ndcScUnlockDispenser")
	public String deleteNdcScUnlockDispenser(@RequestBody NdcScUnlockDispenserId id) {
		ndcScUnlockDispenserService.deleteNdcScUnlockDispenser(id);
		return "Deleted Successfully";
	}
}