pipeline {
    agent any

    environment {
        docker = tool name: 'docker', type: 'org.jenkinsci.plugins.docker.commons.tools.DockerTool'
        mvnHome = tool name: 'Maven 3.6.0', type: 'maven'
    }

    stages {
        stage('Repository') {
            steps {
                git 'https://github.com/getantazri/myerp'
            }
        }

        stage('Build') {
            steps {
                sh "${mvnHome}/bin/mvn clean install -P test-consumer,test-business sonar:sonar -Dsonar.projectKey=MyERP -Dsonar.host.url=http://51.77.230.10:9000 -Dsonar.login=6122654de79d360c392fcbfe6e0e06432343798a
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