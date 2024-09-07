package com.simulator.globalController.atm;

import com.simulator.entities.atm.NdcShInfoEntry;
import com.simulator.entities.atm.NdcShInfoEntryId;
import com.simulator.globalService.atm.NdcShInfoEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NdcShInfoEntryController {

	@Autowired
	private NdcShInfoEntryService ndcShInfoEntryService;

	@PostMapping("/ndcShInfoEntry")
	public void saveNdcShInfoEntry(@RequestBody NdcShInfoEntry ndcShInfoEntry) {
		ndcShInfoEntryService.saveNewNdcShInfoEntry(ndcShInfoEntry);
	}

	@GetMapping("/ndcShInfoEntry")
	public List<NdcShInfoEntry> getAllNdcShInfoEntry() {
		return ndcShInfoEntryService.getAllNdcShInfoEntry();
	}

	@PutMapping("/ndcShInfoEntry/{_profileCode}/{_stateNumber}")
	public void updateNdcShInfoEntry(@RequestBody NdcShInfoEntry ndcShInfoEntry, @PathVariable String _profileCode,
			@PathVariable String _stateNumber) {
		NdcShInfoEntryId id = new NdcShInfoEntryId();
		id.setProfileCode(_profileCode);
		id.setStateNumber(_stateNumber);
		ndcShInfoEntry.setId(id);
		ndcShInfoEntryService.updateNdcShInfoEntry(ndcShInfoEntry, id);
	}

	@DeleteMapping("/ndcShInfoEntry")
	public String deleteNdcShInfoEntry(@RequestBody NdcShInfoEntryId id) {
		ndcShInfoEntryService.deleteNdcShInfoEntry(id);
		return "Deleted Successfully";
	}
}