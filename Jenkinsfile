node {
    stage('Repository') {
        git '/Users/anthony/idea-workspace/ocrp9/src/'
    }

    stage('Maven') {
        def mvnHome = tool name: 'Maven 3.6.0', type: 'maven'
        sh "${mvnHome}/bin/mvn clean install sonar:sonar"
    }
}git