# Programming 5

**Noah Guerin**  
*noah.guerin@student.kdg.be*  
*0152794-19*  
*ACS202*  

## Domain Entities

| Entity     | Relationships                                               |
|------------|-------------------------------------------------------------|
| MenuItem   | Belongs to one Restaurant, Owns and owned by many Chefs     |
| Chef       | Belongs to one Restaurant, Owns and owned by many MenuItems |
| Restaurant | Owns many Chefs and many MenuItems                          |

## Week 2

### Fetching one chef - OK

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

### Fetching one chef - Not Found

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

### Fetching All chefs - OK

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

### Deleting one chef - OK

```
DELETE http://localhost:9242/api/chefs/1
```
```
HTTP/1.1 204
Date: Sun, 03 Mar 2024 15:57:17 GMT
Keep-Alive: timeout=60
Connection: keep-alive
```

### Deleting one chef - Not Found

```
DELETE http://localhost:9242/api/chefs/99
```

## Week 3

```
HTTP/1.1 404
Content-Length: 0
Date: Sun, 03 Mar 2024 15:54:25 GMT
Keep-Alive: timeout=60
Connection: keep-alive
```

### Adding a chef - OK

```
POST http://localhost:9242/api/chefs
Accept: application/json
Content-Type: application/json

{
"firstName": "First name",
"lastName": "Last name"
}
```

```
HTTP/1.1 201
Content-Type: application/json
Transfer-Encoding: chunked
Date: Wed, 06 Mar 2024 16:24:11 GMT
Keep-Alive: timeout=60
Connection: keep-alive
```

### Change a chef name - OK

```
PATCH http://localhost:9242/api/chefs/1
Accept: application/json
Content-Type: application/json

{
"firstName": "Bob",
"lastName": "Marley"
}
```

```
HTTP/1.1 204
Date: Wed, 06 Mar 2024 17:00:31 GMT
Keep-Alive: timeout=60
Connection: keep-alive
```

