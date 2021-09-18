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
	       bat 'mvn -f vehicleseller-backend/pom.xml clean package'
	       echo 'Buid and Test stage executed successfully'
	    }
	}
	    
	stage('Deploy'){
	     steps{
	        echo 'Deploy the project'
		bat 'mvn -f vehicleseller-backend/pom.xml install'
	     }
	}
   }
	
   
}
