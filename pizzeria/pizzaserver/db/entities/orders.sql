CREATE TABLE orders (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    user_id INTEGER NOT NULL,
    product_ids TEXT NOT NULL,
    order_type_id INTEGER NOT NULL,
    order_state_id INTEGER NOT NULL,
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total_price REAL NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user(id),
    FOREIGN KEY (order_type_id) REFERENCES order_type(id),
    FOREIGN KEY (order_state_id) REFERENCES order_states(id)
);