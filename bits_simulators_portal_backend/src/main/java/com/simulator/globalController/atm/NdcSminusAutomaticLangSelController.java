package com.simulator.globalController.atm;

import com.simulator.entities.atm.NdcSminusAutomaticLangSel;
import com.simulator.entities.atm.NdcSminusAutomaticLangSelId;
import com.simulator.globalService.atm.NdcSminusAutomaticLangSelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NdcSminusAutomaticLangSelController {

	@Autowired
	private NdcSminusAutomaticLangSelService ndcSminusAutomaticLangSelService;

	@PostMapping("/ndcSminusAutomaticLangSel")
	public void saveNdcSminusAutomaticLangSel(@RequestBody NdcSminusAutomaticLangSel ndcSminusAutomaticLangSel) {
		ndcSminusAutomaticLangSelService.saveNewNdcSminusAutomaticLangSel(ndcSminusAutomaticLangSel);
	}

	@GetMapping("/ndcSminusAutomaticLangSel")
	public List<NdcSminusAutomaticLangSel> getAllNdcSminusAutomaticLangSel() {
		return ndcSminusAutomaticLangSelService.getAllNdcSminusAutomaticLangSel();
	}

	@PutMapping("/ndcSminusAutomaticLangSel/{_profileCode}/{_stateNumber}")
	public void updateNdcSminusAutomaticLangSel(@RequestBody NdcSminusAutomaticLangSel ndcSminusAutomaticLangSel,
			@PathVariable String _profileCode, @PathVariable String _stateNumber) {
		NdcSminusAutomaticLangSelId id = new NdcSminusAutomaticLangSelId();
		id.setProfileCode(_profileCode);
		id.setStateNumber(_stateNumber);
		ndcSminusAutomaticLangSel.setId(id);
		ndcSminusAutomaticLangSelService.updateNdcSminusAutomaticLangSel(ndcSminusAutomaticLangSel, id);
	}

	@DeleteMapping("/ndcSminusAutomaticLangSel")
	public String deleteNdcSminusAutomaticLangSel(@RequestBody NdcSminusAutomaticLangSelId id) {
		ndcSminusAutomaticLangSelService.deleteNdcSminusAutomaticLangSel(id);
		return "Deleted Successfully";
	}
}