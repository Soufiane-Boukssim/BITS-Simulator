package com.simulator.globalService.atm;

import com.simulator.entities.atm.NdcDisplayPolice;
import com.simulator.repository.atm.NdcDisplayPoliceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NdcDisplayPoliceService {

	@Autowired
	private NdcDisplayPoliceRepository ndcDisplayPoliceRepository;
	private static final Logger logger = LoggerFactory.getLogger(NdcDisplayPoliceService.class);

	public void saveNewNdcDisplayPolice(NdcDisplayPolice ndcDisplayPolice) {
		logger.info("Starting saveNewNdcDisplayPolice method");
		ndcDisplayPoliceRepository.save(ndcDisplayPolice);
		logger.info("Completed saveNewNdcDisplayPolice method");
	}

	public List<NdcDisplayPolice> getAllNdcDisplayPolice() {
		logger.info("Starting getAllNdcDisplayPolice method");
		List<NdcDisplayPolice> result = ndcDisplayPoliceRepository.findAll();
		logger.info("Completed getAllNdcDisplayPolice method");
		return result;
	}

	public void updateNdcDisplayPolice(NdcDisplayPolice updatedNdcDisplayPolice, String policeCode) {
		logger.info("Starting updateNdcDisplayPolice method");
		NdcDisplayPolice existingNdcDisplayPolice = ndcDisplayPoliceRepository.findById(policeCode).get();
		updatedNdcDisplayPolice.setPoliceCode(policeCode);
		existingNdcDisplayPolice = updatedNdcDisplayPolice;
		ndcDisplayPoliceRepository.save(existingNdcDisplayPolice);
		logger.info("Completed updateNdcDisplayPolice method");
	}

	public void deleteNdcDisplayPolice(String policeCode) {
		logger.info("Starting deleteNdcDisplayPolice method");
		ndcDisplayPoliceRepository.deleteById(policeCode);
		logger.info("Completed deleteNdcDisplayPolice method");
	}
}