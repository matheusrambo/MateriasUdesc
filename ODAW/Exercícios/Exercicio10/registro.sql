/* Acadêmicos: Leonardo Merbold e Gustavo Ribeiro de Aguiar

// mysql -h localhost -u root
//show databases;
//show tables from academia; 
*/
--_______________________________ Base de Dados __________________________________

CREATE DATABASE academia;

--use academia;

CREATE TABLE registro(id int auto_increment primary key, nome char(20), email varchar(80) not null, telefone int(8) not null, cep int(8) not null, modalidade varchar(15) not null, inicio date not null);

INSERT INTO registro (nome, email, telefone, cep, modalidade, inicio) VALUES
	('Ana', 'ana.a@gmail.com', 55993388, 12354031, 'Zumba', 10-10-2018),
	('Bruno', 'Brunoobruno@gmail.com', 22991188, 12324032, 'Musculacao', 31-10-2017),
	('Fernando', 'F3rn4nd1nh0@gmail.com', 12343388, 11354033, 'Funcional', 16-04-2006),
	('Gabriela', 'Gabi.bibi@hotmail.com', 75233128, 11154031, 'Zumba', 18-05-2019),
	('Leticia', 'Leticia-ab@gmail.com', 55999265, 12354923, 'HIIT', 03-09-2013),
	('Marcos', 'marquitodagalera@motortt.com', 12398388, 82354064, 'Funcional', 25-03-2019),
	('Roger', 'rogm@gmail.com', 55993328, 12854283, 'Musculacao', 09-09-2009),
	('Vanderley', 'vandinho@hotmail.com', 15993388, 87654103, 'Musculacao', 12-11-2014);

update registro set nome = "Roberto" where cod = 7;
delete from registro where cod >= 12;
drop table registro;
drop database academia;