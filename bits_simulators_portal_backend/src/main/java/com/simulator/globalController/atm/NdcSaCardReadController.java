package com.simulator.globalController.atm;

import com.simulator.entities.atm.NdcSaCardRead;
import com.simulator.entities.atm.NdcSaCardReadId;
import com.simulator.globalService.atm.NdcSaCardReadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NdcSaCardReadController {

	@Autowired
	private NdcSaCardReadService ndcSaCardReadService;

	@PostMapping("/ndcSaCardRead")
	public void saveNdcSaCardRead(@RequestBody NdcSaCardRead ndcSaCardRead) {
		ndcSaCardReadService.saveNewNdcSaCardRead(ndcSaCardRead);
	}

	@GetMapping("/ndcSaCardRead")
	public List<NdcSaCardRead> getAllNdcSaCardRead() {
		return ndcSaCardReadService.getAllNdcSaCardRead();
	}

	@PutMapping("/ndcSaCardRead/{_profileCode}/{_stateNumber}")
	public void updateNdcSaCardRead(@RequestBody NdcSaCardRead ndcSaCardRead, @PathVariable String _profileCode,
			@PathVariable String _stateNumber) {
		NdcSaCardReadId id = new NdcSaCardReadId();
		id.setProfileCode(_profileCode);
		id.setStateNumber(_stateNumber);
		ndcSaCardRead.setId(id);
		ndcSaCardReadService.updateNdcSaCardRead(ndcSaCardRead, id);
	}

	@DeleteMapping("/ndcSaCardRead")
	public String deleteNdcSaCardRead(@RequestBody NdcSaCardReadId id) {
		ndcSaCardReadService.deleteNdcSaCardRead(id);
		return "Deleted Successfully";
	}
}