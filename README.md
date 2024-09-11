![image](https://scontent.fsdu38-1.fna.fbcdn.net/v/t39.30808-6/312985842_496033049211358_5973914414839344733_n.jpg?_nc_cat=109&ccb=1-7&_nc_sid=6ee11a&_nc_eui2=AeHT4I8RrPGD8vPBB1ZrHeLFubbFq8iecsS5tsWryJ5yxNf456Wip-bqL3EgZtOKXSdjtzrh89a_ZOOgXwlfZ4yP&_nc_ohc=uJ1cUjMYErgQ7kNvgE-RyYD&_nc_ht=scontent.fsdu38-1.fna&oh=00_AYAXRS-DVtGftMtefX8z4McWb5kziMGmlvIxdlTsK30h8g&oe=66E74F1C)

---

# ReciMe API

ReciMe API is a sample project to manage and query culinary recipes. The application provides endpoints to fetch trending recipes and filter recipes by difficulty.

## Table of Contents

- [Technologies](#technologies)
- [Installation](#installation)
- [Running the Application](#running-the-application)
- [API Endpoints](#api-endpoints)
- [Contributing](#contributing)
- [License](#license)

## Technologies

This project uses the following technologies:

- **Java 17**: Main programming language.
- **Spring Boot**: Framework for building Java applications.
- **Spring Data JPA**: Simplifies integration with relational databases.
- **Spring HATEOAS**: Support for adding hypermedia links to RESTful resources.
- **Lombok**: Library to reduce boilerplate code in Java.
- **Maven**: Dependency management and project build tool.

## Prerequisites
   Make sure that you have maven installed on your machine. If not, you can download it from [here](https://maven.apache.org/download.cgi), and follow [this](https://alexadam.dev/blog/install-java-maven) tutorial to properly install maven,

## Installation

1. **Clone the repository:**

   ```bash
   git clone https://github.com/xxxprincemaker/recime.git
   cd recime
   ```

2. **Configure the Database:**

   Make sure to configure a compatible JPA database (e.g., H2, MySQL, PostgreSQL) in the application's `application.yml` file in the following project I have used H2 in memory database just to simulate an iteration with database.

3. **Build the Project:**

   To build and install the dependencies, run:

   ```bash
   mvn clean install
   ```

## Running the Application

To start the Spring Boot server, run:

```bash
mvn spring-boot:run
```

The application will be available at `http://localhost:8080`.

## API Endpoints

### 1. Get Trending Recipes

**Endpoint:** `/getTrendingRecipes`

**Method:** `GET`

**Query Parameters:**

- `page` (optional, default: 0): Page number.
- `size` (optional, default: 5): Number of recipes per page.
- `sort` (optional, default: position): Sort field (e.g., position, name, difficulty).
- `direction` (optional, default: asc): Sort direction (e.g., asc, desc).

**Example Request:**

```http
GET http://localhost:8080/getTrendingRecipes?page=0&size=5
```

**Description:**

Returns a paginated list of trending recipes ordered by position.

### 2. Get Trending Recipes by Difficulty

**Endpoint:** `/getTrendingRecipesByDifficulty`

**Method:** `GET`

**Query Parameters:**

- `page` (optional, default: 0): Page number.
- `size` (optional, default: 5): Number of recipes per page.
- `sort` (optional, default: position): Sort field (e.g., position, name, difficulty).
- `direction` (optional, default: asc): Sort direction (e.g., asc, desc).
- `difficulty` (required): Difficulty level of the recipes (e.g., easy, medium, hard).

**Example Request:**

```http
GET http://localhost:8080/getTrendingRecipesByDifficulty?page=0&size=10&difficulty=easy
```

**Description:**

Returns a paginated list of recipes filtered by the specified difficulty level.

### Postman Collection

You can import the Postman collection to test the API endpoints. The collection is available at [here](Code%20Challenge.postman_collection.json) .

## Contributing

Contributions are welcome! To contribute, follow the steps below:

1. **Fork** the project.
2. Create a new **branch**: `git checkout -b my-new-feature`.
3. **Commit** your changes: `git commit -m 'Add new feature'`.
4. **Push** to the branch: `git push origin my-new-feature`.
5. Open a **Pull Request**.

## License

This project is licensed under the terms of the MIT license. See the [LICENSE](LICENSE.md) file for more information.
