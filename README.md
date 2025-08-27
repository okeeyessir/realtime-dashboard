# DOCUMENTATION

## Installation

```bash
  cd realtime-dashboard
  cd backend
  mvn clean package -DskipTests
  
  cd ../frontend
  rm -rf node_modules package-lock.json dist
  npm install
  npm run build
  
  cd ..
  docker compose up --build
```