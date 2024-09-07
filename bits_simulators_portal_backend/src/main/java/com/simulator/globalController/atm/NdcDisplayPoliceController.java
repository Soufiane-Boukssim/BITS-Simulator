package com.simulator.globalController.atm;

import com.simulator.entities.atm.NdcDisplayPolice;
import com.simulator.globalService.atm.NdcDisplayPoliceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NdcDisplayPoliceController {

	@Autowired
	private NdcDisplayPoliceService ndcDisplayPoliceService;

	@PostMapping("/ndcDisplayPolice")
	public void saveNdcDisplayPolice(@RequestBody NdcDisplayPolice ndcDisplayPolice) {
		ndcDisplayPoliceService.saveNewNdcDisplayPolice(ndcDisplayPolice);
	}

	@GetMapping("/ndcDisplayPolice")
	public List<NdcDisplayPolice> getAllNdcDisplayPolice() {
		return ndcDisplayPoliceService.getAllNdcDisplayPolice();
	}

	@PutMapping("/ndcDisplayPolice/{policeCode}")
	public void updateNdcDisplayPolice(@RequestBody NdcDisplayPolice ndcDisplayPolice,
			@PathVariable String policeCode) {
		ndcDisplayPoliceService.updateNdcDisplayPolice(ndcDisplayPolice, policeCode);
	}

	@DeleteMapping("/ndcDisplayPolice")
	public String deleteNdcDisplayPolice(@RequestBody String policeCode) {
		ndcDisplayPoliceService.deleteNdcDisplayPolice(policeCode);
		return "Deleted Successfully";
	}
}