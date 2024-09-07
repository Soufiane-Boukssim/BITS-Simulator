package com.simulator.globalController.atm;

import com.simulator.entities.atm.NdcSpointBeginAppSelInit;
import com.simulator.entities.atm.NdcSpointBeginAppSelInitId;
import com.simulator.globalService.atm.NdcSpointBeginAppSelInitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NdcSpointBeginAppSelInitController {

	@Autowired
	private NdcSpointBeginAppSelInitService ndcSpointBeginAppSelInitService;

	@PostMapping("/ndcSpointBeginAppSelInit")
	public void saveNdcSpointBeginAppSelInit(@RequestBody NdcSpointBeginAppSelInit ndcSpointBeginAppSelInit) {
		ndcSpointBeginAppSelInitService.saveNewNdcSpointBeginAppSelInit(ndcSpointBeginAppSelInit);
	}

	@GetMapping("/ndcSpointBeginAppSelInit")
	public List<NdcSpointBeginAppSelInit> getAllNdcSpointBeginAppSelInit() {
		return ndcSpointBeginAppSelInitService.getAllNdcSpointBeginAppSelInit();
	}

	@PutMapping("/ndcSpointBeginAppSelInit/{_profileCode}/{_stateNumber}")
	public void updateNdcSpointBeginAppSelInit(@RequestBody NdcSpointBeginAppSelInit ndcSpointBeginAppSelInit,
			@PathVariable String _profileCode, @PathVariable String _stateNumber) {
		NdcSpointBeginAppSelInitId id = new NdcSpointBeginAppSelInitId();
		id.setProfileCode(_profileCode);
		id.setStateNumber(_stateNumber);
		ndcSpointBeginAppSelInit.setId(id);
		ndcSpointBeginAppSelInitService.updateNdcSpointBeginAppSelInit(ndcSpointBeginAppSelInit, id);
	}

	@DeleteMapping("/ndcSpointBeginAppSelInit")
	public String deleteNdcSpointBeginAppSelInit(@RequestBody NdcSpointBeginAppSelInitId id) {
		ndcSpointBeginAppSelInitService.deleteNdcSpointBeginAppSelInit(id);
		return "Deleted Successfully";
	}
}