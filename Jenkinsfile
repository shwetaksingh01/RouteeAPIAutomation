pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                 checkout scm
            }
        }
        stage('Compile') {
            steps {
                withMaven(maven : 'maven_3_5_0')
                {
                     sh 'mvn clean compile'
                }
            }
        }
        stage('Test') {
           steps {
                withMaven(maven : 'maven_3_5_0')
                {
                     sh 'mvn test'
                }
            }
        }
    }
}
