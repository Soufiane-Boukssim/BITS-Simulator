package com.simulator.globalController.atm;

import com.simulator.entities.atm.NdcSslachCpleteAppSelInit;
import com.simulator.entities.atm.NdcSslachCpleteAppSelInitId;
import com.simulator.globalService.atm.NdcSslachCpleteAppSelInitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NdcSslachCpleteAppSelInitController {

	@Autowired
	private NdcSslachCpleteAppSelInitService ndcSslachCpleteAppSelInitService;

	@PostMapping("/ndcSslachCpleteAppSelInit")
	public void saveNdcSslachCpleteAppSelInit(@RequestBody NdcSslachCpleteAppSelInit ndcSslachCpleteAppSelInit) {
		ndcSslachCpleteAppSelInitService.saveNewNdcSslachCpleteAppSelInit(ndcSslachCpleteAppSelInit);
	}

	@GetMapping("/ndcSslachCpleteAppSelInit")
	public List<NdcSslachCpleteAppSelInit> getAllNdcSslachCpleteAppSelInit() {
		return ndcSslachCpleteAppSelInitService.getAllNdcSslachCpleteAppSelInit();
	}

	@PutMapping("/ndcSslachCpleteAppSelInit/{_profileCode}/{_stateNumber}")
	public void updateNdcSslachCpleteAppSelInit(@RequestBody NdcSslachCpleteAppSelInit ndcSslachCpleteAppSelInit,
			@PathVariable String _profileCode, @PathVariable String _stateNumber) {
		NdcSslachCpleteAppSelInitId id = new NdcSslachCpleteAppSelInitId();
		id.setProfileCode(_profileCode);
		id.setStateNumber(_stateNumber);
		ndcSslachCpleteAppSelInit.setId(id);
		ndcSslachCpleteAppSelInitService.updateNdcSslachCpleteAppSelInit(ndcSslachCpleteAppSelInit, id);
	}

	@DeleteMapping("/ndcSslachCpleteAppSelInit")
	public String deleteNdcSslachCpleteAppSelInit(@RequestBody NdcSslachCpleteAppSelInitId id) {
		ndcSslachCpleteAppSelInitService.deleteNdcSslachCpleteAppSelInit(id);
		return "Deleted Successfully";
	}
}