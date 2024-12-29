CREATE TABLE user (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    username TEXT NOT NULL UNIQUE,
    password_hash TEXT NOT NULL,
    salt TEXT NOT NULL,
    email TEXT NOT NULL UNIQUE,
    address TEXT NOT NULL,
    phone TEXT NOT NULL
);