# DNA Technology test task

## Job offer REST service

The goal is to implement a simple RESTful web-server with basic functionality.

## Summary

I implemented a basic service as solution of problems you sent me. I used a Spring Boot and H2 in-memory database with
some open-source libraries for fast development. Also, Liquibase for database schema and preset of categories was used. All the user's passwords are encrypted in database without usage of
Spring Security.

## Methods:

### User

- `GET /user` - returns list of all the users
- `POST /user` - create new user with specified params. This params should be specified in JSON body like this:

 ```
{
    "login": "johndoe",
    "password": "qwerty",
    "name": "John Doe"
}
 ```

- `GET /user/{id}` - get the user with specified ID
- `DELETE /user/{id}` - delete the user with specified ID
- `PATCH /user/{id}` - update user with the properties sent. Example body:

```
{
    "password": "qwerty23",
    "name": "John Grind"
}
```
### Categories

There's only one method for retrieving all the categories in DB - `GET /category`.

### Offers

- `POST /offer` - create new offer with properties sent. Example:
```
{
    "categoryId": 3,
    "employerId": 2,
    "startDate": "26.12.2021",
    "endDate": "30.12.2021"
}
```
- `GET /offer` - search **valid** offers with optional criteria. It maybe: `category` (it's ID) and `employerName`. For example, request 
`http://server:port/offer?category=1&employerName=tes` may return this result:
```
{
    "body": [
        {
            "id": 1,
            "category": {
                "id": 1,
                "name": "IT"
            },
            "employer": {
                "id": 1,
                "login": "itsok",
                "name": "Maria Grotess",
                "creationDate": "28.12.2021"
            },
            "startDate": "26.12.2021",
            "endDate": "30.12.2021"
        }
    ],
    "success": true,
    "message": null,
    "code": 0
}
```

### Things I wanted to complete, but didn't cause of 5 hours time limit

- Write tests
- Do a better validation