#!/bin/bash

# Define base URL
base_url="http://localhost:8080"

# Function to execute curl command and print status code and response body
function execute_curl {
    local curl_command="$1"
    local endpoint_description="$2"

    echo "=== $endpoint_description ==="

    # Execute curl command and capture output
    response=$(eval "$curl_command")
    echo "$curl_command"

    # Print status code
    status_code=$(echo "$response" | head -n 1 | awk '{print $2}')
    # echo "Status Code: $status_code"

    # Print response body
    echo "Response Body:"
    echo "$response"

    echo ""  # Print newline for readability
}

# Create a Category
execute_curl "curl -s -X POST -H 'Content-Type: application/json' -d '{\"name\":\"Fiction\"}' '$base_url/categories'" "Create Category"

sleep 1

# Retrieve all Categories
execute_curl "curl -s -X GET '$base_url/categories'" "Retrieve All Categories"

sleep 1

# Retrieve a specific Category (replace {categoryId} with the actual ID)
execute_curl "curl -s -X GET '$base_url/categories/1'" "Retrieve Specific Category"

sleep 1

# Create a Book
execute_curl "curl -s -X POST -H 'Content-Type: application/json' -d '{\"title\":\"The Alchemist\",\"authorName\":\"Paulo Coelho\",\"price\":300,\"category\":{\"categoryId\":1,\"name\":\"Fiction\"}}' '$base_url/books'" "Create Book"

sleep 1

# Retrieve all Books
execute_curl "curl -s -X GET '$base_url/books'" "Retrieve All Books"

sleep 1

# Retrieve a specific Book (replace {bookId} with the actual ID)
execute_curl "curl -s -X GET '$base_url/books/1'" "Retrieve Specific Book"

sleep 1

# Update a Book (replace {bookId} with the actual ID and adjust the body accordingly)
execute_curl "curl -s -X PUT -H 'Content-Type: application/json' -d '{\"bookId\":1,\"title\":\"Updated Book Title\",\"authorName\":\"Updated Author\",\"price\":250,\"category\":{\"categoryId\":1,\"name\":\"Fiction\"}}' '$base_url/books'" "Update Book"

sleep 1

# Delete a Book (replace {bookId} with the actual ID)
execute_curl "curl -s -X DELETE '$base_url/books/1'" "Delete Book"

sleep 1