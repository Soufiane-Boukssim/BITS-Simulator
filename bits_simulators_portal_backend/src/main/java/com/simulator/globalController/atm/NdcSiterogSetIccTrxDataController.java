package com.simulator.globalController.atm;

import com.simulator.entities.atm.NdcSiterogSetIccTrxData;
import com.simulator.entities.atm.NdcSiterogSetIccTrxDataId;
import com.simulator.globalService.atm.NdcSiterogSetIccTrxDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NdcSiterogSetIccTrxDataController {

	@Autowired
	private NdcSiterogSetIccTrxDataService ndcSiterogSetIccTrxDataService;

	@PostMapping("/ndcSiterogSetIccTrxData")
	public void saveNdcSiterogSetIccTrxData(@RequestBody NdcSiterogSetIccTrxData ndcSiterogSetIccTrxData) {
		ndcSiterogSetIccTrxDataService.saveNewNdcSiterogSetIccTrxData(ndcSiterogSetIccTrxData);
	}

	@GetMapping("/ndcSiterogSetIccTrxData")
	public List<NdcSiterogSetIccTrxData> getAllNdcSiterogSetIccTrxData() {
		return ndcSiterogSetIccTrxDataService.getAllNdcSiterogSetIccTrxData();
	}

	@PutMapping("/ndcSiterogSetIccTrxData/{_profileCode}/{_stateNumber}")
	public void updateNdcSiterogSetIccTrxData(@RequestBody NdcSiterogSetIccTrxData ndcSiterogSetIccTrxData,
			@PathVariable String _profileCode, @PathVariable String _stateNumber) {
		NdcSiterogSetIccTrxDataId id = new NdcSiterogSetIccTrxDataId();
		id.setProfileCode(_profileCode);
		id.setStateNumber(_stateNumber);
		ndcSiterogSetIccTrxData.setId(id);
		ndcSiterogSetIccTrxDataService.updateNdcSiterogSetIccTrxData(ndcSiterogSetIccTrxData, id);
	}

	@DeleteMapping("/ndcSiterogSetIccTrxData")
	public String deleteNdcSiterogSetIccTrxData(@RequestBody NdcSiterogSetIccTrxDataId id) {
		ndcSiterogSetIccTrxDataService.deleteNdcSiterogSetIccTrxData(id);
		return "Deleted Successfully";
	}
}