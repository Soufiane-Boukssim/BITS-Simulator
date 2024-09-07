package com.simulator.globalController.atm;


import com.simulator.entities.atm.AtmNdcSuppliesDataLog;
import com.simulator.entities.atm.AtmNdcSuppliesDataLogId;
import com.simulator.globalService.atm.AtmNdcSuppliesDataLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
public class AtmNdcSuppliesDataLogController {

	@Autowired
	private AtmNdcSuppliesDataLogService atmNdcSuppliesDataLogService;

	@PostMapping("/atmNdcSuppliesDataLog")
	public void saveAtmNdcSuppliesDataLog(@RequestBody AtmNdcSuppliesDataLog atmNdcSuppliesDataLog) {
		atmNdcSuppliesDataLogService.saveNewAtmNdcSuppliesDataLog(atmNdcSuppliesDataLog);
	}

	@GetMapping("/atmNdcSuppliesDataLog")
	public List<AtmNdcSuppliesDataLog> getAllAtmNdcSuppliesDataLog() {
		return atmNdcSuppliesDataLogService.getAllAtmNdcSuppliesDataLog();
	}

	@PutMapping("/atmNdcSuppliesDataLog/{_bankCode}/{_atmTerminalNumber}/{_suppliesTs}")
	public void updateAtmNdcSuppliesDataLog(@RequestBody AtmNdcSuppliesDataLog atmNdcSuppliesDataLog,
			@PathVariable String _bankCode, @PathVariable String _atmTerminalNumber,
			@PathVariable Timestamp _suppliesTs) {
		AtmNdcSuppliesDataLogId id = new AtmNdcSuppliesDataLogId();
		id.setBankCode(_bankCode);
		id.setAtmTerminalNumber(_atmTerminalNumber);
		id.setSuppliesTs(_suppliesTs);
		atmNdcSuppliesDataLog.setId(id);
		atmNdcSuppliesDataLogService.updateAtmNdcSuppliesDataLog(atmNdcSuppliesDataLog, id);
	}

	@DeleteMapping("/atmNdcSuppliesDataLog")
	public String deleteAtmNdcSuppliesDataLog(@RequestBody AtmNdcSuppliesDataLogId id) {
		atmNdcSuppliesDataLogService.deleteAtmNdcSuppliesDataLog(id);
		return "Deleted Successfully";
	}
}