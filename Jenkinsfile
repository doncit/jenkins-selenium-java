pipeline {
    agent {
        dockerfile {
            args  '--network=host'
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
