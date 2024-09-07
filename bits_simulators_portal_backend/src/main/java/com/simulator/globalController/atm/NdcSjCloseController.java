package com.simulator.globalController.atm;

import com.simulator.entities.atm.NdcSjClose;
import com.simulator.entities.atm.NdcSjCloseId;
import com.simulator.globalService.atm.NdcSjCloseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NdcSjCloseController {

	@Autowired
	private NdcSjCloseService ndcSjCloseService;

	@PostMapping("/ndcSjClose")
	public void saveNdcSjClose(@RequestBody NdcSjClose ndcSjClose) {
		ndcSjCloseService.saveNewNdcSjClose(ndcSjClose);
	}

	@GetMapping("/ndcSjClose")
	public List<NdcSjClose> getAllNdcSjClose() {
		return ndcSjCloseService.getAllNdcSjClose();
	}

	@PutMapping("/ndcSjClose/{_profileCode}/{_stateNumber}")
	public void updateNdcSjClose(@RequestBody NdcSjClose ndcSjClose, @PathVariable String _profileCode,
			@PathVariable String _stateNumber) {
		NdcSjCloseId id = new NdcSjCloseId();
		id.setProfileCode(_profileCode);
		id.setStateNumber(_stateNumber);
		ndcSjClose.setId(id);
		ndcSjCloseService.updateNdcSjClose(ndcSjClose, id);
	}

	@DeleteMapping("/ndcSjClose")
	public String deleteNdcSjClose(@RequestBody NdcSjCloseId id) {
		ndcSjCloseService.deleteNdcSjClose(id);
		return "Deleted Successfully";
	}
}