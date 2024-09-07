package com.simulator.globalController.atm;

import com.simulator.entities.atm.NdcEmvCurrency;
import com.simulator.entities.atm.NdcEmvCurrencyId;
import com.simulator.globalService.atm.NdcEmvCurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NdcEmvCurrencyController {

	@Autowired
	private NdcEmvCurrencyService ndcEmvCurrencyService;

	@PostMapping("/ndcEmvCurrency")
	public void saveNdcEmvCurrency(@RequestBody NdcEmvCurrency ndcEmvCurrency) {
		ndcEmvCurrencyService.saveNewNdcEmvCurrency(ndcEmvCurrency);
	}

	@GetMapping("/ndcEmvCurrency")
	public List<NdcEmvCurrency> getAllNdcEmvCurrency() {
		return ndcEmvCurrencyService.getAllNdcEmvCurrency();
	}

	@PutMapping("/ndcEmvCurrency/{_emvProfile}/{_currencyCode}")
	public void updateNdcEmvCurrency(@RequestBody NdcEmvCurrency ndcEmvCurrency, @PathVariable String _emvProfile,
			@PathVariable String _currencyCode) {
		NdcEmvCurrencyId id = new NdcEmvCurrencyId();
		id.setEmvProfile(_emvProfile);
		id.setCurrencyCode(_currencyCode);
		ndcEmvCurrency.setId(id);
		ndcEmvCurrencyService.updateNdcEmvCurrency(ndcEmvCurrency, id);
	}

	@DeleteMapping("/ndcEmvCurrency")
	public String deleteNdcEmvCurrency(@RequestBody NdcEmvCurrencyId id) {
		ndcEmvCurrencyService.deleteNdcEmvCurrency(id);
		return "Deleted Successfully";
	}
}