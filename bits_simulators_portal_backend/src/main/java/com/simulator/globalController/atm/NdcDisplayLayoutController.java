package com.simulator.globalController.atm;

import com.simulator.entities.atm.NdcDisplayLayout;
import com.simulator.entities.atm.NdcDisplayLayoutId;
import com.simulator.globalService.atm.NdcDisplayLayoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/layouts")
public class NdcDisplayLayoutController {

	@Autowired
	private NdcDisplayLayoutService service;

	@GetMapping
	public List<NdcDisplayLayout> getAllLayouts() {
		return service.findAll();
	}

	@GetMapping("/byId")
	public ResponseEntity<NdcDisplayLayout> getLayoutById(
			@RequestParam("profileCode") String profileCode,
			@RequestParam("screenNumber") String screenNumber) {
		NdcDisplayLayoutId id = new NdcDisplayLayoutId();
		id.setProfileCode(profileCode);
		id.setScreenNumber(screenNumber);
		Optional<NdcDisplayLayout> layout = service.findById(id);
		return layout.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping
	public NdcDisplayLayout createLayout(@RequestBody NdcDisplayLayout layout) {
		return service.save(layout);
	}

	@PutMapping
	public ResponseEntity<NdcDisplayLayout> updateLayout(
			@RequestParam("profileCode") String profileCode,
			@RequestParam("screenNumber") String screenNumber,
			@RequestBody NdcDisplayLayout layoutDetails) {

		// Création de l'ID composite à partir des paramètres de l'URL
		NdcDisplayLayoutId id = new NdcDisplayLayoutId();
		id.setProfileCode(profileCode);
		id.setScreenNumber(screenNumber);

		// Recherche de l'élément par son ID
		Optional<NdcDisplayLayout> layout = service.findById(id);
		if (layout.isPresent()) {
			// Si trouvé, mettre à jour les champs et sauvegarder
			layoutDetails.setId(id);  // Assurez-vous que l'ID est bien défini
			return ResponseEntity.ok(service.save(layoutDetails));
		} else {
			// Si non trouvé, renvoyer 404
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping
	public ResponseEntity<Void> deleteLayout(
			@RequestParam("profileCode") String profileCode,
			@RequestParam("screenNumber") String screenNumber) {

		// Créer l'objet ID à partir des paramètres
		NdcDisplayLayoutId id = new NdcDisplayLayoutId();
		id.setProfileCode(profileCode);
		id.setScreenNumber(screenNumber);

		// Vérifier si l'entité existe avant la suppression
		Optional<NdcDisplayLayout> layout = service.findById(id);
		if (layout.isPresent()) {
			service.deleteById(id);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
