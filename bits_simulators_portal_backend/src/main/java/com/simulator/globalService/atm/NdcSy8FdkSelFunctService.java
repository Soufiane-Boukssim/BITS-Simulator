package com.simulator.globalService.atm;

import com.simulator.entities.atm.NdcSy8FdkSelFunct;
import com.simulator.entities.atm.NdcSy8FdkSelFunctId;
import com.simulator.repository.atm.NdcSy8FdkSelFunctRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NdcSy8FdkSelFunctService {

	@Autowired
	private NdcSy8FdkSelFunctRepository ndcSy8FdkSelFunctRepository;
	private static final Logger logger = LoggerFactory.getLogger(NdcSy8FdkSelFunctService.class);

	public void saveNewNdcSy8FdkSelFunct(NdcSy8FdkSelFunct ndcSy8FdkSelFunct) {
		logger.info("Starting saveNewNdcSy8FdkSelFunct method");
		ndcSy8FdkSelFunctRepository.save(ndcSy8FdkSelFunct);
		logger.info("Completed saveNewNdcSy8FdkSelFunct method");
	}

	public List<NdcSy8FdkSelFunct> getAllNdcSy8FdkSelFunct() {
		logger.info("Starting getAllNdcSy8FdkSelFunct method");
		List<NdcSy8FdkSelFunct> result = ndcSy8FdkSelFunctRepository.findAll();
		logger.info("Completed getAllNdcSy8FdkSelFunct method");
		return result;
	}

	public void updateNdcSy8FdkSelFunct(NdcSy8FdkSelFunct updatedNdcSy8FdkSelFunct, NdcSy8FdkSelFunctId id) {
		logger.info("Starting updateNdcSy8FdkSelFunct method");
		NdcSy8FdkSelFunct existingNdcSy8FdkSelFunct = ndcSy8FdkSelFunctRepository.findById(id).get();
		updatedNdcSy8FdkSelFunct.setId(id);
		existingNdcSy8FdkSelFunct = updatedNdcSy8FdkSelFunct;
		ndcSy8FdkSelFunctRepository.save(existingNdcSy8FdkSelFunct);
		logger.info("Completed updateNdcSy8FdkSelFunct method");
	}

	public void deleteNdcSy8FdkSelFunct(NdcSy8FdkSelFunctId id) {
		logger.info("Starting deleteNdcSy8FdkSelFunct method");
		ndcSy8FdkSelFunctRepository.deleteById(id);
		logger.info("Completed deleteNdcSy8FdkSelFunct method");
	}
}