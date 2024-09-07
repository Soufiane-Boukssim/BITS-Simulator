package com.simulator.globalService.atm;

import com.simulator.entities.atm.NdcSgAmountCheck;
import com.simulator.entities.atm.NdcSgAmountCheckId;
import com.simulator.repository.atm.NdcSgAmountCheckRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NdcSgAmountCheckService {

	@Autowired
	private NdcSgAmountCheckRepository ndcSgAmountCheckRepository;
	private static final Logger logger = LoggerFactory.getLogger(NdcSgAmountCheckService.class);

	public void saveNewNdcSgAmountCheck(NdcSgAmountCheck ndcSgAmountCheck) {
		logger.info("Starting saveNewNdcSgAmountCheck method");
		ndcSgAmountCheckRepository.save(ndcSgAmountCheck);
		logger.info("Completed saveNewNdcSgAmountCheck method");
	}

	public List<NdcSgAmountCheck> getAllNdcSgAmountCheck() {
		logger.info("Starting getAllNdcSgAmountCheck method");
		List<NdcSgAmountCheck> result = ndcSgAmountCheckRepository.findAll();
		logger.info("Completed getAllNdcSgAmountCheck method");
		return result;
	}

	public void updateNdcSgAmountCheck(NdcSgAmountCheck updatedNdcSgAmountCheck, NdcSgAmountCheckId id) {
		logger.info("Starting updateNdcSgAmountCheck method");
		NdcSgAmountCheck existingNdcSgAmountCheck = ndcSgAmountCheckRepository.findById(id).get();
		updatedNdcSgAmountCheck.setId(id);
		existingNdcSgAmountCheck = updatedNdcSgAmountCheck;
		ndcSgAmountCheckRepository.save(existingNdcSgAmountCheck);
		logger.info("Completed updateNdcSgAmountCheck method");
	}

	public void deleteNdcSgAmountCheck(NdcSgAmountCheckId id) {
		logger.info("Starting deleteNdcSgAmountCheck method");
		ndcSgAmountCheckRepository.deleteById(id);
		logger.info("Completed deleteNdcSgAmountCheck method");
	}
}