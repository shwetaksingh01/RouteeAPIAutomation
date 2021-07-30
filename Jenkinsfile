
pipeline {

    agent any
//     tools {
//         maven 'Maven_3.5.2' 
//     }
    stages {
          stage('Checkout') {
            steps {
                 checkout scm
            }
        }
        stage('Compile stage') {
            steps {
                sh "mvn clean compile" 
        }
    }

         stage('testing stage') {
             steps {
                sh "mvn test"
        }
    }

  }

}
