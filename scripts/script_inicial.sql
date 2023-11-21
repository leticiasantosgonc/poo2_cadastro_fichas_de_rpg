CREATE DATABASE rpg;

USE rpg;

CREATE TABLE conta(
	idConta int PRIMARY KEY AUTO_INCREMENT,
    login varchar(100) NOT NULL UNIQUE, 
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

SELECT * FROM raca;

CREATE TABLE contaPersonagem(
	idConta int,
    idPersonagem int,
    FOREIGN KEY (idConta) REFERENCES conta(idConta),
    FOREIGN KEY (idPersonagem) REFERENCES personagem(idPersonagem)
);

CREATE TABLE personagemRaca(
	idPersonagem int,
    idRaca int,
    FOREIGN KEY (idPersonagem) REFERENCES personagem(idPersonagem),
    FOREIGN KEY (idRaca) REFERENCES raca(idRaca)    
);