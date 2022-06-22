package com.projreport.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Register the task and generate the project report
 */
class ProjReportPlugin implements Plugin<Project> {

    void apply(Project project) {
        project.getTasks().register('ProjectReport', ProjectReport.class, projReportTask -> {
            // Set values for the producer lazily
            String output = project.getName() + ".md"
            outputFile.set(project.layout.buildDirectory.file(output))
        });
    }
}