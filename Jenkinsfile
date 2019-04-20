pipeline {
    agent any

    environment {
        docker = tool name: 'docker', type: 'org.jenkinsci.plugins.docker.commons.tools.DockerTool'
        mvnHome = tool name: 'Maven 3.6.0', type: 'maven'
        sonar = tool name: 'Sonarqube Scanner', type: 'hudson.plugins.sonar.SonarRunnerInstallation'
    }

    stages {
        stage('Repository') {
            steps {
                git '/Users/anthony/idea-workspace/ocrp9/src/'
            }
        }

        stage('Database') {
            steps {
                sh "${docker} docker container stop dev_myerp.db_1"
                sh "${docker} docker container rm dev_myerp.db_1"
                sh "${docker} docker-compose up --build"
            }
        }

        stage('Build') {
            steps {
                sh "${mvnHome}/bin/mvn clean install sonar:sonar -Dsonar.projectKey=myerp -Dsonar.host.url=http://localhost:9000/sonarqube -Dsonar.login=b5d60e74d8b4f80b21e4dbba5809edc5a5ec824d"
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