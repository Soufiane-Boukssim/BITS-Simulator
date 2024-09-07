package com.simulator.globalController.atm;

import com.simulator.entities.atm.NdcSe4FdkSelection;
import com.simulator.entities.atm.NdcSe4FdkSelectionId;
import com.simulator.globalService.atm.NdcSe4FdkSelectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NdcSe4FdkSelectionController {

	@Autowired
	private NdcSe4FdkSelectionService ndcSe4FdkSelectionService;

	@PostMapping("/ndcSe4FdkSelection")
	public void saveNdcSe4FdkSelection(@RequestBody NdcSe4FdkSelection ndcSe4FdkSelection) {
		ndcSe4FdkSelectionService.saveNewNdcSe4FdkSelection(ndcSe4FdkSelection);
	}

	@GetMapping("/ndcSe4FdkSelection")
	public List<NdcSe4FdkSelection> getAllNdcSe4FdkSelection() {
		return ndcSe4FdkSelectionService.getAllNdcSe4FdkSelection();
	}

	@PutMapping("/ndcSe4FdkSelection/{_profileCode}/{_stateNumber}")
	public void updateNdcSe4FdkSelection(@RequestBody NdcSe4FdkSelection ndcSe4FdkSelection,
			@PathVariable String _profileCode, @PathVariable String _stateNumber) {
		NdcSe4FdkSelectionId id = new NdcSe4FdkSelectionId();
		id.setProfileCode(_profileCode);
		id.setStateNumber(_stateNumber);
		ndcSe4FdkSelection.setId(id);
		ndcSe4FdkSelectionService.updateNdcSe4FdkSelection(ndcSe4FdkSelection, id);
	}

	@DeleteMapping("/ndcSe4FdkSelection")
	public String deleteNdcSe4FdkSelection(@RequestBody NdcSe4FdkSelectionId id) {
		ndcSe4FdkSelectionService.deleteNdcSe4FdkSelection(id);
		return "Deleted Successfully";
	}
}