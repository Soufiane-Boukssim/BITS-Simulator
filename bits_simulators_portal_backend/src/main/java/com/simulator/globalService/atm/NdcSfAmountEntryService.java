package com.simulator.globalService.atm;

import com.simulator.entities.atm.NdcSfAmountEntry;
import com.simulator.entities.atm.NdcSfAmountEntryId;
import com.simulator.repository.atm.NdcSfAmountEntryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NdcSfAmountEntryService {

	@Autowired
	private NdcSfAmountEntryRepository ndcSfAmountEntryRepository;
	private static final Logger logger = LoggerFactory.getLogger(NdcSfAmountEntryService.class);

	public void saveNewNdcSfAmountEntry(NdcSfAmountEntry ndcSfAmountEntry) {
		logger.info("Starting saveNewNdcSfAmountEntry method");
		ndcSfAmountEntryRepository.save(ndcSfAmountEntry);
		logger.info("Completed saveNewNdcSfAmountEntry method");
	}

	public List<NdcSfAmountEntry> getAllNdcSfAmountEntry() {
		logger.info("Starting getAllNdcSfAmountEntry method");
		List<NdcSfAmountEntry> result = ndcSfAmountEntryRepository.findAll();
		logger.info("Completed getAllNdcSfAmountEntry method");
		return result;
	}

	public void updateNdcSfAmountEntry(NdcSfAmountEntry updatedNdcSfAmountEntry, NdcSfAmountEntryId id) {
		logger.info("Starting updateNdcSfAmountEntry method");
		NdcSfAmountEntry existingNdcSfAmountEntry = ndcSfAmountEntryRepository.findById(id).get();
		updatedNdcSfAmountEntry.setId(id);
		existingNdcSfAmountEntry = updatedNdcSfAmountEntry;
		ndcSfAmountEntryRepository.save(existingNdcSfAmountEntry);
		logger.info("Completed updateNdcSfAmountEntry method");
	}

	public void deleteNdcSfAmountEntry(NdcSfAmountEntryId id) {
		logger.info("Starting deleteNdcSfAmountEntry method");
		ndcSfAmountEntryRepository.deleteById(id);
		logger.info("Completed deleteNdcSfAmountEntry method");
	}
}