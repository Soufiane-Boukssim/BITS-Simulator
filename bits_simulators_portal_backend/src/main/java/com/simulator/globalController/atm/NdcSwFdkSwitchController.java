package com.simulator.globalController.atm;

import com.simulator.entities.atm.NdcSwFdkSwitch;
import com.simulator.entities.atm.NdcSwFdkSwitchId;
import com.simulator.globalService.atm.NdcSwFdkSwitchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NdcSwFdkSwitchController {

	@Autowired
	private NdcSwFdkSwitchService ndcSwFdkSwitchService;

	@PostMapping("/ndcSwFdkSwitch")
	public void saveNdcSwFdkSwitch(@RequestBody NdcSwFdkSwitch ndcSwFdkSwitch) {
		ndcSwFdkSwitchService.saveNewNdcSwFdkSwitch(ndcSwFdkSwitch);
	}

	@GetMapping("/ndcSwFdkSwitch")
	public List<NdcSwFdkSwitch> getAllNdcSwFdkSwitch() {
		return ndcSwFdkSwitchService.getAllNdcSwFdkSwitch();
	}

	@PutMapping("/ndcSwFdkSwitch/{_profileCode}/{_stateNumber}")
	public void updateNdcSwFdkSwitch(@RequestBody NdcSwFdkSwitch ndcSwFdkSwitch, @PathVariable String _profileCode,
			@PathVariable String _stateNumber) {
		NdcSwFdkSwitchId id = new NdcSwFdkSwitchId();
		id.setProfileCode(_profileCode);
		id.setStateNumber(_stateNumber);
		ndcSwFdkSwitch.setId(id);
		ndcSwFdkSwitchService.updateNdcSwFdkSwitch(ndcSwFdkSwitch, id);
	}

	@DeleteMapping("/ndcSwFdkSwitch")
	public String deleteNdcSwFdkSwitch(@RequestBody NdcSwFdkSwitchId id) {
		ndcSwFdkSwitchService.deleteNdcSwFdkSwitch(id);
		return "Deleted Successfully";
	}
}