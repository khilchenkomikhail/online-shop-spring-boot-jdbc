pipeline {
    agent { docker { image 'maven:3.9.0-eclipse-temurin-17' } }
    stages {
        stage('build') {
            steps {
                git 'https://github.com/khilchenkomikhail/online-shop-spring-boot-jdbc/projects?query=is%3Aopen'
                sh 'mvn clean test -Dmaven.test.failure.ignore=false'
            }
        }
    }
}