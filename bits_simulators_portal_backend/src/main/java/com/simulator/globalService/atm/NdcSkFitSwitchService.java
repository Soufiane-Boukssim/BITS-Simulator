package com.simulator.globalService.atm;

import com.simulator.entities.atm.NdcSkFitSwitch;
import com.simulator.entities.atm.NdcSkFitSwitchId;
import com.simulator.repository.atm.NdcSkFitSwitchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NdcSkFitSwitchService {

	@Autowired
	private NdcSkFitSwitchRepository ndcSkFitSwitchRepository;
	private static final Logger logger = LoggerFactory.getLogger(NdcSkFitSwitchService.class);

	public void saveNewNdcSkFitSwitch(NdcSkFitSwitch ndcSkFitSwitch) {
		logger.info("Starting saveNewNdcSkFitSwitch method");
		ndcSkFitSwitchRepository.save(ndcSkFitSwitch);
		logger.info("Completed saveNewNdcSkFitSwitch method");
	}

	public List<NdcSkFitSwitch> getAllNdcSkFitSwitch() {
		logger.info("Starting getAllNdcSkFitSwitch method");
		List<NdcSkFitSwitch> result = ndcSkFitSwitchRepository.findAll();
		logger.info("Completed getAllNdcSkFitSwitch method");
		return result;
	}

	public void updateNdcSkFitSwitch(NdcSkFitSwitch updatedNdcSkFitSwitch, NdcSkFitSwitchId id) {
		logger.info("Starting updateNdcSkFitSwitch method");
		NdcSkFitSwitch existingNdcSkFitSwitch = ndcSkFitSwitchRepository.findById(id).get();
		updatedNdcSkFitSwitch.setId(id);
		existingNdcSkFitSwitch = updatedNdcSkFitSwitch;
		ndcSkFitSwitchRepository.save(existingNdcSkFitSwitch);
		logger.info("Completed updateNdcSkFitSwitch method");
	}

	public void deleteNdcSkFitSwitch(NdcSkFitSwitchId id) {
		logger.info("Starting deleteNdcSkFitSwitch method");
		ndcSkFitSwitchRepository.deleteById(id);
		logger.info("Completed deleteNdcSkFitSwitch method");
	}
}