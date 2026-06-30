package com.selfhealing.report;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CsvReportWriter {


    private CsvReportWriter() {
        // Utility class
    }


    /**
     * Generates CSV healing report
     *
     * @param reports healing report data
     * @param filePath output file location
     */
    public static void write(
            List<HealingReport> reports,
            String filePath) {


        if (reports == null || reports.isEmpty()) {
            return;
        }


        File file = new File(filePath);


        try {

            File parent = file.getParentFile();

            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }


            try (BufferedWriter writer =
                         new BufferedWriter(new FileWriter(file))) {


                // Header
                writer.write(
                        "Test Name," +
                        "Failed Locator," +
                        "Healed Locator," +
                        "Locator Type," +
                        "Confidence," +
                        "Source," +
                        "Exception," +
                        "URL," +
                        "Healing Status," +
                        "Test Result," +
                        "Timestamp"
                );

                writer.newLine();


                // Data rows
                for (HealingReport report : reports) {

                    writer.write(
                            escape(report.getTestName()) + "," +
                            escape(report.getFailedLocator()) + "," +
                            escape(report.getHealedLocator()) + "," +
                            escape(report.getLocatorType()) + "," +
                            report.getConfidence() + "," +
                            escape(report.getHealingSource()) + "," +
                            escape(report.getException()) + "," +
                            escape(report.getUrl()) + "," +
                            escape(report.getHealingStatus()) + "," +
                            escape(report.getTestResult()) + "," +
                            escape(report.getTimestamp())
                    );

                    writer.newLine();
                }
            }


        } catch (IOException e) {

            throw new RuntimeException(
                    "Failed to generate healing CSV report", e);
        }
    }


    /**
     * Handles commas and special characters
     */
    private static String escape(String value) {

        if (value == null) {
            return "";
        }

        if (value.contains(",")
                || value.contains("\"")
                || value.contains("\n")) {

            return "\"" +
                    value.replace("\"", "\"\"")
                    + "\"";
        }

        return value;
    }
}