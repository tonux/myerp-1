pipeline {
    agent any

    environment {
        mvnHome = tool name: 'Maven 3.6.0', type: 'maven'
    }

    stages {
        stage('Build') {
            steps {
                sh "${mvnHome}/bin/mvn clean install -P test-consumer,test-business sonar:sonar -Dsonar.projectKey=MyERP -Dsonar.host.url=http://51.77.230.10:9000 -Dsonar.login=6122654de79d360c392fcbfe6e0e06432343798a
            }
        }
    }
}