pipeline {
    agent any
    environment {
        EMAIL_RECIPIENTS = 'silvere.djam@gmail.com'
    }
	
    tools{
    	jdk 'jdk8'
		maven 'maven'    
    }

    stages{    
	    
		stage('Code Analysis'){
			steps{
				def mvn = tool 'maven';
				withSonarQubeEnv() {
					sh "${mvn}/bin/mvn clean verify sonar:sonar"
				}
			}
		}
       
        stage('Build'){
			steps{
				echo 'Checking out from Git Repo' 
				git 'https://github.com/deepdream-apps/vehicleseller.git'
				bat 'mvn -f vehicleseller-backend/pom.xml -DskipTests=true  clean package'
				echo 'Build stage executed successfully'
			}
		}
	   
		stage('Test'){
			steps{
				bat 'java -jar --spring.profiles.active=test vehicleseller-backend/target/vehicleseller-backend.jar'
				echo 'Test stage executed successfully'
			}
		}
	    
		stage('Deploy'){
			steps{
				echo 'Deploy the project'
			}
		}
    }
	
	post{
	
		always{
		
		}
		
		success{
			echo 'The pipline executed successfully'
		}
		
		failure{
			echo 'The pipline execution failed'
		}
	}
	
   
}
