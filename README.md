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


## User Accounts

| Username | Password |
|----------|----------|
| noah     | noah     |
| lars     | lars     |
| admin    | admin    |

## Week 2

### Fetching one menu itemd - OK

```
GET http://localhost:9242/api/menu-items/1
Accept: application/json
```
```
HTTP/1.1 200 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Thu, 07 Mar 2024 15:47:42 GMT
Keep-Alive: timeout=60
Connection: keep-alive

{
  "id": 1,
  "name": "Ceasar Salad",
  "price": 3.5
}
```

### Fetching one menu item - Not Found

```
GET http://localhost:9242/api/menu-items/99
Accept: application/json
```

```
HTTP/1.1 404 
Content-Length: 0
Date: Thu, 07 Mar 2024 15:48:10 GMT
Keep-Alive: timeout=60
Connection: keep-alive

<Response body is empty>Response code: 404; Time: 20ms (20 ms); Content length: 0 bytes (0 B)
```

### Fetching all menu items - OK

```
GET http://localhost:9242/api/menu-items
Accept: application/json
```

```
HTTP/1.1 200 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Thu, 07 Mar 2024 15:28:28 GMT
Keep-Alive: timeout=60
Connection: keep-alive
```

### Deleting one menu item - OK

```
DELETE http://localhost:9242/api/menu-items/1
```
```
HTTP/1.1 204 
Date: Thu, 07 Mar 2024 15:28:29 GMT
Keep-Alive: timeout=60
Connection: keep-alive

<Response body is empty>Response code: 204; Time: 124ms (124 ms); Content length: 0 bytes (0 B)
```

### Deleting one menu item - Not Found

```
DELETE http://localhost:9242/api/menu-items/99
```

```
HTTP/1.1 404
Content-Length: 0
Date: Thu, 07 Mar 2024 15:28:29 GMT
Keep-Alive: timeout=60
Connection: keep-alive

<Response body is empty>Response code: 404; Time: 27ms (27 ms); Content length: 0 bytes (0 B)
```

## Week 3

### Adding a menu item - OK

```
POST http://localhost:9242/api/menu-items
Accept: application/json
Content-Type: application/json

{
"name": "Name"
}
```

```
HTTP/1.1 201 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Thu, 07 Mar 2024 15:28:29 GMT
Keep-Alive: timeout=60
Connection: keep-alive
```

### Changing a menu item name - OK

```
PATCH http://localhost:9242/api/menu-items/1
Accept: application/json
Content-Type: application/json

{
"name": "Pizza"
}
```

```
HTTP/1.1 204 
Date: Thu, 07 Mar 2024 15:40:35 GMT
Keep-Alive: timeout=60
Connection: keep-alive

<Response body is empty>Response code: 204; Time: 468ms (468 ms); Content length: 0 bytes (0 B)
```


### Changing a menu item name - Not Found

```
PATCH http://localhost:9242/api/menu-items/99
Accept: application/json
Content-Type: application/json

{
"name": "Name"
}
```

```
HTTP/1.1 404
Content-Length: 0
Date: Thu, 07 Mar 2024 15:56:07 GMT
Keep-Alive: timeout=60
Connection: keep-alive

<Response body is empty>Response code: 404; Time: 26ms (26 ms); Content length: 0 bytes (0 B)
```

### Fetching one menu item in XML format - OK

```
GET http://localhost:9242/api/menu-items/1
Accept: application/xml
```

```
HTTP/1.1 200
Content-Type: application/xml;charset=UTF-8
Transfer-Encoding: chunked
Date: Thu, 07 Mar 2024 15:46:34 GMT
Keep-Alive: timeout=60
Connection: keep-alive

<MenuItemDto>
    <id>1</id>
    <name>Ceasar Salad</name>
    <price>3.5</price>
</MenuItemDto>
```

### Fetching one menu item in XML format - Not Found

```
GET http://localhost:9242/api/menu-items/99
Accept: application/xml
```

```
HTTP/1.1 404
Content-Length: 0
Date: Thu, 07 Mar 2024 15:47:10 GMT
Keep-Alive: timeout=60
Connection: keep-alive

<Response body is empty>Response code: 404; Time: 22ms (22 ms); Content length: 0 bytes (0 B)
```
