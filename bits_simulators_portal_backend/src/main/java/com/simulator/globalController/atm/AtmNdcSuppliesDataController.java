package com.simulator.globalController.atm;

import com.simulator.entities.atm.AtmNdcSuppliesData;
import com.simulator.entities.atm.AtmNdcSuppliesDataId;
import com.simulator.globalService.atm.AtmNdcSuppliesDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;


@RestController
public class AtmNdcSuppliesDataController {

	@Autowired
	private AtmNdcSuppliesDataService atmNdcSuppliesDataService;

	@PostMapping("/atmNdcSuppliesData")
	public void saveAtmNdcSuppliesData(@RequestBody AtmNdcSuppliesData atmNdcSuppliesData) {
		atmNdcSuppliesDataService.saveNewAtmNdcSuppliesData(atmNdcSuppliesData);
	}

	@GetMapping("/atmNdcSuppliesData")
	public List<AtmNdcSuppliesData> getAllAtmNdcSuppliesData() {
		return atmNdcSuppliesDataService.getAllAtmNdcSuppliesData();
	}

	@PutMapping("/atmNdcSuppliesData/{_bankCode}/{_atmTerminalNumber}/{_suppliesTs}")
	public void updateAtmNdcSuppliesData(@RequestBody AtmNdcSuppliesData atmNdcSuppliesData,
			@PathVariable String _bankCode, @PathVariable String _atmTerminalNumber,
			@PathVariable Timestamp _suppliesTs) {
		AtmNdcSuppliesDataId id = new AtmNdcSuppliesDataId();
		id.setBankCode(_bankCode);
		id.setAtmTerminalNumber(_atmTerminalNumber);
		id.setSuppliesTs(_suppliesTs);
		atmNdcSuppliesData.setId(id);
		atmNdcSuppliesDataService.updateAtmNdcSuppliesData(atmNdcSuppliesData, id);
	}

	@DeleteMapping("/atmNdcSuppliesData")
	public String deleteAtmNdcSuppliesData(@RequestBody AtmNdcSuppliesDataId id) {
		atmNdcSuppliesDataService.deleteAtmNdcSuppliesData(id);
		return "Deleted Successfully";
	}
}