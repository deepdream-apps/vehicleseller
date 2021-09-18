pipeline {
    agent { label 'java8' }
    environment {
        EMAIL_RECIPIENTS = 'silvere.djam@gmail.com'
    }
  tools {
      maven "maven"
      jdk "jdk"
      git "git"
   }
   stages{    
       
       stage('Build'){
	    steps{
	       echo 'Checking out from Git Repo' 
	       git 'https://github.com/deepdream-apps/vehicleseller.git'
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
