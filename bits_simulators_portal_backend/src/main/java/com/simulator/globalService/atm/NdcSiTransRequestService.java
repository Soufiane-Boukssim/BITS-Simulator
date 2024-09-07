package com.simulator.globalService.atm;

import com.simulator.entities.atm.NdcSiTransRequest;
import com.simulator.entities.atm.NdcSiTransRequestId;
import com.simulator.repository.atm.NdcSiTransRequestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NdcSiTransRequestService {

	@Autowired
	private NdcSiTransRequestRepository ndcSiTransRequestRepository;
	private static final Logger logger = LoggerFactory.getLogger(NdcSiTransRequestService.class);

	public void saveNewNdcSiTransRequest(NdcSiTransRequest ndcSiTransRequest) {
		logger.info("Starting saveNewNdcSiTransRequest method");
		ndcSiTransRequestRepository.save(ndcSiTransRequest);
		logger.info("Completed saveNewNdcSiTransRequest method");
	}

	public List<NdcSiTransRequest> getAllNdcSiTransRequest() {
		logger.info("Starting getAllNdcSiTransRequest method");
		List<NdcSiTransRequest> result = ndcSiTransRequestRepository.findAll();
		logger.info("Completed getAllNdcSiTransRequest method");
		return result;
	}

	public void updateNdcSiTransRequest(NdcSiTransRequest updatedNdcSiTransRequest, NdcSiTransRequestId id) {
		logger.info("Starting updateNdcSiTransRequest method");
		NdcSiTransRequest existingNdcSiTransRequest = ndcSiTransRequestRepository.findById(id).get();
		updatedNdcSiTransRequest.setId(id);
		existingNdcSiTransRequest = updatedNdcSiTransRequest;
		ndcSiTransRequestRepository.save(existingNdcSiTransRequest);
		logger.info("Completed updateNdcSiTransRequest method");
	}

	public void deleteNdcSiTransRequest(NdcSiTransRequestId id) {
		logger.info("Starting deleteNdcSiTransRequest method");
		ndcSiTransRequestRepository.deleteById(id);
		logger.info("Completed deleteNdcSiTransRequest method");
	}
}