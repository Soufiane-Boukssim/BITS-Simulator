package com.simulator.globalController.atm;

import com.simulator.entities.atm.NdcStateType;
import com.simulator.globalService.atm.NdcStateTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NdcStateTypeController {

	@Autowired
	private NdcStateTypeService ndcStateTypeService;

	@PostMapping("/ndcStateType")
	public void saveNdcStateType(@RequestBody NdcStateType ndcStateType) {
		ndcStateTypeService.saveNewNdcStateType(ndcStateType);
	}

	@GetMapping("/ndcStateType")
	public List<NdcStateType> getAllNdcStateType() {
		return ndcStateTypeService.getAllNdcStateType();
	}

	@PutMapping("/ndcStateType/{stateType}")
	public void updateNdcStateType(@RequestBody NdcStateType ndcStateType, @PathVariable Character stateType) {
		ndcStateTypeService.updateNdcStateType(ndcStateType, stateType);
	}

	@DeleteMapping("/ndcStateType")
	public String deleteNdcStateType(@RequestBody Character stateType) {
		ndcStateTypeService.deleteNdcStateType(stateType);
		return "Deleted Successfully";
	}
}