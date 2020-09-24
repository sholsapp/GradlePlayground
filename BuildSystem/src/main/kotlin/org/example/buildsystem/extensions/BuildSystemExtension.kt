package org.example.buildsystem.extensions

import groovy.lang.Closure
import groovy.lang.DelegatesTo
import org.gradle.api.Project

/**
 * Image configuration.
 */
open class BuildSystemExtension(project: Project) {
    var project: Project = project

    /**
     * An extension to configure a (likely local) Ivy repository.
     */
    var repo: CustomRepositoryExtension = project.extensions.create("repo", CustomRepositoryExtension::class.java, project)

    /**
     * Delegate to the repository extension.
     */
    fun repo(@DelegatesTo(CustomRepositoryExtension::class) cl: Closure<Any>) {
        cl.delegate = repo
        cl.resolveStrategy = Closure.DELEGATE_FIRST
        cl.call()
    }

}