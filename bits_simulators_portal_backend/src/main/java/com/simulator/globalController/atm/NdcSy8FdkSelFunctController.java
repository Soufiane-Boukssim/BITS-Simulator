package com.simulator.globalController.atm;

import com.simulator.entities.atm.NdcSy8FdkSelFunct;
import com.simulator.entities.atm.NdcSy8FdkSelFunctId;
import com.simulator.globalService.atm.NdcSy8FdkSelFunctService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NdcSy8FdkSelFunctController {

	@Autowired
	private NdcSy8FdkSelFunctService ndcSy8FdkSelFunctService;

	@PostMapping("/ndcSy8FdkSelFunct")
	public void saveNdcSy8FdkSelFunct(@RequestBody NdcSy8FdkSelFunct ndcSy8FdkSelFunct) {
		ndcSy8FdkSelFunctService.saveNewNdcSy8FdkSelFunct(ndcSy8FdkSelFunct);
	}

	@GetMapping("/ndcSy8FdkSelFunct")
	public List<NdcSy8FdkSelFunct> getAllNdcSy8FdkSelFunct() {
		return ndcSy8FdkSelFunctService.getAllNdcSy8FdkSelFunct();
	}

	@PutMapping("/ndcSy8FdkSelFunct/{_profileCode}/{_stateNumber}")
	public void updateNdcSy8FdkSelFunct(@RequestBody NdcSy8FdkSelFunct ndcSy8FdkSelFunct,
			@PathVariable Character _profileCode, @PathVariable Character _stateNumber) {
		NdcSy8FdkSelFunctId id = new NdcSy8FdkSelFunctId();
		id.setProfileCode(_profileCode);
		id.setStateNumber(_stateNumber);
		ndcSy8FdkSelFunct.setId(id);
		ndcSy8FdkSelFunctService.updateNdcSy8FdkSelFunct(ndcSy8FdkSelFunct, id);
	}

	@DeleteMapping("/ndcSy8FdkSelFunct")
	public String deleteNdcSy8FdkSelFunct(@RequestBody NdcSy8FdkSelFunctId id) {
		ndcSy8FdkSelFunctService.deleteNdcSy8FdkSelFunct(id);
		return "Deleted Successfully";
	}
}