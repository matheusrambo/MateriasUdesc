1)  CREATE OR REPLACE VIEW nome_funcao_m AS
    SELECT nome, funcao FROM mecanico

2)  CREATE OR REPLACE VIEW modelo_marca_v AS
    SELECT v.modelo, v.marca, c.nome
    FROM veiculo v JOIN cliente c ON c.codc = v.codc

3)  CREATE OR REPLACE VIEW conserto_realizados AS
    SELECT DISTINCT m.nome as nome_mecanico, c.nome as nome_cliente, v.modelo, k.data, k.hora
    FROM Cliente c, Conserto k, Veiculo v, Mecanico m
    WHERE m.codm = k.codm AND v.codv=k.codv AND c.codc=v.codc

4)  CREATE OR REPLACE VIEW ano_km_v AS
    SELECT ano, AVG(quilometragem) FROM veiculo
    GROUP BY ano ORDER BY ano

5)  CREATE OR REPLACE VIEW conserto_dia AS
    SELECT m.nome, COUNT(c.data), c.data
    FROM mecanico m JOIN conserto c ON c.codm = m.codm
    GROUP BY m.codm, c.data ORDER BY c.data

6)  CREATE OR REPLACE VIEW conserto_dia_setor AS
    SELECT s.nome, COUNT(c.data), c.data
    FROM mecanico m JOIN conserto c ON c.codm = m.codm
    JOIN setor s ON s.cods = m.cods
    GROUP BY s.nome, c.data ORDER BY c.data

7)  CREATE OR REPLACE VIEW conta_funcoes AS
    SELECT funcao, COUNT(codm)
    FROM mecanico
    GROUP BY funcao

8) CREATE OR REPLACE VIEW funcaosetor AS
   SELECT m.nome, m.funcao, s.cods, s.nome  as NOME_SETOR
   FROM mecanico m LEFT JOIN setor s ON s.cods = m.cods

9) CREATE OR REPLACE VIEW conserto_por_funcao AS
   SELECT m.funcao, COUNT(c.codm)
   FROM mecanico m JOIN conserto c ON m.codm = c.codm
   GROUP BY funcao
