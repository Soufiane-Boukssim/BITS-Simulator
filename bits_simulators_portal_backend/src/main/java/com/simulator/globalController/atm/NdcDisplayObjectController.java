package com.simulator.globalController.atm;

import com.simulator.entities.atm.NdcDisplayObject;
import com.simulator.entities.atm.NdcDisplayObjectId;
import com.simulator.globalService.atm.NdcDisplayObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NdcDisplayObjectController {

	@Autowired
	private NdcDisplayObjectService ndcDisplayObjectService;

	@PostMapping("/ndcDisplayObject")
	public void saveNdcDisplayObject(@RequestBody NdcDisplayObject ndcDisplayObject) {
		ndcDisplayObjectService.saveNewNdcDisplayObject(ndcDisplayObject);
	}

	@GetMapping("/ndcDisplayObject")
	public List<NdcDisplayObject> getAllNdcDisplayObject() {
		return ndcDisplayObjectService.getAllNdcDisplayObject();
	}

	@PutMapping("/ndcDisplayObject/{_profileCode}/{_screenNumber}")
	public void updateNdcDisplayObject(@RequestBody NdcDisplayObject ndcDisplayObject,
			@PathVariable String _profileCode, @PathVariable String _screenNumber) {
		NdcDisplayObjectId id = new NdcDisplayObjectId();
		id.setProfileCode(_profileCode);
		id.setScreenNumber(_screenNumber);
		ndcDisplayObject.setId(id);
		ndcDisplayObjectService.updateNdcDisplayObject(ndcDisplayObject, id);
	}

	@DeleteMapping("/ndcDisplayObject")
	public String deleteNdcDisplayObject(@RequestBody NdcDisplayObjectId id) {
		ndcDisplayObjectService.deleteNdcDisplayObject(id);
		return "Deleted Successfully";
	}
}