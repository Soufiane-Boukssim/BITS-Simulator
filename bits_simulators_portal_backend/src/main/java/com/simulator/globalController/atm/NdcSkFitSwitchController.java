package com.simulator.globalController.atm;

import com.simulator.entities.atm.NdcSkFitSwitch;
import com.simulator.entities.atm.NdcSkFitSwitchId;
import com.simulator.globalService.atm.NdcSkFitSwitchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NdcSkFitSwitchController {

	@Autowired
	private NdcSkFitSwitchService ndcSkFitSwitchService;

	@PostMapping("/ndcSkFitSwitch")
	public void saveNdcSkFitSwitch(@RequestBody NdcSkFitSwitch ndcSkFitSwitch) {
		ndcSkFitSwitchService.saveNewNdcSkFitSwitch(ndcSkFitSwitch);
	}

	@GetMapping("/ndcSkFitSwitch")
	public List<NdcSkFitSwitch> getAllNdcSkFitSwitch() {
		return ndcSkFitSwitchService.getAllNdcSkFitSwitch();
	}

	@PutMapping("/ndcSkFitSwitch/{_profileCode}/{_stateNumber}")
	public void updateNdcSkFitSwitch(@RequestBody NdcSkFitSwitch ndcSkFitSwitch, @PathVariable String _profileCode,
			@PathVariable String _stateNumber) {
		NdcSkFitSwitchId id = new NdcSkFitSwitchId();
		id.setProfileCode(_profileCode);
		id.setStateNumber(_stateNumber);
		ndcSkFitSwitch.setId(id);
		ndcSkFitSwitchService.updateNdcSkFitSwitch(ndcSkFitSwitch, id);
	}

	@DeleteMapping("/ndcSkFitSwitch")
	public String deleteNdcSkFitSwitch(@RequestBody NdcSkFitSwitchId id) {
		ndcSkFitSwitchService.deleteNdcSkFitSwitch(id);
		return "Deleted Successfully";
	}
}