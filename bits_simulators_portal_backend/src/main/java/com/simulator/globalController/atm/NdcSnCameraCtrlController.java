package com.simulator.globalController.atm;

import com.simulator.entities.atm.NdcSnCameraCtrl;
import com.simulator.entities.atm.NdcSnCameraCtrlId;
import com.simulator.globalService.atm.NdcSnCameraCtrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NdcSnCameraCtrlController {

	@Autowired
	private NdcSnCameraCtrlService ndcSnCameraCtrlService;

	@PostMapping("/ndcSnCameraCtrl")
	public void saveNdcSnCameraCtrl(@RequestBody NdcSnCameraCtrl ndcSnCameraCtrl) {
		ndcSnCameraCtrlService.saveNewNdcSnCameraCtrl(ndcSnCameraCtrl);
	}

	@GetMapping("/ndcSnCameraCtrl")
	public List<NdcSnCameraCtrl> getAllNdcSnCameraCtrl() {
		return ndcSnCameraCtrlService.getAllNdcSnCameraCtrl();
	}

	@PutMapping("/ndcSnCameraCtrl/{_profileCode}/{_stateNumber}")
	public void updateNdcSnCameraCtrl(@RequestBody NdcSnCameraCtrl ndcSnCameraCtrl, @PathVariable String _profileCode,
			@PathVariable String _stateNumber) {
		NdcSnCameraCtrlId id = new NdcSnCameraCtrlId();
		id.setProfileCode(_profileCode);
		id.setStateNumber(_stateNumber);
		ndcSnCameraCtrl.setId(id);
		ndcSnCameraCtrlService.updateNdcSnCameraCtrl(ndcSnCameraCtrl, id);
	}

	@DeleteMapping("/ndcSnCameraCtrl")
	public String deleteNdcSnCameraCtrl(@RequestBody NdcSnCameraCtrlId id) {
		ndcSnCameraCtrlService.deleteNdcSnCameraCtrl(id);
		return "Deleted Successfully";
	}
}