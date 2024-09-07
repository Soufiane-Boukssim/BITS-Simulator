package com.simulator.globalService.atm;

import com.simulator.entities.atm.AtmNdcSuppliesData;
import com.simulator.entities.atm.AtmNdcSuppliesDataId;
import com.simulator.repository.atm.AtmNdcSuppliesDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AtmNdcSuppliesDataService {

	@Autowired
	private AtmNdcSuppliesDataRepository atmNdcSuppliesDataRepository;
	private static final Logger logger = LoggerFactory.getLogger(AtmNdcSuppliesDataService.class);

	public void saveNewAtmNdcSuppliesData(AtmNdcSuppliesData atmNdcSuppliesData) {
		logger.info("Starting saveNewAtmNdcSuppliesData method");
		atmNdcSuppliesDataRepository.save(atmNdcSuppliesData);
		logger.info("Completed saveNewAtmNdcSuppliesData method");
	}

	public List<AtmNdcSuppliesData> getAllAtmNdcSuppliesData() {
		logger.info("Starting getAllAtmNdcSuppliesData method");
		List<AtmNdcSuppliesData> result = atmNdcSuppliesDataRepository.findAll();
		logger.info("Completed getAllAtmNdcSuppliesData method");
		return result;
	}

	public void updateAtmNdcSuppliesData(AtmNdcSuppliesData updatedAtmNdcSuppliesData, AtmNdcSuppliesDataId id) {
		logger.info("Starting updateAtmNdcSuppliesData method");
		AtmNdcSuppliesData existingAtmNdcSuppliesData = atmNdcSuppliesDataRepository.findById(id).get();
		updatedAtmNdcSuppliesData.setId(id);
		existingAtmNdcSuppliesData = updatedAtmNdcSuppliesData;
		atmNdcSuppliesDataRepository.save(existingAtmNdcSuppliesData);
		logger.info("Completed updateAtmNdcSuppliesData method");
	}

	public void deleteAtmNdcSuppliesData(AtmNdcSuppliesDataId id) {
		logger.info("Starting deleteAtmNdcSuppliesData method");
		atmNdcSuppliesDataRepository.deleteById(id);
		logger.info("Completed deleteAtmNdcSuppliesData method");
	}
}