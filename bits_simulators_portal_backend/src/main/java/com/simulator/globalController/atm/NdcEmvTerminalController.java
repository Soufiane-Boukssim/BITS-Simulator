package com.simulator.globalController.atm;

import com.simulator.entities.atm.NdcEmvTerminal;
import com.simulator.entities.atm.NdcEmvTerminalId;
import com.simulator.globalService.atm.NdcEmvTerminalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NdcEmvTerminalController {

	@Autowired
	private NdcEmvTerminalService ndcEmvTerminalService;

	@PostMapping("/ndcEmvTerminal")
	public void saveNdcEmvTerminal(@RequestBody NdcEmvTerminal ndcEmvTerminal) {
		ndcEmvTerminalService.saveNewNdcEmvTerminal(ndcEmvTerminal);
	}

	@GetMapping("/ndcEmvTerminal")
	public List<NdcEmvTerminal> getAllNdcEmvTerminal() {
		return ndcEmvTerminalService.getAllNdcEmvTerminal();
	}

	@PutMapping("/ndcEmvTerminal/{_emvProfile}/{_termCountryCode}")
	public void updateNdcEmvTerminal(@RequestBody NdcEmvTerminal ndcEmvTerminal, @PathVariable String _emvProfile,
			@PathVariable String _termCountryCode) {
		NdcEmvTerminalId id = new NdcEmvTerminalId();
		id.setEmvProfile(_emvProfile);
		id.setTermCountryCode(_termCountryCode);
		ndcEmvTerminal.setId(id);
		ndcEmvTerminalService.updateNdcEmvTerminal(ndcEmvTerminal, id);
	}

	@DeleteMapping("/ndcEmvTerminal")
	public String deleteNdcEmvTerminal(@RequestBody NdcEmvTerminalId id) {
		ndcEmvTerminalService.deleteNdcEmvTerminal(id);
		return "Deleted Successfully";
	}
}