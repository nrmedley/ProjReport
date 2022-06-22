package com.projreport.plugin

import org.gradle.api.DefaultTask
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.attributes.Attribute
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.tasks.*

/**
 * Build the project report
 */
abstract class ProjectReport extends DefaultTask {

    @OutputFile
    abstract RegularFileProperty getOutputFile()

    /**
     * Adds the report text to the file
     * @throws IOException
     */
    @TaskAction
    void produceReport() throws IOException {
        def output = outputFile.get().asFile
        output.text = addProjectProperties(true)
        logger.quiet("Wrote '${addProjectProperties(true)}' to ${output}")
    }

    /**
     * build report properties and dependencies
     * @param renderDependencies
     * @return
     */
    private StringBuilder addProjectProperties(boolean renderDependencies) {
        String projName = "Project Name: " + project.getName()
        String ProjGroup = "Project Group: " + project.getGroup()
        String ProjDesc = "Project Description: " + project.getDescription()
        StringBuilder builder = new StringBuilder()
        builder.append(projName + "\n")
        builder.append(ProjGroup + "\n")
        builder.append(ProjDesc + "\n")

        //add dependencies to report, TODO: Figure out how to get dependency elements
        if (renderDependencies) {
            DependencyHandler dependencies = project.getDependencies();
            builder.append("Dependencies: " + "\n");
            for (Attribute<?> e : dependencies.getAttributesSchema().getAttributes()) {
                builder.append(e.toString());
            }
        }
        return builder;
    }
}



