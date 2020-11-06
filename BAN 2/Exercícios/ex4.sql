1)  SELECT DISTINCT m.nome, m.cpf FROM mecanico m JOIN setor s ON m.cods=s.cods WHERE m.cods
    IN (SELECT cods FROM setor WHERE cods=1 OR cods=2)

2)  SELECT DISTINCT m.nome, m.cpf FROM mecanico m JOIN setor s ON m.cods=s.cods WHERE m.cods
    IN (SELECT cods FROM setor WHERE nome = 'Funilaria' OR nome = 'Pintura')

3)  SELECT DISTINCT m.nome, m.cpf FROM mecanico m JOIN conserto c ON m.codm=c.codm
    WHERE c.data='2014-06-13'

4)  SELECT DISTINCT m.nome, c.nome, k.hora FROM Cliente c, Conserto k, Veiculo v, Mecanico m
    WHERE m.codm = k.codm AND v.codv=k.codv AND c.codc=v.codc AND k.data='2014-06-12'

5)  SELECT DISTINCT m.nome, m.funcao, s.cods, s.nome FROM mecanico m LEFT JOIN setor s ON m.cods=s.cods

6) FODASSEEEEE
