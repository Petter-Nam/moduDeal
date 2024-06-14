CREATE DATABASE MODUDEAL;

USE MODUDEAL;

CREATE TABLE ADMIN (
    ADMIN_ID INT AUTO_INCREMENT PRIMARY KEY,
    USERNAME VARCHAR(50) NOT NULL UNIQUE,
    PASSWORD VARCHAR(255) NOT NULL
);

CREATE TABLE USER (
    USER_ID VARCHAR(20) PRIMARY KEY,
    PASSWORD VARCHAR(200) NOT NULL,
    NAME VARCHAR(50) NOT NULL,
    BIRTHDATE DATE NOT NULL, 
    HP VARCHAR(13) NOT NULL,
    EMAIL VARCHAR(30) NOT NULL,
    ADDRESS_ZIPCODE VARCHAR(10),
    ADDRESS_ROAD VARCHAR(200),
    ADDRESS_JIBUN VARCHAR(200),
    ADDRESS_NAMUJI VARCHAR(200),
    ACTIVE_YN CHAR(1) DEFAULT 'y',
    INACTIVE_AT DATE DEFAULT NULL,
    JOIN_AT DATE DEFAULT (CURRENT_DATE)
);

CREATE TABLE PRODUCT (
    PRODUCT_ID INT AUTO_INCREMENT PRIMARY KEY,
    TITLE VARCHAR(255) NOT NULL,
    DESCRIPTION TEXT,
    QTY INT,
    PRICE INT,
    PRODUCT_CATEGORY VARCHAR(50) NOT NULL,
    USER_ID VARCHAR(50),
    PRODUCT_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (USER_ID) REFERENCES USER(USER_ID)
);

CREATE TABLE PRODUCT_IMG (
	IMG_UUID CHAR(36) NOT NULL,
    PRODUCT_ID INT NOT NULL,
    IMG_URL VARCHAR(255) NOT NULL,
    IMG_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (PRODUCT_ID) REFERENCES PRODUCT(PRODUCT_ID)
);

CREATE TABLE CHAT (
    CHAT_ID INT AUTO_INCREMENT PRIMARY KEY,
    SENDER_ID VARCHAR(50) NOT NULL, 
    RECEIVER_ID VARCHAR(50) NOT NULL,
    MESSAGE TEXT NOT NULL,
    SEND_TIME TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (SENDER_ID) REFERENCES USER(USER_ID),
    FOREIGN KEY (RECEIVER_ID) REFERENCES USER(USER_ID)
);

CREATE TABLE CART (
    CART_ID INT AUTO_INCREMENT PRIMARY KEY,
    USER_ID VARCHAR(50) NOT NULL, 
    PRODUCT_ID INT NOT NULL,
    QTY INT NOT NULL,
    ADDED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (USER_ID) REFERENCES USER(USER_ID),
    FOREIGN KEY (PRODUCT_ID) REFERENCES PRODUCT(PRODUCT_ID)
);

CREATE TABLE WISHLIST (
    WISHLIST_ID INT AUTO_INCREMENT PRIMARY KEY,
    USER_ID VARCHAR(50) NOT NULL,
    PRODUCT_ID INT NOT NULL,
    PRODUCT_IMG_URL VARCHAR(255),
    ADDED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (USER_ID) REFERENCES USER(USER_ID),
    FOREIGN KEY (PRODUCT_ID) REFERENCES PRODUCT(PRODUCT_ID)
);

CREATE TABLE NOTICE (
    NOTICE_ID INT AUTO_INCREMENT PRIMARY KEY,
    ADMIN_ID INT NOT NULL,
    TITLE VARCHAR(255) NOT NULL,
    CONTENT TEXT NOT NULL,
    CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (ADMIN_ID) REFERENCES ADMIN(ADMIN_ID)
);

CREATE TABLE QNA (
    QNA_ID INT AUTO_INCREMENT PRIMARY KEY,
    USER_ID VARCHAR(50) NOT NULL,
    PRODUCT_ID INT NOT NULL,
    QUESTION TEXT NOT NULL,
    ANSWER TEXT,
    QUESTION_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ANSWER_DATE TIMESTAMP,
    FOREIGN KEY (USER_ID) REFERENCES USER(USER_ID),
    FOREIGN KEY (PRODUCT_ID) REFERENCES PRODUCT(PRODUCT_ID)
);

CREATE TABLE QUESTION_BOARD (
    QUESTION_ID INT AUTO_INCREMENT PRIMARY KEY,
    USER_ID VARCHAR(50) NOT NULL, 
    TITLE VARCHAR(255) NOT NULL,
    QUESTION TEXT NOT NULL,
    ANSWER TEXT,
    QUESTION_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ANSWER_DATE TIMESTAMP,
    FOREIGN KEY (USER_ID) REFERENCES USER(USER_ID)
);

CREATE TABLE SELLER_PRODUCT (
    SELLER_PRODUCT_ID INT AUTO_INCREMENT PRIMARY KEY,
    USER_ID VARCHAR(50) NOT NULL,
    PRODUCT_ID INT NOT NULL,
    PRODUCT_IMG_URL VARCHAR(255),
    ADDED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (USER_ID) REFERENCES USER(USER_ID),
    FOREIGN KEY (PRODUCT_ID) REFERENCES PRODUCT(PRODUCT_ID)
);

CREATE TABLE BUYER_PRODUCT (
    BUYER_PRODUCT_ID INT AUTO_INCREMENT PRIMARY KEY,
    USER_ID VARCHAR(50) NOT NULL,
    PRODUCT_ID INT NOT NULL,
    PRODUCT_IMG_URL VARCHAR(255),
    ADDED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (USER_ID) REFERENCES USER(USER_ID),
    FOREIGN KEY (PRODUCT_ID) REFERENCES PRODUCT(PRODUCT_ID)
);

CREATE TABLE COMPLETED_TRADE(
    TRANSACTION_ID INT AUTO_INCREMENT PRIMARY KEY,
    BUYER_ID VARCHAR(50) NOT NULL, 
    SELLER_ID VARCHAR(50) NOT NULL, 
    PRODUCT_ID INT NOT NULL,
    TRANSACTION_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (BUYER_ID) REFERENCES USER(USER_ID),
    FOREIGN KEY (SELLER_ID) REFERENCES USER(USER_ID),
    FOREIGN KEY (PRODUCT_ID) REFERENCES PRODUCT(PRODUCT_ID)
);

CREATE TABLE PRODUCT_LIKE (
    LIKE_ID INT AUTO_INCREMENT PRIMARY KEY,
    PRODUCT_ID INT NOT NULL,
    LIKES_COUNT INT DEFAULT 0,
    FOREIGN KEY (PRODUCT_ID) REFERENCES PRODUCT(PRODUCT_ID)
);