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
		    dir("C:/DEV/deploy/jenkins/workspace") {
		    	 cmd 'mvn clean package'
			 sh 'make' 
  			 archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
		    }
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
