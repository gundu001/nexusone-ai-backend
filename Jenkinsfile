pipeline {
  agent any
  parameters {
    string(name: 'REPOSITORY_NAME', defaultValue: 'Order-Service')
  }
  stages {
    stage('Validate') {
      steps {
        echo "Deploying ${params.REPOSITORY_NAME}"
      }
    }
    stage('Build') {
      steps {
        bat 'echo Replace with: mvnw.cmd clean test'
      }
    }
    stage('Deploy') {
      steps {
        echo 'Replace with your deployment command'
      }
    }
  }
  post {
    success { echo 'Deployment successful' }
    failure { echo 'Deployment failed' }
  }
}