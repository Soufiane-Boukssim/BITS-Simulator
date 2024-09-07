package com.simulator.globalController.atm;

import com.simulator.entities.atm.ATMNdcFitnessData;
import com.simulator.entities.atm.ATMNdcFitnessDataId;
import com.simulator.globalService.atm.ATMNdcFitnessDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
public class ATMNdcFitnessDataController {

	@Autowired
	private ATMNdcFitnessDataService aTMNdcFitnessDataService;

	@PostMapping("/aTMNdcFitnessData")
	public void saveATMNdcFitnessData(@RequestBody ATMNdcFitnessData aTMNdcFitnessData) {
		aTMNdcFitnessDataService.saveNewATMNdcFitnessData(aTMNdcFitnessData);
	}

	@GetMapping("/aTMNdcFitnessData")
	public List<ATMNdcFitnessData> getAllATMNdcFitnessData() {
		return aTMNdcFitnessDataService.getAllATMNdcFitnessData();
	}

	@PutMapping("/aTMNdcFitnessData/{_bankCode}/{_atmTerminalNumber}/{_fitnessTs}")
	public void updateATMNdcFitnessData(@RequestBody ATMNdcFitnessData aTMNdcFitnessData,
			@PathVariable String _bankCode, @PathVariable String _atmTerminalNumber,
			@PathVariable Timestamp _fitnessTs) {
		ATMNdcFitnessDataId id = new ATMNdcFitnessDataId();
		id.setBankCode(_bankCode);
		id.setAtmTerminalNumber(_atmTerminalNumber);
		id.setFitnessTs(_fitnessTs);
		aTMNdcFitnessData.setId(id);
		aTMNdcFitnessDataService.updateATMNdcFitnessData(aTMNdcFitnessData, id);
	}

	@DeleteMapping("/aTMNdcFitnessData")
	public String deleteATMNdcFitnessData(@RequestBody ATMNdcFitnessDataId id) {
		aTMNdcFitnessDataService.deleteATMNdcFitnessData(id);
		return "Deleted Successfully";
	}
}