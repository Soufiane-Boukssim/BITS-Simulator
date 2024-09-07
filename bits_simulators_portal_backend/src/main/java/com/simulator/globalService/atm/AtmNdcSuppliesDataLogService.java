package com.simulator.globalService.atm;

import com.simulator.entities.atm.AtmNdcSuppliesDataLog;
import com.simulator.entities.atm.AtmNdcSuppliesDataLogId;
import com.simulator.repository.atm.AtmNdcSuppliesDataLogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AtmNdcSuppliesDataLogService {

	@Autowired
	private AtmNdcSuppliesDataLogRepository atmNdcSuppliesDataLogRepository;
	private static final Logger logger = LoggerFactory.getLogger(AtmNdcSuppliesDataLogService.class);

	public void saveNewAtmNdcSuppliesDataLog(AtmNdcSuppliesDataLog atmNdcSuppliesDataLog) {
		logger.info("Starting saveNewAtmNdcSuppliesDataLog method");
		atmNdcSuppliesDataLogRepository.save(atmNdcSuppliesDataLog);
		logger.info("Completed saveNewAtmNdcSuppliesDataLog method");
	}

	public List<AtmNdcSuppliesDataLog> getAllAtmNdcSuppliesDataLog() {
		logger.info("Starting getAllAtmNdcSuppliesDataLog method");
		List<AtmNdcSuppliesDataLog> result = atmNdcSuppliesDataLogRepository.findAll();
		logger.info("Completed getAllAtmNdcSuppliesDataLog method");
		return result;
	}

	public void updateAtmNdcSuppliesDataLog(AtmNdcSuppliesDataLog updatedAtmNdcSuppliesDataLog,
			AtmNdcSuppliesDataLogId id) {
		logger.info("Starting updateAtmNdcSuppliesDataLog method");
		AtmNdcSuppliesDataLog existingAtmNdcSuppliesDataLog = atmNdcSuppliesDataLogRepository.findById(id).get();
		updatedAtmNdcSuppliesDataLog.setId(id);
		existingAtmNdcSuppliesDataLog = updatedAtmNdcSuppliesDataLog;
		atmNdcSuppliesDataLogRepository.save(existingAtmNdcSuppliesDataLog);
		logger.info("Completed updateAtmNdcSuppliesDataLog method");
	}

	public void deleteAtmNdcSuppliesDataLog(AtmNdcSuppliesDataLogId id) {
		logger.info("Starting deleteAtmNdcSuppliesDataLog method");
		atmNdcSuppliesDataLogRepository.deleteById(id);
		logger.info("Completed deleteAtmNdcSuppliesDataLog method");
	}
}