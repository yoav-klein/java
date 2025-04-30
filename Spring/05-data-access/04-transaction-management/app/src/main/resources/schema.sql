
CREATE TABLE users(id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(50) NOT NULL)
CREATE TABLE accounts(owner_id INT PRIMARY KEY, sum INT NOT NULL DEFAULT 0, FOREIGN KEY(owner_id) REFERENCES users(id) ON DELETE CASCADE)
CREATE TABLE transactions(sender_id INT NOT NULL, receiver_id INT NOT NULL, sum INT NOT NULL, FOREIGN KEY(sender_id) REFERENCES users(id))

/* , 
    receiver_id INT, 
    sum INT, 
    FOREIGN KEY(sender_id) REFERENCES users(id),
    FOREIGN KEY(receiver_id) REFERENCES users(id)
)
 */
