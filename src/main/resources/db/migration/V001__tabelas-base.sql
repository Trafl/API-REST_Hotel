SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema Hotelo
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema Hotelo
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Hotelo` DEFAULT CHARACTER SET utf8 ;
USE `Hotelo` ;

-- -----------------------------------------------------
-- Table `Hotelo`.`client`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Hotelo`.`client` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `celular` VARCHAR(15) NOT NULL,
  `cpf` VARCHAR(14) NOT NULL,
  `email` VARCHAR(60) NOT NULL,
  `nome` VARCHAR(200) NOT NULL,
  `rg` VARCHAR(12) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Hotelo`.`hotel`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Hotelo`.`hotel` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(200) NOT NULL,
  `bairro` VARCHAR(200) NOT NULL,
  `cep` VARCHAR(10) NOT NULL,
  `cidade` VARCHAR(45) NOT NULL,
  `complemento` VARCHAR(45) NOT NULL,
  `numero` VARCHAR(10) NOT NULL,
  `nome` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Hotelo`.`room`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Hotelo`.`room` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `code` INT NOT NULL,
  `descricao` VARCHAR(255) NULL,
  `diaria` DECIMAL NOT NULL,
  `status` VARCHAR(45) NULL,
  `hotel_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `room_hotel_fk_idx` (`hotel_id` ASC) VISIBLE,
  CONSTRAINT `room_hotel_fk`
    FOREIGN KEY (`hotel_id`)
    REFERENCES `Hotelo`.`hotel` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Hotelo`.`rent`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Hotelo`.`rent` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `check_in` TIMESTAMP NOT NULL,
  `check_out` TIMESTAMP NOT NULL,
  `file_name` VARCHAR(100) NOT NULL,
  `observacoes` VARCHAR(200) NOT NULL,
  `pagamento` VARCHAR(45) NOT NULL,
  `status` VARCHAR(45) NOT NULL,
  `valor` DECIMAL NULL,
  `cliente_id` BIGINT NOT NULL,
  `quarto_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `rent_room_fk_idx` (`quarto_id` ASC) VISIBLE,
  INDEX `rent_client_fk_idx` (`cliente_id` ASC) VISIBLE,
  CONSTRAINT `rent_room_fk`
    FOREIGN KEY (`quarto_id`)
    REFERENCES `Hotelo`.`room` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `rent_client_fk`
    FOREIGN KEY (`cliente_id`)
    REFERENCES `Hotelo`.`client` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;