package com.simulator.globalController.atm;

import com.simulator.entities.atm.NdcEmvTransaction;
import com.simulator.entities.atm.NdcEmvTransactionId;
import com.simulator.globalService.atm.NdcEmvTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NdcEmvTransactionController {

	@Autowired
	private NdcEmvTransactionService ndcEmvTransactionService;

	@PostMapping("/ndcEmvTransaction")
	public void saveNdcEmvTransaction(@RequestBody NdcEmvTransaction ndcEmvTransaction) {
		ndcEmvTransactionService.saveNewNdcEmvTransaction(ndcEmvTransaction);
	}

	@GetMapping("/ndcEmvTransaction")
	public List<NdcEmvTransaction> getAllNdcEmvTransaction() {
		return ndcEmvTransactionService.getAllNdcEmvTransaction();
	}

	@PutMapping("/ndcEmvTransaction/{_emvProfile}/{_transactionType}")
	public void updateNdcEmvTransaction(@RequestBody NdcEmvTransaction ndcEmvTransaction,
			@PathVariable String _emvProfile, @PathVariable String _transactionType) {
		NdcEmvTransactionId id = new NdcEmvTransactionId();
		id.setEmvProfile(_emvProfile);
		id.setTransactionType(_transactionType);
		ndcEmvTransaction.setId(id);
		ndcEmvTransactionService.updateNdcEmvTransaction(ndcEmvTransaction, id);
	}

	@DeleteMapping("/ndcEmvTransaction")
	public String deleteNdcEmvTransaction(@RequestBody NdcEmvTransactionId id) {
		ndcEmvTransactionService.deleteNdcEmvTransaction(id);
		return "Deleted Successfully";
	}
}