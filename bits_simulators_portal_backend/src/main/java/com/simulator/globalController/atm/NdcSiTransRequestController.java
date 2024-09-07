package com.simulator.globalController.atm;

import com.simulator.entities.atm.NdcSiTransRequest;
import com.simulator.entities.atm.NdcSiTransRequestId;
import com.simulator.globalService.atm.NdcSiTransRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NdcSiTransRequestController {

	@Autowired
	private NdcSiTransRequestService ndcSiTransRequestService;

	@PostMapping("/ndcSiTransRequest")
	public void saveNdcSiTransRequest(@RequestBody NdcSiTransRequest ndcSiTransRequest) {
		ndcSiTransRequestService.saveNewNdcSiTransRequest(ndcSiTransRequest);
	}

	@GetMapping("/ndcSiTransRequest")
	public List<NdcSiTransRequest> getAllNdcSiTransRequest() {
		return ndcSiTransRequestService.getAllNdcSiTransRequest();
	}

	@PutMapping("/ndcSiTransRequest/{_profileCode}/{_stateNumber}")
	public void updateNdcSiTransRequest(@RequestBody NdcSiTransRequest ndcSiTransRequest,
			@PathVariable String _profileCode, @PathVariable String _stateNumber) {
		NdcSiTransRequestId id = new NdcSiTransRequestId();
		id.setProfileCode(_profileCode);
		id.setStateNumber(_stateNumber);
		ndcSiTransRequest.setId(id);
		ndcSiTransRequestService.updateNdcSiTransRequest(ndcSiTransRequest, id);
	}

	@DeleteMapping("/ndcSiTransRequest")
	public String deleteNdcSiTransRequest(@RequestBody NdcSiTransRequestId id) {
		ndcSiTransRequestService.deleteNdcSiTransRequest(id);
		return "Deleted Successfully";
	}
}