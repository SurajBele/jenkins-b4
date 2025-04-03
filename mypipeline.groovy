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
                tool('/usr/lib/jvm/java-11-openjdk-amd64')
                script {
                sh 'mvn clean package'
                echo "building is successful"
                }
            }
        }
        stage('testing') { 
            steps {
                
                withSonarQubeEnv(installationName: 'sonar-server', credentialsId: 'sonar-token') {
                 sh 'mvn clean package sonar:sonar -Dsonar.projectKey=myproject'
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


