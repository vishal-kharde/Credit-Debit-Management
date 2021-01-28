# RESTful service for managing debit/credit cards.

Steps to launch:
 - jdk version 1.8 and maven installen on your machine
 - open terminal and run 'mvn clean install' and then 'mvn spring-boot:run' commands from project root directory
***

## API Details

### `GET users/{userId}/cards`
fetch a list of cards for a specific user.
If nonexistent user id is specified then exception with message 'There is no User with id = {id}' will be thrown.
If user with speciffied id does not have any card the method will return empty list.

**Response Codes**: 200; 204; 404;
```
HTTP/1.1 200
Content-Type: application/json;charset=UTF-8

[
  {
    "id": 2,
    "dailyLimit": 789,
    "balance": 4386,
    "cardNumber": "5265227000100063",
    "expirationDate": "2023-09-01",
    "isActivated": true,
    "cardHolder": {
      "id": 2,
      "userName": "Dany Targaryen",
      "userAddress": "Dragonstone"
    }
  },
  {
    "id": 3,
    "dailyLimit": 100,
    "balance": 34782364322,
    "cardNumber": "5522800574180809",
    "expirationDate": "2019-05-01",
    "isActivated": false,
    "cardHolder": {
      "id": 2,
      "userName": "Dany Targaryen",
      "userAddress": "Dragonstone"
    }
  },
  {
    "id": 4,
    "dailyLimit": 382,
    "balance": 875836,
    "cardNumber": "5281492384844245",
    "expirationDate": "2019-08-01",
    "isActivated": true,
    "cardHolder": {
      "id": 2,
      "userName": "Dany Targaryen",
      "userAddress": "Dragonstone"
    }
  }
]
```

```
HTTP/1.1 404
Content-Type: text/plain;charset=UTF-8
Content-Length: 29

There is no User with id = 38
```

### `GET cards/{id}`
fetch the card details for a specific card.
The details include the cardholder name, address, daily limit, balance, card number, expiration date, and status
If card with specified id does not exist exception with message 'There is no Card with id = {id}' will bethrown.

**Response Codes**: 200; 404;
```
HTTP/1.1 200
Content-Type: application/json;charset=UTF-8

{
  "id": 1,
  "dailyLimit": 10,
  "balance": 100,
  "cardNumber": "4024007170581887",
  "expirationDate": "2024-12-01",
  "isActivated": false,
  "cardHolder": {
    "id": 1,
    "userName": "Jon Snow",
    "userAddress": "Castle Black"
  }
}
```

```
HTTP/1.1 404
Content-Type: text/plain;charset=UTF-8
Content-Length: 30

There is no Card with id = 189
```
### `GET cards`
fetch all saved cards. If there are no cards empty list will be returned.

**Response Codes**: 200; 204;
```
HTTP/1.1 200
Content-Type: application/json;charset=UTF-8

[
  {
    "id": 1,
    "dailyLimit": 10,
    "balance": 100,
    "cardNumber": "4024007170581887",
    "expirationDate": "2024-12-01",
    "isActivated": false,
    "cardHolder": {
      "id": 1,
      "userName": "Jon Snow",
      "userAddress": "Castle Black"
    }
  },
  {
    "id": 2,
    "dailyLimit": 789,
    "balance": 4386,
    "cardNumber": "5265227000100063",
    "expirationDate": "2023-09-01",
    "isActivated": true,
    "cardHolder": {
      "id": 2,
      "userName": "Dany Targaryen",
      "userAddress": "Dragonstone"
    }
  }
]
```
***

### `PATCH cards/{id}/activate`
change card 'isActivated' flag to true. Even if card with passed id is activated already method will return code 200 and card details. If card with specified id was not inserted in db before the exception with message 'There is no Card with id = {id}' will be thrown.

**Response Codes**: 200; 404;
```
HTTP/1.1 200
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked

{
  "id": 1,
  "dailyLimit": 10,
  "balance": 100,
  "cardNumber": "4024007170581887",
  "expirationDate": "2024-12-01",
  "isActivated": true,
  "cardHolder": {
    "id": 1,
    "userName": "Jon Snow",
    "userAddress": "Castle Black"
  }
}
```

```
HTTP/1.1 404
Content-Type: text/plain;charset=UTF-8
Content-Length: 30

There is no Card with id = 123
```

### `PATCH cards/{id}/deactivate`
change card 'isActivated' flag to false. Even if card with passed id is activated already method will return code 200 and card details. If card with specified id was not inserted in db before the exception with message 'There is no Card with id = {id}' will be thrown.

**Response Codes**: 200; 404;
```
HTTP/1.1 200
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked

{
  "id": 1,
  "dailyLimit": 10,
  "balance": 100,
  "cardNumber": "4024007170581887",
  "expirationDate": "2024-12-01",
  "isActivated": false,
  "cardHolder": {
    "id": 1,
    "userName": "Jon Snow",
    "userAddress": "Castle Black"
  }
}
```

```
HTTP/1.1 404
Content-Type: text/plain;charset=UTF-8
Content-Length: 30

There is no Card with id = 123
```

### `PATCH cards/{id}`
change the daily limit on the card. If card with specified id dose not exist exception with message 'There is no Card with id = {id}' will be thrown. If value of daily limit is null error with code 400 will thrown. If daily limit value is negative error with code 422 and message 'Value can not be negative' will bee thrown.

**Request**
```
Content-Type: application/json
{
    "dailyLimit": 200
}
```

**Response Codes**: 200; 400; 404; 422;

```
HTTP/1.1 200
Content-Type: application/json;charset=UTF-8

{
  "id": 1,
  "dailyLimit": 1000,
  "balance": 100,
  "cardNumber": "4024007170581887",
  "expirationDate": "2024-12-01",
  "isActivated": false,
  "cardHolder": {
    "id": 1,
    "userName": "Jon Snow",
    "userAddress": "Castle Black"
  }
}
```

```
HTTP/1.1 404
Content-Type: text/plain;charset=UTF-8
Content-Length: 30

There is no Card with id = 167
```

```
HTTP/1.1 422
Content-Type: text/plain;charset=UTF-8
Content-Length: 27

Value can not be negative.
```
