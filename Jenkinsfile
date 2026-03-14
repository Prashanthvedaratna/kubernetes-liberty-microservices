pipeline {
    agent any

    environment {
        DOCKER_HUB_REPO = 'prashanthvedarathna'
    }

    stages {
        stage('Build and Push Product Service') {
            steps {
                script {
                    // Build with Maven inside product-service
                    dir('product-service') {
                        sh 'mvn clean package'
                    }
                    // Build Docker image from project root
                    docker.withRegistry('', 'docker-hub') {
                        def image = docker.build("${DOCKER_HUB_REPO}/product-service:${env.BUILD_NUMBER}", "-f product-service/Dockerfile .")
                        image.push()
                        image.push('latest')
                    }
                }
            }
        }

        stage('Build and Push Order Service') {
            steps {
                script {
                    dir('order-service') {
                        sh 'mvn clean package'
                    }
                    docker.withRegistry('', 'docker-hub') {
                        def image = docker.build("${DOCKER_HUB_REPO}/order-service:${env.BUILD_NUMBER}", "-f order-service/Dockerfile .")
                        image.push()
                        image.push('latest')
                    }
                }
            }
        }

        stage('Build and Push User Service') {
            steps {
                script {
                    dir('user-service') {
                        sh 'mvn clean package'
                    }
                    docker.withRegistry('', 'docker-hub') {
                        def image = docker.build("${DOCKER_HUB_REPO}/user-service:${env.BUILD_NUMBER}", "-f user-service/Dockerfile .")
                        image.push()
                        image.push('latest')
                    }
                }
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                script {
                    withKubeConfig([credentialsId: 'kubeconfig-text']) {
                        sh """
                            kubectl set image deployment/product-service product-service=${DOCKER_HUB_REPO}/product-service:${env.BUILD_NUMBER}
                            kubectl set image deployment/order-service order-service=${DOCKER_HUB_REPO}/order-service:${env.BUILD_NUMBER}
                            kubectl set image deployment/user-service user-service=${DOCKER_HUB_REPO}/user-service:${env.BUILD_NUMBER}
                            kubectl rollout status deployment/product-service
                            kubectl rollout status deployment/order-service
                            kubectl rollout status deployment/user-service
                        """
                    }
                }
            }
        }
    }

    post {
        always {
            echo 'Pipeline finished.'
        }
        success {
            echo 'All stages succeeded!'
        }
        failure {
            echo 'Pipeline failed. Check the logs.'
        }
    }
}