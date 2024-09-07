package com.simulator.globalService.atm;

import com.simulator.entities.atm.NdcEmvLanguage;
import com.simulator.entities.atm.NdcEmvLanguageId;
import com.simulator.repository.atm.NdcEmvLanguageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NdcEmvLanguageService {

	@Autowired
	private NdcEmvLanguageRepository ndcEmvLanguageRepository;
	private static final Logger logger = LoggerFactory.getLogger(NdcEmvLanguageService.class);

	public void saveNewNdcEmvLanguage(NdcEmvLanguage ndcEmvLanguage) {
		logger.info("Starting saveNewNdcEmvLanguage method");
		ndcEmvLanguageRepository.save(ndcEmvLanguage);
		logger.info("Completed saveNewNdcEmvLanguage method");
	}

	public List<NdcEmvLanguage> getAllNdcEmvLanguage() {
		logger.info("Starting getAllNdcEmvLanguage method");
		List<NdcEmvLanguage> result = ndcEmvLanguageRepository.findAll();
		logger.info("Completed getAllNdcEmvLanguage method");
		return result;
	}

	public void updateNdcEmvLanguage(NdcEmvLanguage updatedNdcEmvLanguage, NdcEmvLanguageId id) {
		logger.info("Starting updateNdcEmvLanguage method");
		NdcEmvLanguage existingNdcEmvLanguage = ndcEmvLanguageRepository.findById(id).get();
		updatedNdcEmvLanguage.setId(id);
		existingNdcEmvLanguage = updatedNdcEmvLanguage;
		ndcEmvLanguageRepository.save(existingNdcEmvLanguage);
		logger.info("Completed updateNdcEmvLanguage method");
	}

	public void deleteNdcEmvLanguage(NdcEmvLanguageId id) {
		logger.info("Starting deleteNdcEmvLanguage method");
		ndcEmvLanguageRepository.deleteById(id);
		logger.info("Completed deleteNdcEmvLanguage method");
	}
}