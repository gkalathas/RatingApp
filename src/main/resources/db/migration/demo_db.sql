--CREATE TABLE user (
--  id BIGINT AUTO_INCREMENT NOT NULL,
--   firstname VARCHAR(255) NOT NULL,
--   lastname VARCHAR(255) NOT NULL,
--   username VARCHAR(255) NOT NULL,
--   email VARCHAR(255) NOT NULL,
--   item_id BIGINT NOT NULL,
--   CONSTRAINT pk_user PRIMARY KEY (id)
--);
--
--
--CREATE TABLE items (
--  item_id BIGINT AUTO_INCREMENT NOT NULL,
--   item_name VARCHAR(255) NOT NULL,
--   CONSTRAINT pk_items PRIMARY KEY (item_id)
--);
--
--
--CREATE TABLE feedback (
--  id BIGINT AUTO_INCREMENT NOT NULL,
--   item_id BIGINT NOT NULL,
--   interested_id BIGINT NOT NULL,
--   rating FLOAT NOT NULL,
--   hash_key VARCHAR(255) NOT NULL,
--   CONSTRAINT pk_feedback PRIMARY KEY (id)
--);

CREATE TABLE feedback (
  id BIGINT AUTO_INCREMENT NOT NULL,
   item_id BIGINT NULL,
   interested_id BIGINT NULL,
   rating FLOAT NOT NULL,
   hash_key VARCHAR(255) NULL,
   CONSTRAINT pk_feedback PRIMARY KEY (id)
);
CREATE TABLE user (
  id BIGINT AUTO_INCREMENT NOT NULL,
   firstname VARCHAR(255) NULL,
   lastname VARCHAR(255) NULL,
   username VARCHAR(255) NULL,
   email VARCHAR(255) NULL,
   item_id BIGINT NULL,
   CONSTRAINT pk_user PRIMARY KEY (id)
);