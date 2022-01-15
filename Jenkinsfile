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
                        sh 'mvn package'
                    }
                }
            }
            stage("build image") {
                steps {
                    script {
                        echo "building an image from jar file.."
                        sh 'docker build -t shashidhar572/nana-java-maven-app:1.0 .'
                        withCredentials([
                                usernamePassword(credentialsId: 'DockerHub', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')
                        ]) {
                            sh "docker login -u $USERNAME -p $PASSWORD"
                            sh "docker push shashidhar572/nana-java-maven-app:1.0"
                        }
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
