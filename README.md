This software actually accepts any input requests, and save the raw traffic, and tell client it is okay.

# Building
`gradle clean bootJar`

# Running

`java -jar build/xxxx.jar`

# Configuration
You don't need to configure anything. The dumped files will be put into current folder, in format of yyyy/MM/dd/HH/mm folder when the request happened.

If you don't like the default parameters, you may consider customizing these opitons:

1. Bind address by using command line arg: -Dserver.address=127.0.0.1
2. Bind port by using command line arg: -Dserver.port=9078
3. Destination folder by using command line arg: -Ddump.dest.folder=/tmp

# Testing
`curl --data-binary "Some-payload" -H "TEST: TEST-VALUE" -X POST "http://localhost:8080/testabc?hello=b&hello=c"`

Sample recording:
```bash
$ cat 2023/02/21/15/39/59.244-000001.txt 
METHOD: POST
HOST: localhost:8080
URL: http://localhost:8080/testabc
URI: /testabc
QUERY_STRING: hello=b&hello=c
REMOTE_ADDR: 127.0.0.1
REMOTE_USER: null
REMOTE_HOST: 127.0.0.1
REMOTE_PORT: 56394
HEADER: host = [localhost:8080]
HEADER: user-agent = [curl/7.79.1]
HEADER: accept = [*/*]
HEADER: test = [TEST-VALUE]
HEADER: content-length = [12]
HEADER: content-type = [application/x-www-form-urlencoded]
BODY(BASE64-ENCODED):
U29tZS1wYXlsb2Fk⏎               
```

# Enjoy
