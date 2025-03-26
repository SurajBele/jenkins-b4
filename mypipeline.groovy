pipeline {
    agent  {
        label 'node1'
    }
    stages {
        stage('Build') { 
            steps {
                echo "building is successful"
            }
        }
        stage('Test') { 
            steps {
                echo "testinig is successful"
            }
        }
        stage('Deploy') { 
            steps {
                echo "Deployment is successful "
            }
        }
    }
}
