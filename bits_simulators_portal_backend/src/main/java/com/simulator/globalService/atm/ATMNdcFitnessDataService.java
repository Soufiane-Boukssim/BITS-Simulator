package com.simulator.globalService.atm;

import com.simulator.entities.atm.ATMNdcFitnessData;
import com.simulator.entities.atm.ATMNdcFitnessDataId;
import com.simulator.repository.atm.ATMNdcFitnessDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ATMNdcFitnessDataService {

	@Autowired
	private ATMNdcFitnessDataRepository aTMNdcFitnessDataRepository;
	private static final Logger logger = LoggerFactory.getLogger(ATMNdcFitnessDataService.class);

	public void saveNewATMNdcFitnessData(ATMNdcFitnessData aTMNdcFitnessData) {
		logger.info("Starting saveNewATMNdcFitnessData method");
		aTMNdcFitnessDataRepository.save(aTMNdcFitnessData);
		logger.info("Completed saveNewATMNdcFitnessData method");
	}

	public List<ATMNdcFitnessData> getAllATMNdcFitnessData() {
		logger.info("Starting getAllATMNdcFitnessData method");
		List<ATMNdcFitnessData> result = aTMNdcFitnessDataRepository.findAll();
		logger.info("Completed getAllATMNdcFitnessData method");
		return result;
	}

	public void updateATMNdcFitnessData(ATMNdcFitnessData updatedATMNdcFitnessData, ATMNdcFitnessDataId id) {
		logger.info("Starting updateATMNdcFitnessData method");
		ATMNdcFitnessData existingATMNdcFitnessData = aTMNdcFitnessDataRepository.findById(id).get();
		updatedATMNdcFitnessData.setId(id);
		existingATMNdcFitnessData = updatedATMNdcFitnessData;
		aTMNdcFitnessDataRepository.save(existingATMNdcFitnessData);
		logger.info("Completed updateATMNdcFitnessData method");
	}

	public void deleteATMNdcFitnessData(ATMNdcFitnessDataId id) {
		logger.info("Starting deleteATMNdcFitnessData method");
		aTMNdcFitnessDataRepository.deleteById(id);
		logger.info("Completed deleteATMNdcFitnessData method");
	}
}