pipeline {
    agent any
    environment {
        EMAIL_RECIPIENTS = 'silvere.djam@gmail.com'
    }
	
    tools{
    	jdk "jdk8"
	git "git"
	maven "maven"    
    }

   stages{    
       
       stage('Build-And-Test'){
	    steps{
	       echo 'Checking out from Git Repo' 
	       git 'https://github.com/deepdream-apps/vehicleseller.git'
	       bat 'mvn -f vehicleseller-backend/pom.xml -DskipTests  clean package'
	       echo 'Build stage executed successfully'
	    }
	}
	   
	stage('Test'){
	    steps{
	       bat 'java -jar --spring.profiles.active=test vehicleseller-backend/target/vehicleseller-backend-0.0.1-SNAPSHOT.jar'
	       echo 'Test stage executed successfully'
	    }
	}
	    
	stage('Deploy'){
	     steps{
	        echo 'Deploy the project'
	     }
	}
   }
	
   
}
