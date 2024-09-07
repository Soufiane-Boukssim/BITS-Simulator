package com.simulator.globalController.atm;

import com.simulator.entities.atm.NdcSrDocumentAmountE;
import com.simulator.entities.atm.NdcSrDocumentAmountEId;
import com.simulator.globalService.atm.NdcSrDocumentAmountEService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NdcSrDocumentAmountEController {

	@Autowired
	private NdcSrDocumentAmountEService ndcSrDocumentAmountEService;

	@PostMapping("/ndcSrDocumentAmountE")
	public void saveNdcSrDocumentAmountE(@RequestBody NdcSrDocumentAmountE ndcSrDocumentAmountE) {
		ndcSrDocumentAmountEService.saveNewNdcSrDocumentAmountE(ndcSrDocumentAmountE);
	}

	@GetMapping("/ndcSrDocumentAmountE")
	public List<NdcSrDocumentAmountE> getAllNdcSrDocumentAmountE() {
		return ndcSrDocumentAmountEService.getAllNdcSrDocumentAmountE();
	}

	@PutMapping("/ndcSrDocumentAmountE/{_profileCode}/{_stateNumber}")
	public void updateNdcSrDocumentAmountE(@RequestBody NdcSrDocumentAmountE ndcSrDocumentAmountE,
			@PathVariable String _profileCode, @PathVariable String _stateNumber) {
		NdcSrDocumentAmountEId id = new NdcSrDocumentAmountEId();
		id.setProfileCode(_profileCode);
		id.setStateNumber(_stateNumber);
		ndcSrDocumentAmountE.setId(id);
		ndcSrDocumentAmountEService.updateNdcSrDocumentAmountE(ndcSrDocumentAmountE, id);
	}

	@DeleteMapping("/ndcSrDocumentAmountE")
	public String deleteNdcSrDocumentAmountE(@RequestBody NdcSrDocumentAmountEId id) {
		ndcSrDocumentAmountEService.deleteNdcSrDocumentAmountE(id);
		return "Deleted Successfully";
	}
}