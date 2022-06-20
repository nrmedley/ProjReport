package com.projreport.plugin

import org.gradle.api.DefaultTask
import org.gradle.api.artifacts.ConfigurationContainer
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.DependencyResolutionListener
import org.gradle.api.artifacts.ResolvableDependencies
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.artifacts.result.ResolvedComponentResult
import org.gradle.api.attributes.Attribute
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.internal.artifacts.configurations.Configurations
import org.gradle.api.provider.Property
import org.gradle.api.reporting.dependencies.HtmlDependencyReportTask
import org.gradle.api.reporting.dependencies.internal.HtmlDependencyReporter
import org.gradle.api.tasks.*
import org.gradle.api.tasks.diagnostics.DependencyReportTask
import org.gradle.api.tasks.diagnostics.PropertyReportTask

import javax.security.auth.login.Configuration
import java.util.concurrent.Callable
import java.util.jar.Attributes

abstract class Producer extends DefaultTask {
        @OutputFile
        abstract RegularFileProperty getOutputFile()


    //  abstract Property<String> renderDependencies()

        @TaskAction
        void produce() throws IOException {
            String projName = "Project Name: " + project.getName()
            String ProjGroup = "Project Group: " + project.getGroup()
            String ProjDesc = "Project Description: " +project.getDescription()
            StringBuilder builder = new StringBuilder()
            builder.append(projName +"\n")
            builder.append(ProjGroup +"\n")
            builder.append(ProjDesc +"\n")

            //add dependencies to report
            DependencyHandler dependencies = project.getDependencies();
            builder.append("Dependencies: " +"\n");
            for(Attribute<?> e: dependencies.getAttributesSchema().getAttributes()) {
                builder.append(e.toString());
            }

            def output = outputFile.get().asFile
            output.text = builder
            logger.quiet("Wrote '${builder}' to ${output}")
        }
    }



