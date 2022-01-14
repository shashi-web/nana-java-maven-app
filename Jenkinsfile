#!/usr/bin/env groovy

pipeline {
    agent any
        stages {
            stage(build) {
                steps {
                    script {
                        echo "build a jar file"
                        sh "npm --version"
                        echo "build an image from jar file using dockerfile"
                    }
                }
            }
            stage(test) {
                steps {
                    script {
                        echo "testing the application..."
                    }
                }
            }
            stage(deploy) {
                steps {
                    script {
                        echo "Deploying the application..."
                    }
                }
            }
        }
}
