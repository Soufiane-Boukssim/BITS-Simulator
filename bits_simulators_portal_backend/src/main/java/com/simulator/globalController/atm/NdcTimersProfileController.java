package com.simulator.globalController.atm;

import com.simulator.entities.atm.NdcTimersProfile;
import com.simulator.globalService.atm.NdcTimersProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NdcTimersProfileController {

	@Autowired
	private NdcTimersProfileService ndcTimersProfileService;

	@PostMapping("/ndcTimersProfile")
	public void saveNdcTimersProfile(@RequestBody NdcTimersProfile ndcTimersProfile) {
		ndcTimersProfileService.saveNewNdcTimersProfile(ndcTimersProfile);
	}

	@GetMapping("/ndcTimersProfile")
	public List<NdcTimersProfile> getAllNdcTimersProfile() {
		return ndcTimersProfileService.getAllNdcTimersProfile();
	}

	@PutMapping("/ndcTimersProfile/{profileCode}")
	public void updateNdcTimersProfile(@RequestBody NdcTimersProfile ndcTimersProfile,
			@PathVariable Character profileCode) {
		ndcTimersProfileService.updateNdcTimersProfile(ndcTimersProfile, profileCode);
	}

	@DeleteMapping("/ndcTimersProfile")
	public String deleteNdcTimersProfile(@RequestBody Character profileCode) {
		ndcTimersProfileService.deleteNdcTimersProfile(profileCode);
		return "Deleted Successfully";
	}
}