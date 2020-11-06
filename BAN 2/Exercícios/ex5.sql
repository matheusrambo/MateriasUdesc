1)
SELECT p.*
FROM professor p
JOIN departamento d ON p.codigoDepartamento = d.codigo
WHERE d.nome = 'CIÊNCIA DA COMPUTAÇÃO'

2)
SELECT p.CPF, p.nome, p.logradouro, p.bairro, p.cidade, p.estado, p.cep
FROM professor p JOIN departamento d ON p.codigoDepartamento = d.codigo
WHERE d.nome = 'ADMINISTRAÇÃO'

3)
SELECT d.nome
FROM curso c JOIN disciplina d ON c.codigo = d.codigocurso
WHERE c.nome = 'CIÊNCIA DA COMPUTAÇÃO' or c.nome = 'FISICA'

4)
SELECT m.anosemestre, m.numerosemestre, a.matricula, a.nome, d.codigo, d.nome, c.codigo
FROM matricula m JOIN aluno a ON m.matriculaaluno = a.matricula
JOIN curso c ON c.codigo = m.codigocurso
JOIN disciplina d ON m.codigodisciplina = d.codigo
WHERE c.nome = 'CIÊNCIA DA COMPUTAÇÃO' AND d.nome = 'ENGENHARIA DE SOFTWARE'
AND m.anosemestre = 2012 AND m.numerosemestre = 2

5)
SELECT m.anosemestre, m.numerosemestre, d.codigo, d.nome, d.codigoCurso, count(m.matriculaAluno)
FROM matricula m JOIN disciplina d ON (m.codigoCurso = d.codigoCurso and m.codigoDisciplina = d.codigo)
JOIN curso c ON (d.codigoCurso = c.codigo)
WHERE d.nome = upper('Banco de Dados II') AND c.nome = upper('Ciência da Computação')
GROUP BY m.anoSemestre, m.numeroSemestre, d.codigo, d.nome, d.codigoCurso


6)
SELECT p.cpf, p.nome, m.anosemestre, m.numerosemestre, COUNT(m.matriculaaluno)
FROM professor p JOIN vinculo v ON p.cpf = v.cpfprofessor
JOIN disciplina d ON v.codigodisciplina = d.codigo AND v.codigocurso = d.codigocurso
JOIN matricula m ON m.codigocurso = v.codigocurso AND m.codigodisciplina = d.codigo WHERE m.notafinal < 7.0
GROUP BY p.cpf, p.nome, m.anosemestre, m.numerosemestre HAVING COUNT(m.matriculaaluno) > 2

7)
SELECT DISTINCT d.codigo, d.nome, d.codigoCurso, m.anoSemestre, m.numeroSemestre, COUNT(m.matriculaAluno), ROUND(AVG(m.notafinal),2) AS notafinal
FROM disciplina d JOIN matricula m ON d.codigocurso = m.codigocurso AND m.codigodisciplina = d.codigo
GROUP BY d.codigo, d.nome, d.codigoCurso, m.anoSemestre, m.numeroSemestre

8)
SELECT professor.nome, professor.cpf, professor.datanascimento, professor.logradouro, professor.bairro, professor.cidade, professor.estado, professor.cep, professor.codigodepartamento, departamento.nome
FROM professor INNER JOIN departamento ON professor.codigodepartamento = departamento.codigo
INNER JOIN curso ON departamento.codigo = curso.codigo
WHERE departamento.nome = 'CIÊNCIA DA COMPUTAÇÃO' ORDER BY professor.nome

9)
SELECT c.nome, d.nome, a.nome, count(f.indPresenca) AS presenca
FROM curso c JOIN disciplina d ON (c.codigo = d.codigocurso)
JOIN matricula m ON (d.codigocurso = m.codigocurso)
JOIN aluno a ON (m.matriculaAluno = a.matricula)
JOIN frequencia f ON (d.codigo = f.codigoDisciplina)
GROUP BY (c.nome, d.nome, a.nome)
ORDER BY presenca DESC
