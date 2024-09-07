package com.simulator.globalService.atm;

import com.simulator.entities.atm.AtmNdcFitnessDataLog;
import com.simulator.repository.atm.AtmNdcFitnessDataLogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AtmNdcFitnessDataLogService {

	@Autowired
	private AtmNdcFitnessDataLogRepository atmNdcFitnessDataLogRepository;
	private static final Logger logger = LoggerFactory.getLogger(AtmNdcFitnessDataLogService.class);

	public void saveNewAtmNdcFitnessDataLog(AtmNdcFitnessDataLog atmNdcFitnessDataLog) {
		logger.info("Starting saveNewAtmNdcFitnessDataLog method");
		atmNdcFitnessDataLogRepository.save(atmNdcFitnessDataLog);
		logger.info("Completed saveNewAtmNdcFitnessDataLog method");
	}

	public List<AtmNdcFitnessDataLog> getAllAtmNdcFitnessDataLog() {
		logger.info("Starting getAllAtmNdcFitnessDataLog method");
		List<AtmNdcFitnessDataLog> result = atmNdcFitnessDataLogRepository.findAll();
		logger.info("Completed getAllAtmNdcFitnessDataLog method");
		return result;
	}

	public void updateAtmNdcFitnessDataLog(AtmNdcFitnessDataLog updatedAtmNdcFitnessDataLog, String bankCode) {
		logger.info("Starting updateAtmNdcFitnessDataLog method");
		AtmNdcFitnessDataLog existingAtmNdcFitnessDataLog = atmNdcFitnessDataLogRepository.findById(bankCode).get();
		updatedAtmNdcFitnessDataLog.setBankCode(bankCode);
		existingAtmNdcFitnessDataLog = updatedAtmNdcFitnessDataLog;
		atmNdcFitnessDataLogRepository.save(existingAtmNdcFitnessDataLog);
		logger.info("Completed updateAtmNdcFitnessDataLog method");
	}

	public void deleteAtmNdcFitnessDataLog(String bankCode) {
		logger.info("Starting deleteAtmNdcFitnessDataLog method");
		atmNdcFitnessDataLogRepository.deleteById(bankCode);
		logger.info("Completed deleteAtmNdcFitnessDataLog method");
	}
}