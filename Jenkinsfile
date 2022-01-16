pipeline {
    agent {
        dockerfile {
            args  '--network=host'
        }
    }
    stages {
        stage('Test') {
            steps {
                withCredentials([file(credentialsId: 'credentials', variable: 'CREDENTIALS')]) {
                    sh 'cat $CREDENTIALS > credentials.csv'
                    sh 'mvn test'
                }
            }
        }
    }
}
