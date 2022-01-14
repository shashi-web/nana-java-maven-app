#!/usr/bin/env groovy

pipeline {
    agent any
    tools {
        maven 'maven:3.8'
    }
        stages {
            stage("build jar") {
                steps {
                    script {
                        echo "build a jar file"
                        sh "mvn build"
                        sh "mvn package"
                    }
                }
            }
            stage("build image") {
                steps {
                    script {
                        echo "building an image from jar file.."
                        sh "docker build ./target/java-maven-app-1.1.7.jar ."
//                        withCredentials([
//                                usernamePassword(credentials: 'DockerHub', usernameVariable: USERNAME, passwordVariable: PASSWORD)
//                        ]) {
//                            sh "docker login -u ${USERNAME} -p ${PASSWORD}"
//                        }
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
