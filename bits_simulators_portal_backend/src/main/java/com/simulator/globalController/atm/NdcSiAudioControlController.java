package com.simulator.globalController.atm;

import com.simulator.entities.atm.NdcSiAudioControl;
import com.simulator.entities.atm.NdcSiAudioControlId;
import com.simulator.globalService.atm.NdcSiAudioControlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NdcSiAudioControlController {

	@Autowired
	private NdcSiAudioControlService ndcSiAudioControlService;

	@PostMapping("/ndcSiAudioControl")
	public void saveNdcSiAudioControl(@RequestBody NdcSiAudioControl ndcSiAudioControl) {
		ndcSiAudioControlService.saveNewNdcSiAudioControl(ndcSiAudioControl);
	}

	@GetMapping("/ndcSiAudioControl")
	public List<NdcSiAudioControl> getAllNdcSiAudioControl() {
		return ndcSiAudioControlService.getAllNdcSiAudioControl();
	}

	@PutMapping("/ndcSiAudioControl/{_profileCode}/{_stateNumber}")
	public void updateNdcSiAudioControl(@RequestBody NdcSiAudioControl ndcSiAudioControl,
			@PathVariable String _profileCode, @PathVariable String _stateNumber) {
		NdcSiAudioControlId id = new NdcSiAudioControlId();
		id.setProfileCode(_profileCode);
		id.setStateNumber(_stateNumber);
		ndcSiAudioControl.setId(id);
		ndcSiAudioControlService.updateNdcSiAudioControl(ndcSiAudioControl, id);
	}

	@DeleteMapping("/ndcSiAudioControl")
	public String deleteNdcSiAudioControl(@RequestBody NdcSiAudioControlId id) {
		ndcSiAudioControlService.deleteNdcSiAudioControl(id);
		return "Deleted Successfully";
	}
}