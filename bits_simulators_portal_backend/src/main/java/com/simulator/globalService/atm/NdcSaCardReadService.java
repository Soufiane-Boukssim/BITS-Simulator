package com.simulator.globalService.atm;

import com.simulator.entities.atm.NdcSaCardRead;
import com.simulator.entities.atm.NdcSaCardReadId;
import com.simulator.repository.atm.NdcSaCardReadRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NdcSaCardReadService {

	@Autowired
	private NdcSaCardReadRepository ndcSaCardReadRepository;
	private static final Logger logger = LoggerFactory.getLogger(NdcSaCardReadService.class);

	public void saveNewNdcSaCardRead(NdcSaCardRead ndcSaCardRead) {
		logger.info("Starting saveNewNdcSaCardRead method");
		ndcSaCardReadRepository.save(ndcSaCardRead);
		logger.info("Completed saveNewNdcSaCardRead method");
	}

	public List<NdcSaCardRead> getAllNdcSaCardRead() {
		logger.info("Starting getAllNdcSaCardRead method");
		List<NdcSaCardRead> result = ndcSaCardReadRepository.findAll();
		logger.info("Completed getAllNdcSaCardRead method");
		return result;
	}

	public void updateNdcSaCardRead(NdcSaCardRead updatedNdcSaCardRead, NdcSaCardReadId id) {
		logger.info("Starting updateNdcSaCardRead method");
		NdcSaCardRead existingNdcSaCardRead = ndcSaCardReadRepository.findById(id).get();
		updatedNdcSaCardRead.setId(id);
		existingNdcSaCardRead = updatedNdcSaCardRead;
		ndcSaCardReadRepository.save(existingNdcSaCardRead);
		logger.info("Completed updateNdcSaCardRead method");
	}

	public void deleteNdcSaCardRead(NdcSaCardReadId id) {
		logger.info("Starting deleteNdcSaCardRead method");
		ndcSaCardReadRepository.deleteById(id);
		logger.info("Completed deleteNdcSaCardRead method");
	}
}