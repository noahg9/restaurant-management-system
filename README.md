# Restaurant Management

## Domain Entities

Chef >------< MenuItem ------ Recipe

## Build/Run Instructions

1. Run the docker-compose.yml file to start the database  
2. Run the application:  
```./gradlew bootRun```
3. Access the application at:  
```http://localhost:9242```
4. Export the test profile:
```export SPRING_PROFILES_ACTIVE=test```
5. Run the tests:  
```./gradlew test```

## User Accounts

| Username | Password | Role      |
|----------|----------|-----------|
| noahg    | noah     | Head Chef |
| larsw    | lars     | Head Chef |
| gordonr  | gordon   | Sous Chef |
| jamieo   | jamie    | Sous Chef |
| joanr    | joan     | Sous Chef |
| davidc   | david    | Sous Chef |

## Access Levels

| Page                       | Guest   | Sous Chef | Head Chef |
|----------------------------|---------|-----------|-----------|
| home                       | Full    | Full      | Full      | 
| search-chefs               | Full    | Full      | Full      |
| search-menu-items          | Full    | Full      | Full      |
| chef (own)                 | Limited | Full      | Full      |
| menu-item (associated)     | Limited | Full      | Full      |
| history                    | None    | Full      | Full      |
| chefs                      | Limited | Limited   | Full      |
| menu-items                 | Limited | Limited   | Full      |   
| chef (other)               | Limited | Limited   | Full      |
| menu-item (not-associated) | Limited | Limited   | Full      |
| register-chef              | None    | None      | Full      |
| insert-menu-items          | None    | None      | Full      |

### Example pages

The following page can be accessed by all users:  
```http://localhost:9242/menu-items```

The following page requires authentication to be accessible:  
```http://localhost:9242/history```

## Spring Profiles

test - Used for testing purposes.

## Code coverage

![code_coverage.png](code_coverage.png)
