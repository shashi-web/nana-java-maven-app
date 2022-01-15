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

def commitversionupdate() {
    withCredentials([usernamePassword(credentialsId: 'github', passwordVariable: 'PASSWORD', usernameVariable: 'USERNAME')]) {
        // git config here for the first time run
        sh 'git config --global user.email "jenkins@example.com"'
        sh 'git config --global user.name "jenkins"'

        sh "git remote set-url origin https://${USERNAME}:${PASSWORD}@github.com/shashi-web/nana-java-maven-app.git"
        sh 'git add .'
        sh 'git commit -m "ci: version bump"'
        sh 'git push origin HEAD:jenkins-jobs'
    }
}

return this