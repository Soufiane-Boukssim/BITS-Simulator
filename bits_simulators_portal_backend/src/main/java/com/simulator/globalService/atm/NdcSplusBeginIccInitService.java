package com.simulator.globalService.atm;

import com.simulator.entities.atm.NdcSplusBeginIccInit;
import com.simulator.entities.atm.NdcSplusBeginIccInitId;
import com.simulator.repository.atm.NdcSplusBeginIccInitRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NdcSplusBeginIccInitService {

	@Autowired
	private NdcSplusBeginIccInitRepository ndcSplusBeginIccInitRepository;
	private static final Logger logger = LoggerFactory.getLogger(NdcSplusBeginIccInitService.class);

	public void saveNewNdcSplusBeginIccInit(NdcSplusBeginIccInit ndcSplusBeginIccInit) {
		logger.info("Starting saveNewNdcSplusBeginIccInit method");
		ndcSplusBeginIccInitRepository.save(ndcSplusBeginIccInit);
		logger.info("Completed saveNewNdcSplusBeginIccInit method");
	}

	public List<NdcSplusBeginIccInit> getAllNdcSplusBeginIccInit() {
		logger.info("Starting getAllNdcSplusBeginIccInit method");
		List<NdcSplusBeginIccInit> result = ndcSplusBeginIccInitRepository.findAll();
		logger.info("Completed getAllNdcSplusBeginIccInit method");
		return result;
	}

	public void updateNdcSplusBeginIccInit(NdcSplusBeginIccInit updatedNdcSplusBeginIccInit,
			NdcSplusBeginIccInitId id) {
		logger.info("Starting updateNdcSplusBeginIccInit method");
		NdcSplusBeginIccInit existingNdcSplusBeginIccInit = ndcSplusBeginIccInitRepository.findById(id).get();
		updatedNdcSplusBeginIccInit.setId(id);
		existingNdcSplusBeginIccInit = updatedNdcSplusBeginIccInit;
		ndcSplusBeginIccInitRepository.save(existingNdcSplusBeginIccInit);
		logger.info("Completed updateNdcSplusBeginIccInit method");
	}

	public void deleteNdcSplusBeginIccInit(NdcSplusBeginIccInitId id) {
		logger.info("Starting deleteNdcSplusBeginIccInit method");
		ndcSplusBeginIccInitRepository.deleteById(id);
		logger.info("Completed deleteNdcSplusBeginIccInit method");
	}
}