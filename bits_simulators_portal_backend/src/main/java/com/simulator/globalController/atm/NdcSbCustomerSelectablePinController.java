package com.simulator.globalController.atm;

import com.simulator.entities.atm.NdcSbCustomerSelectablePin;
import com.simulator.entities.atm.NdcSbCustomerSelectablePinId;
import com.simulator.globalService.atm.NdcSbCustomerSelectablePinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NdcSbCustomerSelectablePinController {

	@Autowired
	private NdcSbCustomerSelectablePinService ndcSbCustomerSelectablePinService;

	@PostMapping("/ndcSbCustomerSelectablePin")
	public void saveNdcSbCustomerSelectablePin(@RequestBody NdcSbCustomerSelectablePin ndcSbCustomerSelectablePin) {
		ndcSbCustomerSelectablePinService.saveNewNdcSbCustomerSelectablePin(ndcSbCustomerSelectablePin);
	}

	@GetMapping("/ndcSbCustomerSelectablePin")
	public List<NdcSbCustomerSelectablePin> getAllNdcSbCustomerSelectablePin() {
		return ndcSbCustomerSelectablePinService.getAllNdcSbCustomerSelectablePin();
	}

	@PutMapping("/ndcSbCustomerSelectablePin/{_profileCode}/{_stateNumber}")
	public void updateNdcSbCustomerSelectablePin(@RequestBody NdcSbCustomerSelectablePin ndcSbCustomerSelectablePin,
			@PathVariable String _profileCode, @PathVariable String _stateNumber) {
		NdcSbCustomerSelectablePinId id = new NdcSbCustomerSelectablePinId();
		id.setProfileCode(_profileCode);
		id.setStateNumber(_stateNumber);
		ndcSbCustomerSelectablePin.setId(id);
		ndcSbCustomerSelectablePinService.updateNdcSbCustomerSelectablePin(ndcSbCustomerSelectablePin, id);
	}

	@DeleteMapping("/ndcSbCustomerSelectablePin")
	public String deleteNdcSbCustomerSelectablePin(@RequestBody NdcSbCustomerSelectablePinId id) {
		ndcSbCustomerSelectablePinService.deleteNdcSbCustomerSelectablePin(id);
		return "Deleted Successfully";
	}
}