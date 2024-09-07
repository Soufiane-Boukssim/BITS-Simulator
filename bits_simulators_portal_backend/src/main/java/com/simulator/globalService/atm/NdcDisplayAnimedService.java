package com.simulator.globalService.atm;

import com.simulator.entities.atm.NdcDisplayAnimed;
import com.simulator.entities.atm.NdcDisplayAnimedId;
import com.simulator.repository.atm.NdcDisplayAnimedRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NdcDisplayAnimedService {

	@Autowired
	private NdcDisplayAnimedRepository ndcDisplayAnimedRepository;
	private static final Logger logger = LoggerFactory.getLogger(NdcDisplayAnimedService.class);

	public void saveNewNdcDisplayAnimed(NdcDisplayAnimed ndcDisplayAnimed) {
		logger.info("Starting saveNewNdcDisplayAnimed method");
		ndcDisplayAnimedRepository.save(ndcDisplayAnimed);
		logger.info("Completed saveNewNdcDisplayAnimed method");
	}

	public List<NdcDisplayAnimed> getAllNdcDisplayAnimed() {
		logger.info("Starting getAllNdcDisplayAnimed method");
		List<NdcDisplayAnimed> result = ndcDisplayAnimedRepository.findAll();
		logger.info("Completed getAllNdcDisplayAnimed method");
		return result;
	}

	public void updateNdcDisplayAnimed(NdcDisplayAnimed updatedNdcDisplayAnimed, NdcDisplayAnimedId id) {
		logger.info("Starting updateNdcDisplayAnimed method");
		NdcDisplayAnimed existingNdcDisplayAnimed = ndcDisplayAnimedRepository.findById(id).get();
		updatedNdcDisplayAnimed.setId(id);
		existingNdcDisplayAnimed = updatedNdcDisplayAnimed;
		ndcDisplayAnimedRepository.save(existingNdcDisplayAnimed);
		logger.info("Completed updateNdcDisplayAnimed method");
	}

	public void deleteNdcDisplayAnimed(NdcDisplayAnimedId id) {
		logger.info("Starting deleteNdcDisplayAnimed method");
		ndcDisplayAnimedRepository.deleteById(id);
		logger.info("Completed deleteNdcDisplayAnimed method");
	}
}