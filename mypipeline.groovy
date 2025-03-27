pipeline {
    agent  {
        label 'node1'
    }
    stages {
        stage('pull') { 
            steps {
                git 'https://github.com/SurajBele/studentapp.ui.git'
                echo "pull is successful"
            }
        }
        stage('building') { 
            steps {
                sh 'sudo mvn clean package'
                echo "building is successful"
            }
        }
        stage('testing') { 
            steps {
                echo "testing is successful "
            }
        }
        stage('Deploy') { 
            steps {
                echo "Deployment is successful "
            }
        }
    }
}
