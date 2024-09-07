package com.simulator.globalService.atm;

import com.simulator.entities.atm.NdcStCardReadPinEnt;
import com.simulator.entities.atm.NdcStCardReadPinEntId;
import com.simulator.repository.atm.NdcStCardReadPinEntRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NdcStCardReadPinEntService {

	@Autowired
	private NdcStCardReadPinEntRepository ndcStCardReadPinEntRepository;
	private static final Logger logger = LoggerFactory.getLogger(NdcStCardReadPinEntService.class);

	public void saveNewNdcStCardReadPinEnt(NdcStCardReadPinEnt ndcStCardReadPinEnt) {
		logger.info("Starting saveNewNdcStCardReadPinEnt method");
		ndcStCardReadPinEntRepository.save(ndcStCardReadPinEnt);
		logger.info("Completed saveNewNdcStCardReadPinEnt method");
	}

	public List<NdcStCardReadPinEnt> getAllNdcStCardReadPinEnt() {
		logger.info("Starting getAllNdcStCardReadPinEnt method");
		List<NdcStCardReadPinEnt> result = ndcStCardReadPinEntRepository.findAll();
		logger.info("Completed getAllNdcStCardReadPinEnt method");
		return result;
	}

	public void updateNdcStCardReadPinEnt(NdcStCardReadPinEnt updatedNdcStCardReadPinEnt, NdcStCardReadPinEntId id) {
		logger.info("Starting updateNdcStCardReadPinEnt method");
		NdcStCardReadPinEnt existingNdcStCardReadPinEnt = ndcStCardReadPinEntRepository.findById(id).get();
		updatedNdcStCardReadPinEnt.setId(id);
		existingNdcStCardReadPinEnt = updatedNdcStCardReadPinEnt;
		ndcStCardReadPinEntRepository.save(existingNdcStCardReadPinEnt);
		logger.info("Completed updateNdcStCardReadPinEnt method");
	}

	public void deleteNdcStCardReadPinEnt(NdcStCardReadPinEntId id) {
		logger.info("Starting deleteNdcStCardReadPinEnt method");
		ndcStCardReadPinEntRepository.deleteById(id);
		logger.info("Completed deleteNdcStCardReadPinEnt method");
	}
}