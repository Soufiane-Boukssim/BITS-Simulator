package com.simulator.globalService.atm;

import com.simulator.entities.atm.NdcSvergCompleteIccInit;
import com.simulator.entities.atm.NdcSvergCompleteIccInitId;
import com.simulator.repository.atm.NdcSvergCompleteIccInitRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NdcSvergCompleteIccInitService {

	@Autowired
	private NdcSvergCompleteIccInitRepository ndcSvergCompleteIccInitRepository;
	private static final Logger logger = LoggerFactory.getLogger(NdcSvergCompleteIccInitService.class);

	public void saveNewNdcSvergCompleteIccInit(NdcSvergCompleteIccInit ndcSvergCompleteIccInit) {
		logger.info("Starting saveNewNdcSvergCompleteIccInit method");
		ndcSvergCompleteIccInitRepository.save(ndcSvergCompleteIccInit);
		logger.info("Completed saveNewNdcSvergCompleteIccInit method");
	}

	public List<NdcSvergCompleteIccInit> getAllNdcSvergCompleteIccInit() {
		logger.info("Starting getAllNdcSvergCompleteIccInit method");
		List<NdcSvergCompleteIccInit> result = ndcSvergCompleteIccInitRepository.findAll();
		logger.info("Completed getAllNdcSvergCompleteIccInit method");
		return result;
	}

	public void updateNdcSvergCompleteIccInit(NdcSvergCompleteIccInit updatedNdcSvergCompleteIccInit,
			NdcSvergCompleteIccInitId id) {
		logger.info("Starting updateNdcSvergCompleteIccInit method");
		NdcSvergCompleteIccInit existingNdcSvergCompleteIccInit = ndcSvergCompleteIccInitRepository.findById(id).get();
		updatedNdcSvergCompleteIccInit.setId(id);
		existingNdcSvergCompleteIccInit = updatedNdcSvergCompleteIccInit;
		ndcSvergCompleteIccInitRepository.save(existingNdcSvergCompleteIccInit);
		logger.info("Completed updateNdcSvergCompleteIccInit method");
	}

	public void deleteNdcSvergCompleteIccInit(NdcSvergCompleteIccInitId id) {
		logger.info("Starting deleteNdcSvergCompleteIccInit method");
		ndcSvergCompleteIccInitRepository.deleteById(id);
		logger.info("Completed deleteNdcSvergCompleteIccInit method");
	}
}