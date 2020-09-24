package org.example.buildsystem.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

/**
 * Be noisy.
 */
open class TaskPrintBanner : DefaultTask() {
    @TaskAction
    fun doTask() {
        val banner = """
             ____        _ __    _______            __               
           / __ )__  __(_) /___/ / ___/__  _______/ /____  ____ ___ 
          / __  / / / / / / __  /\__ \/ / / / ___/ __/ _ \/ __ `__ \
         / /_/ / /_/ / / / /_/ /___/ / /_/ (__  ) /_/  __/ / / / / /
        /_____/\__,_/_/_/\__,_//____/\__, /____/\__/\___/_/ /_/ /_/ 
                                    /____/
        """.trimIndent()
        project.logger.lifecycle(banner)
    }
}