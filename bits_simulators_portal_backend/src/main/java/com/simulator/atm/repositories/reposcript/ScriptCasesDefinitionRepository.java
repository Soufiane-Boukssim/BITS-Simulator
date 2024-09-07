package com.simulator.atm.repositories.reposcript;

import com.simulator.atm.model.ScriptCasesDefinition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScriptCasesDefinitionRepository extends JpaRepository<ScriptCasesDefinition, Long> {

    List<ScriptCasesDefinition> findByBankCode(String bankCode);

    ScriptCasesDefinition findByLibelle(String libelle);
}
