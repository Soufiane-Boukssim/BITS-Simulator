package com.simulator.globalService.atm;

import com.simulator.entities.atm.NdcFit;
import com.simulator.entities.atm.NdcFitId;
import com.simulator.repository.atm.NdcFitRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NdcFitService {

	@Autowired
	private NdcFitRepository ndcFitRepository;
	private static final Logger logger = LoggerFactory.getLogger(NdcFitService.class);

	public void saveNewNdcFit(NdcFit ndcFit) {
		logger.info("Starting saveNewNdcFit method");
		ndcFitRepository.save(ndcFit);
		logger.info("Completed saveNewNdcFit method");
	}

	public List<NdcFit> getAllNdcFit() {
		logger.info("Starting getAllNdcFit method");
		List<NdcFit> result = ndcFitRepository.findAll();
		logger.info("Completed getAllNdcFit method");
		return result;
	}

	public void updateNdcFit(NdcFit updatedNdcFit, NdcFitId id) {
		logger.info("Starting updateNdcFit method");
		NdcFit existingNdcFit = ndcFitRepository.findById(id).get();
		updatedNdcFit.setId(id);
		existingNdcFit = updatedNdcFit;
		ndcFitRepository.save(existingNdcFit);
		logger.info("Completed updateNdcFit method");
	}

	public void deleteNdcFit(NdcFitId id) {
		logger.info("Starting deleteNdcFit method");
		ndcFitRepository.deleteById(id);
		logger.info("Completed deleteNdcFit method");
	}
}