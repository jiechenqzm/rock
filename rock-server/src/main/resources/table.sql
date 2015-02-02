--core_data
CREATE TABLE `core_data` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `group` varchar(128) CHARACTER SET utf8mb4 NOT NULL,
  `data_id` varchar(128) CHARACTER SET utf8mb4 NOT NULL,
  `version` bigint(20) NOT NULL,
  `summary` varchar(128) CHARACTER SET utf8mb4 NOT NULL,
  `deleted` bit(1) NOT NULL,
  `content` text CHARACTER SET utf8mb4 NOT NULL,
  `gmt_create` datetime NOT NULL,
  `gmt_modified` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `group_dataid_index` (`group`,`data_id`) USING BTREE,
  KEY `gmtmodified_index` (`gmt_modified`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8