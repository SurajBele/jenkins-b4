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
                sh 'mvn clean package'
                echo "building is successful"
            }
        }
        stage('testing') { 
            steps {
                withSonarQubeEnv(credentialsId: 'sonar-token') {
                 sh 'mvn clean package sonar:sonar \
                        -Dsonar.projectKey=myproject \
                        -Dsonar.projectName='myproject''
                }
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


