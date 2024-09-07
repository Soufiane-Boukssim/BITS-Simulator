package com.simulator.globalService.atm;

import com.simulator.entities.atm.NdcDisplayObject;
import com.simulator.entities.atm.NdcDisplayObjectId;
import com.simulator.repository.atm.NdcDisplayObjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NdcDisplayObjectService {

	@Autowired
	private NdcDisplayObjectRepository ndcDisplayObjectRepository;
	private static final Logger logger = LoggerFactory.getLogger(NdcDisplayObjectService.class);

	public void saveNewNdcDisplayObject(NdcDisplayObject ndcDisplayObject) {
		logger.info("Starting saveNewNdcDisplayObject method");
		ndcDisplayObjectRepository.save(ndcDisplayObject);
		logger.info("Completed saveNewNdcDisplayObject method");
	}

	public List<NdcDisplayObject> getAllNdcDisplayObject() {
		logger.info("Starting getAllNdcDisplayObject method");
		List<NdcDisplayObject> result = ndcDisplayObjectRepository.findAll();
		logger.info("Completed getAllNdcDisplayObject method");
		return result;
	}

	public void updateNdcDisplayObject(NdcDisplayObject updatedNdcDisplayObject, NdcDisplayObjectId id) {
		logger.info("Starting updateNdcDisplayObject method");
		NdcDisplayObject existingNdcDisplayObject = ndcDisplayObjectRepository.findById(id).get();
		updatedNdcDisplayObject.setId(id);
		existingNdcDisplayObject = updatedNdcDisplayObject;
		ndcDisplayObjectRepository.save(existingNdcDisplayObject);
		logger.info("Completed updateNdcDisplayObject method");
	}

	public void deleteNdcDisplayObject(NdcDisplayObjectId id) {
		logger.info("Starting deleteNdcDisplayObject method");
		ndcDisplayObjectRepository.deleteById(id);
		logger.info("Completed deleteNdcDisplayObject method");
	}
}