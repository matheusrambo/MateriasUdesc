1)  SELECT nome, endereco FROM Cliente

2)  SELECT nome, funcao FROM Mecanico WHERE cods = 2

3)  SELECT m.cpf, m.nome FROM Mecanico m, Cliente c WHERE m.cpf = c.cpf

4)  SELECT DISTINCT m.cidade FROM cliente c JOIN mecanico m ON c.cidade = m.cidade

5)  SELECT DISTINCT marca FROM Veiculo v JOIN Cliente c ON c.cidade = 'Joinville'

6)  SELECT DISTINCT funcao FROM Mecanico

7)  SELECT * FROM Cliente WHERE idade>25

8)  SELECT m.cpf, m.nome FROM Mecanico m JOIN Setor s ON m.cods = s.cods WHERE s.nome = 'Mecânica'

9)  SELECT DISTINCT m.cpf, m.nome FROM Mecanico m JOIN Conserto c ON c.codm = m.codm WHERE data = '2014-06-13'

10) SELECT DISTINCT c.nome, v.modelo, m.nome, m.funcao FROM Cliente c, Conserto k, Veiculo v, Mecanico m
    WHERE m.codm = k.codm AND v.codv=k.codv AND c.codc=v.codc

11) SELECT DISTINCT c.nome, k.hora , m.nome FROM Cliente c, Conserto k, Veiculo v, Mecanico m
    WHERE m.codm = k.codm AND v.codv=k.codv AND c.codc=v.codc AND k.data = '2014-06-19'

12) SELECT DISTINCT s.cods, s.nome FROM Setor s, Mecanico m, Conserto c
    WHERE s.cods=m.cods AND m.codm=c.codm AND c.data BETWEEN '12/06/2014' AND '14/06/2014'
