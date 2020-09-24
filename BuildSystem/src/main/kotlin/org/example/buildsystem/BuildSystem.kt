package org.example.buildsystem

import org.example.buildsystem.extensions.BuildSystemExtension
import org.example.buildsystem.tasks.TaskPrintBanner
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.artifacts.dsl.DependencyLockingHandler
import org.gradle.api.artifacts.dsl.LockMode
import org.gradle.api.artifacts.repositories.IvyArtifactRepository
import org.gradle.api.artifacts.repositories.IvyPatternRepositoryLayout
import org.gradle.api.artifacts.repositories.RepositoryLayout
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.ivy.plugins.IvyPublishPlugin
import org.gradle.internal.impldep.org.apache.ivy.Ivy
import org.gradle.internal.impldep.org.apache.ivy.ant.IvyPublish
import java.net.URL

class BuildSystem : Plugin<Project> {

    companion object {
        const val TASK_BANNER: String = "taskPrintBanner"
    }

    /**
     * Apply our custom plugin.
     */
    override fun apply(project: Project) {

        // Create and load the extension.
        val extension = project.extensions.create(
            "buildsystem",
            BuildSystemExtension::class.java,
            project)

        // Add/configure additional plugins that clients need.
        project.pluginManager.apply(IvyPublishPlugin::class.java)

        // Add our example task.
        val printBannerTask: Task = project.tasks.create(
            TASK_BANNER,
            TaskPrintBanner::class.java)

        // After the client has evaluated/configured their own project...
        project.afterEvaluate {
            // Wire in our example task to the task dependency graph.
            project.tasks.findByName("build")?.dependsOn(printBannerTask)

            // Enable dependency locking.
            project.dependencyLocking { lockHandler: DependencyLockingHandler ->
                lockHandler.lockAllConfigurations()
            }

            // Add our repository (for dependencies).
            project.repositories.ivy { repo: IvyArtifactRepository ->
                extension.repo.configureIvyRepo(repo)
            }

            // Add our repository (for publications).
            val publishing: PublishingExtension =
                project.extensions.getByType(PublishingExtension::class.java)
            publishing.repositories.ivy { repo: IvyArtifactRepository ->
                extension.repo.configureIvyRepo(repo)
            }
        }
    }
}