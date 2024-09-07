package com.simulator.globalService.atm;

import com.simulator.entities.atm.NdcSiAudioControl;
import com.simulator.entities.atm.NdcSiAudioControlId;
import com.simulator.repository.atm.NdcSiAudioControlRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NdcSiAudioControlService {

	@Autowired
	private NdcSiAudioControlRepository ndcSiAudioControlRepository;
	private static final Logger logger = LoggerFactory.getLogger(NdcSiAudioControlService.class);

	public void saveNewNdcSiAudioControl(NdcSiAudioControl ndcSiAudioControl) {
		logger.info("Starting saveNewNdcSiAudioControl method");
		ndcSiAudioControlRepository.save(ndcSiAudioControl);
		logger.info("Completed saveNewNdcSiAudioControl method");
	}

	public List<NdcSiAudioControl> getAllNdcSiAudioControl() {
		logger.info("Starting getAllNdcSiAudioControl method");
		List<NdcSiAudioControl> result = ndcSiAudioControlRepository.findAll();
		logger.info("Completed getAllNdcSiAudioControl method");
		return result;
	}

	public void updateNdcSiAudioControl(NdcSiAudioControl updatedNdcSiAudioControl, NdcSiAudioControlId id) {
		logger.info("Starting updateNdcSiAudioControl method");
		NdcSiAudioControl existingNdcSiAudioControl = ndcSiAudioControlRepository.findById(id).get();
		updatedNdcSiAudioControl.setId(id);
		existingNdcSiAudioControl = updatedNdcSiAudioControl;
		ndcSiAudioControlRepository.save(existingNdcSiAudioControl);
		logger.info("Completed updateNdcSiAudioControl method");
	}

	public void deleteNdcSiAudioControl(NdcSiAudioControlId id) {
		logger.info("Starting deleteNdcSiAudioControl method");
		ndcSiAudioControlRepository.deleteById(id);
		logger.info("Completed deleteNdcSiAudioControl method");
	}
}