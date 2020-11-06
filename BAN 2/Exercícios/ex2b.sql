CREATE TABLE Curso(
sigla_curso CHAR(10) NOT NULL PRIMARY KEY,
nome VARCHAR(100) NOT NULL,
titulacao VARCHAR(100) NOT NULL
);

CREATE TABLE Aluno(
cpf CHAR(11) NOT NULL PRIMARY KEY,
nome VARCHAR(100) NOT NULL,
rua VARCHAR(255) NOT NULL,
numero INTEGER NOT NULL,
estado CHAR(2) NOT NULL,
cidade VARCHAR(100) NOT NULL,
cep INTEGER NOT NULL
);

CREATE TABLE Area(
cod_area INTEGER NOT NULL PRIMARY KEY,
descricao VARCHAR(100)
);

CREATE TABLE Inscricao(
matricula INTEGER NOT NULL PRIMARY KEY,
sigla_curso CHAR(10),
cpf CHAR(11),
FOREIGN KEY (sigla_curso) REFERENCES Curso,
FOREIGN KEY (cpf) REFERENCES Aluno
);

CREATE TABLE Disciplina(
sigla_disc CHAR(10) NOT NULL PRIMARY KEY,
nome VARCHAR(100) NOT NULL,
carga_horaria INTEGER NOT NULL
);

CREATE TABLE Professor(
reg_mec INTEGER NOT NULL PRIMARY KEY,
nome VARCHAR(100) NOT NULL,
rua VARCHAR(255) NOT NULL,
numero INTEGER NOT NULL,
cidade VARCHAR(100) NOT NULL,
estado CHAR(2) NOT NULL,
cep INTEGER NOT NULL
);

CREATE TABLE Grade(
sigla_curso CHAR(10),
sigla_disc CHAR(10),
PRIMARY KEY (sigla_curso, sigla_disc),
FOREIGN KEY (sigla_curso) REFERENCES Curso,
FOREIGN KEY (sigla_disc) REFERENCES Disciplina
);

CREATE TABLE Matricula(
ano DATE NOT NULL,
matricula INTEGER NOT NULL,
sigla_disc CHAR(10) NOT NULL,
semestre INTEGER NOT NULL,
PRIMARY KEY(ano, matricula, sigla_disc, semestre),
FOREIGN KEY (matricula) REFERENCES Inscricao,
FOREIGN KEY (sigla_disc) REFERENCES Disciplina
);

CREATE TABLE Leciona(
   sigla_disc CHAR(10),
   reg_mec INTEGER,
   ano DATE,
   semestre INTEGER,
   PRIMARY KEY (sigla_disc, reg_mec, ano, semestre),
   FOREIGN KEY (sigla_disc) REFERENCES Disciplina,
   FOREIGN KEY (reg_mec) REFERENCES Professor
);

CREATE TABLE Habilitacao(
   sigla_disc CHAR(10),
   reg_mec INTEGER,
   PRIMARY KEY (sigla_disc, reg_mec),
   FOREIGN KEY (sigla_disc) REFERENCES Disciplina,
   FOREIGN KEY (reg_mec) REFERENCES Professor
);

CREATE TABLE Responsavel(
   reg_mec INTEGER,
   cod_area INTEGER,
   ate DATE NOT NULL,
   PRIMARY KEY (reg_mec, cod_area),
   FOREIGN KEY (reg_mec) REFERENCES Professor,
   FOREIGN KEY (cod_area) REFERENCES Area
);

CREATE TABLE Titulo(
   reg_mec INTEGER,
   cod_area INTEGER,
   data_da_titulacao DATE NOT NULL,
   PRIMARY KEY (reg_mec, cod_area),
   FOREIGN KEY (reg_mec) REFERENCES Professor,
   FOREIGN KEY (cod_area) REFERENCES Area
);

CREATE TABLE Requisito(
   sigla_disc CHAR(10),
   sigla_curso CHAR(10),
   sigla_disc_req CHAR(10),
   sigla_curso_req CHAR(10),
   PRIMARY KEY (sigla_disc, sigla_curso, sigla_disc_req, sigla_curso_req),
   FOREIGN KEY (sigla_disc) REFERENCES Disciplina(sigla_disc),
   FOREIGN KEY (sigla_disc_req) REFERENCES Disciplina(sigla_disc),
   FOREIGN KEY (sigla_curso) REFERENCES Curso(sigla_curso),
   FOREIGN KEY (sigla_curso_req) REFERENCES Curso(sigla_curso)
);
