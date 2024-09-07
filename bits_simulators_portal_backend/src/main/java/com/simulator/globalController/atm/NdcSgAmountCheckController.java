package com.simulator.globalController.atm;

import com.simulator.entities.atm.NdcSgAmountCheck;
import com.simulator.entities.atm.NdcSgAmountCheckId;
import com.simulator.globalService.atm.NdcSgAmountCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NdcSgAmountCheckController {

	@Autowired
	private NdcSgAmountCheckService ndcSgAmountCheckService;

	@PostMapping("/ndcSgAmountCheck")
	public void saveNdcSgAmountCheck(@RequestBody NdcSgAmountCheck ndcSgAmountCheck) {
		ndcSgAmountCheckService.saveNewNdcSgAmountCheck(ndcSgAmountCheck);
	}

	@GetMapping("/ndcSgAmountCheck")
	public List<NdcSgAmountCheck> getAllNdcSgAmountCheck() {
		return ndcSgAmountCheckService.getAllNdcSgAmountCheck();
	}

	@PutMapping("/ndcSgAmountCheck/{_profileCode}/{_stateNumber}")
	public void updateNdcSgAmountCheck(@RequestBody NdcSgAmountCheck ndcSgAmountCheck,
			@PathVariable String _profileCode, @PathVariable String _stateNumber) {
		NdcSgAmountCheckId id = new NdcSgAmountCheckId();
		id.setProfileCode(_profileCode);
		id.setStateNumber(_stateNumber);
		ndcSgAmountCheck.setId(id);
		ndcSgAmountCheckService.updateNdcSgAmountCheck(ndcSgAmountCheck, id);
	}

	@DeleteMapping("/ndcSgAmountCheck")
	public String deleteNdcSgAmountCheck(@RequestBody NdcSgAmountCheckId id) {
		ndcSgAmountCheckService.deleteNdcSgAmountCheck(id);
		return "Deleted Successfully";
	}
}