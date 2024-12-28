CREATE TABLE components (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    type_id INTEGER,
    description TEXT,
    price REAL NOT NULL,
    FOREIGN KEY (type_id) REFERENCES components_types(id)
);