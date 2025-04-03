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
            tools {
                jdk 'openjdk11'            
            }
            steps {
                sh 'mvn clean package'
                echo "building is successful"
            }
        }
        stage('testing') { 
            steps {
                script {
                    // Switch to Java 17 for SonarQube
                    env.JAVA_HOME = tool 'openjdk17'
                    env.PATH = "${env.JAVA_HOME}/bin:${env.PATH}"
                }
                sh 'java -version'  // Verify Java 17 is active
                withSonarQubeEnv(installationName: 'sonar-server', credentialsId: 'sonar-token') {
                 sh 'mvn varify sonar:sonar -Dsonar.projectKey=myproject'
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


