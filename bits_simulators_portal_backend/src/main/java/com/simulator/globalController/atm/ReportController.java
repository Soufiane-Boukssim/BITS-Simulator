package com.simulator.globalController.atm;

import com.simulator.entities.atm.Report;
import com.simulator.globalService.atm.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    // Create or update a Report
    @PostMapping
    public ResponseEntity<Report> createOrUpdateReport(@RequestBody Report report) {
        Report savedReport = reportService.saveOrUpdateReport(report);
        return ResponseEntity.ok(savedReport);
    }

    // Get a Report by profile
    @GetMapping("/{profile}")
    public ResponseEntity<Report> getReportByProfile(@PathVariable String profile) {
        Optional<Report> report = reportService.getReportByProfile(profile);
        return report.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get all Reports
    @GetMapping
    public ResponseEntity<List<Report>> getAllReports() {
        List<Report> reportList = reportService.getAllReports();
        return ResponseEntity.ok(reportList);
    }

    // Delete a Report by profile
    @DeleteMapping("/{profile}")
    public ResponseEntity<Void> deleteReportByProfile(@PathVariable String profile) {
        reportService.deleteReportByProfile(profile);
        return ResponseEntity.noContent().build();
    }
}
