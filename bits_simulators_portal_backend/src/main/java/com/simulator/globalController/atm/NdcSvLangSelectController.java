package com.simulator.globalController.atm;

import com.simulator.entities.atm.NdcSvLangSelect;
import com.simulator.entities.atm.NdcSvLangSelectId;
import com.simulator.globalService.atm.NdcSvLangSelectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NdcSvLangSelectController {

	@Autowired
	private NdcSvLangSelectService ndcSvLangSelectService;

	@PostMapping("/ndcSvLangSelect")
	public void saveNdcSvLangSelect(@RequestBody NdcSvLangSelect ndcSvLangSelect) {
		ndcSvLangSelectService.saveNewNdcSvLangSelect(ndcSvLangSelect);
	}

	@GetMapping("/ndcSvLangSelect")
	public List<NdcSvLangSelect> getAllNdcSvLangSelect() {
		return ndcSvLangSelectService.getAllNdcSvLangSelect();
	}

	@PutMapping("/ndcSvLangSelect/{_profileCode}/{_stateNumber}")
	public void updateNdcSvLangSelect(@RequestBody NdcSvLangSelect ndcSvLangSelect,
			@PathVariable Character _profileCode, @PathVariable Character _stateNumber) {
		NdcSvLangSelectId id = new NdcSvLangSelectId();
		id.setProfileCode(_profileCode);
		id.setStateNumber(_stateNumber);
		ndcSvLangSelect.setId(id);
		ndcSvLangSelectService.updateNdcSvLangSelect(ndcSvLangSelect, id);
	}

	@DeleteMapping("/ndcSvLangSelect")
	public String deleteNdcSvLangSelect(@RequestBody NdcSvLangSelectId id) {
		ndcSvLangSelectService.deleteNdcSvLangSelect(id);
		return "Deleted Successfully";
	}
}