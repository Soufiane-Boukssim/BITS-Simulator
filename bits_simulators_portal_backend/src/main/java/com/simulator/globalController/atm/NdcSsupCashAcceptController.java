package com.simulator.globalController.atm;

import com.simulator.entities.atm.NdcSsupCashAccept;
import com.simulator.entities.atm.NdcSsupCashAcceptId;
import com.simulator.globalService.atm.NdcSsupCashAcceptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NdcSsupCashAcceptController {

	@Autowired
	private NdcSsupCashAcceptService ndcSsupCashAcceptService;

	@PostMapping("/ndcSsupCashAccept")
	public void saveNdcSsupCashAccept(@RequestBody NdcSsupCashAccept ndcSsupCashAccept) {
		ndcSsupCashAcceptService.saveNewNdcSsupCashAccept(ndcSsupCashAccept);
	}

	@GetMapping("/ndcSsupCashAccept")
	public List<NdcSsupCashAccept> getAllNdcSsupCashAccept() {
		return ndcSsupCashAcceptService.getAllNdcSsupCashAccept();
	}

	@PutMapping("/ndcSsupCashAccept/{_profileCode}/{_stateNumber}")
	public void updateNdcSsupCashAccept(@RequestBody NdcSsupCashAccept ndcSsupCashAccept,
			@PathVariable String _profileCode, @PathVariable String _stateNumber) {
		NdcSsupCashAcceptId id = new NdcSsupCashAcceptId();
		id.setProfileCode(_profileCode);
		id.setStateNumber(_stateNumber);
		ndcSsupCashAccept.setId(id);
		ndcSsupCashAcceptService.updateNdcSsupCashAccept(ndcSsupCashAccept, id);
	}

	@DeleteMapping("/ndcSsupCashAccept")
	public String deleteNdcSsupCashAccept(@RequestBody NdcSsupCashAcceptId id) {
		ndcSsupCashAcceptService.deleteNdcSsupCashAccept(id);
		return "Deleted Successfully";
	}
}