pipeline {
    agent any

    environment {
        docker = tool name: 'docker', type: 'org.jenkinsci.plugins.docker.commons.tools.DockerTool'
        mvnHome = tool name: 'Maven 3.6.0', type: 'maven'
    }

    stages {
        stage('Repository') {
            steps {
                git '/Users/anthony/idea-workspace/ocrp9/src/'
            }
        }

        stage('Build') {
            steps {
                sh "${mvnHome}/bin/mvn clean install -P test-consumer,test-business sonar:sonar -Dsonar.projectKey=myerp -Dsonar.host.url=http://localhost:9000/sonarqube -Dsonar.login=b5d60e74d8b4f80b21e4dbba5809edc5a5ec824d"
            }
        }

        stage('Git Push') {
            steps {
                git 'push origin master'
            }
        }
    }

    post {
        always {
            jacoco( 
                execPattern: 'target/*.exec',
                classPattern: 'target/classes',
                sourcePattern: 'src/main/java',
                exclusionPattern: 'src/test*'
            )
        }
    }
}