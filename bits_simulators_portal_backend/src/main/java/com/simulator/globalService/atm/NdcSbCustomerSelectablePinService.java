package com.simulator.globalService.atm;

import com.simulator.entities.atm.NdcSbCustomerSelectablePin;
import com.simulator.entities.atm.NdcSbCustomerSelectablePinId;
import com.simulator.repository.atm.NdcSbCustomerSelectablePinRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NdcSbCustomerSelectablePinService {

	@Autowired
	private NdcSbCustomerSelectablePinRepository ndcSbCustomerSelectablePinRepository;
	private static final Logger logger = LoggerFactory.getLogger(NdcSbCustomerSelectablePinService.class);

	public void saveNewNdcSbCustomerSelectablePin(NdcSbCustomerSelectablePin ndcSbCustomerSelectablePin) {
		logger.info("Starting saveNewNdcSbCustomerSelectablePin method");
		ndcSbCustomerSelectablePinRepository.save(ndcSbCustomerSelectablePin);
		logger.info("Completed saveNewNdcSbCustomerSelectablePin method");
	}

	public List<NdcSbCustomerSelectablePin> getAllNdcSbCustomerSelectablePin() {
		logger.info("Starting getAllNdcSbCustomerSelectablePin method");
		List<NdcSbCustomerSelectablePin> result = ndcSbCustomerSelectablePinRepository.findAll();
		logger.info("Completed getAllNdcSbCustomerSelectablePin method");
		return result;
	}

	public void updateNdcSbCustomerSelectablePin(NdcSbCustomerSelectablePin updatedNdcSbCustomerSelectablePin,
			NdcSbCustomerSelectablePinId id) {
		logger.info("Starting updateNdcSbCustomerSelectablePin method");
		NdcSbCustomerSelectablePin existingNdcSbCustomerSelectablePin = ndcSbCustomerSelectablePinRepository
				.findById(id).get();
		updatedNdcSbCustomerSelectablePin.setId(id);
		existingNdcSbCustomerSelectablePin = updatedNdcSbCustomerSelectablePin;
		ndcSbCustomerSelectablePinRepository.save(existingNdcSbCustomerSelectablePin);
		logger.info("Completed updateNdcSbCustomerSelectablePin method");
	}

	public void deleteNdcSbCustomerSelectablePin(NdcSbCustomerSelectablePinId id) {
		logger.info("Starting deleteNdcSbCustomerSelectablePin method");
		ndcSbCustomerSelectablePinRepository.deleteById(id);
		logger.info("Completed deleteNdcSbCustomerSelectablePin method");
	}
}