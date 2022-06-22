package com.projreport.plugin

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.jupiter.api.Test
import org.gradle.testkit.runner.GradleRunner
import static org.gradle.testkit.runner.TaskOutcome.*
import spock.lang.TempDir
import spock.lang.Specification
import static org.junit.jupiter.api.Assertions.assertNotNull

/**
 * Unit testing for the plugin. TODO: generate a test report and compare the outputs of the report vs the plugin report
 */
class ProjReportPluginTest extends Specification {
/*    @TempDir File testProjectDir
    File settingsFile
    File buildFile

    def setup() {
        settingsFile = new File(testProjectDir, 'settings.gradle')
        buildFile = new File(testProjectDir, 'build.gradle')
    }

    def "print text"() {
        given:
        settingsFile << "rootProject.name = 'ProjReport'"
        buildFile << """
       ProjectReport{
            output = project.getName() + ".md"
            outputFile.set(project.layout.buildDirectory.file(output))
       }
        """

        when:
        def result = GradleRunner.create()
                .withProjectDir(testProjectDir)
                .withPluginClasspath()
                .withArguments('ProjectReport')
                .build()

        then:
        result.output.contains('Project Name')
        result.task(":ProjectReport").outcome == SUCCESS

         where:
        gradleVersion << ['7.4']
    }*/


    /**
     * Verify the plugin is findable
     */
    @Test
    void pluginRegistersProjectReportTask() {
        // Create a test project and apply the plugin
        Project project = ProjectBuilder.builder().build()
        project.getPlugins().apply("ProjectReport")

        // Verify the resultProjectReport
        assertNotNull(project.getTasks().findByName("ProjectReport"))
    }
}

