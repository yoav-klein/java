CREATE TABLE Categories (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL UNIQUE
);


CREATE TABLE QuantityTypes (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL UNIQUE
);


CREATE TABLE Items (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    category_id INT NOT NULL,
    quantity DECIMAL(10, 2) NOT NULL,
    quantity_type_id INT NOT NULL,
    UNIQUE (name, category_id, quantity_type_id), -- Ensure unique item name within category and quantity type
    FOREIGN KEY (category_id) REFERENCES Categories(id),
    FOREIGN KEY (quantity_type_id) REFERENCES QuantityTypes(id)
);

-- Insert categories
INSERT INTO Categories (name) VALUES ('VEGETABLES');
INSERT INTO Categories (name) VALUES ('FRUITS');

-- Insert quantity types
INSERT INTO QuantityTypes (name) VALUES ('KG');
INSERT INTO QuantityTypes (name) VALUES ('UNIT');
