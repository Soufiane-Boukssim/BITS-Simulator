package com.simulator.globalService.atm;

import com.simulator.entities.atm.NdcTimersProfile;
import com.simulator.repository.atm.NdcTimersProfileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NdcTimersProfileService {

	@Autowired
	private NdcTimersProfileRepository ndcTimersProfileRepository;
	private static final Logger logger = LoggerFactory.getLogger(NdcTimersProfileService.class);

	public void saveNewNdcTimersProfile(NdcTimersProfile ndcTimersProfile) {
		logger.info("Starting saveNewNdcTimersProfile method");
		ndcTimersProfileRepository.save(ndcTimersProfile);
		logger.info("Completed saveNewNdcTimersProfile method");
	}

	public List<NdcTimersProfile> getAllNdcTimersProfile() {
		logger.info("Starting getAllNdcTimersProfile method");
		List<NdcTimersProfile> result = ndcTimersProfileRepository.findAll();
		logger.info("Completed getAllNdcTimersProfile method");
		return result;
	}

	public void updateNdcTimersProfile(NdcTimersProfile updatedNdcTimersProfile, Character profileCode) {
		logger.info("Starting updateNdcTimersProfile method");
		NdcTimersProfile existingNdcTimersProfile = ndcTimersProfileRepository.findById(profileCode).get();
		updatedNdcTimersProfile.setProfileCode(profileCode);
		existingNdcTimersProfile = updatedNdcTimersProfile;
		ndcTimersProfileRepository.save(existingNdcTimersProfile);
		logger.info("Completed updateNdcTimersProfile method");
	}

	public void deleteNdcTimersProfile(Character profileCode) {
		logger.info("Starting deleteNdcTimersProfile method");
		ndcTimersProfileRepository.deleteById(profileCode);
		logger.info("Completed deleteNdcTimersProfile method");
	}
}