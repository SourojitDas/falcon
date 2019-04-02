# Falcon API Server

## Deployment
### Build

```bash
gradle jar
```

### Build docker image
```bash
sudo docker build .
```

### Build HAproxy docker image
```bash
sudo docker build haproxy
```

### Execute
```bash
sudo docker run -d -p8080:7000 --name falcon-server-n1 falcon:latest
sudo docker run -d -p8081:7000 --name falcon-server-n2 falcon:latest
sudo docker run -d -p80:80 --name falcon-haproxy falcon-haproxy
```

