package com.selfhealing.report;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class HtmlReportWriter {


    private HtmlReportWriter() {
        // Utility class
    }


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


                writer.write("""
                        <!DOCTYPE html>
                        <html>
                        <head>
                        <title>Self Healing Automation Report</title>

                        <style>

                        body {
                            font-family: Arial, sans-serif;
                            margin: 30px;
                            background: #f5f7fa;
                        }

                        h1 {
                            text-align: center;
                            color: #333;
                        }

                        .summary {
                            display: flex;
                            gap: 20px;
                            margin-bottom: 30px;
                        }

                        .card {
                            background: white;
                            padding: 20px;
                            border-radius: 8px;
                            width: 200px;
                            text-align: center;
                            box-shadow: 0 2px 8px rgba(0,0,0,0.1);
                        }

                        .success {
                            color: green;
                            font-weight: bold;
                        }

                        .failed {
                            color: red;
                            font-weight: bold;
                        }

                        table {
                            width: 100%;
                            border-collapse: collapse;
                            background: white;
                        }

                        th {
                            background: #333;
                            color: white;
                            padding: 10px;
                        }

                        td {
                            padding: 10px;
                            border-bottom: 1px solid #ddd;
                        }

                        tr:hover {
                            background: #f1f1f1;
                        }

                        .confidence {
                            font-weight: bold;
                        }

                        </style>

                        </head>

                        <body>


                        <h1>
                        Self Healing Automation Report
                        </h1>

                        """);


                // Summary cards

                long success =
                        reports.stream()
                                .filter(r ->
                                        "SUCCESS".equalsIgnoreCase(
                                                r.getHealingStatus()))
                                .count();


                writer.write("""
                        <div class="summary">

                        <div class="card">
                        <h3>Total Healing</h3>
                        <h2>
                        """ + reports.size() +
                        """
                        </h2>
                        </div>


                        <div class="card">
                        <h3>Successful</h3>
                        <h2 class="success">
                        """ + success +
                        """
                        </h2>
                        </div>


                        <div class="card">
                        <h3>Failed</h3>
                        <h2 class="failed">
                        """ + (reports.size() - success) +
                        """
                        </h2>
                        </div>


                        </div>
                        """);


                // Table

                writer.write("""
                        <table>

                        <tr>
                        <th>Test Name</th>
                        <th>Failed Locator</th>
                        <th>Healed Locator</th>
                        <th>Confidence</th>
                        <th>Source</th>
                        <th>Status</th>
                        <th>Result</th>
                        <th>Timestamp</th>
                        </tr>
                        """);


                for (HealingReport report : reports) {


                    String status =
                            "SUCCESS".equalsIgnoreCase(
                                    report.getHealingStatus())
                                    ? "success"
                                    : "failed";


                    writer.write(
                            "<tr>" +

                            "<td>" +
                            safe(report.getTestName()) +
                            "</td>" +

                            "<td>" +
                            safe(report.getFailedLocator()) +
                            "</td>" +

                            "<td>" +
                            safe(report.getHealedLocator()) +
                            "</td>" +

                            "<td class='confidence'>" +
                            report.getConfidence() +
                            "</td>" +

                            "<td>" +
                            safe(report.getHealingSource()) +
                            "</td>" +

                            "<td class='" +
                            status +
                            "'>" +
                            safe(report.getHealingStatus()) +
                            "</td>" +

                            "<td>" +
                            safe(report.getTestResult()) +
                            "</td>" +

                            "<td>" +
                            safe(report.getTimestamp()) +
                            "</td>" +

                            "</tr>"
                    );
                }


                writer.write("""
                        </table>

                        </body>
                        </html>
                        """);
            }


        } catch (IOException e) {

            throw new RuntimeException(
                    "Failed to generate HTML healing report",
                    e);
        }
    }


    private static String safe(String value) {

        if (value == null) {
            return "";
        }

        return value
                .replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;");
    }
}