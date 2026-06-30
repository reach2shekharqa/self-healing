package com.selfhealing.report;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HealingReportManager {

    private static final List<HealingReport> reports = new ArrayList<>();


    private HealingReportManager() {
        // Utility class
    }


    /**
     * Add a healing report entry
     */
    public static void addReport(HealingReport report) {

        if (report != null) {
            reports.add(report);
        }
    }


    /**
     * Get all healing reports
     */
    public static List<HealingReport> getReports() {

        return Collections.unmodifiableList(reports);
    }


    /**
     * Get total healing attempts
     */
    public static int getTotalHealingAttempts() {

        return reports.size();
    }


    /**
     * Get successful healing count
     */
    public static long getSuccessfulHealingCount() {

        return reports.stream()
                .filter(report ->
                        "SUCCESS".equalsIgnoreCase(
                                report.getHealingStatus()))
                .count();
    }


    /**
     * Clear reports after execution
     */
    public static void clear() {

        reports.clear();
    }
}