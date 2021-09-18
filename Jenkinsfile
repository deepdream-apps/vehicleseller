pipeline {
  agent any
  tools {
      maven "maven"
      jdk "jdk"
      git "git"
   }
   stages{    
       stage('Initialize'){
            steps{
                echo "PATH = ${M2_HOME}"
            }
       }
	   
       stage('Build'){
	    steps{
	        echo 'Build the project'   
		sh 'make' 
  		archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
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
