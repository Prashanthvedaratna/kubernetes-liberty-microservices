# Kubernetes Liberty Microservices

[![GitHub Repository](https://img.shields.io/badge/GitHub-Prashanthvedaratna%2Fkubernetes--liberty--microservices-blue?logo=github)](https://github.com/Prashanthvedaratna/kubernetes-liberty-microservices)
[![Docker Hub](https://img.shields.io/badge/Docker%20Hub-prashanthvedarathna-blue?logo=docker)](https://hub.docker.com/u/prashanthvedarathna)

A complete end-to-end project demonstrating deployment of **IBM WebSphere Liberty** microservices on **Kubernetes** with CI/CD pipeline using **Jenkins**. The project includes three independent services (Product, Order, User) with REST APIs, containerization, orchestration, and external access via Ingress.

## 🏗️ Architecture

<img src="architecture.png" width="900">
## 🛠️ Technologies

- **Java 11** + **MicroProfile 4.0**
- **IBM WebSphere Liberty** (Open Liberty)
- **Maven** – build tool
- **Docker** – containerization
- **Kubernetes** (Minikube) – orchestration
- **Jenkins** – CI/CD automation
- **GitHub** – source control
- **Docker Hub** – image registry
- **NGINX Ingress** – external access

## 📋 Prerequisites

- **WSL Ubuntu** (or any Linux)
- **Java 11** (`sudo apt install openjdk-11-jdk`)
- **Maven** (`sudo apt install maven`)
- **Docker** ([install guide](https://docs.docker.com/engine/install/))
- **Minikube** ([install guide](https://minikube.sigs.k8s.io/docs/start/))
- **kubectl** (`sudo snap install kubectl --classic`)
- **Git**

## 🚀 Quick Start

### 1. Clone the Repository

```bash
git clone https://github.com/Prashanthvedaratna/kubernetes-liberty-microservices.git
cd kubernetes-liberty-microservices
2. Start Minikube
bash
minikube start --driver=docker --cpus=4 --memory=8192
minikube addons enable ingress
3. Build the Microservices
bash
mvn clean package
4. Build Docker Images
bash
docker build -t prashanthvedarathna/product-service:latest -f product-service/Dockerfile .
docker build -t prashanthvedarathna/order-service:latest -f order-service/Dockerfile .
docker build -t prashanthvedarathna/user-service:latest -f user-service/Dockerfile .
5. Push Images to Docker Hub
bash
docker login -u prashanthvedarathna
docker push prashanthvedarathna/product-service:latest
docker push prashanthvedarathna/order-service:latest
docker push prashanthvedarathna/user-service:latest
6. Deploy to Kubernetes
bash
kubectl apply -f k8s/
Wait for all pods to be ready:

bash
kubectl get pods -w
7. Test the Services
Port-Forward (local testing)
bash
kubectl port-forward service/product-service 8081:80 &
kubectl port-forward service/order-service 8082:80 &
kubectl port-forward service/user-service 8083:80 &
bash
curl http://localhost:8081/api/products      # []
curl http://localhost:8082/api/orders        # []
curl http://localhost:8083/api/users         # []
Ingress (external access)
Get Minikube IP:

bash
minikube ip
# e.g., 192.168.49.2
Add to /etc/hosts (optional):

bash
echo "$(minikube ip) liberty.local" | sudo tee -a /etc/hosts
Then test:

bash
curl http://liberty.local/api/products
curl http://liberty.local/api/orders
curl http://liberty.local/api/users
Health endpoints are also available:

bash
curl http://liberty.local/health/live
curl http://liberty.local/health/ready
8. Create Sample Data
bash
curl -X POST http://liberty.local/api/products \
  -H "Content-Type: application/json" \
  -d '{"name":"Laptop","description":"Gaming laptop","price":1200.00,"stock":5}'
🔄 CI/CD with Jenkins
A Jenkinsfile is provided in the repository. It automates:

Maven build

Docker image creation

Push to Docker Hub

Kubernetes deployment restart

Jenkins Setup
Run Jenkins in Docker:

bash
docker run -d --name jenkins -p 8080:8080 -p 50000:50000 \
  -v jenkins_home:/var/jenkins_home -v /var/run/docker.sock:/var/run/docker.sock \
  --restart=unless-stopped jenkins/jenkins:lts-jdk11
Access http://localhost:8080, complete setup, and install plugins: Git, Pipeline, Docker Pipeline, Kubernetes CLI.

Add credentials in Jenkins:

Docker Hub (ID: docker-hub)

GitHub (ID: github)

Kubeconfig (as secret file, ID: kubeconfig)

Create a pipeline job pointing to this repository and the Jenkinsfile.

📁 Project Structure
text
kubernetes-liberty-microservices/
├── product-service/          # Product microservice (Java, Liberty)
├── order-service/            # Order microservice
├── user-service/             # User microservice
├── k8s/                      # Kubernetes manifests
│   ├── product-deployment.yaml
│   ├── product-service.yaml
│   ├── order-deployment.yaml
│   ├── order-service.yaml
│   ├── user-deployment.yaml
│   ├── user-service.yaml
│   └── ingress.yaml
├── Jenkinsfile               # CI/CD pipeline
├── pom.xml                   # Parent Maven POM
└── README.md                 # This file
📌 Key Features
✅ Three independent microservices with REST endpoints

✅ Health checks (liveness + readiness) using MicroProfile Health

✅ JAX-RS Application class for proper endpoint scanning

✅ Docker multi-stage builds for small images

✅ Kubernetes Deployments with 2 replicas each

✅ Internal ClusterIP services for discovery

✅ Ingress for external access with path-based routing

✅ Jenkins pipeline for CI/CD

✅ Persistent data in memory (easily swappable with database)

🧪 Troubleshooting
If pods crash or health checks fail:

Check logs: kubectl logs <pod-name>

Verify server.xml in each service points to the correct WAR (product-service.war, etc.)

Ensure Docker images are built with the correct base image (full-java11-openj9-ubi)

📄 License
MIT License – see LICENSE file.

👤 Author
Prashanth Vedarathna

GitHub: @Prashanthvedaratna

Docker Hub: prashanthvedarathna

Project: kubernetes-liberty-microservices
# Webhook test
