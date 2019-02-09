CREATE TABLE sneakers(
    product_number INTEGER AUTO_INCREMENT PRIMARY KEY,
    brand TEXT,
    name TEXT ,
    size NUMBER,  
    price NUMBER,
    about TEXT,
    picture TEXT

);

CREATE TABLE accessories(
    product_number INTEGER IDENTITY PRIMARY KEY,
    type TEXT,
    brand TEXT,
    description TEXT,  
);