# 还没完善 先占个位置

## 前期准备
### mysql
```shell
docker run -p 3306:3306 --name mysql-container -v /mydata/mysql/log:/var/log/mysql -v /mydata/mysql/data:/var/lib/mysql -v /mydata/mysql/conf:/etc/mysql/conf.d -e MYSQL_ROOT_PASSWORD=123456 -d mysql:latest
```


### redis
```shell
docker run --name redis-container -p 6379:6379 -v redis-data:/data -d redis:latest --requirepass 123456
```

管理员
账号：admin@example.com
密码：admin123

测试用户：123456