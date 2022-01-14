#!/usr/bin/env groovy

pipeline {
    agent any {
        stages {
            stage(build) {
                script {
                    echo "build a jar file"
                    echo "build an image from jar file using dockerfile"
                }
            }
            stage(test) {
                script {
                    echo "testing the application..."
                }
            }
            stage(deploy) {
                script {
                    echo "Deploying the application..."
                }
            }
        }
    }
}
