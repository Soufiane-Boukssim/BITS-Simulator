package com.simulator.globalService.atm;

import com.simulator.entities.atm.NdcSlCardWrite;
import com.simulator.entities.atm.NdcSlCardWriteId;
import com.simulator.repository.atm.NdcSlCardWriteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NdcSlCardWriteService {

	@Autowired
	private NdcSlCardWriteRepository ndcSlCardWriteRepository;
	private static final Logger logger = LoggerFactory.getLogger(NdcSlCardWriteService.class);

	public void saveNewNdcSlCardWrite(NdcSlCardWrite ndcSlCardWrite) {
		logger.info("Starting saveNewNdcSlCardWrite method");
		ndcSlCardWriteRepository.save(ndcSlCardWrite);
		logger.info("Completed saveNewNdcSlCardWrite method");
	}

	public List<NdcSlCardWrite> getAllNdcSlCardWrite() {
		logger.info("Starting getAllNdcSlCardWrite method");
		List<NdcSlCardWrite> result = ndcSlCardWriteRepository.findAll();
		logger.info("Completed getAllNdcSlCardWrite method");
		return result;
	}

	public void updateNdcSlCardWrite(NdcSlCardWrite updatedNdcSlCardWrite, NdcSlCardWriteId id) {
		logger.info("Starting updateNdcSlCardWrite method");
		NdcSlCardWrite existingNdcSlCardWrite = ndcSlCardWriteRepository.findById(id).get();
		updatedNdcSlCardWrite.setId(id);
		existingNdcSlCardWrite = updatedNdcSlCardWrite;
		ndcSlCardWriteRepository.save(existingNdcSlCardWrite);
		logger.info("Completed updateNdcSlCardWrite method");
	}

	public void deleteNdcSlCardWrite(NdcSlCardWriteId id) {
		logger.info("Starting deleteNdcSlCardWrite method");
		ndcSlCardWriteRepository.deleteById(id);
		logger.info("Completed deleteNdcSlCardWrite method");
	}
}