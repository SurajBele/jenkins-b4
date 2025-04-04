pipeline {
    agent  {
        label 'node1'
    }
    // If you are having multiple java versions, and need to define different version for
    // different stages so set env as below.
    environment {
        JAVA_11_HOME = "/usr/lib/jvm/java-11-openjdk-amd64"
        JAVA_17_HOME = "/usr/lib/jvm/java-17-openjdk-amd64"
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
                script {
                    env.JAVA_HOME = env.JAVA_11_HOME
                    env.PATH = "${env.JAVA_HOME}/bin:${env.PATH}"
                }
                sh 'java -version'  // Verify Java 11 is active
                sh 'mvn clean package'
                echo "building is successful"
            }
        }
        stage('testing') { 
            steps {
                script {
                    // Switch to Java 17 for SonarQube
                    env.JAVA_HOME = env.JAVA_17_HOME
                    env.PATH = "${env.JAVA_HOME}/bin:${env.PATH}"
                }
                sh 'java -version'  // Verify Java 17 is active
                withSonarQubeEnv(installationName: 'sonar-server', credentialsId: 'sonar-token') {
                 sh 'mvn sonar:sonar -Dsonar.projectKey=myproject'
                }
                echo "testing is successful "
            }
        }
        stage('Deploy') { 
            steps {
                echo "Deploy is successful "
            }
        }
    }
}


