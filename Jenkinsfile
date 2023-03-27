pipeline {
    agent { windows-local }
    stages {
        stage('build') {
            steps {
                sh 'mvn clean test'
            }
        }
    }
}