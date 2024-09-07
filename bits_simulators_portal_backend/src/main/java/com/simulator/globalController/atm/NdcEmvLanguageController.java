package com.simulator.globalController.atm;

import com.simulator.entities.atm.NdcEmvLanguage;
import com.simulator.entities.atm.NdcEmvLanguageId;
import com.simulator.globalService.atm.NdcEmvLanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NdcEmvLanguageController {

	@Autowired
	private NdcEmvLanguageService ndcEmvLanguageService;

	@PostMapping("/ndcEmvLanguage")
	public void saveNdcEmvLanguage(@RequestBody NdcEmvLanguage ndcEmvLanguage) {
		ndcEmvLanguageService.saveNewNdcEmvLanguage(ndcEmvLanguage);
	}

	@GetMapping("/ndcEmvLanguage")
	public List<NdcEmvLanguage> getAllNdcEmvLanguage() {
		return ndcEmvLanguageService.getAllNdcEmvLanguage();
	}

	@PutMapping("/ndcEmvLanguage/{_emvProfile}/{_languageCode}")
	public void updateNdcEmvLanguage(@RequestBody NdcEmvLanguage ndcEmvLanguage, @PathVariable String _emvProfile,
			@PathVariable String _languageCode) {
		NdcEmvLanguageId id = new NdcEmvLanguageId();
		id.setEmvProfile(_emvProfile);
		id.setLanguageCode(_languageCode);
		ndcEmvLanguage.setId(id);
		ndcEmvLanguageService.updateNdcEmvLanguage(ndcEmvLanguage, id);
	}

	@DeleteMapping("/ndcEmvLanguage")
	public String deleteNdcEmvLanguage(@RequestBody NdcEmvLanguageId id) {
		ndcEmvLanguageService.deleteNdcEmvLanguage(id);
		return "Deleted Successfully";
	}
}