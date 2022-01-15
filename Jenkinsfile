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
                    sh 'mvn build-helper:parse-version versions:set \
                            -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVersion.nextIncrementalVersion} \
                             versions:commit'
                    def matcher = readFile('pom.xml') =~ '<version>(.+)</version>'
                    def version = matcher[0][1]
                    env.IMAGE_NAME = "$version-$BUILD_NUMBER"
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
        stage('commit version update') {
            steps {
                script {
                    echo "Commiting the incremented version to github"
                    gv.commitversioupdate()
                }
            }

        }

    }

}