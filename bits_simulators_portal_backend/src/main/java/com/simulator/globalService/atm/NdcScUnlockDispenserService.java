package com.simulator.globalService.atm;

import com.simulator.entities.atm.NdcScUnlockDispenser;
import com.simulator.entities.atm.NdcScUnlockDispenserId;
import com.simulator.repository.atm.NdcScUnlockDispenserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NdcScUnlockDispenserService {

	@Autowired
	private NdcScUnlockDispenserRepository ndcScUnlockDispenserRepository;
	private static final Logger logger = LoggerFactory.getLogger(NdcScUnlockDispenserService.class);

	public void saveNewNdcScUnlockDispenser(NdcScUnlockDispenser ndcScUnlockDispenser) {
		logger.info("Starting saveNewNdcScUnlockDispenser method");
		ndcScUnlockDispenserRepository.save(ndcScUnlockDispenser);
		logger.info("Completed saveNewNdcScUnlockDispenser method");
	}

	public List<NdcScUnlockDispenser> getAllNdcScUnlockDispenser() {
		logger.info("Starting getAllNdcScUnlockDispenser method");
		List<NdcScUnlockDispenser> result = ndcScUnlockDispenserRepository.findAll();
		logger.info("Completed getAllNdcScUnlockDispenser method");
		return result;
	}

	public void updateNdcScUnlockDispenser(NdcScUnlockDispenser updatedNdcScUnlockDispenser,
			NdcScUnlockDispenserId id) {
		logger.info("Starting updateNdcScUnlockDispenser method");
		NdcScUnlockDispenser existingNdcScUnlockDispenser = ndcScUnlockDispenserRepository.findById(id).get();
		updatedNdcScUnlockDispenser.setId(id);
		existingNdcScUnlockDispenser = updatedNdcScUnlockDispenser;
		ndcScUnlockDispenserRepository.save(existingNdcScUnlockDispenser);
		logger.info("Completed updateNdcScUnlockDispenser method");
	}

	public void deleteNdcScUnlockDispenser(NdcScUnlockDispenserId id) {
		logger.info("Starting deleteNdcScUnlockDispenser method");
		ndcScUnlockDispenserRepository.deleteById(id);
		logger.info("Completed deleteNdcScUnlockDispenser method");
	}
}