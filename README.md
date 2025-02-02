# SCHOOL EXERCISE


#### API REST developed in Java with SpringBoot - MySQL - Spring Data JPA 


# Liste des enpoints :
## Categories

### 1. Get all categories
- **Method**: GET
- **URL**: `http://localhost:8080/categories`
- **Authentication**: Bearer Token
- **Response**: List of all categories

### 2. Create category
- **Method**: POST
- **URL**: `http://localhost:8080/categories/create`
- **Authentication**: Bearer Token
- **Body** (form data):
    - `name`: "Plateforme"

### 3. Edit category
- **Method**: PUT
- **URL**: `http://localhost:8080/categories/edit`
- **Authentication**: Bearer Token
- **Body** (form data):
    - `id`: 2
    - `newName`: "Adventure"

### 4. Delete category
- **Method**: POST
- **URL**: `http://localhost:8080/categories/delete`
- **Authentication**: Bearer Token
- **Body** (form data):
    - `id`: 1

---

## VideoGames

### 1. Get all videogames
- **Method**: GET
- **URL**: `http://localhost:8080/videogames`
- **Authentication**: Bearer Token
- **Response**: List of all videogames

### 2. Get videogame by ID
- **Method**: GET
- **URL**: `http://localhost:8080/videogames/1`
- **Authentication**: Bearer Token
- **Response**: Videogame with ID 1

### 3. Get videogame by name
- **Method**: GET
- **URL**: `http://localhost:8080/videogames/byname`
- **Authentication**: Bearer Token
- **Body** (form data):
    - `name`: "Super Mario Bros."

### 4. Create videogame
- **Method**: POST
- **URL**: `http://localhost:8080/videogames/create`
- **Authentication**: Bearer Token
- **Body** (form data):
    - `name`: "Super Mario Bros."
    - `description`: "Le joueur contrôle Mario et voyage à travers le Royaume Champignon afin de sauver la princesse Peach des griffes de Bowser..."
    - `releaseDate`: "09/1985"
    - `image`: "https://static.posters.cz/image/1300/affiches-et-posters/super-mario-bros-world-1-1-i218009.jpg"
    - `categoryIds`: 10

### 5. Delete videogame
- **Method**: POST
- **URL**: `http://localhost:8080/videogames/delete`
- **Authentication**: Bearer Token
- **Body** (form data):
    - `id`: 3

### 6. Edit videogame
- **Method**: PUT
- **URL**: `http://localhost:8080/videogames/edit`
- **Authentication**: Bearer Token
- **Body** (form data):
    - `id`: 2
    - `newName`: "Zelda le retour encore 5"
    - `newDescription`: "Link veut toujours sauver Zelda"
    - `newReleaseDate`: "10/1986"
    - `newImage`: "https://m.media-amazon.com/images/I/81KGsbq8ekL.jpg"
    - `categoryIds`: 2

---

## Reviews

### 1. Add Review to a game
- **Method**: POST
- **URL**: `http://localhost:8080/reviews/2/addreview`
- **Authentication**: Bearer Token
- **Body** (form data):
    - `comment`: "vraiment trop bien"
    - `authorName`: "koko"
    - `rate`: 5

### 2. Delete review from a game
- **Method**: POST
- **URL**: `http://localhost:8080/reviews/2/deletereview`
- **Authentication**: Bearer Token
- **Body** (form data):
    - `reviewId`: 4

---

## Users

### 1. Get all users
- **Method**: GET
- **URL**: `http://localhost:8080/users`
- **Authentication**: Bearer Token
- **Response**: List of all users

### 2. Create user
- **Method**: POST
- **URL**: `http://localhost:8080/users/create`
- **Authentication**: No Authentication
- **Body** (form data):
    - `firstName`: "user"
    - `lastName`: "user"
    - `email`: "user@email.com"
    - `password`: "password"

### 3. Edit user
- **Method**: POST
- **URL**: `http://localhost:8080/users/1/edit`
- **Authentication**: No Authentication
- **Body** (form data):
    - `firstName`: "user"
    - `lastName`: "user"
    - `email`: "user@email.com"
    - `password`: "password"

### 4. Delete user
- **Method**: POST
- **URL**: `http://localhost:8080/users/delete`
- **Authentication**: No Authentication
- **Body** (form data):
    - `id`: 1

---

## Login

### 1. Login
- **Method**: POST
- **URL**: `http://localhost:8080/auth/login`
- **Authentication**: No Authentication
- **Body** (form data):
    - `email`: "user@email.com"
    - `password`: "password"

---
