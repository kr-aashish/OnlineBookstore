DROP TABLE BOOKS CASCADE;
DROP TABLE CATEGORIES CASCADE;
DROP SEQUENCE category_id_seq;
DROP SEQUENCE book_id_seq;

-- CREATE TABLE categories (
--   category_id INT AUTO_INCREMENT PRIMARY KEY,
--   name VARCHAR(50) NOT NULL
-- );

-- CREATE TABLE categories (
--   -- category_id SERIAL PRIMARY KEY,
--   category_id INT PRIMARY KEY,
--   name TEXT NOT NULL
-- );

CREATE SEQUENCE category_id_seq START 1;

CREATE TABLE categories (
  category_id INT DEFAULT nextval('category_id_seq') PRIMARY KEY,
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
-- CREATE TABLE books (
--   -- book_id SERIAL PRIMARY KEY,
--   book_id INT PRIMARY KEY,
--   title TEXT NOT NULL,
--   author_name TEXT NOT NULL,
--   price DECIMAL(10, 2) NOT NULL,
--   category_id INT NOT NULL,
--   CONSTRAINT fk_category FOREIGN KEY (category_id) REFERENCES categories(category_id)
-- );

CREATE SEQUENCE book_id_seq START 1;

CREATE TABLE books (
  book_id INT DEFAULT nextval('book_id_seq') PRIMARY KEY,
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