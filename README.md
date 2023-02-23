This software actually accepts any input requests, and save the raw traffic, and tell client it is okay.

# Building
```
$ gradle clean bootJar
```

# Configuration
You don't need to configure anything. 

The dumped files will be put into current folder. 

Folder structure would be in format of `yyyy/MM/dd/HH/mm` folder when the request happened.

If you don't like the default parameters, you may consider customizing these opitons:

1. Bind address by using command line arg: `-Dserver.address=127.0.0.1`
2. Bind port by using command line arg: `-Dserver.port=9078`
3. Destination folder by using command line arg: `-Ddump.dest.folder=/tmp`. This folder must already **exist** and **writable**.

# Running

**This program requires JAVA 17**

(Default) Running at port 8080, dump to current folder:
```bash
$ java -jar build/libs/webhook-debug-0.0.1-SNAPSHOT.jar
```

Running at port `4747`, listen on `127.0.0.1`, dump to `/tmp` folder:
```bash
$ java -jar \
      -Dserver.address=127.0.0.1 \
      -Dserver.port=4747 \
      -Ddump.dest.folder=/tmp \ 
      build/libs/webhook-debug-0.0.1-SNAPSHOT.jar
```

# Testing
```bash
$ curl --data-binary "Some-payload" \
    -H "TEST: TEST-VALUE" \ 
    -X POST \
    "http://localhost:8080/testabc?hello=b&hello=c"
```

Sample recording:
```bash
$ cat 2023/02/21/15/39/59.244-000001.json
```

NOTE: Body is base64 encoded.
```json
{
  "method" : "POST",
  "serverName" : "localhost",
  "serverPort" : 8080,
  "queryString" : "hello=b&hello=c",
  "remoteInfo" : {
    "user" : null,
    "host" : "127.0.0.1",
    "port" : 51911,
    "address" : "127.0.0.1"
  },
  "headers" : [ {
    "name" : "host",
    "values" : [ "localhost:8080" ]
  }, {
    "name" : "user-agent",
    "values" : [ "curl/7.79.1" ]
  }, {
    "name" : "accept",
    "values" : [ "*/*" ]
  }, {
    "name" : "test",
    "values" : [ "TEST-VALUE" ]
  }, {
    "name" : "content-length",
    "values" : [ "12" ]
  }, {
    "name" : "content-type",
    "values" : [ "application/x-www-form-urlencoded" ]
  } ],
  "body" : "U29tZS1wYXlsb2Fk",
  "url" : "http://localhost:8080/testabc",
  "uri" : "/testabc"
}
```

# Enjoy your debugging!
