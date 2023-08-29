ALTER TABLE client
ADD COLUMN usuario_id BIGINT;


ALTER TABLE client
ADD CONSTRAINT fk_client_user_1
FOREIGN KEY (usuario_id)
REFERENCES user(id);

ALTER TABLE client
DROP COLUMN email;

ALTER TABLE `hotelo`.`client` 
DROP FOREIGN KEY `fk_client_user_1`;
ALTER TABLE `hotelo`.`client` 
CHANGE COLUMN `usuario_id` `usuario_id` BIGINT NOT NULL ;
ALTER TABLE `hotelo`.`client` 
ADD CONSTRAINT `fk_client_user_1`
  FOREIGN KEY (`usuario_id`)
  REFERENCES `hotelo`.`user` (`id`);