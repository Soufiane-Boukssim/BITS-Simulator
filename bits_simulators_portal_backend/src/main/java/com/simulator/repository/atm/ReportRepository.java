package com.simulator.repository.atm;

import com.simulator.entities.atm.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, String> {

}
