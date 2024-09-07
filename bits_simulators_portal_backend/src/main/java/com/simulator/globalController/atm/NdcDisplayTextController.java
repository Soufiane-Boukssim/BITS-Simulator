package com.simulator.globalController.atm;

import com.simulator.entities.atm.NdcDisplayText;
import com.simulator.entities.atm.NdcDisplayTextId;
import com.simulator.globalService.atm.NdcDisplayTextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NdcDisplayTextController {

	@Autowired
	private NdcDisplayTextService ndcDisplayTextService;

	@PostMapping("/ndcDisplayText")
	public void saveNdcDisplayText(@RequestBody NdcDisplayText ndcDisplayText) {
		ndcDisplayTextService.saveNewNdcDisplayText(ndcDisplayText);
	}

	@GetMapping("/ndcDisplayText")
	public List<NdcDisplayText> getAllNdcDisplayText() {
		return ndcDisplayTextService.getAllNdcDisplayText();
	}

	@PutMapping("/ndcDisplayText/{_profileCode}/{_screenNumber}")
	public void updateNdcDisplayText(@RequestBody NdcDisplayText ndcDisplayText, @PathVariable String _profileCode,
			@PathVariable String _screenNumber) {
		NdcDisplayTextId id = new NdcDisplayTextId();
		id.setProfileCode(_profileCode);
		id.setScreenNumber(_screenNumber);
		ndcDisplayText.setId(id);
		ndcDisplayTextService.updateNdcDisplayText(ndcDisplayText, id);
	}

	@DeleteMapping("/ndcDisplayText")
	public String deleteNdcDisplayText(@RequestBody NdcDisplayTextId id) {
		ndcDisplayTextService.deleteNdcDisplayText(id);
		return "Deleted Successfully";
	}
}