package com.simulator.globalController.atm;

import com.simulator.entities.atm.NdcSkSmartFitCheck;
import com.simulator.entities.atm.NdcSkSmartFitCheckId;
import com.simulator.globalService.atm.NdcSkSmartFitCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NdcSkSmartFitCheckController {

	@Autowired
	private NdcSkSmartFitCheckService ndcSkSmartFitCheckService;

	@PostMapping("/ndcSkSmartFitCheck")
	public void saveNdcSkSmartFitCheck(@RequestBody NdcSkSmartFitCheck ndcSkSmartFitCheck) {
		ndcSkSmartFitCheckService.saveNewNdcSkSmartFitCheck(ndcSkSmartFitCheck);
	}

	@GetMapping("/ndcSkSmartFitCheck")
	public List<NdcSkSmartFitCheck> getAllNdcSkSmartFitCheck() {
		return ndcSkSmartFitCheckService.getAllNdcSkSmartFitCheck();
	}

	@PutMapping("/ndcSkSmartFitCheck/{_profileCode}/{_stateNumber}")
	public void updateNdcSkSmartFitCheck(@RequestBody NdcSkSmartFitCheck ndcSkSmartFitCheck,
			@PathVariable String _profileCode, @PathVariable String _stateNumber) {
		NdcSkSmartFitCheckId id = new NdcSkSmartFitCheckId();
		id.setProfileCode(_profileCode);
		id.setStateNumber(_stateNumber);
		ndcSkSmartFitCheck.setId(id);
		ndcSkSmartFitCheckService.updateNdcSkSmartFitCheck(ndcSkSmartFitCheck, id);
	}

	@DeleteMapping("/ndcSkSmartFitCheck")
	public String deleteNdcSkSmartFitCheck(@RequestBody NdcSkSmartFitCheckId id) {
		ndcSkSmartFitCheckService.deleteNdcSkSmartFitCheck(id);
		return "Deleted Successfully";
	}
}