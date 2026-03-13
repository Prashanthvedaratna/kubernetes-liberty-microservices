# Port-forward order-service (use a different local port)
kubectl port-forward service/order-service 8082:80 &
curl http://localhost:8082/api/orders
curl http://localhost:8082/health/live

# Port-forward user-service
kubectl port-forward service/user-service 8083:80 &
curl http://localhost:8083/api/users
curl http://localhost:8083/health/live
