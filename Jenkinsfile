pipeline {
    agent {
        dockerfile {
            args  '--net="jenkinsseleniumjava_jenkins"'
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
