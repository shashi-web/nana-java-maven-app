#!/usr/bin/env groovy

def gv

pipeline {
    agent any
    tools {
        maven 'maven:3.8'
    }
        stages {
            stage("init") {
                steps {
                    script {
                        gv = load "script.groovy"
                    }
                }
            }
            stage('Increment version') {
                steps {
                    script {
                        echo 'incrementing the app version'
                        gv.incrementversion()
                    }
                }
            }
            stage("build jar") {
                steps {
                    script {
                        echo "build a jar file"
                        gv.buildjar()
                    }
                }
            }
            stage("build image") {
                steps {
                    script {
                        echo "building an image from jar file.."
                        gv.buildimage()
                    }
                }
            }
            stage(deploy) {
                steps {
                    script {
                        echo "Deploying the Image into DockerHub..."
                        gv.deployimage()
                    }
                }
            }
        }
}
