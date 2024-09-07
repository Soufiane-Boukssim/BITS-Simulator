package com.simulator.globalService.atm;

import com.simulator.entities.atm.NdcSpointBeginAppSelInit;
import com.simulator.entities.atm.NdcSpointBeginAppSelInitId;
import com.simulator.repository.atm.NdcSpointBeginAppSelInitRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NdcSpointBeginAppSelInitService {

	@Autowired
	private NdcSpointBeginAppSelInitRepository ndcSpointBeginAppSelInitRepository;
	private static final Logger logger = LoggerFactory.getLogger(NdcSpointBeginAppSelInitService.class);

	public void saveNewNdcSpointBeginAppSelInit(NdcSpointBeginAppSelInit ndcSpointBeginAppSelInit) {
		logger.info("Starting saveNewNdcSpointBeginAppSelInit method");
		ndcSpointBeginAppSelInitRepository.save(ndcSpointBeginAppSelInit);
		logger.info("Completed saveNewNdcSpointBeginAppSelInit method");
	}

	public List<NdcSpointBeginAppSelInit> getAllNdcSpointBeginAppSelInit() {
		logger.info("Starting getAllNdcSpointBeginAppSelInit method");
		List<NdcSpointBeginAppSelInit> result = ndcSpointBeginAppSelInitRepository.findAll();
		logger.info("Completed getAllNdcSpointBeginAppSelInit method");
		return result;
	}

	public void updateNdcSpointBeginAppSelInit(NdcSpointBeginAppSelInit updatedNdcSpointBeginAppSelInit,
			NdcSpointBeginAppSelInitId id) {
		logger.info("Starting updateNdcSpointBeginAppSelInit method");
		NdcSpointBeginAppSelInit existingNdcSpointBeginAppSelInit = ndcSpointBeginAppSelInitRepository.findById(id)
				.get();
		updatedNdcSpointBeginAppSelInit.setId(id);
		existingNdcSpointBeginAppSelInit = updatedNdcSpointBeginAppSelInit;
		ndcSpointBeginAppSelInitRepository.save(existingNdcSpointBeginAppSelInit);
		logger.info("Completed updateNdcSpointBeginAppSelInit method");
	}

	public void deleteNdcSpointBeginAppSelInit(NdcSpointBeginAppSelInitId id) {
		logger.info("Starting deleteNdcSpointBeginAppSelInit method");
		ndcSpointBeginAppSelInitRepository.deleteById(id);
		logger.info("Completed deleteNdcSpointBeginAppSelInit method");
	}
}