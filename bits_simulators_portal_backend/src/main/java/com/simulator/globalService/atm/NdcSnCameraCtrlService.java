package com.simulator.globalService.atm;

import com.simulator.entities.atm.NdcSnCameraCtrl;
import com.simulator.entities.atm.NdcSnCameraCtrlId;
import com.simulator.repository.atm.NdcSnCameraCtrlRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NdcSnCameraCtrlService {

	@Autowired
	private NdcSnCameraCtrlRepository ndcSnCameraCtrlRepository;
	private static final Logger logger = LoggerFactory.getLogger(NdcSnCameraCtrlService.class);

	public void saveNewNdcSnCameraCtrl(NdcSnCameraCtrl ndcSnCameraCtrl) {
		logger.info("Starting saveNewNdcSnCameraCtrl method");
		ndcSnCameraCtrlRepository.save(ndcSnCameraCtrl);
		logger.info("Completed saveNewNdcSnCameraCtrl method");
	}

	public List<NdcSnCameraCtrl> getAllNdcSnCameraCtrl() {
		logger.info("Starting getAllNdcSnCameraCtrl method");
		List<NdcSnCameraCtrl> result = ndcSnCameraCtrlRepository.findAll();
		logger.info("Completed getAllNdcSnCameraCtrl method");
		return result;
	}

	public void updateNdcSnCameraCtrl(NdcSnCameraCtrl updatedNdcSnCameraCtrl, NdcSnCameraCtrlId id) {
		logger.info("Starting updateNdcSnCameraCtrl method");
		NdcSnCameraCtrl existingNdcSnCameraCtrl = ndcSnCameraCtrlRepository.findById(id).get();
		updatedNdcSnCameraCtrl.setId(id);
		existingNdcSnCameraCtrl = updatedNdcSnCameraCtrl;
		ndcSnCameraCtrlRepository.save(existingNdcSnCameraCtrl);
		logger.info("Completed updateNdcSnCameraCtrl method");
	}

	public void deleteNdcSnCameraCtrl(NdcSnCameraCtrlId id) {
		logger.info("Starting deleteNdcSnCameraCtrl method");
		ndcSnCameraCtrlRepository.deleteById(id);
		logger.info("Completed deleteNdcSnCameraCtrl method");
	}
}