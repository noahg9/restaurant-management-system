# Programming 5

**Noah Guerin**  
*noah.guerin@student.kdg.be*  
*0152794-19*  
*ACS202*

## Domain Entities

| Entity     | Relationships                    |
|------------|----------------------------------|
| MenuItem   | Owns and owned by many Chefs     |
| Chef       | Owns and owned by many MenuItems |


## User Accounts

| Username | Password | Role   |
|----------|----------|--------|
| noahg    | noah     | Admin  |
| larsw    | noah     | Admin  |
| gordonr  | noah     | Chef   |
| jamieo   | noah     | Chef   |
| joanr    | noah     | Chef   |
| davidc   | noah     | Chef   |

## Role Rights

| Page                   | Guest  | Chef   | Admin  |
|------------------------|--------|--------|--------|
| home                   | Viewer | Viewer | Viewer |
| chefs                  | Viewer | Viewer | Editor |
| menu-items             | Viewer | Viewer | Editor |      
| search-chefs           | Viewer | Viewer | Viewer |
| search-menu-items      | Viewer | Viewer | Viewer |
| history                | Viewer | Viewer | Viewer |
| chef                   | N/a    | Viewer | Editor |
| chef (own)             | N/a    | Editor | Editor |
| menu-item              | N/a    | Viewer | Editor |
| menu-item (associated) | N/a    | Editor | Editor |

## Week 2

### Fetching one menu item - OK

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
```

## Week 3

### Adding a menu item - OK

```
POST http://localhost:9242/api/menu-items
Accept: application/json
Content-Type: application/json

{
  "name": "Pizza",
  "price": 10.0,
  "course": "Main",
  "vegetarian": false,
  "spiceLvl": 3
}
```

```
HTTP/1.1 201 
Vary: Origin
Vary: Access-Control-Request-Method
Vary: Access-Control-Request-Headers
X-Content-Type-Options: nosniff
X-XSS-Protection: 0
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Pragma: no-cache
Expires: 0
X-Frame-Options: DENY
Content-Type: application/json
Transfer-Encoding: chunked
Date: Sun, 10 Mar 2024 16:21:42 GMT
Keep-Alive: timeout=60
Connection: keep-alive

{
  "id": 6,
  "name": "Pizza",
  "price": 10.0,
  "course": "Main",
  "vegetarian": false,
  "spiceLvl": 3
}
```

### Changing a menu item name - OK

```
PATCH http://localhost:9242/api/menu-items/1
Accept: application/json
Content-Type: application/json

{
  "name": "Pizza",
  "price": 10.0,
  "course": "Main",
  "vegetarian": false,
  "spiceLvl": 3
}
```

```
HTTP/1.1 204 
Vary: Origin
Vary: Access-Control-Request-Method
Vary: Access-Control-Request-Headers
X-Content-Type-Options: nosniff
X-XSS-Protection: 0
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Pragma: no-cache
Expires: 0
X-Frame-Options: DENY
Date: Sun, 10 Mar 2024 16:22:13 GMT
Keep-Alive: timeout=60
Connection: keep-alive
```


### Changing a menu item name - Not Found

```
PATCH http://localhost:9242/api/menu-items/99
Accept: application/json
Content-Type: application/json

{
  "name": "Pizza",
  "price": 10.0,
  "course": "Main",
  "vegetarian": false,
  "spiceLvl": 3
}
```

```
HTTP/1.1 404 
Vary: Origin
Vary: Access-Control-Request-Method
Vary: Access-Control-Request-Headers
X-Content-Type-Options: nosniff
X-XSS-Protection: 0
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Pragma: no-cache
Expires: 0
X-Frame-Options: DENY
Content-Length: 0
Date: Sun, 10 Mar 2024 16:23:45 GMT
Keep-Alive: timeout=60
Connection: keep-alive
```

### Fetching one menu item in XML format - OK

```
GET http://localhost:9242/api/menu-items/1
Accept: application/xml
```

```
HTTP/1.1 200 
Vary: Origin
Vary: Access-Control-Request-Method
Vary: Access-Control-Request-Headers
X-Content-Type-Options: nosniff
X-XSS-Protection: 0
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Pragma: no-cache
Expires: 0
X-Frame-Options: DENY
Content-Type: application/xml;charset=UTF-8
Transfer-Encoding: chunked
Date: Sun, 10 Mar 2024 16:53:17 GMT
Keep-Alive: timeout=60
Connection: keep-alive

<MenuItemDto>
    <id>1</id>
    <name>Ceasar Salad</name>
    <price>3.5</price>
    <course>Main</course>
    <vegetarian>false</vegetarian>
    <spiceLvl>0</spiceLvl>
</MenuItemDto>
```

### Fetching one menu item in XML format - Not Found

```
GET http://localhost:9242/api/menu-items/99
Accept: application/xml
```

```
HTTP/1.1 200 
Vary: Origin
Vary: Access-Control-Request-Method
Vary: Access-Control-Request-Headers
X-Content-Type-Options: nosniff
X-XSS-Protection: 0
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Pragma: no-cache
Expires: 0
X-Frame-Options: DENY
Content-Type: application/xml;charset=UTF-8
Transfer-Encoding: chunked
Date: Sun, 10 Mar 2024 16:53:17 GMT
Keep-Alive: timeout=60
Connection: keep-alive

<MenuItemDto>
    <id>1</id>
    <name>Ceasar Salad</name>
    <price>3.5</price>
    <course>Main</course>
    <vegetarian>false</vegetarian>
    <spiceLvl>0</spiceLvl>
</MenuItemDto>
```
