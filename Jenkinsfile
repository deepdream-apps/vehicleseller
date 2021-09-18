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
	       sh 'mvn -Dmaven.test.failure.ignore=true install' 
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
