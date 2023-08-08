CREATE TABLE IF NOT EXISTS `product`
(
    `id`              VARCHAR(64)             NOT NULL PRIMARY KEY,
    `name`            VARCHAR(64)             NOT NULL,
    `price`           DECIMAL(10, 2),
    `status`          VARCHAR(32)             NOT NULL,
    `create_time`     DATETIME                DEFAULT CURRENT_TIMESTAMP NOT NULL,
    `update_time`     DATETIME                DEFAULT CURRENT_TIMESTAMP NOT NULL
);