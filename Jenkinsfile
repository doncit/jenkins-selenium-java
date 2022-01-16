pipeline {
    agent {
        dockerfile {
            args  '--net="jenkins"'
        }
    }
    stages {
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
    }
}
