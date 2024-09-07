package com.simulator.globalController.atm;

import com.simulator.entities.atm.AtmNdcFitnessDataLog;
import com.simulator.globalService.atm.AtmNdcFitnessDataLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AtmNdcFitnessDataLogController {

	@Autowired
	private AtmNdcFitnessDataLogService atmNdcFitnessDataLogService;

	@PostMapping("/atmNdcFitnessDataLog")
	public void saveAtmNdcFitnessDataLog(@RequestBody AtmNdcFitnessDataLog atmNdcFitnessDataLog) {
		atmNdcFitnessDataLogService.saveNewAtmNdcFitnessDataLog(atmNdcFitnessDataLog);
	}

	@GetMapping("/atmNdcFitnessDataLog")
	public List<AtmNdcFitnessDataLog> getAllAtmNdcFitnessDataLog() {
		return atmNdcFitnessDataLogService.getAllAtmNdcFitnessDataLog();
	}

	@PutMapping("/atmNdcFitnessDataLog/{bankCode}")
	public void updateAtmNdcFitnessDataLog(@RequestBody AtmNdcFitnessDataLog atmNdcFitnessDataLog,
			@PathVariable String bankCode) {
		atmNdcFitnessDataLogService.updateAtmNdcFitnessDataLog(atmNdcFitnessDataLog, bankCode);
	}

	@DeleteMapping("/atmNdcFitnessDataLog")
	public String deleteAtmNdcFitnessDataLog(@RequestBody String bankCode) {
		atmNdcFitnessDataLogService.deleteAtmNdcFitnessDataLog(bankCode);
		return "Deleted Successfully";
	}
}