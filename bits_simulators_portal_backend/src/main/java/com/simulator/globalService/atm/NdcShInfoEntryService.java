package com.simulator.globalService.atm;

import com.simulator.entities.atm.NdcShInfoEntry;
import com.simulator.entities.atm.NdcShInfoEntryId;
import com.simulator.repository.atm.NdcShInfoEntryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NdcShInfoEntryService {

	@Autowired
	private NdcShInfoEntryRepository ndcShInfoEntryRepository;
	private static final Logger logger = LoggerFactory.getLogger(NdcShInfoEntryService.class);

	public void saveNewNdcShInfoEntry(NdcShInfoEntry ndcShInfoEntry) {
		logger.info("Starting saveNewNdcShInfoEntry method");
		ndcShInfoEntryRepository.save(ndcShInfoEntry);
		logger.info("Completed saveNewNdcShInfoEntry method");
	}

	public List<NdcShInfoEntry> getAllNdcShInfoEntry() {
		logger.info("Starting getAllNdcShInfoEntry method");
		List<NdcShInfoEntry> result = ndcShInfoEntryRepository.findAll();
		logger.info("Completed getAllNdcShInfoEntry method");
		return result;
	}

	public void updateNdcShInfoEntry(NdcShInfoEntry updatedNdcShInfoEntry, NdcShInfoEntryId id) {
		logger.info("Starting updateNdcShInfoEntry method");
		NdcShInfoEntry existingNdcShInfoEntry = ndcShInfoEntryRepository.findById(id).get();
		updatedNdcShInfoEntry.setId(id);
		existingNdcShInfoEntry = updatedNdcShInfoEntry;
		ndcShInfoEntryRepository.save(existingNdcShInfoEntry);
		logger.info("Completed updateNdcShInfoEntry method");
	}

	public void deleteNdcShInfoEntry(NdcShInfoEntryId id) {
		logger.info("Starting deleteNdcShInfoEntry method");
		ndcShInfoEntryRepository.deleteById(id);
		logger.info("Completed deleteNdcShInfoEntry method");
	}
}