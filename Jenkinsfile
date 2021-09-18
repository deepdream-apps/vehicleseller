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
       
       stage('Build'){
	    steps{
	       echo 'Checking out from Git Repo' 
	       git 'https://github.com/deepdream-apps/vehicleseller.git'
	       bat 'cd vehicleseller-bakend'
	       bat 'mvn clean package'
	    }
	}
	    
	stage('Test'){
		
	     steps{
	        echo 'Test the project'    
	     }
	}
	    
	stage('Deploy'){
	     steps{
	        echo 'Deploy the project'
	     }
	}
   }
	
   
}
