package com.simulator.globalService.atm;

import com.simulator.entities.atm.NdcSpvergIccReInitialise;
import com.simulator.entities.atm.NdcSpvergIccReInitialiseId;
import com.simulator.repository.atm.NdcSpvergIccReInitialiseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NdcSpvergIccReInitialiseService {

	@Autowired
	private NdcSpvergIccReInitialiseRepository ndcSpvergIccReInitialiseRepository;
	private static final Logger logger = LoggerFactory.getLogger(NdcSpvergIccReInitialiseService.class);

	public void saveNewNdcSpvergIccReInitialise(NdcSpvergIccReInitialise ndcSpvergIccReInitialise) {
		logger.info("Starting saveNewNdcSpvergIccReInitialise method");
		ndcSpvergIccReInitialiseRepository.save(ndcSpvergIccReInitialise);
		logger.info("Completed saveNewNdcSpvergIccReInitialise method");
	}

	public List<NdcSpvergIccReInitialise> getAllNdcSpvergIccReInitialise() {
		logger.info("Starting getAllNdcSpvergIccReInitialise method");
		List<NdcSpvergIccReInitialise> result = ndcSpvergIccReInitialiseRepository.findAll();
		logger.info("Completed getAllNdcSpvergIccReInitialise method");
		return result;
	}

	public void updateNdcSpvergIccReInitialise(NdcSpvergIccReInitialise updatedNdcSpvergIccReInitialise,
			NdcSpvergIccReInitialiseId id) {
		logger.info("Starting updateNdcSpvergIccReInitialise method");
		NdcSpvergIccReInitialise existingNdcSpvergIccReInitialise = ndcSpvergIccReInitialiseRepository.findById(id)
				.get();
		updatedNdcSpvergIccReInitialise.setId(id);
		existingNdcSpvergIccReInitialise = updatedNdcSpvergIccReInitialise;
		ndcSpvergIccReInitialiseRepository.save(existingNdcSpvergIccReInitialise);
		logger.info("Completed updateNdcSpvergIccReInitialise method");
	}

	public void deleteNdcSpvergIccReInitialise(NdcSpvergIccReInitialiseId id) {
		logger.info("Starting deleteNdcSpvergIccReInitialise method");
		ndcSpvergIccReInitialiseRepository.deleteById(id);
		logger.info("Completed deleteNdcSpvergIccReInitialise method");
	}
}