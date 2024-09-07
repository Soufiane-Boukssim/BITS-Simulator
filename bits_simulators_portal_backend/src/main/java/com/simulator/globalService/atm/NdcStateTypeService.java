package com.simulator.globalService.atm;

import com.simulator.entities.atm.NdcStateType;
import com.simulator.repository.atm.NdcStateTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NdcStateTypeService {

	@Autowired
	private NdcStateTypeRepository ndcStateTypeRepository;
	private static final Logger logger = LoggerFactory.getLogger(NdcStateTypeService.class);

	public void saveNewNdcStateType(NdcStateType ndcStateType) {
		logger.info("Starting saveNewNdcStateType method");
		ndcStateTypeRepository.save(ndcStateType);
		logger.info("Completed saveNewNdcStateType method");
	}

	public List<NdcStateType> getAllNdcStateType() {
		logger.info("Starting getAllNdcStateType method");
		List<NdcStateType> result = ndcStateTypeRepository.findAll();
		logger.info("Completed getAllNdcStateType method");
		return result;
	}

	public void updateNdcStateType(NdcStateType updatedNdcStateType, Character stateType) {
		logger.info("Starting updateNdcStateType method");
		NdcStateType existingNdcStateType = ndcStateTypeRepository.findById(stateType).get();
		updatedNdcStateType.setStateType(stateType);
		existingNdcStateType = updatedNdcStateType;
		ndcStateTypeRepository.save(existingNdcStateType);
		logger.info("Completed updateNdcStateType method");
	}

	public void deleteNdcStateType(Character stateType) {
		logger.info("Starting deleteNdcStateType method");
		ndcStateTypeRepository.deleteById(stateType);
		logger.info("Completed deleteNdcStateType method");
	}
}