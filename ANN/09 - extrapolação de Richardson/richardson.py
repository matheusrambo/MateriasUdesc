N = [[-2.5035062937] ,
    [-0.4254818586] ,
    [0.0331350033] ,
    [0.1518417775] ,
    [0.1864449967] ,
    [0.1979120642] ,
    [0.2022346213] ]
grau = 7
b = 1
for i in range (grau - 1):
    for j in range (grau-i-1):
        N[j].append((( 2**(i*b+b) * N[j+1][i]) - N[j][i])/(2**(i*b+b) - 1))
print('N %d(1) = %.10f' %(grau ,N[0][-1]))
