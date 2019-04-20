node {
    def mvnHome = tool name: 'Maven 3.6.0', type: 'maven'

    stage('Repository') {
            git '/Users/anthony/idea-workspace/ocrp9/src/'
    }

    stage('Database') {
        sh 'docker container stop dev_myerp.db_1'
        sh 'docker container rm dev_myerp.db_1'
        sh 'docker-compose up --build'
    }

    stage('Build') {
            sh "${mvnHome}/bin/mvn clean install sonar:sonar"
            jacoco( 
                execPattern: 'target/*.exec',
                classPattern: 'target/classes',
                sourcePattern: 'src/main/java',
                exclusionPattern: 'src/test*'
            )
    }
}