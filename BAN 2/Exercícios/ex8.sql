--1) Gatilho para impedir a inserção ou atualização de Clientes com o mesmo CPF.

CREATE OR REPLACE FUNCTION verificaCPF() RETURNS TRIGGER AS
$$
BEGIN
	IF TG_OP = 'INSERT' THEN
		IF (SELECT COUNT(codc) FROM cliente WHERE cpf = NEW.cpf) > 0 THEN
			RAISE EXCEPTION 'CPF já cadastrado no banco de dados.';
		END IF;
	ELSIF TG_OP = 'UPDATE' THEN
		IF NEW.cpf <> OLD.cpf THEN
			IF (SELECT COUNT(codc) FROM cliente WHERE cpf = NEW.cpf) > 0 THEN
				RAISE EXCEPTION 'CPF já cadastrado no banco de dados.';
				END IF;
			END IF;
		END IF;
	RETURN NEW;
END;
$$
LANGUAGE plpgsql;

CREATE TRIGGER verificaCPF
BEFORE INSERT OR UPDATE ON cliente
FOR EACH ROW EXECUTE PROCEDURE verificaCPF();

-- Executa o trigger fazendo a condição de disparo. Nesse caso, uma inserção ou atualização de cpf.

SELECT * FROM cliente
INSERT INTO cliente VALUES (8, '20000200000', 'Ana', 20, 'América', 'Joinville');

UPDATE cliente SET nome = 'Ana Teste', cpf = '10000110000' WHERE  codc = 1


--2) Gatilho para impedir a inserção ou atualização de Mecânicos com idade menor que 20 anos.

CREATE OR REPLACE FUNCTION verificaIdade() RETURNS TRIGGER AS
$$
BEGIN
IF TG_OP = 'INSERT' THEN
		IF (SELECT idade FROM mecanico WHERE idade = NEW.idade) < 20 THEN
			RAISE EXCEPTION 'Mecânico com idade inferior a 20 anos.';
		END IF;
	ELSIF TG_OP = 'UPDATE' THEN
		IF NEW.idade <> OLD.idade THEN
			IF (SELECT idade FROM mecanico WHERE idade = NEW.idade) < 20 THEN
				RAISE EXCEPTION 'Mecânico com idade inferior a 20 anos.';
				END IF;
			END IF;
		END IF;
	RETURN NEW;
END;
$$
LANGUAGE plpgsql

CREATE TRIGGER verificaIdadeMec
BEFORE INSERT OR UPDATE ON mecanico
FOR EACH ROW EXECUTE PROCEDURE verificaIdadeMec()

SELECT * FROM mecanico
INSERT INTO mecanico VALUES (5, '10000110000', 'João', 18, 'América', 'Joinville', 'som', 1);


--3) Gatilho para atribuir um cods (sequencial) para um novo setor inserido.

CREATE OR REPLACE FUNCTION novo_cods() RETURNS TRIGGER AS
$$
DECLARE
	vcods INT DEFAULT 0;
BEGIN
	SELECT MAX(cods) FROM setor INTO vcods;
	vcods := vcods + 1;
	NEW.cods := vcods;
	RETURN NEW;
END;
$$
LANGUAGE plpgsql;

CREATE TRIGGER novo_cods
BEFORE INSERT OR UPDATE ON setor
FOR EACH ROW EXECUTE PROCEDURE novo_cods()

INSERT INTO setor VALUES (1, 'Testando');

--4) Gatilho para impedir a inserção de um mecânico ou cliente com CPF inválido.

CREATE OR REPLACE FUNCTION verificaCPF() RETURNS TRIGGER AS
$$
BEGIN
	IF NOT validaCPF(new.cpf) THEN
		RAISE EXCEPTION 'CPF inválido.';
	END IF;
	RETURN NEW;
END;
$$
LANGUAGE plpgsql;

CREATE TRIGGER verificaCPFCli
BEFORE INSERT OR UPDATE ON cliente
FOR EACH ROW EXECUTE PROCEDURE verificaCPF()

CREATE TRIGGER verificaCPFMec
BEFORE INSERT OR UPDATE ON mecanico
FOR EACH ROW EXECUTE PROCEDURE verificaCPF()

INSERT INTO mecanico VALUES (10, '11000111000', 'rambo', 23, 'blabla', 'bléblé', 'blibli', 3)


--5) Gatilho para impedir que um mecânico seja removido caso não exista outro mecânico com a mesma função.

CREATE OR REPLACE FUNCTION verificaFuncao() RETURNS TRIGGER AS
$$
BEGIN
	IF (SELECT COUNT(codm) FROM mecanico WHERE funcao = OLD.funcao) < 2 THEN
		RAISE EXCEPTION 'Não é possível excluir o mecânico.';
	END IF;
	RETURN NULL;
END;
$$
LANGUAGE plpgsql;

CREATE TRIGGER verificaFuncao
BEFORE DELETE ON mecanico
FOR EACH ROW EXECUTE PROCEDURE verificaFuncao()

DELETE FROM mecanico WHERE codm=1


--6) Gatilho que ao inserir, atualizar ou remover um mecânico, reflita as mesmas modificações na tabela de Cliente. 
--Em caso de atualização, se o mecânico ainda não existir na tabela de Cliente, deve ser inserido.

CREATE OR REPLACE FUNCTION atualizaMecCli() RETURNS TRIGGER AS
$$
BEGIN
	IF TG_OP = 'INSERT' THEN
		INSERT INTO cliente VALUES (NEXTVAL('cliente_codc_seq'), NEW.cpf, NEW.nome, NEW.idade, NEW.endereco, NEW.cidade);
	ELSIF TG_OP = 'UPDATE' THEN
		IF (SELECT 1 FROM cliente WHERE cpf = NEW.cpf) THEN
			UPDATE cliente SET nome = NEW.nome, idade = NEW.idade, endereco = NEW.endereco, cidade = NEW.cidade WHERE cpf = NEW.cpf;
		ELSE
			INSERT INTO cliente VALUES (NEXTVAL('cliente_codc_seq'), NEW.cpf, NEW.nome, NEW.idade, NEW.endereco, NEW.cidade);
		END IF;
	ELSIF TG_OP = 'DELETE' THEN
		DELETE FROM cliente WHERE cpf = OLD.cpf;
	END IF;
	RETURN NULL;
END;
$$
LANGUAGE plpgsql;

CREATE SEQUENCE cliente_codc_seq START 20;

CREATE TRIGGER atualizaMecCli
AFTER INSERT OR UPDATE OR DELETE ON mecanico
FOR EACH ROW EXECUTE PROCEDURE atualizaMecCli()


INSERT INTO mecanico VALUES (7, '08455517956', 'Paulo uliveira', 32, 'Itaum', 'Joinville', 'som', 1)
DELETE FROM mecanico WHERE codm = 7;
UPDATE mecanico SET nome = 'Paulo de Oliveira' WHERE codm = 7;
SELECT * FROM mecanico
SELECT * FROM cliente

--7) Gatilho para impedir que um conserto seja inserido na tabela Conserto se o mecânico já realizou mais de 20 horas extras no mês.

CREATE OR REPLACE FUNCTION calculaHorasExtras(pcodm int, mes int, ano int) RETURNS int AS
$$
DECLARE
	vHoras int DEFAULT 0;
	vsql text;
	diasMes int[] DEFAULT ARRAY[31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
BEGIN
	vsql := 'SELECT COUNT(*) FROM conserto c WHERE c.codm = ' || pcodm || ' AND c.data BETWEEN '
		 || '''01/'||mes||'/'||ano||'''' || ' AND ' || ''''||diasMes[mes]||'/'||mes||'/'||ano||'''';
	EXECUTE vsql INTO vHoras;
	IF vHoras <= 10 THEN
		RETURN 0;
	ELSE
		RETURN vHoras - 10;
	END IF;
END;
$$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION verificaHorasExtras() RETURNS TRIGGER AS
$$
DECLARE
	vMes int;
	vAno int;
BEGIN
	vMes := DATE_PART('month', new.data);
	vAno := DATE_PART('year', new.data);
	IF calculaHorasExtras(new.codm, vMes, vAno) >= 2 THEN
		RAISE EXCEPTION 'O funcionário excedeu o limite de horas extras.';
	END IF;
	RETURN NEW;
	
END;
$$
LANGUAGE plpgsql;

CREATE TRIGGER verificaHorasExtras
BEFORE INSERT ON conserto
FOR EACH ROW EXECUTE PROCEDURE verificaHorasExtras()

INSERT INTO conserto VALUES 
(1, 4, '10/06/2014', '13:00'),
(1, 3, '19/06/2014', '12:00'),
(1, 2, '20/06/2014', '08:00'),
(1, 1, '21/06/2014', '10:00'),
(1, 4, '22/06/2014', '13:00'),
(1, 3, '23/06/2014', '16:00'),
(1, 3, '24/06/2014', '15:00'),
(1, 1, '25/06/2014', '11:00'),
(1, 3, '26/06/2014', '16:00'),
(1, 4, '27/06/2014', '14:00');

SELECT DATE_PART('month', data) AS mes, COUNT(1) FROM conserto WHERE codm = 1
GROUP BY DATE_PART('month', data);

--8) Gatilho para impedir que mais de 1 conserto seja agendado no mesmo setor na mesma hora. 

CREATE OR REPLACE FUNCTION verificaHorario() RETURNS TRIGGER AS
$$
BEGIN
	IF (SELECT COUNT(1) FROM conserto WHERE codm = NEW.codm AND data = NEW.data AND hora = NEW.hora) > 0 THEN
		RAISE EXCEPTION 'Mecânico já possui conserto agendado para a data e hora.';
	END IF;
	RETURN NEW;
END;
$$
LANGUAGE plpgsql;

CREATE TRIGGER verificaHorario
BEFORE INSERT OR UPDATE ON conserto
FOR EACH ROW EXECUTE PROCEDURE verificaHorario()

INSERT INTO conserto VALUES (1, 4, '10/06/2014', '13:00')

