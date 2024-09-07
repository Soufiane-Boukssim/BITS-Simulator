package com.simulator.globalService.atm;

import com.simulator.entities.atm.NdcSvLangSelect;
import com.simulator.entities.atm.NdcSvLangSelectId;
import com.simulator.repository.atm.NdcSvLangSelectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NdcSvLangSelectService {

	@Autowired
	private NdcSvLangSelectRepository ndcSvLangSelectRepository;
	private static final Logger logger = LoggerFactory.getLogger(NdcSvLangSelectService.class);

	public void saveNewNdcSvLangSelect(NdcSvLangSelect ndcSvLangSelect) {
		logger.info("Starting saveNewNdcSvLangSelect method");
		ndcSvLangSelectRepository.save(ndcSvLangSelect);
		logger.info("Completed saveNewNdcSvLangSelect method");
	}

	public List<NdcSvLangSelect> getAllNdcSvLangSelect() {
		logger.info("Starting getAllNdcSvLangSelect method");
		List<NdcSvLangSelect> result = ndcSvLangSelectRepository.findAll();
		logger.info("Completed getAllNdcSvLangSelect method");
		return result;
	}

	public void updateNdcSvLangSelect(NdcSvLangSelect updatedNdcSvLangSelect, NdcSvLangSelectId id) {
		logger.info("Starting updateNdcSvLangSelect method");
		NdcSvLangSelect existingNdcSvLangSelect = ndcSvLangSelectRepository.findById(id).get();
		updatedNdcSvLangSelect.setId(id);
		existingNdcSvLangSelect = updatedNdcSvLangSelect;
		ndcSvLangSelectRepository.save(existingNdcSvLangSelect);
		logger.info("Completed updateNdcSvLangSelect method");
	}

	public void deleteNdcSvLangSelect(NdcSvLangSelectId id) {
		logger.info("Starting deleteNdcSvLangSelect method");
		ndcSvLangSelectRepository.deleteById(id);
		logger.info("Completed deleteNdcSvLangSelect method");
	}
}