package com.simulator.globalService.atm;

import com.simulator.entities.atm.NdcSkSmartFitCheck;
import com.simulator.entities.atm.NdcSkSmartFitCheckId;
import com.simulator.repository.atm.NdcSkSmartFitCheckRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NdcSkSmartFitCheckService {

	@Autowired
	private NdcSkSmartFitCheckRepository ndcSkSmartFitCheckRepository;
	private static final Logger logger = LoggerFactory.getLogger(NdcSkSmartFitCheckService.class);

	public void saveNewNdcSkSmartFitCheck(NdcSkSmartFitCheck ndcSkSmartFitCheck) {
		logger.info("Starting saveNewNdcSkSmartFitCheck method");
		ndcSkSmartFitCheckRepository.save(ndcSkSmartFitCheck);
		logger.info("Completed saveNewNdcSkSmartFitCheck method");
	}

	public List<NdcSkSmartFitCheck> getAllNdcSkSmartFitCheck() {
		logger.info("Starting getAllNdcSkSmartFitCheck method");
		List<NdcSkSmartFitCheck> result = ndcSkSmartFitCheckRepository.findAll();
		logger.info("Completed getAllNdcSkSmartFitCheck method");
		return result;
	}

	public void updateNdcSkSmartFitCheck(NdcSkSmartFitCheck updatedNdcSkSmartFitCheck, NdcSkSmartFitCheckId id) {
		logger.info("Starting updateNdcSkSmartFitCheck method");
		NdcSkSmartFitCheck existingNdcSkSmartFitCheck = ndcSkSmartFitCheckRepository.findById(id).get();
		updatedNdcSkSmartFitCheck.setId(id);
		existingNdcSkSmartFitCheck = updatedNdcSkSmartFitCheck;
		ndcSkSmartFitCheckRepository.save(existingNdcSkSmartFitCheck);
		logger.info("Completed updateNdcSkSmartFitCheck method");
	}

	public void deleteNdcSkSmartFitCheck(NdcSkSmartFitCheckId id) {
		logger.info("Starting deleteNdcSkSmartFitCheck method");
		ndcSkSmartFitCheckRepository.deleteById(id);
		logger.info("Completed deleteNdcSkSmartFitCheck method");
	}
}