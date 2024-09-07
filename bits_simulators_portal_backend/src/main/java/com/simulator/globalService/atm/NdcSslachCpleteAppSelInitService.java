package com.simulator.globalService.atm;

import com.simulator.entities.atm.NdcSslachCpleteAppSelInit;
import com.simulator.entities.atm.NdcSslachCpleteAppSelInitId;
import com.simulator.repository.atm.NdcSslachCpleteAppSelInitRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NdcSslachCpleteAppSelInitService {

	@Autowired
	private NdcSslachCpleteAppSelInitRepository ndcSslachCpleteAppSelInitRepository;
	private static final Logger logger = LoggerFactory.getLogger(NdcSslachCpleteAppSelInitService.class);

	public void saveNewNdcSslachCpleteAppSelInit(NdcSslachCpleteAppSelInit ndcSslachCpleteAppSelInit) {
		logger.info("Starting saveNewNdcSslachCpleteAppSelInit method");
		ndcSslachCpleteAppSelInitRepository.save(ndcSslachCpleteAppSelInit);
		logger.info("Completed saveNewNdcSslachCpleteAppSelInit method");
	}

	public List<NdcSslachCpleteAppSelInit> getAllNdcSslachCpleteAppSelInit() {
		logger.info("Starting getAllNdcSslachCpleteAppSelInit method");
		List<NdcSslachCpleteAppSelInit> result = ndcSslachCpleteAppSelInitRepository.findAll();
		logger.info("Completed getAllNdcSslachCpleteAppSelInit method");
		return result;
	}

	public void updateNdcSslachCpleteAppSelInit(NdcSslachCpleteAppSelInit updatedNdcSslachCpleteAppSelInit,
			NdcSslachCpleteAppSelInitId id) {
		logger.info("Starting updateNdcSslachCpleteAppSelInit method");
		NdcSslachCpleteAppSelInit existingNdcSslachCpleteAppSelInit = ndcSslachCpleteAppSelInitRepository.findById(id)
				.get();
		updatedNdcSslachCpleteAppSelInit.setId(id);
		existingNdcSslachCpleteAppSelInit = updatedNdcSslachCpleteAppSelInit;
		ndcSslachCpleteAppSelInitRepository.save(existingNdcSslachCpleteAppSelInit);
		logger.info("Completed updateNdcSslachCpleteAppSelInit method");
	}

	public void deleteNdcSslachCpleteAppSelInit(NdcSslachCpleteAppSelInitId id) {
		logger.info("Starting deleteNdcSslachCpleteAppSelInit method");
		ndcSslachCpleteAppSelInitRepository.deleteById(id);
		logger.info("Completed deleteNdcSslachCpleteAppSelInit method");
	}
}