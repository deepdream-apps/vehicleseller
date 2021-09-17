pipeline {
  agent any
  tools {
      maven "maven"
      jdk "jdk8"
   }
   stages{    
       stage('Build'){
	    steps{
	        echo 'Build the project'   
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
