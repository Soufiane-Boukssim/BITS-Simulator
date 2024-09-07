package com.simulator.globalService.atm;

import com.simulator.entities.atm.NdcProfile;
import com.simulator.repository.atm.NdcProfileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NdcProfileService {

	@Autowired
	private NdcProfileRepository ndcProfileRepository;
	private static final Logger logger = LoggerFactory.getLogger(NdcProfileService.class);

	public void saveNewNdcProfile(NdcProfile ndcProfile) {
		logger.info("Starting saveNewNdcProfile method");
		ndcProfileRepository.save(ndcProfile);
		logger.info("Completed saveNewNdcProfile method");
	}

	public List<NdcProfile> getAllNdcProfile() {
		logger.info("Starting getAllNdcProfile method");
		List<NdcProfile> result = ndcProfileRepository.findAll();
		logger.info("Completed getAllNdcProfile method");
		return result;
	}

	public void updateNdcProfile(NdcProfile updatedNdcProfile, String profileCode) {
		logger.info("Starting updateNdcProfile method");
		NdcProfile existingNdcProfile = ndcProfileRepository.findById(profileCode).get();
		updatedNdcProfile.setProfileCode(profileCode);
		existingNdcProfile = updatedNdcProfile;
		ndcProfileRepository.save(existingNdcProfile);
		logger.info("Completed updateNdcProfile method");
	}

	public void deleteNdcProfile(String profileCode) {
		logger.info("Starting deleteNdcProfile method");
		ndcProfileRepository.deleteById(profileCode);
		logger.info("Completed deleteNdcProfile method");
	}
}