pipeline {
    agent { docker { image 'maven:3.8.3-openjdk-17' } }
    stages {
        stage('build') {
            steps {
                sh 'mvn clean test'
            }
        }
    }
}