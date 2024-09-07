package com.simulator.globalService.atm;

import com.simulator.entities.atm.NdcSsLangCodeSwitch;
import com.simulator.entities.atm.NdcSsLangCodeSwitchId;
import com.simulator.repository.atm.NdcSsLangCodeSwitchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NdcSsLangCodeSwitchService {

	@Autowired
	private NdcSsLangCodeSwitchRepository ndcSsLangCodeSwitchRepository;
	private static final Logger logger = LoggerFactory.getLogger(NdcSsLangCodeSwitchService.class);

	public void saveNewNdcSsLangCodeSwitch(NdcSsLangCodeSwitch ndcSsLangCodeSwitch) {
		logger.info("Starting saveNewNdcSsLangCodeSwitch method");
		ndcSsLangCodeSwitchRepository.save(ndcSsLangCodeSwitch);
		logger.info("Completed saveNewNdcSsLangCodeSwitch method");
	}

	public List<NdcSsLangCodeSwitch> getAllNdcSsLangCodeSwitch() {
		logger.info("Starting getAllNdcSsLangCodeSwitch method");
		List<NdcSsLangCodeSwitch> result = ndcSsLangCodeSwitchRepository.findAll();
		logger.info("Completed getAllNdcSsLangCodeSwitch method");
		return result;
	}

	public void updateNdcSsLangCodeSwitch(NdcSsLangCodeSwitch updatedNdcSsLangCodeSwitch, NdcSsLangCodeSwitchId id) {
		logger.info("Starting updateNdcSsLangCodeSwitch method");
		NdcSsLangCodeSwitch existingNdcSsLangCodeSwitch = ndcSsLangCodeSwitchRepository.findById(id).get();
		updatedNdcSsLangCodeSwitch.setId(id);
		existingNdcSsLangCodeSwitch = updatedNdcSsLangCodeSwitch;
		ndcSsLangCodeSwitchRepository.save(existingNdcSsLangCodeSwitch);
		logger.info("Completed updateNdcSsLangCodeSwitch method");
	}

	public void deleteNdcSsLangCodeSwitch(NdcSsLangCodeSwitchId id) {
		logger.info("Starting deleteNdcSsLangCodeSwitch method");
		ndcSsLangCodeSwitchRepository.deleteById(id);
		logger.info("Completed deleteNdcSsLangCodeSwitch method");
	}
}