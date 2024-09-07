package com.simulator.globalService.atm;

import com.simulator.entities.atm.NdcSsupCashAccept;
import com.simulator.entities.atm.NdcSsupCashAcceptId;
import com.simulator.repository.atm.NdcSsupCashAcceptRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NdcSsupCashAcceptService {

	@Autowired
	private NdcSsupCashAcceptRepository ndcSsupCashAcceptRepository;
	private static final Logger logger = LoggerFactory.getLogger(NdcSsupCashAcceptService.class);

	public void saveNewNdcSsupCashAccept(NdcSsupCashAccept ndcSsupCashAccept) {
		logger.info("Starting saveNewNdcSsupCashAccept method");
		ndcSsupCashAcceptRepository.save(ndcSsupCashAccept);
		logger.info("Completed saveNewNdcSsupCashAccept method");
	}

	public List<NdcSsupCashAccept> getAllNdcSsupCashAccept() {
		logger.info("Starting getAllNdcSsupCashAccept method");
		List<NdcSsupCashAccept> result = ndcSsupCashAcceptRepository.findAll();
		logger.info("Completed getAllNdcSsupCashAccept method");
		return result;
	}

	public void updateNdcSsupCashAccept(NdcSsupCashAccept updatedNdcSsupCashAccept, NdcSsupCashAcceptId id) {
		logger.info("Starting updateNdcSsupCashAccept method");
		NdcSsupCashAccept existingNdcSsupCashAccept = ndcSsupCashAcceptRepository.findById(id).get();
		updatedNdcSsupCashAccept.setId(id);
		existingNdcSsupCashAccept = updatedNdcSsupCashAccept;
		ndcSsupCashAcceptRepository.save(existingNdcSsupCashAccept);
		logger.info("Completed updateNdcSsupCashAccept method");
	}

	public void deleteNdcSsupCashAccept(NdcSsupCashAcceptId id) {
		logger.info("Starting deleteNdcSsupCashAccept method");
		ndcSsupCashAcceptRepository.deleteById(id);
		logger.info("Completed deleteNdcSsupCashAccept method");
	}
}