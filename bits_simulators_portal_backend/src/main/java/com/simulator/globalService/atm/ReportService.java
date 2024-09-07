package com.simulator.globalService.atm;

import com.simulator.entities.atm.Report;
import com.simulator.repository.atm.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    // Save or update a Report
    public Report saveOrUpdateReport(Report report) {
        return reportRepository.save(report);
    }

    // Get a Report by profile
    public Optional<Report> getReportByProfile(String profile) {
        return reportRepository.findById(profile);
    }

    // Get all Reports
    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }

    // Delete a Report by profile
    public void deleteReportByProfile(String profile) {
        reportRepository.deleteById(profile);
    }
}
