pipeline {
    agent {
        docker  { image '18.06.3-ce' }
        docker { image 'maven:3.6.1-jdk-8' }
        docker  { image '18.06.3-ce' }
    }

    stages {
        stage('Repository') {
            steps {
                git '/Users/anthony/idea-workspace/ocrp9/src/'
            }
        }

        stage('Database') {
            steps {        
                sh "docker-compose up --build"
            }
        }

        stage('Build') {
            steps {
                sh "mvn clean install -P test-consumer,test-business sonar:sonar -Dsonar.projectKey=myerp -Dsonar.host.url=http://localhost:9000/sonarqube -Dsonar.login=b5d60e74d8b4f80b21e4dbba5809edc5a5ec824d"
            }
        }
    }

    post {
        always {
            sh "docker container stop dev_myerp.db_1"
            sh "docker container rm dev_myerp.db_1"
        }
    }
}