-- CREATE TABLE categories (
--   category_id INT AUTO_INCREMENT PRIMARY KEY,
--   name VARCHAR(50) NOT NULL
-- );

CREATE TABLE categories (
  category_id SERIAL PRIMARY KEY,
  name TEXT NOT NULL
);


-- CREATE TABLE books (
--   book_id INT AUTO_INCREMENT PRIMARY KEY,
--   title VARCHAR(100) NOT NULL,
--   author_name VARCHAR(100) NOT NULL,
--   price DECIMAL(10, 2) NOT NULL,
--   category_id INT NOT NULL,
--   CONSTRAINT fk_category FOREIGN KEY (category_id) REFERENCES categories(category_id)
-- );
CREATE TABLE books (
  book_id SERIAL PRIMARY KEY,
  title TEXT NOT NULL,
  author_name TEXT NOT NULL,
  price DECIMAL(10, 2) NOT NULL,
  category_id INT NOT NULL,
  CONSTRAINT fk_category FOREIGN KEY (category_id) REFERENCES categories(category_id)
);


SELECT table_name
FROM information_schema.tables
WHERE table_schema = 'public'
  AND table_type = 'BASE TABLE';