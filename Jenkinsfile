def docker = tool name: 'Docker', type: 'org.jenkinsci.plugins.docker.commons.tools.DockerTool'
def mvnHome = tool name: 'Maven 3.6.0', type: 'maven'
pipeline {
    agent any

    stages {
        stage('Repository') {
            steps {
                git '/Users/anthony/idea-workspace/ocrp9/src/'
            }
        }

        stage('Database') {
            steps {
                sh 'docker container stop dev_myerp.db_1'
                sh 'docker container rm dev_myerp.db_1'
                sh 'docker-compose up --build'
            }
        }

        stage('Build') {
            steps {
                sh "${mvnHome}/bin/mvn clean install sonar:sonar"
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