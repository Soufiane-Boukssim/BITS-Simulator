package com.simulator.globalService.atm;

import com.simulator.entities.atm.NdcSiterogSetIccTrxData;
import com.simulator.entities.atm.NdcSiterogSetIccTrxDataId;
import com.simulator.repository.atm.NdcSiterogSetIccTrxDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NdcSiterogSetIccTrxDataService {

	@Autowired
	private NdcSiterogSetIccTrxDataRepository ndcSiterogSetIccTrxDataRepository;
	private static final Logger logger = LoggerFactory.getLogger(NdcSiterogSetIccTrxDataService.class);

	public void saveNewNdcSiterogSetIccTrxData(NdcSiterogSetIccTrxData ndcSiterogSetIccTrxData) {
		logger.info("Starting saveNewNdcSiterogSetIccTrxData method");
		ndcSiterogSetIccTrxDataRepository.save(ndcSiterogSetIccTrxData);
		logger.info("Completed saveNewNdcSiterogSetIccTrxData method");
	}

	public List<NdcSiterogSetIccTrxData> getAllNdcSiterogSetIccTrxData() {
		logger.info("Starting getAllNdcSiterogSetIccTrxData method");
		List<NdcSiterogSetIccTrxData> result = ndcSiterogSetIccTrxDataRepository.findAll();
		logger.info("Completed getAllNdcSiterogSetIccTrxData method");
		return result;
	}

	public void updateNdcSiterogSetIccTrxData(NdcSiterogSetIccTrxData updatedNdcSiterogSetIccTrxData,
			NdcSiterogSetIccTrxDataId id) {
		logger.info("Starting updateNdcSiterogSetIccTrxData method");
		NdcSiterogSetIccTrxData existingNdcSiterogSetIccTrxData = ndcSiterogSetIccTrxDataRepository.findById(id).get();
		updatedNdcSiterogSetIccTrxData.setId(id);
		existingNdcSiterogSetIccTrxData = updatedNdcSiterogSetIccTrxData;
		ndcSiterogSetIccTrxDataRepository.save(existingNdcSiterogSetIccTrxData);
		logger.info("Completed updateNdcSiterogSetIccTrxData method");
	}

	public void deleteNdcSiterogSetIccTrxData(NdcSiterogSetIccTrxDataId id) {
		logger.info("Starting deleteNdcSiterogSetIccTrxData method");
		ndcSiterogSetIccTrxDataRepository.deleteById(id);
		logger.info("Completed deleteNdcSiterogSetIccTrxData method");
	}
}