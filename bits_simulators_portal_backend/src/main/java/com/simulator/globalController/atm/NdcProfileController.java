package com.simulator.globalController.atm;

import com.simulator.entities.atm.NdcProfile;
import com.simulator.globalService.atm.NdcProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NdcProfileController {

	@Autowired
	private NdcProfileService ndcProfileService;

	@PostMapping("/ndcProfile")
	public void saveNdcProfile(@RequestBody NdcProfile ndcProfile) {
		ndcProfileService.saveNewNdcProfile(ndcProfile);
	}

	@GetMapping("/ndcProfile")
	public List<NdcProfile> getAllNdcProfile() {
		return ndcProfileService.getAllNdcProfile();
	}

	@PutMapping("/ndcProfile/{profileCode}")
	public void updateNdcProfile(@RequestBody NdcProfile ndcProfile, @PathVariable String profileCode) {
		ndcProfileService.updateNdcProfile(ndcProfile, profileCode);
	}

	@DeleteMapping("/ndcProfile")
	public String deleteNdcProfile(@RequestBody String profileCode) {
		ndcProfileService.deleteNdcProfile(profileCode);
		return "Deleted Successfully";
	}
}