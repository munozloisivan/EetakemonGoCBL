DROP DATABASE IF EXISTS juego;
CREATE DATABASE juego;
USE juego;

CREATE TABLE `juego`.`usuario` (
  `idusuario` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `nick` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `contraseña` VARCHAR(45) NOT NULL,
  `experiencia` INT NULL,
  `batjugadas` INT NULL,
  `batganadas` INT NULL,
  PRIMARY KEY (`idusuario`),
  UNIQUE INDEX `idusuario_UNIQUE` (`idusuario` ASC),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC));

  CREATE TABLE `juego`.`etakemon` (
  `idetakemon` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `descripcion` VARCHAR(45),
  PRIMARY KEY (`idetakemon`));

CREATE TABLE `juego`.`lugares` (
  `idlugares` INT NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `longitud` DECIMAL(10) NOT NULL,
  `latitud` DECIMAL(10) NOT NULL,
  PRIMARY KEY (`idlugares`));

CREATE TABLE `juego`.`habilidades` (
  `idhabilidades` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `potencia` INT NOT NULL,
  `descripcion` VARCHAR(70) NOT NULL,
  PRIMARY KEY (`idhabilidades`));

CREATE TABLE `juego`.`objetos` (
  `idobjetos` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `descripcion` VARCHAR(70) NOT NULL,
  PRIMARY KEY (`idobjetos`));

CREATE TABLE `juego`.`objetosusuario` (
  `idobjeto` INT NOT NULL,
  `idusuario` INT NOT NULL,
  INDEX `idusuario_idx` (`idusuario` ASC),
  INDEX `idobjeto_idx` (`idobjeto` ASC),
  CONSTRAINT `idobjeto`
    FOREIGN KEY (`idobjeto`)
    REFERENCES `juego`.`objetos` (`idobjetos`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `idusuario`
    FOREIGN KEY (`idusuario`)
    REFERENCES `juego`.`usuario` (`idusuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE `juego`.`captura` (
  `idcaptura` INT NOT NULL AUTO_INCREMENT,
  `idusuarios` INT NOT NULL,
  `idetakemons` INT NOT NULL,
  `idhabilidads` INT NOT NULL,
  `vida` INT NOT NULL,
  `ataque` INT NOT NULL,
  `defensa` INT NOT NULL,
  `fecha` DATE NULL,
  PRIMARY KEY (`idcaptura`),
  INDEX `idusuario_idx` (`idusuarios` ASC),
  INDEX `idetakemon_idx` (`idetakemons` ASC),
  INDEX `idhabilidad_idx` (`idhabilidads` ASC),
  CONSTRAINT `idusuarios`
    FOREIGN KEY (`idusuarios`)
    REFERENCES `juego`.`usuario` (`idusuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `idetakemons`
    FOREIGN KEY (`idetakemons`)
    REFERENCES `juego`.`etakemon` (`idetakemon`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `idhabilidads`
    FOREIGN KEY (`idhabilidads`)
    REFERENCES `juego`.`habilidades` (`idhabilidad`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);