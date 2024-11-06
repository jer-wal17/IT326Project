-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema it326_group_project
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema it326_group_project
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `it326_group_project` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `it326_group_project` ;

-- -----------------------------------------------------
-- Table `it326_group_project`.`AccountPreferences`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `it326_group_project`.`AccountPreferences` ;

CREATE TABLE IF NOT EXISTS `it326_group_project`.`AccountPreferences` (
  `AccountID` INT NOT NULL,
  `FavoriteDecade` INT NOT NULL,
  PRIMARY KEY (`AccountID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `it326_group_project`.`Accounts`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `it326_group_project`.`Accounts` ;

CREATE TABLE IF NOT EXISTS `it326_group_project`.`Accounts` (
  `AccountID` INT NOT NULL AUTO_INCREMENT,
  `Username` VARCHAR(60) NOT NULL,
  `Password` VARCHAR(30) NOT NULL,
  `PhoneNumber` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`AccountID`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `it326_group_project`.`GroupMembers`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `it326_group_project`.`GroupMembers` ;

CREATE TABLE IF NOT EXISTS `it326_group_project`.`GroupMembers` (
  `GroupMembersID` INT NOT NULL AUTO_INCREMENT,
  `GroupID` INT NOT NULL,
  `AccountID` INT NOT NULL,
  PRIMARY KEY (`GroupMembersID`))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `it326_group_project`.`Groups`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `it326_group_project`.`Groups` ;

CREATE TABLE IF NOT EXISTS `it326_group_project`.`Groups` (
  `GroupID` INT NOT NULL AUTO_INCREMENT,
  `MovieTitle` VARCHAR(70) NOT NULL,
  `MeetingAddress` VARCHAR(70) NOT NULL,
  `MeetingDate` DATETIME NOT NULL,
  PRIMARY KEY (`GroupID`),
  UNIQUE INDEX `GroupID_UNIQUE` (`GroupID` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `it326_group_project`.`MovieReviews`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `it326_group_project`.`MovieReviews` ;

CREATE TABLE IF NOT EXISTS `it326_group_project`.`MovieReviews` (
  `MovieReviewsID` INT NOT NULL AUTO_INCREMENT,
  `AccountID` INT NOT NULL,
  `Review` VARCHAR(200) NOT NULL,
  `MovieTitle` VARCHAR(70) NOT NULL,
  PRIMARY KEY (`MovieReviewsID`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
