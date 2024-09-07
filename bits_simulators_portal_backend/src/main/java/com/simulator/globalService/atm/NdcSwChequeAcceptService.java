package com.simulator.globalService.atm;

import com.simulator.entities.atm.NdcSwChequeAccept;
import com.simulator.entities.atm.NdcSwChequeAcceptId;
import com.simulator.repository.atm.NdcSwChequeAcceptRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NdcSwChequeAcceptService {

	@Autowired
	private NdcSwChequeAcceptRepository ndcSwChequeAcceptRepository;
	private static final Logger logger = LoggerFactory.getLogger(NdcSwChequeAcceptService.class);

	public void saveNewNdcSwChequeAccept(NdcSwChequeAccept ndcSwChequeAccept) {
		logger.info("Starting saveNewNdcSwChequeAccept method");
		ndcSwChequeAcceptRepository.save(ndcSwChequeAccept);
		logger.info("Completed saveNewNdcSwChequeAccept method");
	}

	public List<NdcSwChequeAccept> getAllNdcSwChequeAccept() {
		logger.info("Starting getAllNdcSwChequeAccept method");
		List<NdcSwChequeAccept> result = ndcSwChequeAcceptRepository.findAll();
		logger.info("Completed getAllNdcSwChequeAccept method");
		return result;
	}

	public void updateNdcSwChequeAccept(NdcSwChequeAccept updatedNdcSwChequeAccept, NdcSwChequeAcceptId id) {
		logger.info("Starting updateNdcSwChequeAccept method");
		NdcSwChequeAccept existingNdcSwChequeAccept = ndcSwChequeAcceptRepository.findById(id).get();
		updatedNdcSwChequeAccept.setId(id);
		existingNdcSwChequeAccept = updatedNdcSwChequeAccept;
		ndcSwChequeAcceptRepository.save(existingNdcSwChequeAccept);
		logger.info("Completed updateNdcSwChequeAccept method");
	}

	public void deleteNdcSwChequeAccept(NdcSwChequeAcceptId id) {
		logger.info("Starting deleteNdcSwChequeAccept method");
		ndcSwChequeAcceptRepository.deleteById(id);
		logger.info("Completed deleteNdcSwChequeAccept method");
	}
}