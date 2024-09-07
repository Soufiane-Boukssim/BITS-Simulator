package com.simulator.globalController.atm;

import com.simulator.entities.atm.NdcDenominationType;
import com.simulator.entities.atm.NdcDenominationTypeId;
import com.simulator.globalService.atm.NdcDenominationTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NdcDenominationTypeController {

	@Autowired
	private NdcDenominationTypeService ndcDenominationTypeService;

	@PostMapping("/ndcDenominationType")
	public void saveNdcDenominationType(@RequestBody NdcDenominationType ndcDenominationType) {
		ndcDenominationTypeService.saveNewNdcDenominationType(ndcDenominationType);
	}

	@GetMapping("/ndcDenominationType")
	public List<NdcDenominationType> getAllNdcDenominationType() {
		return ndcDenominationTypeService.getAllNdcDenominationType();
	}

	@PutMapping("/ndcDenominationType/{_ndcCassetteProfile}/{_denominationType}")
	public void updateNdcDenominationType(@RequestBody NdcDenominationType ndcDenominationType,
			@PathVariable String _ndcCassetteProfile, @PathVariable String _denominationType) {
		NdcDenominationTypeId id = new NdcDenominationTypeId();
		id.setNdcCassetteProfile(_ndcCassetteProfile);
		id.setDenominationType(_denominationType);
		ndcDenominationType.setId(id);
		ndcDenominationTypeService.updateNdcDenominationType(ndcDenominationType, id);
	}

	@DeleteMapping("/ndcDenominationType")
	public String deleteNdcDenominationType(@RequestBody NdcDenominationTypeId id) {
		ndcDenominationTypeService.deleteNdcDenominationType(id);
		return "Deleted Successfully";
	}
}