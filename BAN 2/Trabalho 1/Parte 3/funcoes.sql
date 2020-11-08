--1) Gatilho para impedir a inserção ou atualização de Hospede com idade menor que 18 anos.

CREATE OR REPLACE FUNCTION verificaIdadeHosp() RETURNS TRIGGER AS
$$
BEGIN
IF TG_OP = 'INSERT' THEN
		IF (SELECT idade FROM Hospede WHERE idade = NEW.idade) < 18 THEN
			RAISE EXCEPTION 'Hospede com idade inferior a 18 anos.';
		END IF;
	ELSIF TG_OP = 'UPDATE' THEN
		IF NEW.idade <> OLD.idade THEN
			IF (SELECT idade FROM Hospede WHERE idade = NEW.idade) < 18 THEN
				RAISE EXCEPTION 'Hospede com idade inferior a 18 anos.';
				END IF;
			END IF;
		END IF;
	RETURN NEW;
END;
$$
LANGUAGE plpgsql;

CREATE TRIGGER verificaIdadeHosp
BEFORE INSERT OR UPDATE ON Hospede
FOR EACH ROW EXECUTE PROCEDURE verificaIdadeHosp()

--Teste:
SELECT * FROM Hospede
INSERT INTO Hospede VALUES (1, 08455517956, 'Matheus', '997664343', 17, 'Joinville');


-- 2) Função para inserir e excluir um Quarto.

CREATE FUNCTION inserirQuarto (id_quarto integer, quant_camas integer, preco character, descricao character, comodidade character, id_frigobar integer, id_tipo_quarto integer)
returns void AS
$$
BEGIN
INSERT INTO Quarto VALUES (id_quarto, quant_camas, preco, descricao, comodidade, id_frigobar, id_tipo_quarto);
END;
$$
language plpgsql;

CREATE FUNCTION excluirConserto (id_quarto integer, id_frigobar integer, id_tipo_quarto integer)
returns void AS
$$
BEGIN
delete from Quarto where id_quarto = id_quarto and id_frigobar = id_frigobar and id_tipo_quarto = id_tipo_quarto;
END;
$$
language plpgsql;

-- 3) Função para calcular a média geral de idade dos Hospedes e Acompanhantes.
CREATE or REPLACE FUNCTION mediaGeral()
returns real AS
$$
DECLARE
	variavelA real;
	variavelB real;
	variavelX real;
	variavelY real;
	soma real;
	retorno real;
BEGIN
	select into variavelA sum(idade) from Hospede;
	select into variavelB sum(idade) from Acompanhantes;
	select into variavelX COUNT(idade) from Hospede;
	select into variavelY COUNT(idade) from Acompanhantes;
	soma = variavelX + variavelY;
	retorno = (variavelA + variavelB)/soma;
	return retorno;
END;
$$
language plpgsql;

--Teste:
select mediaGeral()

--4) Gatilho para impedir a inserção ou atualização de Hospede com o mesmo CPF.

CREATE OR REPLACE FUNCTION verificaCPF() RETURNS TRIGGER AS
$$
BEGIN
	IF TG_OP = 'INSERT' THEN
		IF (SELECT COUNT(id_hospede) FROM Hospede WHERE cpf = NEW.cpf) > 0 THEN
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

--5) Gatilho para atribuir um id_frigobar (sequencial) para um novo frigobar inserido.

CREATE OR REPLACE FUNCTION novo_id_frigobar() RETURNS TRIGGER AS
$$
DECLARE
	vidfrigobar INT DEFAULT 0;
BEGIN
	SELECT MAX(id_frigobar) FROM Frigobar INTO vidfrigobar;
	vidfrigobar := vidfrigobar + 1;
	NEW.id_frigobar := vidfrigobar;
	RETURN NEW;
END;
$$
LANGUAGE plpgsql;

CREATE TRIGGER novo_id_frigobar
BEFORE INSERT OR UPDATE ON Frigobar
FOR EACH ROW EXECUTE PROCEDURE novo_id_frigobar()

INSERT INTO Frigobar VALUES (1, 'Teste');