package com.simulator.globalService.atm;

import com.simulator.entities.atm.NdcSminusAutomaticLangSel;
import com.simulator.entities.atm.NdcSminusAutomaticLangSelId;
import com.simulator.repository.atm.NdcSminusAutomaticLangSelRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NdcSminusAutomaticLangSelService {

	@Autowired
	private NdcSminusAutomaticLangSelRepository ndcSminusAutomaticLangSelRepository;
	private static final Logger logger = LoggerFactory.getLogger(NdcSminusAutomaticLangSelService.class);

	public void saveNewNdcSminusAutomaticLangSel(NdcSminusAutomaticLangSel ndcSminusAutomaticLangSel) {
		logger.info("Starting saveNewNdcSminusAutomaticLangSel method");
		ndcSminusAutomaticLangSelRepository.save(ndcSminusAutomaticLangSel);
		logger.info("Completed saveNewNdcSminusAutomaticLangSel method");
	}

	public List<NdcSminusAutomaticLangSel> getAllNdcSminusAutomaticLangSel() {
		logger.info("Starting getAllNdcSminusAutomaticLangSel method");
		List<NdcSminusAutomaticLangSel> result = ndcSminusAutomaticLangSelRepository.findAll();
		logger.info("Completed getAllNdcSminusAutomaticLangSel method");
		return result;
	}

	public void updateNdcSminusAutomaticLangSel(NdcSminusAutomaticLangSel updatedNdcSminusAutomaticLangSel,
			NdcSminusAutomaticLangSelId id) {
		logger.info("Starting updateNdcSminusAutomaticLangSel method");
		NdcSminusAutomaticLangSel existingNdcSminusAutomaticLangSel = ndcSminusAutomaticLangSelRepository.findById(id)
				.get();
		updatedNdcSminusAutomaticLangSel.setId(id);
		existingNdcSminusAutomaticLangSel = updatedNdcSminusAutomaticLangSel;
		ndcSminusAutomaticLangSelRepository.save(existingNdcSminusAutomaticLangSel);
		logger.info("Completed updateNdcSminusAutomaticLangSel method");
	}

	public void deleteNdcSminusAutomaticLangSel(NdcSminusAutomaticLangSelId id) {
		logger.info("Starting deleteNdcSminusAutomaticLangSel method");
		ndcSminusAutomaticLangSelRepository.deleteById(id);
		logger.info("Completed deleteNdcSminusAutomaticLangSel method");
	}
}