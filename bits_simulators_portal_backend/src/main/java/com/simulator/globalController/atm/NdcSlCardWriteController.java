package com.simulator.globalController.atm;

import com.simulator.entities.atm.NdcSlCardWrite;
import com.simulator.entities.atm.NdcSlCardWriteId;
import com.simulator.globalService.atm.NdcSlCardWriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NdcSlCardWriteController {

	@Autowired
	private NdcSlCardWriteService ndcSlCardWriteService;

	@PostMapping("/ndcSlCardWrite")
	public void saveNdcSlCardWrite(@RequestBody NdcSlCardWrite ndcSlCardWrite) {
		ndcSlCardWriteService.saveNewNdcSlCardWrite(ndcSlCardWrite);
	}

	@GetMapping("/ndcSlCardWrite")
	public List<NdcSlCardWrite> getAllNdcSlCardWrite() {
		return ndcSlCardWriteService.getAllNdcSlCardWrite();
	}

	@PutMapping("/ndcSlCardWrite/{_profileCode}/{_stateNumber}")
	public void updateNdcSlCardWrite(@RequestBody NdcSlCardWrite ndcSlCardWrite, @PathVariable String _profileCode,
			@PathVariable String _stateNumber) {
		NdcSlCardWriteId id = new NdcSlCardWriteId();
		id.setProfileCode(_profileCode);
		id.setStateNumber(_stateNumber);
		ndcSlCardWrite.setId(id);
		ndcSlCardWriteService.updateNdcSlCardWrite(ndcSlCardWrite, id);
	}

	@DeleteMapping("/ndcSlCardWrite")
	public String deleteNdcSlCardWrite(@RequestBody NdcSlCardWriteId id) {
		ndcSlCardWriteService.deleteNdcSlCardWrite(id);
		return "Deleted Successfully";
	}
}