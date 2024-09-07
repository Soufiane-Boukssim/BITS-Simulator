package com.simulator.globalService.atm;

import com.simulator.entities.atm.NdcDisplayText;
import com.simulator.entities.atm.NdcDisplayTextId;
import com.simulator.repository.atm.NdcDisplayTextRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NdcDisplayTextService {

	@Autowired
	private NdcDisplayTextRepository ndcDisplayTextRepository;
	private static final Logger logger = LoggerFactory.getLogger(NdcDisplayTextService.class);

	public void saveNewNdcDisplayText(NdcDisplayText ndcDisplayText) {
		logger.info("Starting saveNewNdcDisplayText method");
		ndcDisplayTextRepository.save(ndcDisplayText);
		logger.info("Completed saveNewNdcDisplayText method");
	}

	public List<NdcDisplayText> getAllNdcDisplayText() {
		logger.info("Starting getAllNdcDisplayText method");
		List<NdcDisplayText> result = ndcDisplayTextRepository.findAll();
		logger.info("Completed getAllNdcDisplayText method");
		return result;
	}

	public void updateNdcDisplayText(NdcDisplayText updatedNdcDisplayText, NdcDisplayTextId id) {
		logger.info("Starting updateNdcDisplayText method");
		NdcDisplayText existingNdcDisplayText = ndcDisplayTextRepository.findById(id).get();
		updatedNdcDisplayText.setId(id);
		existingNdcDisplayText = updatedNdcDisplayText;
		ndcDisplayTextRepository.save(existingNdcDisplayText);
		logger.info("Completed updateNdcDisplayText method");
	}

	public void deleteNdcDisplayText(NdcDisplayTextId id) {
		logger.info("Starting deleteNdcDisplayText method");
		ndcDisplayTextRepository.deleteById(id);
		logger.info("Completed deleteNdcDisplayText method");
	}
}