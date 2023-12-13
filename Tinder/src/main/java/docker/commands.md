
### start service
```bash
cd java-web/src/main/java/web3sql1/docker
docker-compose up
```

### look for services running
```bash
docker ps
```

### stop
```bash
docker stop pg_0
```

### create db
```bash
docker exec -ti pg_0 bash
su postgres
createdb fs6-online
```

### connect

- connection url: `jdbc:postgresql://localhost:5432/fs6-online`
- username: `postgres`
- password: `pg123456`
