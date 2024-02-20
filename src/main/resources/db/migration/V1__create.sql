CREATE TABLE School (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    hour_price DECIMAL(10,2) NOT NULL
);

CREATE TABLE Parent (
    id INT PRIMARY KEY AUTO_INCREMENT,
    firstname VARCHAR(100) NOT NULL,
    lastname VARCHAR(100) NOT NULL
);

CREATE TABLE Child (
    id INT PRIMARY KEY AUTO_INCREMENT,
    firstname VARCHAR(100) NOT NULL,
    lastname VARCHAR(100) NOT NULL,
    parent_id INT,
    school_id INT,
    FOREIGN KEY (parent_id) REFERENCES Parent(id),
    FOREIGN KEY (school_id) REFERENCES School(id)
);

CREATE TABLE Attendance (
    id INT PRIMARY KEY AUTO_INCREMENT,
    child_id INT,
    entry_date DATETIME NOT NULL,
    exit_date DATETIME NOT NULL,
    FOREIGN KEY (child_id) REFERENCES Child(id),
    CHECK (entry_date <= exit_date)
);