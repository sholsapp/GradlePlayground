package org.example.buildsystem.extensions

import org.gradle.api.Project
import org.gradle.api.artifacts.repositories.IvyArtifactRepository
import org.gradle.api.artifacts.repositories.IvyPatternRepositoryLayout
import org.gradle.api.artifacts.repositories.RepositoryLayout
import java.net.URL

/**
 * Repository configuration.
 * <p />
 * When dealing with patterns, you have the following keys available:
 * <ul>
 *     <li>organization</li>
 *     <li>module</li>
 *     <li>artifact</li>
 *     <li>revision</li>
 *     <li>classifier</li>
 *     <li>ext</li>
 * </ul>
 */
open class CustomRepositoryExtension(project: Project) {
    var project: Project = project

    /**
     * The repository URL.
     */
    var url: String = "file:////tmp/ivy-repo"

    /**
     * The Ivy pattern used within the repository.
     * <p />
     * That is, the pattern that identifies the Ivy files.
     */
    var ivyPattern: String = "[organisation]/[module]/[revision]/[module]-[revision].ivy"

    /**
     * The artifact pattern used within the repository.
     * <p />
     * That is, the pattern that identifies the artifact files.
     */
    var artifactPattern: String = "[organisation]/[module]/[revision]/[artifact]-[revision].[ext]"

    fun configureIvyRepo(repo: IvyArtifactRepository) {
        repo.name = "localIvyRepo"
        repo.url = URL(this.url).toURI()
        repo.patternLayout { layout: RepositoryLayout ->
            val ivyLayout = layout as IvyPatternRepositoryLayout
            ivyLayout.ivy(this.ivyPattern)
            ivyLayout.artifact(this.artifactPattern)
            // Maven-style layout = false. Allows for '.' notation in
            // the repo layout dir/file names.
            ivyLayout.setM2compatible(false)
        }
    }
}