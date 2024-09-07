package com.simulator.globalService.atm;

import com.simulator.entities.atm.NdcState;
import com.simulator.entities.atm.NdcStateId;
import com.simulator.repository.atm.NdcStateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NdcStateService {

	@Autowired
	private NdcStateRepository ndcStateRepository;
	private static final Logger logger = LoggerFactory.getLogger(NdcStateService.class);

	public void saveNewNdcState(NdcState ndcState) {
		logger.info("Starting saveNewNdcState method");
		ndcStateRepository.save(ndcState);
		logger.info("Completed saveNewNdcState method");
	}

	public List<NdcState> getAllNdcState() {
		logger.info("Starting getAllNdcState method");
		List<NdcState> result = ndcStateRepository.findAll();
		logger.info("Completed getAllNdcState method");
		return result;
	}

	public void updateNdcState(NdcState updatedNdcState, NdcStateId id) {
		logger.info("Starting updateNdcState method");
		NdcState existingNdcState = ndcStateRepository.findById(id).get();
		updatedNdcState.setId(id);
		existingNdcState = updatedNdcState;
		ndcStateRepository.save(existingNdcState);
		logger.info("Completed updateNdcState method");
	}

	public void deleteNdcState(NdcStateId id) {
		logger.info("Starting deleteNdcState method");
		ndcStateRepository.deleteById(id);
		logger.info("Completed deleteNdcState method");
	}
}