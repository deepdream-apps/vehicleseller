pipeline {
    agent any
    environment {
        EMAIL_RECIPIENTS = 'silvere.djam@gmail.com'
    }

   stages{    
       
       stage('Build'){
	    steps{
	       echo 'Checking out from Git Repo' 
	       git 'https://github.com/deepdream-apps/vehicleseller.git'
	       sh './mvnw -Dmaven.test.failure.ignore=true clean package'
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
	
   post{
       always{
          
       }
       failure{
	       
       }
   }
}
