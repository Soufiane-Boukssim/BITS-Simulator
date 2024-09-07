package com.simulator.globalService.atm;

import com.simulator.entities.atm.NdcSjClose;
import com.simulator.entities.atm.NdcSjCloseId;
import com.simulator.repository.atm.NdcSjCloseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NdcSjCloseService {

	@Autowired
	private NdcSjCloseRepository ndcSjCloseRepository;
	private static final Logger logger = LoggerFactory.getLogger(NdcSjCloseService.class);

	public void saveNewNdcSjClose(NdcSjClose ndcSjClose) {
		logger.info("Starting saveNewNdcSjClose method");
		ndcSjCloseRepository.save(ndcSjClose);
		logger.info("Completed saveNewNdcSjClose method");
	}

	public List<NdcSjClose> getAllNdcSjClose() {
		logger.info("Starting getAllNdcSjClose method");
		List<NdcSjClose> result = ndcSjCloseRepository.findAll();
		logger.info("Completed getAllNdcSjClose method");
		return result;
	}

	public void updateNdcSjClose(NdcSjClose updatedNdcSjClose, NdcSjCloseId id) {
		logger.info("Starting updateNdcSjClose method");
		NdcSjClose existingNdcSjClose = ndcSjCloseRepository.findById(id).get();
		updatedNdcSjClose.setId(id);
		existingNdcSjClose = updatedNdcSjClose;
		ndcSjCloseRepository.save(existingNdcSjClose);
		logger.info("Completed updateNdcSjClose method");
	}

	public void deleteNdcSjClose(NdcSjCloseId id) {
		logger.info("Starting deleteNdcSjClose method");
		ndcSjCloseRepository.deleteById(id);
		logger.info("Completed deleteNdcSjClose method");
	}
}