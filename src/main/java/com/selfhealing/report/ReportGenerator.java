package com.selfhealing.report;

public class ReportGenerator {


    private ReportGenerator() {
        // Utility class
    }


    /**
     * Generates all healing reports
     */
    public static void generate() {


        String reportDirectory =
                "target";


        String csvPath =
                reportDirectory + "/healing-report.csv";


        String htmlPath =
                reportDirectory + "/healing-report.html";



        CsvReportWriter.write(
                HealingReportManager.getReports(),
                csvPath
        );


        HtmlReportWriter.write(
                HealingReportManager.getReports(),
                htmlPath
        );


        System.out.println(
                "\n========== Healing Report Generated =========="
        );


        System.out.println(
                "CSV Report : "
                        + csvPath
        );


        System.out.println(
                "HTML Report: "
                        + htmlPath
        );


        System.out.println(
                "==============================================\n"
        );

        HealingReportManager.clear();
    }
}