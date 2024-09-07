package com.simulator.globalService.atm;

import com.simulator.entities.atm.NdcSxFdkInfoEntry;
import com.simulator.entities.atm.NdcSxFdkInfoEntryId;
import com.simulator.repository.atm.NdcSxFdkInfoEntryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NdcSxFdkInfoEntryService {

	@Autowired
	private NdcSxFdkInfoEntryRepository ndcSxFdkInfoEntryRepository;
	private static final Logger logger = LoggerFactory.getLogger(NdcSxFdkInfoEntryService.class);

	public void saveNewNdcSxFdkInfoEntry(NdcSxFdkInfoEntry ndcSxFdkInfoEntry) {
		logger.info("Starting saveNewNdcSxFdkInfoEntry method");
		ndcSxFdkInfoEntryRepository.save(ndcSxFdkInfoEntry);
		logger.info("Completed saveNewNdcSxFdkInfoEntry method");
	}

	public List<NdcSxFdkInfoEntry> getAllNdcSxFdkInfoEntry() {
		logger.info("Starting getAllNdcSxFdkInfoEntry method");
		List<NdcSxFdkInfoEntry> result = ndcSxFdkInfoEntryRepository.findAll();
		logger.info("Completed getAllNdcSxFdkInfoEntry method");
		return result;
	}

	public void updateNdcSxFdkInfoEntry(NdcSxFdkInfoEntry updatedNdcSxFdkInfoEntry, NdcSxFdkInfoEntryId id) {
		logger.info("Starting updateNdcSxFdkInfoEntry method");
		NdcSxFdkInfoEntry existingNdcSxFdkInfoEntry = ndcSxFdkInfoEntryRepository.findById(id).get();
		updatedNdcSxFdkInfoEntry.setId(id);
		existingNdcSxFdkInfoEntry = updatedNdcSxFdkInfoEntry;
		ndcSxFdkInfoEntryRepository.save(existingNdcSxFdkInfoEntry);
		logger.info("Completed updateNdcSxFdkInfoEntry method");
	}

	public void deleteNdcSxFdkInfoEntry(NdcSxFdkInfoEntryId id) {
		logger.info("Starting deleteNdcSxFdkInfoEntry method");
		ndcSxFdkInfoEntryRepository.deleteById(id);
		logger.info("Completed deleteNdcSxFdkInfoEntry method");
	}
}