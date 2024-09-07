package com.simulator.globalController.atm;

import com.simulator.entities.atm.NdcSdPresetOperation;
import com.simulator.entities.atm.NdcSdPresetOperationId;
import com.simulator.globalService.atm.NdcSdPresetOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NdcSdPresetOperationController {

	@Autowired
	private NdcSdPresetOperationService ndcSdPresetOperationService;

	@PostMapping("/ndcSdPresetOperation")
	public void saveNdcSdPresetOperation(@RequestBody NdcSdPresetOperation ndcSdPresetOperation) {
		ndcSdPresetOperationService.saveNewNdcSdPresetOperation(ndcSdPresetOperation);
	}

	@GetMapping("/ndcSdPresetOperation")
	public List<NdcSdPresetOperation> getAllNdcSdPresetOperation() {
		return ndcSdPresetOperationService.getAllNdcSdPresetOperation();
	}

	@PutMapping("/ndcSdPresetOperation/{_profileCode}/{_stateNumber}")
	public void updateNdcSdPresetOperation(@RequestBody NdcSdPresetOperation ndcSdPresetOperation,
			@PathVariable String _profileCode, @PathVariable String _stateNumber) {
		NdcSdPresetOperationId id = new NdcSdPresetOperationId();
		id.setProfileCode(_profileCode);
		id.setStateNumber(_stateNumber);
		ndcSdPresetOperation.setId(id);
		ndcSdPresetOperationService.updateNdcSdPresetOperation(ndcSdPresetOperation, id);
	}

	@DeleteMapping("/ndcSdPresetOperation")
	public String deleteNdcSdPresetOperation(@RequestBody NdcSdPresetOperationId id) {
		ndcSdPresetOperationService.deleteNdcSdPresetOperation(id);
		return "Deleted Successfully";
	}
}