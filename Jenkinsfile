node {
    def mvnHome = tool name: 'Maven 3.6.0', type: 'maven'

    stage('Repository') {
        git '/Users/anthony/idea-workspace/ocrp9/src/'
    }

    stage('Database') {
        sh '/Users/anthony/idea-workspace/ocrp9/docker/dev/restart.sh'
    }

    stage('Build') {
        sh "${mvnHome}/bin/mvn clean install sonar:sonar"
        jacoco()
    }
}