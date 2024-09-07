package com.simulator.globalController.atm;

import com.simulator.entities.atm.NdcSxFdkInfoEntry;
import com.simulator.entities.atm.NdcSxFdkInfoEntryId;
import com.simulator.globalService.atm.NdcSxFdkInfoEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NdcSxFdkInfoEntryController {

	@Autowired
	private NdcSxFdkInfoEntryService ndcSxFdkInfoEntryService;

	@PostMapping("/ndcSxFdkInfoEntry")
	public void saveNdcSxFdkInfoEntry(@RequestBody NdcSxFdkInfoEntry ndcSxFdkInfoEntry) {
		ndcSxFdkInfoEntryService.saveNewNdcSxFdkInfoEntry(ndcSxFdkInfoEntry);
	}

	@GetMapping("/ndcSxFdkInfoEntry")
	public List<NdcSxFdkInfoEntry> getAllNdcSxFdkInfoEntry() {
		return ndcSxFdkInfoEntryService.getAllNdcSxFdkInfoEntry();
	}

	@PutMapping("/ndcSxFdkInfoEntry/{_profileCode}/{_stateNumber}")
	public void updateNdcSxFdkInfoEntry(@RequestBody NdcSxFdkInfoEntry ndcSxFdkInfoEntry,
			@PathVariable Character _profileCode, @PathVariable Character _stateNumber) {
		NdcSxFdkInfoEntryId id = new NdcSxFdkInfoEntryId();
		id.setProfileCode(_profileCode);
		id.setStateNumber(_stateNumber);
		ndcSxFdkInfoEntry.setId(id);
		ndcSxFdkInfoEntryService.updateNdcSxFdkInfoEntry(ndcSxFdkInfoEntry, id);
	}

	@DeleteMapping("/ndcSxFdkInfoEntry")
	public String deleteNdcSxFdkInfoEntry(@RequestBody NdcSxFdkInfoEntryId id) {
		ndcSxFdkInfoEntryService.deleteNdcSxFdkInfoEntry(id);
		return "Deleted Successfully";
	}
}