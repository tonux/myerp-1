node {
    stage('Repository') {
        git '/Users/anthony/idea-workspace/ocrp9/src/'
    }

    stage('Maven') {
        sh 'mvn clean install sonar:sonar'
    }
}git