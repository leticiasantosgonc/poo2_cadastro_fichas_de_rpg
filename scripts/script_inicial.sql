CREATE DATABASE rpg;

USE rpg;

CREATE TABLE conta(
	idConta int PRIMARY KEY AUTO_INCREMENT,
    login varchar(100) NOT NULL, 
    senha varchar(100) NOT NULL
);

CREATE TABLE personagem(
	idPersonagem int PRIMARY KEY AUTO_INCREMENT,
	nome varchar(100) NOT NULL, 
    nivel int NOT NULL
);

CREATE TABLE raca(
	idRaca int PRIMARY KEY AUTO_INCREMENT,
	nome varchar(100) NOT NULL, 
    descricao varchar(100) NOT NULL, 
    fraqueza int NOT NULL,
    classe int NOT NULL
);