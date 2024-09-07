package com.simulator.globalService.atm;

import com.simulator.entities.atm.NdcSdPresetOperation;
import com.simulator.entities.atm.NdcSdPresetOperationId;
import com.simulator.repository.atm.NdcSdPresetOperationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NdcSdPresetOperationService {

	@Autowired
	private NdcSdPresetOperationRepository ndcSdPresetOperationRepository;
	private static final Logger logger = LoggerFactory.getLogger(NdcSdPresetOperationService.class);

	public void saveNewNdcSdPresetOperation(NdcSdPresetOperation ndcSdPresetOperation) {
		logger.info("Starting saveNewNdcSdPresetOperation method");
		ndcSdPresetOperationRepository.save(ndcSdPresetOperation);
		logger.info("Completed saveNewNdcSdPresetOperation method");
	}

	public List<NdcSdPresetOperation> getAllNdcSdPresetOperation() {
		logger.info("Starting getAllNdcSdPresetOperation method");
		List<NdcSdPresetOperation> result = ndcSdPresetOperationRepository.findAll();
		logger.info("Completed getAllNdcSdPresetOperation method");
		return result;
	}

	public void updateNdcSdPresetOperation(NdcSdPresetOperation updatedNdcSdPresetOperation,
			NdcSdPresetOperationId id) {
		logger.info("Starting updateNdcSdPresetOperation method");
		NdcSdPresetOperation existingNdcSdPresetOperation = ndcSdPresetOperationRepository.findById(id).get();
		updatedNdcSdPresetOperation.setId(id);
		existingNdcSdPresetOperation = updatedNdcSdPresetOperation;
		ndcSdPresetOperationRepository.save(existingNdcSdPresetOperation);
		logger.info("Completed updateNdcSdPresetOperation method");
	}

	public void deleteNdcSdPresetOperation(NdcSdPresetOperationId id) {
		logger.info("Starting deleteNdcSdPresetOperation method");
		ndcSdPresetOperationRepository.deleteById(id);
		logger.info("Completed deleteNdcSdPresetOperation method");
	}
}