package com.simulator.globalService.atm;

import com.simulator.entities.atm.NdcDenominationType;
import com.simulator.entities.atm.NdcDenominationTypeId;
import com.simulator.repository.atm.NdcDenominationTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NdcDenominationTypeService {

	@Autowired
	private NdcDenominationTypeRepository ndcDenominationTypeRepository;
	private static final Logger logger = LoggerFactory.getLogger(NdcDenominationTypeService.class);

	public void saveNewNdcDenominationType(NdcDenominationType ndcDenominationType) {
		logger.info("Starting saveNewNdcDenominationType method");
		ndcDenominationTypeRepository.save(ndcDenominationType);
		logger.info("Completed saveNewNdcDenominationType method");
	}

	public List<NdcDenominationType> getAllNdcDenominationType() {
		logger.info("Starting getAllNdcDenominationType method");
		List<NdcDenominationType> result = ndcDenominationTypeRepository.findAll();
		logger.info("Completed getAllNdcDenominationType method");
		return result;
	}

	public void updateNdcDenominationType(NdcDenominationType updatedNdcDenominationType, NdcDenominationTypeId id) {
		logger.info("Starting updateNdcDenominationType method");
		NdcDenominationType existingNdcDenominationType = ndcDenominationTypeRepository.findById(id).get();
		updatedNdcDenominationType.setId(id);
		existingNdcDenominationType = updatedNdcDenominationType;
		ndcDenominationTypeRepository.save(existingNdcDenominationType);
		logger.info("Completed updateNdcDenominationType method");
	}

	public void deleteNdcDenominationType(NdcDenominationTypeId id) {
		logger.info("Starting deleteNdcDenominationType method");
		ndcDenominationTypeRepository.deleteById(id);
		logger.info("Completed deleteNdcDenominationType method");
	}
}