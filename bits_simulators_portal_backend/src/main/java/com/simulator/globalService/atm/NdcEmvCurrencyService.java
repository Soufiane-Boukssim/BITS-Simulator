package com.simulator.globalService.atm;

import com.simulator.entities.atm.NdcEmvCurrency;
import com.simulator.entities.atm.NdcEmvCurrencyId;
import com.simulator.repository.atm.NdcEmvCurrencyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NdcEmvCurrencyService {

	@Autowired
	private NdcEmvCurrencyRepository ndcEmvCurrencyRepository;
	private static final Logger logger = LoggerFactory.getLogger(NdcEmvCurrencyService.class);

	public void saveNewNdcEmvCurrency(NdcEmvCurrency ndcEmvCurrency) {
		logger.info("Starting saveNewNdcEmvCurrency method");
		ndcEmvCurrencyRepository.save(ndcEmvCurrency);
		logger.info("Completed saveNewNdcEmvCurrency method");
	}

	public List<NdcEmvCurrency> getAllNdcEmvCurrency() {
		logger.info("Starting getAllNdcEmvCurrency method");
		List<NdcEmvCurrency> result = ndcEmvCurrencyRepository.findAll();
		logger.info("Completed getAllNdcEmvCurrency method");
		return result;
	}

	public void updateNdcEmvCurrency(NdcEmvCurrency updatedNdcEmvCurrency, NdcEmvCurrencyId id) {
		logger.info("Starting updateNdcEmvCurrency method");
		NdcEmvCurrency existingNdcEmvCurrency = ndcEmvCurrencyRepository.findById(id).get();
		updatedNdcEmvCurrency.setId(id);
		existingNdcEmvCurrency = updatedNdcEmvCurrency;
		ndcEmvCurrencyRepository.save(existingNdcEmvCurrency);
		logger.info("Completed updateNdcEmvCurrency method");
	}

	public void deleteNdcEmvCurrency(NdcEmvCurrencyId id) {
		logger.info("Starting deleteNdcEmvCurrency method");
		ndcEmvCurrencyRepository.deleteById(id);
		logger.info("Completed deleteNdcEmvCurrency method");
	}
}