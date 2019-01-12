# Sql2Mongo
sql query convert to mongo query
e.g:
```
select * from user where id = 93 limit 1;
```
=>
```
db.user.find({"_id": {$eq, NumberLong(93)}}).limit(1)
```

# PRE
- maven 3.3+
- jdk 1.8+
- graalvm 1.0+

# Build
```bash
mvn clean package

./build.sh # build binary executable file named s2m.

```

# Install
```bash
mv s2m ~/.local/bin
```

# Showcase
## My Case

```bash
which pq
# =>
pq () {
  mq=`s2m "$*"`
  echo "__mq: $mq"
  mongo [ip]:[port]/[db] --quiet --eval "$mq.forEach(printjson)"
}

pq "select * from recUser where id = 93 limit 1"
# =>
__mq: db.recUser.find({"_id": {$eq: NumberLong(93)}}).limit(1)
{
  "_id" : NumberLong(93),
  "level" : "0",
  "location" : [
    121.47293085930734,
    31.24114302576437
  ],
  "age" : 19,
  "_class" : "com.dtx.pluto.biz.recommend.domain.RecUser"
}
```

---

![1543560933.png](http://raw.githubusercontent.com/guxingke/oss/master/blog/1543560933.png)
![1543560956.png](http://raw.githubusercontent.com/guxingke/oss/master/blog/1543560956.png)


# Note
it build for myself, just a demo.

# Changelog
- =, !=.
- single query condition.
