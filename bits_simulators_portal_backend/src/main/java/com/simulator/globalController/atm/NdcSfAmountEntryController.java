package com.simulator.globalController.atm;

import com.simulator.entities.atm.NdcSfAmountEntry;
import com.simulator.entities.atm.NdcSfAmountEntryId;
import com.simulator.globalService.atm.NdcSfAmountEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NdcSfAmountEntryController {

	@Autowired
	private NdcSfAmountEntryService ndcSfAmountEntryService;

	@PostMapping("/ndcSfAmountEntry")
	public void saveNdcSfAmountEntry(@RequestBody NdcSfAmountEntry ndcSfAmountEntry) {
		ndcSfAmountEntryService.saveNewNdcSfAmountEntry(ndcSfAmountEntry);
	}

	@GetMapping("/ndcSfAmountEntry")
	public List<NdcSfAmountEntry> getAllNdcSfAmountEntry() {
		return ndcSfAmountEntryService.getAllNdcSfAmountEntry();
	}

	@PutMapping("/ndcSfAmountEntry/{_profileCode}/{_stateNumber}")
	public void updateNdcSfAmountEntry(@RequestBody NdcSfAmountEntry ndcSfAmountEntry,
			@PathVariable String _profileCode, @PathVariable String _stateNumber) {
		NdcSfAmountEntryId id = new NdcSfAmountEntryId();
		id.setProfileCode(_profileCode);
		id.setStateNumber(_stateNumber);
		ndcSfAmountEntry.setId(id);
		ndcSfAmountEntryService.updateNdcSfAmountEntry(ndcSfAmountEntry, id);
	}

	@DeleteMapping("/ndcSfAmountEntry")
	public String deleteNdcSfAmountEntry(@RequestBody NdcSfAmountEntryId id) {
		ndcSfAmountEntryService.deleteNdcSfAmountEntry(id);
		return "Deleted Successfully";
	}
}