package com.simulator.globalService.atm;

import com.simulator.entities.atm.NdcSmEnhancedPinEnter;
import com.simulator.entities.atm.NdcSmEnhancedPinEnterId;
import com.simulator.repository.atm.NdcSmEnhancedPinEnterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NdcSmEnhancedPinEnterService {

	@Autowired
	private NdcSmEnhancedPinEnterRepository ndcSmEnhancedPinEnterRepository;
	private static final Logger logger = LoggerFactory.getLogger(NdcSmEnhancedPinEnterService.class);

	public void saveNewNdcSmEnhancedPinEnter(NdcSmEnhancedPinEnter ndcSmEnhancedPinEnter) {
		logger.info("Starting saveNewNdcSmEnhancedPinEnter method");
		ndcSmEnhancedPinEnterRepository.save(ndcSmEnhancedPinEnter);
		logger.info("Completed saveNewNdcSmEnhancedPinEnter method");
	}

	public List<NdcSmEnhancedPinEnter> getAllNdcSmEnhancedPinEnter() {
		logger.info("Starting getAllNdcSmEnhancedPinEnter method");
		List<NdcSmEnhancedPinEnter> result = ndcSmEnhancedPinEnterRepository.findAll();
		logger.info("Completed getAllNdcSmEnhancedPinEnter method");
		return result;
	}

	public void updateNdcSmEnhancedPinEnter(NdcSmEnhancedPinEnter updatedNdcSmEnhancedPinEnter,
			NdcSmEnhancedPinEnterId id) {
		logger.info("Starting updateNdcSmEnhancedPinEnter method");
		NdcSmEnhancedPinEnter existingNdcSmEnhancedPinEnter = ndcSmEnhancedPinEnterRepository.findById(id).get();
		updatedNdcSmEnhancedPinEnter.setId(id);
		existingNdcSmEnhancedPinEnter = updatedNdcSmEnhancedPinEnter;
		ndcSmEnhancedPinEnterRepository.save(existingNdcSmEnhancedPinEnter);
		logger.info("Completed updateNdcSmEnhancedPinEnter method");
	}

	public void deleteNdcSmEnhancedPinEnter(NdcSmEnhancedPinEnterId id) {
		logger.info("Starting deleteNdcSmEnhancedPinEnter method");
		ndcSmEnhancedPinEnterRepository.deleteById(id);
		logger.info("Completed deleteNdcSmEnhancedPinEnter method");
	}
}