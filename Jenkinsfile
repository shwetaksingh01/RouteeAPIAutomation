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
                    maven(maven : 'Maven_3.5.2'){
                     sh 'mvn clean compile'
                }
            }
        }
        stage('Test') {
           steps {
                maven(maven : 'Maven_3.5.2')
                {
                     sh 'mvn test'
                }
            }
        }
    }
}
