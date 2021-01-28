CREATE TABLE card (
   id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
   card_number VARCHAR(16) NOT NULL,
   balance DECIMAL NOT NULL,
   daily_limit DECIMAL NOT NULL,
   expiration_date DATE NOT NULL,
   is_activated BOOLEAN NOT NULL default FALSE,
   card_holder_id BIGINT NOT NULL,
   FOREIGN KEY (card_holder_id) REFERENCES user(id),
   UNIQUE KEY card_number_uniq_key (card_number)
);