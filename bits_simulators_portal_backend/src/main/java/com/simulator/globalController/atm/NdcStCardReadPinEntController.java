package com.simulator.globalController.atm;

import com.simulator.entities.atm.NdcStCardReadPinEnt;
import com.simulator.entities.atm.NdcStCardReadPinEntId;
import com.simulator.globalService.atm.NdcStCardReadPinEntService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NdcStCardReadPinEntController {

	@Autowired
	private NdcStCardReadPinEntService ndcStCardReadPinEntService;

	@PostMapping("/ndcStCardReadPinEnt")
	public void saveNdcStCardReadPinEnt(@RequestBody NdcStCardReadPinEnt ndcStCardReadPinEnt) {
		ndcStCardReadPinEntService.saveNewNdcStCardReadPinEnt(ndcStCardReadPinEnt);
	}

	@GetMapping("/ndcStCardReadPinEnt")
	public List<NdcStCardReadPinEnt> getAllNdcStCardReadPinEnt() {
		return ndcStCardReadPinEntService.getAllNdcStCardReadPinEnt();
	}

	@PutMapping("/ndcStCardReadPinEnt/{_profileCode}/{_stateNumber}")
	public void updateNdcStCardReadPinEnt(@RequestBody NdcStCardReadPinEnt ndcStCardReadPinEnt,
			@PathVariable String _profileCode, @PathVariable String _stateNumber) {
		NdcStCardReadPinEntId id = new NdcStCardReadPinEntId();
		id.setProfileCode(_profileCode);
		id.setStateNumber(_stateNumber);
		ndcStCardReadPinEnt.setId(id);
		ndcStCardReadPinEntService.updateNdcStCardReadPinEnt(ndcStCardReadPinEnt, id);
	}

	@DeleteMapping("/ndcStCardReadPinEnt")
	public String deleteNdcStCardReadPinEnt(@RequestBody NdcStCardReadPinEntId id) {
		ndcStCardReadPinEntService.deleteNdcStCardReadPinEnt(id);
		return "Deleted Successfully";
	}
}