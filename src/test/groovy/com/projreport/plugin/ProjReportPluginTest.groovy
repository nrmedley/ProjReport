
package com.projreport.plugin

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.gradle.testkit.runner.BuildResult
import org.gradle.testkit.runner.TaskOutcome
import org.junit.jupiter.api.Test
import org.gradle.testkit.runner.GradleRunner
import static org.gradle.testkit.runner.TaskOutcome.*
import spock.lang.TempDir
import spock.lang.Specification

import static org.junit.jupiter.api.Assertions.assertNotNull

class ProjReportPluginTest extends Specification {
    @TempDir File testProjectDir
    File settingsFile
    File buildFile

    def setup() {
        settingsFile = new File(testProjectDir, 'settings.gradle')
        buildFile = new File(testProjectDir, 'build.gradle')
    }

    def "hello world task prints hello world"() {
        given:
        settingsFile << "rootProject.name = 'ProjReport'"
        buildFile << """
            task greet {
                doLast {
                    println 'Hello world!'
                }
            }
        """

        when:
        def result = GradleRunner.create()
                .withProjectDir(testProjectDir)
                .withArguments('greet')
                .build()

        then:
        result.output.contains('Hello world!')
        result.task(":greet").outcome == SUCCESS

        where:
        gradleVersion << ['7.x']
    }

    @Test
    void pluginRegistersGreetingTask() {
        // Create a test project and apply the plugin
        Project project = ProjectBuilder.builder().build();
        project.getPlugins().apply("com.projreport");

        // Verify the result
        assertNotNull(project.getTasks().findByName("producer"));
        assertNotNull(project.getTasks().findByName("greeting"));
    }
}

