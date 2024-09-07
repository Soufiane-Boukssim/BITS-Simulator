package com.simulator.globalController.atm;

import com.simulator.entities.atm.NdcSsLangCodeSwitch;
import com.simulator.entities.atm.NdcSsLangCodeSwitchId;
import com.simulator.globalService.atm.NdcSsLangCodeSwitchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NdcSsLangCodeSwitchController {

	@Autowired
	private NdcSsLangCodeSwitchService ndcSsLangCodeSwitchService;

	@PostMapping("/ndcSsLangCodeSwitch")
	public void saveNdcSsLangCodeSwitch(@RequestBody NdcSsLangCodeSwitch ndcSsLangCodeSwitch) {
		ndcSsLangCodeSwitchService.saveNewNdcSsLangCodeSwitch(ndcSsLangCodeSwitch);
	}

	@GetMapping("/ndcSsLangCodeSwitch")
	public List<NdcSsLangCodeSwitch> getAllNdcSsLangCodeSwitch() {
		return ndcSsLangCodeSwitchService.getAllNdcSsLangCodeSwitch();
	}

	@PutMapping("/ndcSsLangCodeSwitch/{_profileCode}/{_stateNumber}")
	public void updateNdcSsLangCodeSwitch(@RequestBody NdcSsLangCodeSwitch ndcSsLangCodeSwitch,
			@PathVariable String _profileCode, @PathVariable String _stateNumber) {
		NdcSsLangCodeSwitchId id = new NdcSsLangCodeSwitchId();
		id.setProfileCode(_profileCode);
		id.setStateNumber(_stateNumber);
		ndcSsLangCodeSwitch.setId(id);
		ndcSsLangCodeSwitchService.updateNdcSsLangCodeSwitch(ndcSsLangCodeSwitch, id);
	}

	@DeleteMapping("/ndcSsLangCodeSwitch")
	public String deleteNdcSsLangCodeSwitch(@RequestBody NdcSsLangCodeSwitchId id) {
		ndcSsLangCodeSwitchService.deleteNdcSsLangCodeSwitch(id);
		return "Deleted Successfully";
	}
}