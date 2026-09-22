# Homework REST API

A Spring Boot REST API that provides endpoints for retrieving profile information, managing items, searching and filtering data, and displaying application statistics.

## Base URL

The application runs on port `8080`.

```text
http://localhost:8080/api
```

Start the Spring Boot application, then use a web browser, Postman, or another HTTP client to test the endpoints.

---

## API Endpoints

| Method | Endpoint          | Parameters         | Description                                                         | Example                                                         |
| ------ | ----------------- | ------------------ | ------------------------------------------------------------------- | --------------------------------------------------------------- |
| `GET`  | `/api/hello`      | None               | Returns the application name, user's name, theme, and introduction. | `http://localhost:8080/api/hello`                               |
| `GET`  | `/api/items`      | None               | Returns all items and their categories.                             | `http://localhost:8080/api/items`                               |
| `GET`  | `/api/choose-one` | `chosen`           | Returns a fact based on the selected index.                         | `http://localhost:8080/api/choose-one?chosen=1`                 |
| `GET`  | `/api/search`     | `text`             | Searches for an item or fact.                                       | `http://localhost:8080/api/search?text=PC`                      |
| `GET`  | `/api/filter`     | `text`             | Returns items that match the specified category.                    | `http://localhost:8080/api/filter?text=Gaming`                  |
| `GET`  | `/api/add`        | `category`, `item` | Adds a new item to the items map.                                   | `http://localhost:8080/api/add?category=keyboard&item=Computer` |
| `GET`  | `/api/update`     | `category`, `item` | Updates an existing item's value.                                   | `http://localhost:8080/api/update?category=PC&item=Technology`  |
| `GET`  | `/api/delete`     | `category`, `item` | Deletes an item from the items map.                                 | `http://localhost:8080/api/delete?category=PC&item=Computer`    |
| `GET`  | `/api/stats`      | None               | Returns the number of items and facts.                              | `http://localhost:8080/api/stats`                               |
| `GET`  | `/api/fav`        | None               | Returns the configured favorite entity.                             | `http://localhost:8080/api/fav`                                 |

---

## API Testing

All endpoints use the `GET` HTTP method and can be tested directly in a browser or using Postman.

### 1. Hello

**Request:**

```text
GET http://localhost:8080/api/hello
```

**Response:**

```text
Ahmed Ahmed Almutawa Software Engineering An aspiring coder and tech enthusiast with a heart of gold
```

---

### 2. Items

**Request:**

```text
GET http://localhost:8080/api/items
```

**Response:**

```text
{PC=Computer, Laptop=Computer, Playstation=Gaming, psp=Gaming, xbox=Gaming, steam=Gaming, camry=vehicle, harley=vehicle, honor=phone, samsung=tablet}
```

---

### 3. Choose One

The `chosen` parameter is used to select a fact from the facts list.

**Request:**

```text
GET http://localhost:8080/api/choose-one?chosen=1
```

**Response:**

```text
prefers sour foods
```

Available facts:

| Index | Fact                     |
| ----: | ------------------------ |
|     0 | Active person            |
|     1 | prefers sour foods       |
|     2 | Loves PC gaming          |
|     3 | Graduated from UOB       |
|     4 | Currently studying in GA |
|     5 | Loves Cars               |

---

### 4. Search

The `text` parameter is used to search the configured items or facts.

**Request:**

```text
GET http://localhost:8080/api/search?text=PC
```

**Response:**

```text
PC
```

If no matching result is found:

```text
Invalid
```

If no text is provided:

```text
Enter text to search
```

---

### 5. Filter

The `text` parameter is used to find all items belonging to a specific category.

**Request:**

```text
GET http://localhost:8080/api/filter?text=Gaming
```

**Response:**

```text
[Playstation, psp, xbox, steam]
```

Other examples:

```text
GET http://localhost:8080/api/filter?text=Computer
```

Returns:

```text
[PC, Laptop]
```

```text
GET http://localhost:8080/api/filter?text=vehicle
```

Returns:

```text
[camry, harley]
```

---

### 6. Add

The `/add` endpoint requires `category` and `item` parameters.

**Request:**

```text
GET http://localhost:8080/api/add?category=keyboard&item=Computer
```

This adds the following entry:

```text
keyboard=Computer
```

The endpoint returns the updated items map.

---

### 7. Update

The `/update` endpoint requires an existing `category` and a new `item` value.

**Request:**

```text
GET http://localhost:8080/api/update?category=PC&item=Technology
```

This changes:

```text
PC=Computer
```

to:

```text
PC=Technology
```

The updated items map is returned.

If the category does not exist:

```text
incorrect category
```

---

### 8. Delete

The `/delete` endpoint requires a category and item.

**Request:**

```text
GET http://localhost:8080/api/delete?category=PC&item=Computer
```

This removes the `PC=Computer` entry from the items map.

The updated items map is returned.

If the category does not exist:

```text
incorrect category
```

If the item does not exist:

```text
incorrect item
```

---

### 9. Statistics

**Request:**

```text
GET http://localhost:8080/api/stats
```

**Response:**

```text
number of Items =10 Number of Facts =6
```

The endpoint displays the current number of items and facts.

---

### 10. Favorite Entity

**Request:**

```text
GET http://localhost:8080/api/fav
```

**Response:**

```text
my pc
```

---

## Configured Data

The API is configured with the following items:

| Item        | Category |
| ----------- | -------- |
| PC          | Computer |
| Laptop      | Computer |
| Playstation | Gaming   |
| psp         | Gaming   |
| xbox        | Gaming   |
| steam       | Gaming   |
| camry       | vehicle  |
| harley      | vehicle  |
| honor       | phone    |
| samsung     | tablet   |

The API is also configured with six facts:

* Active person
* prefers sour foods
* Loves PC gaming
* Graduated from UOB
* Currently studying in GA
* Loves Cars

---

## Testing with Postman

To test an endpoint using Postman:

1. Start the Spring Boot application.
2. Open Postman.
3. Select the `GET` HTTP method.
4. Enter the endpoint URL.
5. Add query parameters where required.
6. Click **Send**.
7. Check the response in the response body.

For example:

```text
GET http://localhost:8080/api/filter?text=Gaming
```

The expected response is:

```text
[Playstation, psp, xbox, steam]
```

## Technology

* Java
* Spring Boot
* Spring Web
* REST API
* Maven
* Postman
