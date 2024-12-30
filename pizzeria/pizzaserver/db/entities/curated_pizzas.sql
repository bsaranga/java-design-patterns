CREATE TABLE curated_pizzas (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    ingredients TEXT NOT NULL,
    description TEXT NOT NULL,
    price REAL NOT NULL
);