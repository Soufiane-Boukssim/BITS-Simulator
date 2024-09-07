package com.simulator.globalController.atm;

import com.simulator.entities.atm.NdcFit;
import com.simulator.entities.atm.NdcFitId;
import com.simulator.globalService.atm.NdcFitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NdcFitController {

	@Autowired
	private NdcFitService ndcFitService;

	@PostMapping("/ndcFit")
	public void saveNdcFit(@RequestBody NdcFit ndcFit) {
		ndcFitService.saveNewNdcFit(ndcFit);
	}

	@GetMapping("/ndcFit")
	public List<NdcFit> getAllNdcFit() {
		return ndcFitService.getAllNdcFit();
	}

	@PutMapping("/ndcFit/{_profileCode}/{_fitCode}")
	public void updateNdcFit(@RequestBody NdcFit ndcFit, @PathVariable String _profileCode,
			@PathVariable String _fitCode) {
		NdcFitId id = new NdcFitId();
		id.setProfileCode(_profileCode);
		id.setFitCode(_fitCode);
		ndcFit.setId(id);
		ndcFitService.updateNdcFit(ndcFit, id);
	}

	@DeleteMapping("/ndcFit")
	public String deleteNdcFit(@RequestBody NdcFitId id) {
		ndcFitService.deleteNdcFit(id);
		return "Deleted Successfully";
	}
}