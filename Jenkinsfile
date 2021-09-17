pipeline {
  agent any
  tools {
      maven "maven"
      jdk "jdk8"
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
                sh 'mvn clean package'
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
