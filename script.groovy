def buildjar() {
    sh 'mvn package'
}

def buildimage() {
    sh 'docker build -t shashidhar572/nana-java-maven-app:${IMAGE_NAME} .'
}

def deployimage() {
    withCredentials([
            usernamePassword(credentialsId: 'DockerHub', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')
    ]) {
        sh "echo $PASSWORD | docker login -u $USERNAME --password-stdin"
        sh "docker push shashidhar572/nana-java-maven-app:${IMAGE_NAME}"
    }
}

def incrementversion() {
    sh 'mvn build-helper:parse-version version:set \
                            -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVersion.nextIncrementVersion} \
                             versions:commit'
    def matcher = readFile('pom.xml') =~ '<version>(.+)</version>'
    def version = matcher[0][1]
    env.IMAGE_NAME = "$version-$BUILD_NUMBER"
}

return this