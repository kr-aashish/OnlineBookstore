## **OnlineBookstore Application Setup and Deployment Guide**

The OnlineBookstore application is a web-based platform that allows users to browse and purchase books online. This guide provides step-by-step instructions on how to set up and run the application.

### **Prerequisites**

Before you begin, ensure that you have the following prerequisites installed on your system:

- Java Development Kit (JDK) 17 or higher
- Apache Maven
- MySQL Database Server
- Internet browser (Chrome, Firefox, etc.)

### **Step 1: Clone the Repository**

Start by cloning the OnlineBookstore repository from the source code hosting platform (e.g., GitHub) to your local machine. You can use Git command-line or any Git client tool of your choice.

```
git clone <repository_url>

```

### **Step 2: Configure the Database**

The OnlineBookstore application requires a MySQL database to store book and category information. Follow these steps to configure the database:

1. Open a MySQL client (e.g., MySQL Workbench).
2. Create a new database named **`online_bookstore`** using the following SQL command:

```
CREATE DATABASE online_bookstore;

```
3. Switch to the online_bookstore database:
```
USE online_bookstore;

```

4. Create the **`categories`** table by executing the following SQL script:

```
CREATE TABLE categories (
  category_id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(50) NOT NULL
);

```

5. Create the **`books`** table by executing the following SQL script:

```
CREATE TABLE books (
  book_id INT AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(100) NOT NULL,
  author_name VARCHAR(100) NOT NULL,
  price DECIMAL(10, 2) NOT NULL,
  category_id INT NOT NULL,
  CONSTRAINT fk_category FOREIGN KEY (category_id) REFERENCES categories(category_id)
);

```


### **Step 3: Configure Application Properties**

The OnlineBookstore application requires configuration properties to connect to the database. Follow these steps to configure the application properties:

1. Navigate to the **`src/main/resources`** directory in the cloned repository.
2. Open the **`application.properties`** file.
3. Modify the following properties to match your MySQL database configuration:

```
spring.datasource.url=jdbc:mysql://localhost:3306/online_bookstore
spring.datasource.username=<your_mysql_username>
spring.datasource.password=<your_mysql_password>

```

### **Step 4: Build the Application**

To build the OnlineBookstore application, execute the following Maven command from the root directory of the cloned repository:

```
mvn clean package

```

Maven will download the required dependencies, compile the source code, and package the application into an executable JAR file.

### **Step 5: Run the Application**

After successfully building the application, you can run it using the following command:

```
java -jar target/OnlineBookstore-0.0.1-SNAPSHOT.jar

```

The application will start, and you should see log messages indicating the application's startup progress.

### **Step 6: Test the APIs using Postman**


#### Test Category APIs:
   - Create a Category:
     - Set the request method to **POST**.
     - Set the request URL to **http://localhost:8080/api/categories**.
     - Set the request body to a JSON object containing the category information, for example:
       ```
       {
         "name": "Fiction"
       }
       ```
     - Send the request to create a new category.
   - Retrieve all Categories:
     - Set the request method to **GET**.
     - Set the request URL to **http://localhost:8080/api/categories**.
     - Send the request to retrieve all categories.
   - Retrieve a specific Category:
     - Set the request method to **GET**.
     - Set the request URL to **http://localhost:8080/api/categories/{categoryId}**, replacing **{categoryId}** with the actual ID of the category you want to retrieve.
     - Send the request to retrieve the specific category.

#### Test Book APIs:
   - Create a Book:
     - Set the request method to **POST**.
     - Set the request URL to **http://localhost:8080/api/books**.
     - Set the request body to a JSON object containing the book information, including the associated category ID, for example:
       ```
       {
         "title": "The Alchemist",
         "authorName": "Paulo Coelho",
         "price": 300,
         "category": {
           "categoryId": 1,
           "name": "Fiction"
         }
       }
       ```
     - Send the request to create a new book.
   - Retrieve all Books:
     - Set the request method to **GET**.
     - Set the request URL to **http://localhost:8080/api/books**.
     - Send the request to retrieve all books.
   - Retrieve a specific Book:
     - Set the request method to **GET**.
     - Set the request URL to **http://localhost:8080/api/books/{bookId}**, replacing **{bookId}** with the actual ID of the book you want to retrieve.
     - Send the request to retrieve the specific book.
   - Update a Book:
     - Set the request method to **PUT**.
     - Set the request URL to **http://localhost:8080/api/books**
     - Set the request body to a JSON object containing the updated book information, use bookId parameter to mention the book which is to be updated.
     - Send the request to update the book.
   - Delete a Book:
     - Set the request method to **DELETE**.
     - Set the request URL to **http://localhost:8080/api/books/{bookId}**, replacing **{bookId}** with the actual ID of the book you want to delete.
     - Send the request to delete the book.

### **Additional Configuration Options**

- **Port Configuration**: By default, the application runs on port **`8080`**. If you want to change the port, you can modify the **`server.port`** property in the **`application.properties`** file.

### **Troubleshooting**

If you encounter any issues during the setup or deployment of the OnlineBookstore application, consider the following:

- Double-check the database configuration properties in the **`application.properties`** file.
- Ensure that the MySQL database server is running.
- Check the console output and log files for any error messages or stack traces.

### **Conclusion**

Congratulations! You have successfully set up and deployed the application. You can now explore the features, test the application, and customize it further according to your requirements.

Enjoy using the OnlineBookstore application!