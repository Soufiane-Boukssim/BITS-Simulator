package com.simulator.globalController.atm;

import com.simulator.entities.atm.NdcDisplayAnimed;
import com.simulator.entities.atm.NdcDisplayAnimedId;
import com.simulator.globalService.atm.NdcDisplayAnimedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NdcDisplayAnimedController {

	@Autowired
	private NdcDisplayAnimedService ndcDisplayAnimedService;

	@PostMapping("/ndcDisplayAnimed")
	public void saveNdcDisplayAnimed(@RequestBody NdcDisplayAnimed ndcDisplayAnimed) {
		ndcDisplayAnimedService.saveNewNdcDisplayAnimed(ndcDisplayAnimed);
	}

	@GetMapping("/ndcDisplayAnimed")
	public List<NdcDisplayAnimed> getAllNdcDisplayAnimed() {
		return ndcDisplayAnimedService.getAllNdcDisplayAnimed();
	}

	@PutMapping("/ndcDisplayAnimed/{_profileCode}/{_animedScreenNumber}")
	public void updateNdcDisplayAnimed(@RequestBody NdcDisplayAnimed ndcDisplayAnimed,
			@PathVariable String _profileCode, @PathVariable String _animedScreenNumber) {
		NdcDisplayAnimedId id = new NdcDisplayAnimedId();
		id.setProfileCode(_profileCode);
		id.setAnimedScreenNumber(_animedScreenNumber);
		ndcDisplayAnimed.setId(id);
		ndcDisplayAnimedService.updateNdcDisplayAnimed(ndcDisplayAnimed, id);
	}

	@DeleteMapping("/ndcDisplayAnimed")
	public String deleteNdcDisplayAnimed(@RequestBody NdcDisplayAnimedId id) {
		ndcDisplayAnimedService.deleteNdcDisplayAnimed(id);
		return "Deleted Successfully";
	}
}