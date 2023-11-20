-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema ssafit
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `ssafit` ;

-- -----------------------------------------------------
-- Schema ssafit
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ssafit` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `ssafit` ;

-- -----------------------------------------------------
-- Table `ssafit`.`level`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafit`.`level` ;

CREATE TABLE IF NOT EXISTS `ssafit`.`level` (
  `levelId` INT NOT NULL AUTO_INCREMENT,
  `levelname` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`levelId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ssafit`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafit`.`user` ;

CREATE TABLE IF NOT EXISTS `ssafit`.`user` (
  `userId` INT NOT NULL AUTO_INCREMENT,
  `levelId` INT NULL DEFAULT 1,
  `name` VARCHAR(10) NOT NULL,
  `email` VARCHAR(40) NOT NULL,
  `password` VARCHAR(200) NOT NULL,
  `nickname` VARCHAR(20) NOT NULL,
  `birth` VARCHAR(6) NOT NULL,
  `phonenum` CHAR(13) NULL DEFAULT NULL,
  `address` VARCHAR(45) NULL DEFAULT NULL,
  `cash` INT NULL DEFAULT 0,
  `sex` CHAR(1) NOT NULL,
  PRIMARY KEY (`userId`),
  INDEX `fk_user_level1_idx` (`levelId` ASC) VISIBLE,
  CONSTRAINT `fk_user_level1`
    FOREIGN KEY (`levelId`)
    REFERENCES `ssafit`.`level` (`levelId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ssafit`.`board`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafit`.`board` ;

CREATE TABLE IF NOT EXISTS `ssafit`.`board` (
  `boardId` INT NOT NULL AUTO_INCREMENT,
  `userId` INT NOT NULL,
  `title` VARCHAR(40) NOT NULL,
  `content` VARCHAR(200) NOT NULL,
  `viewCnt` INT NULL DEFAULT '0',
  `regDate` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `likeCount` INT NULL DEFAULT 0,
  PRIMARY KEY (`boardId`),
  INDEX `fk_board_user1_idx` (`userId` ASC) VISIBLE,
  CONSTRAINT `fk_board_user1`
    FOREIGN KEY (`userId`)
    REFERENCES `ssafit`.`user` (`userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ssafit`.`comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafit`.`comment` ;

CREATE TABLE IF NOT EXISTS `ssafit`.`comment` (
  `commentId` INT NOT NULL AUTO_INCREMENT,
  `userId` INT NOT NULL,
  `boardId` INT NOT NULL,
  `content` VARCHAR(100) NOT NULL,
  `regDate` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`commentId`),
  INDEX `userId` (`userId` ASC) VISIBLE,
  INDEX `fk_comment_board1_idx` (`boardId` ASC) VISIBLE,
  CONSTRAINT `comment_ibfk_2`
    FOREIGN KEY (`userId`)
    REFERENCES `ssafit`.`user` (`userId`),
  CONSTRAINT `fk_comment_board1`
    FOREIGN KEY (`boardId`)
    REFERENCES `ssafit`.`board` (`boardId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ssafit`.`video`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafit`.`video` ;

CREATE TABLE IF NOT EXISTS `ssafit`.`video` (
  `videoId` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(200) NOT NULL,
  `fitPartName` VARCHAR(10) NOT NULL,
  `channelName` VARCHAR(40) NOT NULL,
  `viewCnt` INT NULL DEFAULT '0',
  `url` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`videoId`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ssafit`.`trainer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafit`.`trainer` ;

CREATE TABLE IF NOT EXISTS `ssafit`.`trainer` (
  `trainerId` INT NOT NULL AUTO_INCREMENT,
  `trainerlevel` INT NULL DEFAULT 1,
  `name` VARCHAR(10) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(200) NOT NULL,
  `nickname` VARCHAR(45) NOT NULL,
  `birth` VARCHAR(6) NOT NULL,
  `phonenum` VARCHAR(45) NOT NULL,
  `address` VARCHAR(45) NOT NULL,
  `cash` INT NULL DEFAULT 0,
  `sex` CHAR(1) NOT NULL,
  PRIMARY KEY (`trainerId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ssafit`.`category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafit`.`category` ;

CREATE TABLE IF NOT EXISTS `ssafit`.`category` (
  `category` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`category`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ssafit`.`class`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafit`.`class` ;

CREATE TABLE IF NOT EXISTS `ssafit`.`class` (
  `classId` INT NOT NULL AUTO_INCREMENT,
  `trainerId` INT NOT NULL,
  `category` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`classId`),
  INDEX `fk_class_trainer2_idx` (`trainerId` ASC) VISIBLE,
  INDEX `fk_class_category1_idx` (`category` ASC) VISIBLE,
  CONSTRAINT `fk_class_trainer2`
    FOREIGN KEY (`trainerId`)
    REFERENCES `ssafit`.`trainer` (`trainerId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_class_category1`
    FOREIGN KEY (`category`)
    REFERENCES `ssafit`.`category` (`category`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ssafit`.`participate`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafit`.`participate` ;

CREATE TABLE IF NOT EXISTS `ssafit`.`participate` (
  `participateId` INT NOT NULL AUTO_INCREMENT,
  `userId` INT NOT NULL,
  `classId` INT NOT NULL,
  PRIMARY KEY (`participateId`),
  INDEX `fk_participate_user1_idx` (`userId` ASC) VISIBLE,
  INDEX `fk_participate_class1_idx` (`classId` ASC) VISIBLE,
  CONSTRAINT `fk_participate_user1`
    FOREIGN KEY (`userId`)
    REFERENCES `ssafit`.`user` (`userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_participate_class1`
    FOREIGN KEY (`classId`)
    REFERENCES `ssafit`.`class` (`classId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ssafit`.`class_info`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafit`.`class_info` ;

CREATE TABLE IF NOT EXISTS `ssafit`.`class_info` (
  `classId` INT NOT NULL,
  `name` INT NOT NULL,
  `detail` VARCHAR(400) NOT NULL,
  `cost` VARCHAR(45) NOT NULL,
  `time` DATE NOT NULL,
  INDEX `fk_class_info_class1_idx` (`classId` ASC) VISIBLE,
  PRIMARY KEY (`classId`),
  CONSTRAINT `fk_class_info_class1`
    FOREIGN KEY (`classId`)
    REFERENCES `ssafit`.`class` (`classId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ssafit`.`gymowner`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafit`.`gymowner` ;

CREATE TABLE IF NOT EXISTS `ssafit`.`gymowner` (
  `gymownerId` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(200) NOT NULL,
  `nickname` VARCHAR(45) NOT NULL,
  `phonenum` VARCHAR(45) NOT NULL,
  `cash` INT NULL DEFAULT 0,
  PRIMARY KEY (`gymownerId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ssafit`.`gym`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafit`.`gym` ;

CREATE TABLE IF NOT EXISTS `ssafit`.`gym` (
  `gymId` INT NOT NULL AUTO_INCREMENT,
  `gymownerId` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `category` VARCHAR(45) NOT NULL,
  `status` VARCHAR(45) NOT NULL,
  `price` INT NOT NULL,
  `likeCount` INT NOT NULL,
  `description` VARCHAR(1000) NOT NULL,
  `regDate` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`gymId`),
  INDEX `fk_gym_gymowner1_idx` (`gymownerId` ASC) VISIBLE,
  CONSTRAINT `fk_gym_gymowner1`
    FOREIGN KEY (`gymownerId`)
    REFERENCES `ssafit`.`gymowner` (`gymownerId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ssafit`.`trainer_reserve`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafit`.`trainer_reserve` ;

CREATE TABLE IF NOT EXISTS `ssafit`.`trainer_reserve` (
  `reserveId` INT NOT NULL AUTO_INCREMENT,
  `trainerId` INT NOT NULL,
  `gymId` INT NOT NULL,
  PRIMARY KEY (`reserveId`),
  INDEX `fk_reserve_trainer1_idx` (`trainerId` ASC) VISIBLE,
  INDEX `fk_reserve_gym1_idx` (`gymId` ASC) VISIBLE,
  CONSTRAINT `fk_reserve_trainer1`
    FOREIGN KEY (`trainerId`)
    REFERENCES `ssafit`.`trainer` (`trainerId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_reserve_gym1`
    FOREIGN KEY (`gymId`)
    REFERENCES `ssafit`.`gym` (`gymId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ssafit`.`follow`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafit`.`follow` ;

CREATE TABLE IF NOT EXISTS `ssafit`.`follow` (
  `followId` INT NOT NULL AUTO_INCREMENT,
  `following` INT NOT NULL,
  `follower` INT NOT NULL,
  PRIMARY KEY (`followId`),
  INDEX `fk_follow_user1_idx` (`follower` ASC) VISIBLE,
  INDEX `fk_follow_user2_idx` (`following` ASC) VISIBLE,
  CONSTRAINT `fk_follow_user1`
    FOREIGN KEY (`follower`)
    REFERENCES `ssafit`.`user` (`userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_follow_user2`
    FOREIGN KEY (`following`)
    REFERENCES `ssafit`.`user` (`userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ssafit`.`board_like`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafit`.`board_like` ;

CREATE TABLE IF NOT EXISTS `ssafit`.`board_like` (
  `boardlikeId` INT NOT NULL AUTO_INCREMENT,
  `userId` INT NOT NULL,
  `boardId` INT NOT NULL,
  PRIMARY KEY (`boardlikeId`),
  INDEX `fk_comment_like_user1_idx` (`userId` ASC) VISIBLE,
  INDEX `fk_comment_like_board1_idx` (`boardId` ASC) VISIBLE,
  CONSTRAINT `fk_comment_like_user1`
    FOREIGN KEY (`userId`)
    REFERENCES `ssafit`.`user` (`userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_like_board1`
    FOREIGN KEY (`boardId`)
    REFERENCES `ssafit`.`board` (`boardId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ssafit`.`gym_like`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafit`.`gym_like` ;

CREATE TABLE IF NOT EXISTS `ssafit`.`gym_like` (
  `gymlikeId` INT NOT NULL AUTO_INCREMENT,
  `gymId` INT NOT NULL,
  `userId` INT NOT NULL,
  PRIMARY KEY (`gymlikeId`),
  INDEX `fk_gym_like_gym1_idx` (`gymId` ASC) VISIBLE,
  INDEX `fk_gym_like_user1_idx` (`userId` ASC) VISIBLE,
  CONSTRAINT `fk_gym_like_gym1`
    FOREIGN KEY (`gymId`)
    REFERENCES `ssafit`.`gym` (`gymId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_gym_like_user1`
    FOREIGN KEY (`userId`)
    REFERENCES `ssafit`.`user` (`userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ssafit`.`user_reserve`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafit`.`user_reserve` ;

CREATE TABLE IF NOT EXISTS `ssafit`.`user_reserve` (
  `reserveId` INT NOT NULL AUTO_INCREMENT,
  `userId` INT NOT NULL,
  `gymId` INT NOT NULL,
  `reserveTime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `meetTime` TIMESTAMP NOT NULL,
  PRIMARY KEY (`reserveId`),
  INDEX `fk_user_reserve_user1_idx` (`userId` ASC) VISIBLE,
  INDEX `fk_user_reserve_gym1_idx` (`gymId` ASC) VISIBLE,
  CONSTRAINT `fk_user_reserve_user1`
    FOREIGN KEY (`userId`)
    REFERENCES `ssafit`.`user` (`userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_reserve_gym1`
    FOREIGN KEY (`gymId`)
    REFERENCES `ssafit`.`gym` (`gymId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ssafit`.`chatroom`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafit`.`chatroom` ;

CREATE TABLE IF NOT EXISTS `ssafit`.`chatroom` (
  `chatmsgId` INT NOT NULL AUTO_INCREMENT,
  `trainerId` INT NOT NULL,
  `userId` INT NOT NULL,
  `message` VARCHAR(500) NOT NULL,
  `read_or_not` TINYINT NOT NULL DEFAULT 0,
  `createDate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`chatmsgId`),
  INDEX `fk_chatmsg_trainer1_idx` (`trainerId` ASC) VISIBLE,
  INDEX `fk_chatmsg_user1_idx` (`userId` ASC) VISIBLE,
  CONSTRAINT `fk_chatmsg_trainer1`
    FOREIGN KEY (`trainerId`)
    REFERENCES `ssafit`.`trainer` (`trainerId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_chatmsg_user1`
    FOREIGN KEY (`userId`)
    REFERENCES `ssafit`.`user` (`userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ssafit`.`payment_kakao`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafit`.`payment_kakao` ;

CREATE TABLE IF NOT EXISTS `ssafit`.`payment_kakao` (
  `paymentId` INT NOT NULL AUTO_INCREMENT,
  `orderId` INT NOT NULL,
  `payMethod` VARCHAR(45) NOT NULL,
  `payDate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `payPrice` INT NOT NULL,
  PRIMARY KEY (`paymentId`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
