
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
                bat "mvn clean compile" 
        }
    }

         stage('testing stage') {
             steps {
                bat "mvn test"
        }
    }

          stage('deployment stage') {
              steps {
                bat "mvn deploy"
        }
    }

  }

}
