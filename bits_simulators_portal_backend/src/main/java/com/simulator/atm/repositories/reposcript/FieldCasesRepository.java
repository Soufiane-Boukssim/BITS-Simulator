package com.simulator.atm.repositories.reposcript;

import com.simulator.atm.model.FieldCases;
import com.simulator.atm.model.enm.PopularList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FieldCasesRepository extends JpaRepository<FieldCases, Long> {
    FieldCases findByAtmFieldIdAndPopularListAndValue(Long atmFieldId, PopularList popularList, String value);
    List<FieldCases> findByBankCode(String bankCode);

}
