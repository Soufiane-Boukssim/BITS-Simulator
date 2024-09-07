package com.simulator.globalService.atm;

import com.simulator.entities.atm.NdcSe4FdkSelection;
import com.simulator.entities.atm.NdcSe4FdkSelectionId;
import com.simulator.repository.atm.NdcSe4FdkSelectionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NdcSe4FdkSelectionService {

	@Autowired
	private NdcSe4FdkSelectionRepository ndcSe4FdkSelectionRepository;
	private static final Logger logger = LoggerFactory.getLogger(NdcSe4FdkSelectionService.class);

	public void saveNewNdcSe4FdkSelection(NdcSe4FdkSelection ndcSe4FdkSelection) {
		logger.info("Starting saveNewNdcSe4FdkSelection method");
		ndcSe4FdkSelectionRepository.save(ndcSe4FdkSelection);
		logger.info("Completed saveNewNdcSe4FdkSelection method");
	}

	public List<NdcSe4FdkSelection> getAllNdcSe4FdkSelection() {
		logger.info("Starting getAllNdcSe4FdkSelection method");
		List<NdcSe4FdkSelection> result = ndcSe4FdkSelectionRepository.findAll();
		logger.info("Completed getAllNdcSe4FdkSelection method");
		return result;
	}

	public void updateNdcSe4FdkSelection(NdcSe4FdkSelection updatedNdcSe4FdkSelection, NdcSe4FdkSelectionId id) {
		logger.info("Starting updateNdcSe4FdkSelection method");
		NdcSe4FdkSelection existingNdcSe4FdkSelection = ndcSe4FdkSelectionRepository.findById(id).get();
		updatedNdcSe4FdkSelection.setId(id);
		existingNdcSe4FdkSelection = updatedNdcSe4FdkSelection;
		ndcSe4FdkSelectionRepository.save(existingNdcSe4FdkSelection);
		logger.info("Completed updateNdcSe4FdkSelection method");
	}

	public void deleteNdcSe4FdkSelection(NdcSe4FdkSelectionId id) {
		logger.info("Starting deleteNdcSe4FdkSelection method");
		ndcSe4FdkSelectionRepository.deleteById(id);
		logger.info("Completed deleteNdcSe4FdkSelection method");
	}
}