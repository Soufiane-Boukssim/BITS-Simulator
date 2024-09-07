package com.simulator.globalService.atm;

import com.simulator.entities.atm.NdcEmvTransaction;
import com.simulator.entities.atm.NdcEmvTransactionId;
import com.simulator.repository.atm.NdcEmvTransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NdcEmvTransactionService {

	@Autowired
	private NdcEmvTransactionRepository ndcEmvTransactionRepository;
	private static final Logger logger = LoggerFactory.getLogger(NdcEmvTransactionService.class);

	public void saveNewNdcEmvTransaction(NdcEmvTransaction ndcEmvTransaction) {
		logger.info("Starting saveNewNdcEmvTransaction method");
		ndcEmvTransactionRepository.save(ndcEmvTransaction);
		logger.info("Completed saveNewNdcEmvTransaction method");
	}

	public List<NdcEmvTransaction> getAllNdcEmvTransaction() {
		logger.info("Starting getAllNdcEmvTransaction method");
		List<NdcEmvTransaction> result = ndcEmvTransactionRepository.findAll();
		logger.info("Completed getAllNdcEmvTransaction method");
		return result;
	}

	public void updateNdcEmvTransaction(NdcEmvTransaction updatedNdcEmvTransaction, NdcEmvTransactionId id) {
		logger.info("Starting updateNdcEmvTransaction method");
		NdcEmvTransaction existingNdcEmvTransaction = ndcEmvTransactionRepository.findById(id).get();
		updatedNdcEmvTransaction.setId(id);
		existingNdcEmvTransaction = updatedNdcEmvTransaction;
		ndcEmvTransactionRepository.save(existingNdcEmvTransaction);
		logger.info("Completed updateNdcEmvTransaction method");
	}

	public void deleteNdcEmvTransaction(NdcEmvTransactionId id) {
		logger.info("Starting deleteNdcEmvTransaction method");
		ndcEmvTransactionRepository.deleteById(id);
		logger.info("Completed deleteNdcEmvTransaction method");
	}
}