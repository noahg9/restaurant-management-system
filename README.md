<h1>Introduction</h1>

<b>Course:</b> Programming 5

<b>Name:</b> Noah Guerin

<b>Email:</b> noah.guerin@student.kdg.be

<b>Student ID:</b> 0152794-19

<b>Group:</b> ACS202

Domain entities: chef, menu item, restaurant

<h1>Week 2</h1>

<h2>Fetching one chef - OK</h2>

```
GET http://localhost:9242/api/chefs/1
Accept: application/xml
```
```
HTTP/1.1 200 
Content-Type: application/xml;charset=UTF-8
Transfer-Encoding: chunked
Date: Sun, 03 Mar 2024 16:04:38 GMT
Keep-Alive: timeout=60
Connection: keep-alive
```

<h2>Fetching one chef - Not Found</h2>

```
GET http://localhost:9242/api/chefs/99
Accept: application/xml
```

```
HTTP/1.1 404
Content-Length: 0
Date: Sun, 03 Mar 2024 15:52:02 GMT
Keep-Alive: timeout=60
Connection: keep-alive
```

<h2>Fetching All chefs - OK</h2>

```
GET http://localhost:9242/api/chefs
Accept: application/json
```

```
HTTP/1.1 200
Content-Type: application/json
Transfer-Encoding: chunked
Date: Sun, 03 Mar 2024 15:51:17 GMT
Keep-Alive: timeout=60
Connection: keep-alive
```

<h2>Deleting one chef - OK</h2>

```
DELETE http://localhost:9242/api/chefs/1
```
```
HTTP/1.1 204
Date: Sun, 03 Mar 2024 15:57:17 GMT
Keep-Alive: timeout=60
Connection: keep-alive
```

<h2>Deleting one chef - Not Found</h2>

```
DELETE http://localhost:9242/api/chefs/99
```

```
HTTP/1.1 404
Content-Length: 0
Date: Sun, 03 Mar 2024 15:54:25 GMT
Keep-Alive: timeout=60
Connection: keep-alive
```
