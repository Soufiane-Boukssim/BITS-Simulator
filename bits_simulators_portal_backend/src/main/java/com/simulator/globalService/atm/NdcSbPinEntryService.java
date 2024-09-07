package com.simulator.globalService.atm;

import com.simulator.entities.atm.NdcSbPinEntry;
import com.simulator.entities.atm.NdcSbPinEntryId;
import com.simulator.repository.atm.NdcSbPinEntryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NdcSbPinEntryService {

	@Autowired
	private NdcSbPinEntryRepository ndcSbPinEntryRepository;
	private static final Logger logger = LoggerFactory.getLogger(NdcSbPinEntryService.class);

	public void saveNewNdcSbPinEntry(NdcSbPinEntry ndcSbPinEntry) {
		logger.info("Starting saveNewNdcSbPinEntry method");
		ndcSbPinEntryRepository.save(ndcSbPinEntry);
		logger.info("Completed saveNewNdcSbPinEntry method");
	}

	public List<NdcSbPinEntry> getAllNdcSbPinEntry() {
		logger.info("Starting getAllNdcSbPinEntry method");
		List<NdcSbPinEntry> result = ndcSbPinEntryRepository.findAll();
		logger.info("Completed getAllNdcSbPinEntry method");
		return result;
	}

	public void updateNdcSbPinEntry(NdcSbPinEntry updatedNdcSbPinEntry, NdcSbPinEntryId id) {
		logger.info("Starting updateNdcSbPinEntry method");
		NdcSbPinEntry existingNdcSbPinEntry = ndcSbPinEntryRepository.findById(id).get();
		updatedNdcSbPinEntry.setId(id);
		existingNdcSbPinEntry = updatedNdcSbPinEntry;
		ndcSbPinEntryRepository.save(existingNdcSbPinEntry);
		logger.info("Completed updateNdcSbPinEntry method");
	}

	public void deleteNdcSbPinEntry(NdcSbPinEntryId id) {
		logger.info("Starting deleteNdcSbPinEntry method");
		ndcSbPinEntryRepository.deleteById(id);
		logger.info("Completed deleteNdcSbPinEntry method");
	}
}