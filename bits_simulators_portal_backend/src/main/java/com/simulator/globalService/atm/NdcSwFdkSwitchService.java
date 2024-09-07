package com.simulator.globalService.atm;

import com.simulator.entities.atm.NdcSwFdkSwitch;
import com.simulator.entities.atm.NdcSwFdkSwitchId;
import com.simulator.repository.atm.NdcSwFdkSwitchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NdcSwFdkSwitchService {

	@Autowired
	private NdcSwFdkSwitchRepository ndcSwFdkSwitchRepository;
	private static final Logger logger = LoggerFactory.getLogger(NdcSwFdkSwitchService.class);

	public void saveNewNdcSwFdkSwitch(NdcSwFdkSwitch ndcSwFdkSwitch) {
		logger.info("Starting saveNewNdcSwFdkSwitch method");
		ndcSwFdkSwitchRepository.save(ndcSwFdkSwitch);
		logger.info("Completed saveNewNdcSwFdkSwitch method");
	}

	public List<NdcSwFdkSwitch> getAllNdcSwFdkSwitch() {
		logger.info("Starting getAllNdcSwFdkSwitch method");
		List<NdcSwFdkSwitch> result = ndcSwFdkSwitchRepository.findAll();
		logger.info("Completed getAllNdcSwFdkSwitch method");
		return result;
	}

	public void updateNdcSwFdkSwitch(NdcSwFdkSwitch updatedNdcSwFdkSwitch, NdcSwFdkSwitchId id) {
		logger.info("Starting updateNdcSwFdkSwitch method");
		NdcSwFdkSwitch existingNdcSwFdkSwitch = ndcSwFdkSwitchRepository.findById(id).get();
		updatedNdcSwFdkSwitch.setId(id);
		existingNdcSwFdkSwitch = updatedNdcSwFdkSwitch;
		ndcSwFdkSwitchRepository.save(existingNdcSwFdkSwitch);
		logger.info("Completed updateNdcSwFdkSwitch method");
	}

	public void deleteNdcSwFdkSwitch(NdcSwFdkSwitchId id) {
		logger.info("Starting deleteNdcSwFdkSwitch method");
		ndcSwFdkSwitchRepository.deleteById(id);
		logger.info("Completed deleteNdcSwFdkSwitch method");
	}
}