-- 1)  	Função para inserção e exclusão de um Setor.
CREATE FUNCTION inserirSetor(cods int, nome varchar)
returns void AS
$$
BEGIN
INSERT INTO setor VALUES(cods,nome);
END;
$$
language plpgsql;

-- 2)  	Função para inserção e exclusão de um Mecânico.
CREATE FUNCTION inserirMecanico(codm int, cpf character, nome varchar, idade int, endereco varchar, cidade varchar, funcao varchar, cods int)
returns void AS
$$
BEGIN
INSERT INTO mecanico VALUES (codm, cpf, nome, idade, endereco, cidade, funcao, cods);
END;
$$
language plpgsql;

--testando função
select inserirSetor(202, 'balala')
select * from setor where cods = 202
-- fim teste

CREATE FUNCTION excluirMecanico(codmExcluir int)
returns void AS
$$
BEGIN
delete from mecanico where codm=codmExcluir;
END;
$$
language plpgsql;

-- testando função
select excluirMecanico(3)
select * from mecanico
-- fim teste


-- 3)  	Função para inserção e exclusão de uma Cliente.

CREATE FUNCTION inserirCliente(codc int, cpf character, nome varchar, idade int, endereco varchar, cidade varchar)
returns void AS
$$
BEGIN
INSERT INTO cliente VALUES (codc, cpf, nome, idade, endereco, cidade);
END;
$$
language plpgsql;

-- testando a função
select * from cliente
select inserirCliente(10, '09129005981', )
-- fim teste

-- 4)      Função para inserção e exclusão de um Veículo.
CREATE FUNCTION inserirVeiculo(codv int, renavam character, modelo varchar, marca varchar, ano int, quilometragem double precision, codc integer)
returns void AS
$$
BEGIN
INSERT INTO Veiculo VALUES (codv , renavam , modelo , marca , ano , quilometragem , codc );
END;
$$
language plpgsql;

-- teste insertVeiculo
select * from Veiculo
select inserirVeiculo(1111, 'capeta', 'aeaeae', 'seASg', 12, 1.2, 1)
-- teste deu certo
-- Função para exclusão de veículo
CREATE FUNCTION removerVeiculo(codve integer)
returns void AS
$$
BEGIN
delete from veiculo where codv = codve;
END;
$$
language plpgsql;

-- fim da função de exclusão
-- teste da função de exclusão
select * from veiculo
select removerVeiculo(5)
-- fim teste

-- 5) Função para inserção e exclusão de um Conserto.
CREATE FUNCTION inserirConserto (codm integer, codv integer, datae date, hora time)
returns void AS
$$
BEGIN
INSERT INTO conserto VALUES (codm, codv, datae, hora);
END;
$$
language plpgsql;

CREATE FUNCTION excluirConserto (codm integer, codv integer)
returns void AS
$$
BEGIN
delete from conserto where codm = codm and codv = codv;
END;
$$
language plpgsql;

-- 6) Função para calcular a média geral de idade dos Mecânicos e Clientes.
CREATE or replace FUNCTION mediaGeral()
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
	select into variavelA sum(idade) from cliente;
	select into variavelB sum(idade) from mecanico;
	select into variavelX COUNT(idade) from cliente;
	select into variavelY COUNT(idade) from mecanico;
	soma = variavelX + variavelY;
	retorno = (variavelA+variavelB)/soma;
	return retorno;
END;
$$
language plpgsql;

-- teste
select mediaGeral()
-- FIM TESTE

-- 7) Uma única função que permita fazer exclusão de um Setor, Mecânico, Cliente ou Veículo.
CREATE or replace FUNCTION exclusaoRegistro(pk integer, choice integer) returns void AS
$$
declare
BEGIN
if choice = 1 then
	delete from setor where cods = pk;
else if choice = 2 then
	delete from mecanico where codm = pk;
else if choice = 3 then
	delete from cliente where codc = pk;
else if choice = 4 then
	delete from veiculo where codv = pk;
else
END;
$$
language plpgsql;

-- 8)Considerando que na tabela Cliente apenas codc é a chave primária, 
faça uma função que remova clientes com CPF repetido, deixando apenas um cadastro para cada CPF. 
Escolha o critério que preferir para definir qual cadastro será mantido: aquele com a menor idade, 
que possuir mais consertos agendados, etc. Para testar a função, 
não se esqueça de inserir na tabela alguns clientes com este problema.


CREATE OR REPLACE FUNCTION excluiClienteCPF()
RETURNS int AS
$$
DECLARE
	vCpf char(11);
	vCodc int;
	cont int DEFAULT 0;
	vlinhas int DEFAULT 0;
BEGIN
	FOR vCpf IN SELECT cpf FROM cliente GROUP BY cpf
	HAVING COUNT(codc) > 1 LOOP
		cont := 0;
		FOR vCodc IN SELECT codc FROM cliente WHERE cpf = vCpf
		ORDER BY idade DESC LOOP
			IF cont>0 THEN
				DELETE FROM cliente WHERE codc = vCodc;
				GET DIAGNOSTICS vlinhas = ROW_COUNT;
			END IF;
			cont := cont + 1;
		END LOOP;
	END LOOP;
	RETURN vlinhas;
END;
$$
LANGUAGE plpgsql;

-- Valores para teste
INSERT INTO cliente VALUES (11, '20000200000', 'Ana', 20, 'América', 'Joinville'),
(12, '20000220000', 'Paulo Test', 24, 'Saguaçú', 'Joinville'),
(13, '22000200000', 'Lucia Test', 30, 'Vila Nova', 'Joinville'),
(14, '11000110000', 'Carlos Test', 28, 'Trindade', 'Florianópolis')

SELECT excluiClienteCPF()
SELECT * FROM cliente

-- 9) Função para calcular se o CPF é válido*.

CREATE OR REPLACE FUNCTION validaCPF(pCpf char(11))
RETURNS boolean AS
$$
DECLARE
	vCpf integer[11];
	sequencia1 integer[] DEFAULT ARRAY[10,9,8,7,6,5,4,3,2];
	sequencia2 integer[] DEFAULT ARRAY[11,10,9,8,7,6,5,4,3,2];
	pdigito integer[9];
	sdigito integer[10];
	somapDigito integer DEFAULT 0;
	resto integer;
	digito integer;
BEGIN
	FOR cont IN 1..11 LOOP
		vCpf[cont] := CAST(SUBSTRING(pCpf FROM cont FOR 1) AS integer);
	END LOOP;
		--Verificação do 1º dígito.
	FOR cont IN 1..9 LOOP
		pdigito[cont] := vCpf[cont] * sequencia1[cont];
		somapDigito := somapDigito + pdigito[cont];
	END LOOP;
	resto := somapDigito % 11;
	IF resto < 2 THEN
		digito := 0;
	ELSE
		digito := 11-resto;
	END IF;
	IF digito <> vCpf[11] THEN
		RETURN false;
	END IF;
	RETURN true;
END;
$$
LANGUAGE plpgsql;

SELECT validaCPF('22233344405')

-- 10) Função que calcula a quantidade de horas extras de um mecânico em um mês de trabalho. 
O número de horas extras é calculado a partir das horas que excedam as 160 horas de trabalho mensais. 
O número de horas mensais trabalhadas é calculada a partir dos consertos realizados. 
Cada conserto tem a duração de 1 hora.

CREATE OR REPLCE FUNCTION calculaHoraExtra(pcodm int, pmes int, pano int) RETURNS int AS
$$
DECLARE
	horasTrab integer DEFAULT 0;
BEGIN
	SELECT COUNT(1) INTO horasTrab FROM conserto WHERE codm = pcodm AND date_part('month', data) = pmes AND date_part('year', data) = pano;

	IF horasTrab > 160 THEN
		RETURN horasTrab - 160;
	ELSE
		RETURN 0;
	END IF;
END
$$
LANGUAGE plpsql;

SELECT calculaHorasExtras(1, 6, 2014)














