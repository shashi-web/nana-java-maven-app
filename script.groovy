def buildjar() {
    sh 'mvn package'
}

def buildimage() {
    sh "docker build -t shashidhar572/nana-java-maven-app:${IMAGE_NAME} ."
}

def deployimage() {
    withCredentials([
            usernamePassword(credentialsId: 'DockerHub', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')
    ]) {
        sh "echo $PASSWORD | docker login -u $USERNAME --password-stdin"
        sh "docker push shashidhar572/nana-java-maven-app:${IMAGE_NAME}"
    }
}

return this