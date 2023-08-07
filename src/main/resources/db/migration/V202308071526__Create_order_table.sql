CREATE TABLE IF NOT EXISTS `order`
(
  `id`                   VARCHAR(32)    NOT NULL,
  `customer_id`          VARCHAR(32)    NOT NULL,
  `products`             JSON           NOT NULL,
  `total`                DECIMAL(12)    NOT NULL,
  `status`               VARCHAR(32)    NOT NULL,
  `create_time`          TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time`          TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
PRIMARY KEY(`id`)
)ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci;
