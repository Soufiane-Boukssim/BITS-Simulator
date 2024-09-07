package com.simulator.globalService.atm;

import com.simulator.entities.atm.NdcSrDocumentAmountE;
import com.simulator.entities.atm.NdcSrDocumentAmountEId;
import com.simulator.repository.atm.NdcSrDocumentAmountERepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NdcSrDocumentAmountEService {

	@Autowired
	private NdcSrDocumentAmountERepository ndcSrDocumentAmountERepository;
	private static final Logger logger = LoggerFactory.getLogger(NdcSrDocumentAmountEService.class);

	public void saveNewNdcSrDocumentAmountE(NdcSrDocumentAmountE ndcSrDocumentAmountE) {
		logger.info("Starting saveNewNdcSrDocumentAmountE method");
		ndcSrDocumentAmountERepository.save(ndcSrDocumentAmountE);
		logger.info("Completed saveNewNdcSrDocumentAmountE method");
	}

	public List<NdcSrDocumentAmountE> getAllNdcSrDocumentAmountE() {
		logger.info("Starting getAllNdcSrDocumentAmountE method");
		List<NdcSrDocumentAmountE> result = ndcSrDocumentAmountERepository.findAll();
		logger.info("Completed getAllNdcSrDocumentAmountE method");
		return result;
	}

	public void updateNdcSrDocumentAmountE(NdcSrDocumentAmountE updatedNdcSrDocumentAmountE,
			NdcSrDocumentAmountEId id) {
		logger.info("Starting updateNdcSrDocumentAmountE method");
		NdcSrDocumentAmountE existingNdcSrDocumentAmountE = ndcSrDocumentAmountERepository.findById(id).get();
		updatedNdcSrDocumentAmountE.setId(id);
		existingNdcSrDocumentAmountE = updatedNdcSrDocumentAmountE;
		ndcSrDocumentAmountERepository.save(existingNdcSrDocumentAmountE);
		logger.info("Completed updateNdcSrDocumentAmountE method");
	}

	public void deleteNdcSrDocumentAmountE(NdcSrDocumentAmountEId id) {
		logger.info("Starting deleteNdcSrDocumentAmountE method");
		ndcSrDocumentAmountERepository.deleteById(id);
		logger.info("Completed deleteNdcSrDocumentAmountE method");
	}
}