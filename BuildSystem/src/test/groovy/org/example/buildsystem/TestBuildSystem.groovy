package org.example.buildsystem

import org.example.buildsystem.tasks.TaskPrintBanner
import org.junit.Test
import spock.lang.Specification
import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder

class TestBuildSystem extends Specification {

    @Test
    def "can apply plugin class"() {
        when:
        def project = ProjectBuilder.builder().build() as Project
        project.plugins.apply(BuildSystem)
        then:
        project.plugins.hasPlugin('org.example.buildsystem')
    }

    @Test
    def "has tasks"() {
        when:
        def project = ProjectBuilder.builder().build() as Project
        project.plugins.apply(BuildSystem)
        then:
        project.getTasks().getByName("taskPrintBanner") instanceof TaskPrintBanner
    }

    //@Test
    //def "has local ivy repository"() {
    //    when:
    //    def project = ProjectBuilder.builder().build() as Project
    //    project.plugins.apply(BuildSystem)
    //    then:
    //    project.repositories.findByName("localIvyRepo")
    //}

}
