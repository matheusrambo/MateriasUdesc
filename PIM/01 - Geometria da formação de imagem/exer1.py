import numpy as np

M = np.array([[1, 0, 0, 0], [0, 1, 0, 0], [0, 0, 1 / 5, 0]])

# pontos
pa = np.array([[150.75], [1500.0], [500.0], [1.0]])
pb = np.array([[153.5], [1500], [500.0], [1.0]])
pc = np.array([[1500.0], [155.00], [500.0], [1.0]])
pd = np.array([[1500.0], [150.75], [500.0], [1.0]])
pe = np.array([[145.0], [153.0], [500.0], [1.0]])
pf = np.array([[144.3], [153.0], [500.0], [1.0]])

# obtendo coordenadas homogeneas
a_linha = M.dot(pa)
b_linha = M.dot(pb)
c_linha = M.dot(pc)
d_linha = M.dot(pd)
e_linha = M.dot(pe)
f_linha = M.dot(pf)

# obtendo coordenadas
a = a_linha / a_linha[2, 0]
b = b_linha / b_linha[2, 0]
c = c_linha / c_linha[2, 0]
d = d_linha / d_linha[2, 0]
e = e_linha / e_linha[2, 0]
f = f_linha / f_linha[2, 0]

# Questao a
#Coordenadas
# A = [0, 1500, 500]
# B = [1500, 1500, 500]
# C = [0, 0, 500]
# D = [1500, 0, 500]
CA = 1500 #Distancia do centro até A
CD = 1500 #Distancia do centro até D
print("Questao A :")
print(f'CA: {CA} | CD: {CD} | ABCD em mm²: {CA*CD}')

# Questao b
print("Questao B :")
print("A':")
print(a)
print("B':")
print(b)
print("C':")
print(c)
print("D':")
print(d)
print("E':")
print(e)
print("F':")
print(f)

# Questao c
print("Questao C :")
dAB = abs(a - b)
dCD = abs(c - d)
dEF = abs(e - f)

# print(dAB)
# print(dCD)
# print(dEF)

if dAB[0, 0] > 0.0075:
    reg_ab = True
else:
    reg_ab = False
if dEF[0, 0] > 0.0075:
    reg_ef = True
else:
    reg_ef = False
if dCD[1, 0] > 0.0075:
    reg_cd = True
else:
    reg_cd = False

cont = 0

if reg_ab and reg_ef and reg_cd:
    cont = 3
    print("%d regioes" % cont)
elif reg_ab and reg_cd:
    cont = 2
    print("%d regioes - ACD e B" % cont)
elif reg_ab and reg_ef:
    cont = 2
    print("%d regioes - AC e BD" % cont)
else:
    cont = 1
    print("%d regiao" % cont)
