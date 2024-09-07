package com.simulator.globalController.atm;

import com.simulator.entities.atm.NdcEmvApplication;
import com.simulator.entities.atm.NdcEmvApplicationId;
import com.simulator.globalService.atm.NdcEmvApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NdcEmvApplicationController {

	@Autowired
	private NdcEmvApplicationService ndcEmvApplicationService;

	@PostMapping("/ndcEmvApplication")
	public void saveNdcEmvApplication(@RequestBody NdcEmvApplication ndcEmvApplication) {
		ndcEmvApplicationService.saveNewNdcEmvApplication(ndcEmvApplication);
	}

	@GetMapping("/ndcEmvApplication")
	public List<NdcEmvApplication> getAllNdcEmvApplication() {
		return ndcEmvApplicationService.getAllNdcEmvApplication();
	}

	@PutMapping("/ndcEmvApplication/{_emvProfile}/{_entryNumber}")
	public void updateNdcEmvApplication(@RequestBody NdcEmvApplication ndcEmvApplication,
			@PathVariable String _emvProfile, @PathVariable String _entryNumber) {
		NdcEmvApplicationId id = new NdcEmvApplicationId();
		id.setEmvProfile(_emvProfile);
		id.setEntryNumber(_entryNumber);
		ndcEmvApplication.setId(id);
		ndcEmvApplicationService.updateNdcEmvApplication(ndcEmvApplication, id);
	}

	@DeleteMapping("/ndcEmvApplication")
	public String deleteNdcEmvApplication(@RequestBody NdcEmvApplicationId id) {
		ndcEmvApplicationService.deleteNdcEmvApplication(id);
		return "Deleted Successfully";
	}
}