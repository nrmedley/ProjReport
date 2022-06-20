package com.projreport.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

class ProjReportPlugin  implements Plugin<Project> {

    void apply(Project project) {

        project.getTasks().register("greeting", Greeting.class) {
            greeting.set('Hi')

        }
        def producer = project.getTasks().register('producer', Producer.class) {
            // Set values for the producer lazily
            String output = project.getName() + ".md"
            ///renderDependencies(true)
            outputFile.set(project.layout.buildDirectory.file(output))
        }

    }
}