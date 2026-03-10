#!/bin/bash

# Build all services
echo "Building all services..."
cd ..
mvn clean package

# Build Docker images
echo "Building Docker images..."
docker build -t prashanthvedarathna/product-service:latest ./product-service
docker build -t prashanthvedarathna/order-service:latest ./order-service
docker build -t prashanthvedarathna/user-service:latest ./user-service

# Push to Docker Hub
echo "Pushing to Docker Hub..."
docker push prashanthvedarathna/product-service:latest
docker push prashanthvedarathna/order-service:latest
docker push prashanthvedarathna/user-service:latest

# Deploy to Kubernetes
echo "Deploying to Kubernetes..."
kubectl apply -f k8s/mysql/
kubectl apply -f k8s/

# Wait for deployments
kubectl wait --for=condition=available --timeout=300s deployment/product-service
kubectl wait --for=condition=available --timeout=300s deployment/order-service
kubectl wait --for=condition=available --timeout=300s deployment/user-service

# Get Ingress URL
echo "Deployment complete! Access your services at:"
minikube service list