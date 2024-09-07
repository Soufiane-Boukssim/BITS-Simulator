package com.simulator.globalService.atm;

import com.simulator.entities.atm.NdcEmvApplication;
import com.simulator.entities.atm.NdcEmvApplicationId;
import com.simulator.repository.atm.NdcEmvApplicationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NdcEmvApplicationService {

	@Autowired
	private NdcEmvApplicationRepository ndcEmvApplicationRepository;
	private static final Logger logger = LoggerFactory.getLogger(NdcEmvApplicationService.class);

	public void saveNewNdcEmvApplication(NdcEmvApplication ndcEmvApplication) {
		logger.info("Starting saveNewNdcEmvApplication method");
		ndcEmvApplicationRepository.save(ndcEmvApplication);
		logger.info("Completed saveNewNdcEmvApplication method");
	}

	public List<NdcEmvApplication> getAllNdcEmvApplication() {
		logger.info("Starting getAllNdcEmvApplication method");
		List<NdcEmvApplication> result = ndcEmvApplicationRepository.findAll();
		logger.info("Completed getAllNdcEmvApplication method");
		return result;
	}

	public void updateNdcEmvApplication(NdcEmvApplication updatedNdcEmvApplication, NdcEmvApplicationId id) {
		logger.info("Starting updateNdcEmvApplication method");
		NdcEmvApplication existingNdcEmvApplication = ndcEmvApplicationRepository.findById(id).get();
		updatedNdcEmvApplication.setId(id);
		existingNdcEmvApplication = updatedNdcEmvApplication;
		ndcEmvApplicationRepository.save(existingNdcEmvApplication);
		logger.info("Completed updateNdcEmvApplication method");
	}

	public void deleteNdcEmvApplication(NdcEmvApplicationId id) {
		logger.info("Starting deleteNdcEmvApplication method");
		ndcEmvApplicationRepository.deleteById(id);
		logger.info("Completed deleteNdcEmvApplication method");
	}
}