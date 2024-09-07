package com.simulator.globalController.atm;

import com.simulator.entities.atm.NdcSbPinEntry;
import com.simulator.entities.atm.NdcSbPinEntryId;
import com.simulator.globalService.atm.NdcSbPinEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NdcSbPinEntryController {

	@Autowired
	private NdcSbPinEntryService ndcSbPinEntryService;

	@PostMapping("/ndcSbPinEntry")
	public void saveNdcSbPinEntry(@RequestBody NdcSbPinEntry ndcSbPinEntry) {
		ndcSbPinEntryService.saveNewNdcSbPinEntry(ndcSbPinEntry);
	}

	@GetMapping("/ndcSbPinEntry")
	public List<NdcSbPinEntry> getAllNdcSbPinEntry() {
		return ndcSbPinEntryService.getAllNdcSbPinEntry();
	}

	@PutMapping("/ndcSbPinEntry/{_profileCode}/{_stateNumber}")
	public void updateNdcSbPinEntry(@RequestBody NdcSbPinEntry ndcSbPinEntry, @PathVariable String _profileCode,
			@PathVariable String _stateNumber) {
		NdcSbPinEntryId id = new NdcSbPinEntryId();
		id.setProfileCode(_profileCode);
		id.setStateNumber(_stateNumber);
		ndcSbPinEntry.setId(id);
		ndcSbPinEntryService.updateNdcSbPinEntry(ndcSbPinEntry, id);
	}

	@DeleteMapping("/ndcSbPinEntry")
	public String deleteNdcSbPinEntry(@RequestBody NdcSbPinEntryId id) {
		ndcSbPinEntryService.deleteNdcSbPinEntry(id);
		return "Deleted Successfully";
	}
}