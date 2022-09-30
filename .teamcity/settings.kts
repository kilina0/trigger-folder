import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildFeatures.perfmon
import jetbrains.buildServer.configs.kotlin.projectFeatures.activeStorage
import jetbrains.buildServer.configs.kotlin.projectFeatures.s3Storage
import jetbrains.buildServer.configs.kotlin.triggers.vcs

/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), buildType(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/

version = "2022.04"

project {

    buildType(Build)

    features {
        activeStorage {
            id = "PROJECT_EXT_152"
            activeStorageID = "storage1"
        }
        s3Storage {
            id = "storage1"
            bucketName = "kilina-bucket"
            awsEnvironment = default {
                awsRegionName = "us-east-2"
            }
            accessKeyID = "AKIA5JH2VERVKKW5RO7U"
            accessKey = "credentialsJSON:3f36ae03-b7d1-4828-b1cf-6c10037eb2e2"
        }
        s3Storage {
            id = "storage2"
            bucketName = "kilina-bucket2-private"
            awsEnvironment = default {
                awsRegionName = "us-east-2"
            }
            accessKeyID = "AKIA5JH2VERVKKW5RO7U"
            accessKey = "credentialsJSON:3f36ae03-b7d1-4828-b1cf-6c10037eb2e2"
        }
    }
}

object Build : BuildType({
    name = "Build"

    artifactRules = """
        Source1 => Source1.zip
        test1 => test1.zip
        README.md
    """.trimIndent()

    vcs {
        root(DslContext.settingsRoot)
    }

    triggers {
        vcs {
        }
    }

    features {
        perfmon {
        }
    }
})
