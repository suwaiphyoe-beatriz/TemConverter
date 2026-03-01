pipeline {
  agent any

  tools {
    maven 'Maven3'
  }

  environment {
    PATH = "/usr/local/bin:${env.PATH}"
    DOCKERHUB_CREDENTIALS_ID = 'docker-hub'
    DOCKERHUB_REPO = 'suph/temconverter'
    DOCKER_IMAGE_TAG = 'latest'
    DOCKER_HOST = 'unix:///var/run/docker.sock'
  }

  stages {

    stage('check'){
        steps {
            git branch: 'main',
              url: 'https://github.com/suwaiphyoe-beatriz/TemConverter.git'
       }
    }

    stage('Build') {
      steps {
        sh 'mvn clean install'
      }
    }

    stage('Test Docker') {
      steps {
        sh 'docker --version'
      }
    }

    stage('Test') {
      steps {
        sh 'mvn test'
      }
    }

    stage('Report') {
      steps {
        sh 'mvn jacoco:report'
      }
    }

    stage('Publish Test Results') {
      steps {
        junit '**/target/surefire-reports/*.xml'
      }
    }

    stage('Publish Coverage Report') {
      steps {
        jacoco()
      }
    }

    stage('Build Docker Image') {
    steps {
        // Use sh to call docker CLI directly instead of docker.build() DSL
        sh "docker build -t ${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG} ."
    }
}

stage('Push Docker Image to Docker Hub') {
    steps {
        // Use sh to push via CLI, ensures PATH works on macOS
        withCredentials([usernamePassword(credentialsId: "${DOCKERHUB_CREDENTIALS_ID}",
                                         usernameVariable: 'DOCKER_USER',
                                         passwordVariable: 'DOCKER_PASS')]) {
            sh """
                echo "$DOCKER_PASS" | docker login -u "$DOCKER_USER" --password-stdin
                docker push ${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG}
            """
        }
    }
}


  }
}
