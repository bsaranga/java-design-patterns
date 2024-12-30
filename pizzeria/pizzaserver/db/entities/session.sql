CREATE TABLE user_session (
    session_id INTEGER PRIMARY KEY AUTOINCREMENT,
    user_id INTEGER NOT NULL,
    session_token TEXT NOT NULL,
    expired BOOLEAN DEFAULT 0,
    FOREIGN KEY (user_id) REFERENCES user(user_id)
);