package com.simulator.globalController.atm;

import com.simulator.entities.atm.NdcSwChequeAccept;
import com.simulator.entities.atm.NdcSwChequeAcceptId;
import com.simulator.globalService.atm.NdcSwChequeAcceptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NdcSwChequeAcceptController {

	@Autowired
	private NdcSwChequeAcceptService ndcSwChequeAcceptService;

	@PostMapping("/ndcSwChequeAccept")
	public void saveNdcSwChequeAccept(@RequestBody NdcSwChequeAccept ndcSwChequeAccept) {
		ndcSwChequeAcceptService.saveNewNdcSwChequeAccept(ndcSwChequeAccept);
	}

	@GetMapping("/ndcSwChequeAccept")
	public List<NdcSwChequeAccept> getAllNdcSwChequeAccept() {
		return ndcSwChequeAcceptService.getAllNdcSwChequeAccept();
	}

	@PutMapping("/ndcSwChequeAccept/{_profileCode}/{_stateNumber}")
	public void updateNdcSwChequeAccept(@RequestBody NdcSwChequeAccept ndcSwChequeAccept,
			@PathVariable String _profileCode, @PathVariable String _stateNumber) {
		NdcSwChequeAcceptId id = new NdcSwChequeAcceptId();
		id.setProfileCode(_profileCode);
		id.setStateNumber(_stateNumber);
		ndcSwChequeAccept.setId(id);
		ndcSwChequeAcceptService.updateNdcSwChequeAccept(ndcSwChequeAccept, id);
	}

	@DeleteMapping("/ndcSwChequeAccept")
	public String deleteNdcSwChequeAccept(@RequestBody NdcSwChequeAcceptId id) {
		ndcSwChequeAcceptService.deleteNdcSwChequeAccept(id);
		return "Deleted Successfully";
	}
}