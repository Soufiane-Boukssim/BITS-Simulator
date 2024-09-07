package com.simulator.globalService.atm;

import com.simulator.entities.atm.NdcDisplayLayout;
import com.simulator.entities.atm.NdcDisplayLayoutId;
import com.simulator.repository.atm.NdcDisplayLayoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NdcDisplayLayoutService {

	@Autowired
	private NdcDisplayLayoutRepository repository;

	public List<NdcDisplayLayout> findAll() {
		return repository.findAll();
	}

	public Optional<NdcDisplayLayout> findById(NdcDisplayLayoutId id) {
		return repository.findById(id);
	}

	public NdcDisplayLayout save(NdcDisplayLayout layout) {
		return repository.save(layout);
	}

	public void deleteById(NdcDisplayLayoutId id) {
		repository.deleteById(id);
	}
}
