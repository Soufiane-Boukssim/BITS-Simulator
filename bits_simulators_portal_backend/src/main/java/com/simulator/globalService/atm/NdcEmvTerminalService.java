package com.simulator.globalService.atm;

import com.simulator.entities.atm.NdcEmvTerminal;
import com.simulator.entities.atm.NdcEmvTerminalId;
import com.simulator.repository.atm.NdcEmvTerminalRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NdcEmvTerminalService {

	@Autowired
	private NdcEmvTerminalRepository ndcEmvTerminalRepository;
	private static final Logger logger = LoggerFactory.getLogger(NdcEmvTerminalService.class);

	public void saveNewNdcEmvTerminal(NdcEmvTerminal ndcEmvTerminal) {
		logger.info("Starting saveNewNdcEmvTerminal method");
		ndcEmvTerminalRepository.save(ndcEmvTerminal);
		logger.info("Completed saveNewNdcEmvTerminal method");
	}

	public List<NdcEmvTerminal> getAllNdcEmvTerminal() {
		logger.info("Starting getAllNdcEmvTerminal method");
		List<NdcEmvTerminal> result = ndcEmvTerminalRepository.findAll();
		logger.info("Completed getAllNdcEmvTerminal method");
		return result;
	}

	public void updateNdcEmvTerminal(NdcEmvTerminal updatedNdcEmvTerminal, NdcEmvTerminalId id) {
		logger.info("Starting updateNdcEmvTerminal method");
		NdcEmvTerminal existingNdcEmvTerminal = ndcEmvTerminalRepository.findById(id).get();
		updatedNdcEmvTerminal.setId(id);
		existingNdcEmvTerminal = updatedNdcEmvTerminal;
		ndcEmvTerminalRepository.save(existingNdcEmvTerminal);
		logger.info("Completed updateNdcEmvTerminal method");
	}

	public void deleteNdcEmvTerminal(NdcEmvTerminalId id) {
		logger.info("Starting deleteNdcEmvTerminal method");
		ndcEmvTerminalRepository.deleteById(id);
		logger.info("Completed deleteNdcEmvTerminal method");
	}
}