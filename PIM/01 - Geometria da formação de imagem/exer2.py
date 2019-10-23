import numpy as np
from decimal import Decimal
from scipy import linalg
import sys
import random


def main():
    #Lendo a base de dados
    arquivo = open("baseDados.txt","r")
    texto = arquivo.readlines()
    matriz = [];
    projecao = [];
    aux = [];
    for linha in texto :
        valores = linha.split()
        aux = [valores[0], valores[1], valores[2]]
        matriz.append(aux)
        aux = [valores[3], valores[4]]
        projecao.append(aux)

    #tam = len(matriz)
    #for i in range(tam):
    #        print("%s, %s, %s " % (matriz[i][0], matriz[i][1] ,matriz[i][2]))
    arquivo.close()

    #Lendo pontos para o algoritmo de seis pontos
    arquivo = open("seisPontos.txt","r")
    texto = arquivo.readlines()
    matrizSeisPontos = [];
    projecaoSeisPontos = [];
    for linha in texto :
        valores = linha.split()
        aux = [valores[0], valores[1], valores[2]]
        matrizSeisPontos.append(aux)
        aux = [valores[3], valores[4]]
        projecaoSeisPontos.append(aux)
    #tam = len(matrizSeisPontos)
    #for i in range(tam):
    #        print("%s, %s, %s " % (matrizSeisPontos[i][0], matrizSeisPontos[i][1] ,matrizSeisPontos[i][2]))
    arquivo.close()


    X1 = float(matrizSeisPontos[0][0])
    X2 = float(matrizSeisPontos[1][0])
    X3 = float(matrizSeisPontos[2][0])
    X4 = float(matrizSeisPontos[3][0])
    X5 = float(matrizSeisPontos[4][0])
    X6 = float(matrizSeisPontos[5][0])

    Y1 = float(matrizSeisPontos[0][1])
    Y2 = float(matrizSeisPontos[1][1])
    Y3 = float(matrizSeisPontos[2][1])
    Y4 = float(matrizSeisPontos[3][1])
    Y5 = float(matrizSeisPontos[4][1])
    Y6 = float(matrizSeisPontos[5][1])

    Z1 = float(matrizSeisPontos[0][2])
    Z2 = float(matrizSeisPontos[1][2])
    Z3 = float(matrizSeisPontos[2][2])
    Z4 = float(matrizSeisPontos[3][2])
    Z5 = float(matrizSeisPontos[4][2])
    Z6 = float(matrizSeisPontos[5][2])

    x1 = float(projecaoSeisPontos[0][0])
    x2 = float(projecaoSeisPontos[1][0])
    x3 = float(projecaoSeisPontos[2][0])
    x4 = float(projecaoSeisPontos[3][0])
    x5 = float(projecaoSeisPontos[4][0])
    x6 = float(projecaoSeisPontos[5][0])

    y1 = float(projecaoSeisPontos[0][1])
    y2 = float(projecaoSeisPontos[1][1])
    y3 = float(projecaoSeisPontos[2][1])
    y4 = float(projecaoSeisPontos[3][1])
    y5 = float(projecaoSeisPontos[4][1])
    y6 = float(projecaoSeisPontos[5][1])



    C = np.array([ [X1, Y1, Z1, 1, 0, 0, 0, 0, -x1*X1, -x1*Y1, -x1*Z1],
                   [0, 0, 0, 0, X1, Y1, Z1, 1, -y1*X1, -y1*Y1, -y1*Z1],
                   [X2, Y2, Z2, 1, 0, 0, 0, 0, -x2*X2, -x2*Y2, -x2*Z2],
                   [0, 0, 0, 0, X2, Y2, Z2, 1, -y2*X2, -y2*Y2, -y2*Z2],
                   [X3, Y3, Z3, 1, 0, 0, 0, 0, -x3*X3, -x3*Y3, -x3*Z3],
                   [0, 0, 0, 0, X3, Y3, Z3, 1, -y3*X3, -y3*Y3, -y3*Z3],
                   [X4, Y4, Z4, 1, 0, 0, 0, 0, -x4*X4, -x4*Y4, -x4*Z4],
                   [0, 0, 0, 0, X4, Y4, Z4, 1, -y4*X4, -y4*Y4, -y4*Z4],
                   [X5, Y5, Z5, 1, 0, 0, 0, 0, -x5*X5, -x5*Y5, -x4*Z5],
                   [0, 0, 0, 0, X5, Y5, Z5, 1, -y5*X5, -y5*Y5, -y5*Z5],
                   [X6, Y6, Z6, 1, 0, 0, 0, 0, -x6*X6, -x6*Y6, -x6*Z6]
    ])


    xy = np.array([x1, y1, x2, y2, x3, y3, x4, y4, x5, y5, x6])
    A = np.array([])
    Cin = linalg.inv(C)
    A = Cin.dot(xy)
    #ainv = inv(np.matrix(a))
    #A = C-1 * P

    print("RESPOSTA DA 2A")
    print("MATRIZ M")
    for i in range (11):
            print("%.5f " % A[i])


    #LETRA B
    print("\nRESPOSTA DA 2B")
    somaA = 0
    somaXY = 0
    for i in range (11):
            somaA += A[i]

    for i in range (11):
            somaXY += xy[i]

    print("Somatorio Matriz Obtida (M) = %.5f\nSomatorio Matriz dada = %.5f" % (somaA, somaXY))
    print("ERRO = %.5f" % (somaXY - somaA))
    erro = somaXY - somaA
    abs(erro)

    #LETRA C
    questaoC(matriz, projecao, erro)

def questaoC(matriz, projecao, erro):

    print("\n\nLETRA C")
    vetErro = [];
    vetErro.append(erro)

    for i in range(3):
            import random
            result = random.sample(range(0,65), 6)

            X1 = float(matriz[result[0]][0])
            X2 = float(matriz[result[1]][0])
            X3 = float(matriz[result[2]][0])
            X4 = float(matriz[result[3]][0])
            X5 = float(matriz[result[4]][0])
            X6 = float(matriz[result[5]][0])

            Y1 = float(matriz[result[0]][1])
            Y2 = float(matriz[result[1]][1])
            Y3 = float(matriz[result[2]][1])
            Y4 = float(matriz[result[3]][1])
            Y5 = float(matriz[result[4]][1])
            Y6 = float(matriz[result[5]][1])

            Z1 = float(matriz[result[0]][2])
            Z2 = float(matriz[result[1]][2])
            Z3 = float(matriz[result[2]][2])
            Z4 = float(matriz[result[3]][2])
            Z5 = float(matriz[result[4]][2])
            Z6 = float(matriz[result[5]][2])

            x1 = float(projecao[result[0]][0])
            x2 = float(projecao[result[1]][0])
            x3 = float(projecao[result[2]][0])
            x4 = float(projecao[result[3]][0])
            x5 = float(projecao[result[4]][0])
            x6 = float(projecao[result[5]][0])

            y1 = float(projecao[result[0]][1])
            y2 = float(projecao[result[1]][1])
            y3 = float(projecao[result[2]][1])
            y4 = float(projecao[result[3]][1])
            y5 = float(projecao[result[4]][1])
            y6 = float(projecao[result[5]][1])


            C = np.array([ [X1, Y1, Z1, 1, 0, 0, 0, 0, -x1*X1, -x1*Y1, -x1*Z1],
                           [0, 0, 0, 0, X1, Y1, Z1, 1, -y1*X1, -y1*Y1, -y1*Z1],
                           [X2, Y2, Z2, 1, 0, 0, 0, 0, -x2*X2, -x2*Y2, -x2*Z2],
                           [0, 0, 0, 0, X2, Y2, Z2, 1, -y2*X2, -y2*Y2, -y2*Z2],
                           [X3, Y3, Z3, 1, 0, 0, 0, 0, -x3*X3, -x3*Y3, -x3*Z3],
                           [0, 0, 0, 0, X3, Y3, Z3, 1, -y3*X3, -y3*Y3, -y3*Z3],
                           [X4, Y4, Z4, 1, 0, 0, 0, 0, -x4*X4, -x4*Y4, -x4*Z4],
                           [0, 0, 0, 0, X4, Y4, Z4, 1, -y4*X4, -y4*Y4, -y4*Z4],
                           [X5, Y5, Z5, 1, 0, 0, 0, 0, -x5*X5, -x5*Y5, -x4*Z5],
                           [0, 0, 0, 0, X5, Y5, Z5, 1, -y5*X5, -y5*Y5, -y5*Z5],
                           [X6, Y6, Z6, 1, 0, 0, 0, 0, -x6*X6, -x6*Y6, -x6*Z6]
            ])


            xy = np.array([x1, y1, x2, y2, x3, y3, x4, y4, x5, y5, x6])
            A = np.array([])
            Cin = linalg.inv(C)
            A = Cin.dot(xy)


            print("\n\nRESPOSTA DA 2C - Iteracao %d" % (i+1))
            print("\nOS PONTOS SAO:")
            print("X = %.5f %.5f %.5f %.5f %.5f" % (X1,Y1,Z1,x1,y1))
            print("Y = %.5f %.5f %.5f %.5f %.5f" % (X2,Y2,Z2,x2,y2))
            print("Z = %.5f %.5f %.5f %.5f %.5f" % (X3,Y3,Z3,x3,y1))
            print("x = %.5f %.5f %.5f %.5f %.5f" % (X4,Y4,Z4,x4,y4))
            print("y = %.5f %.5f %.5f %.5f %.5f" % (X5,Y5,Z5,x5,y5))

            print("\nMATRIZ M")

            for j in range (11):
                    print("%.5f " % A[j])

            somaA = 0
            somaXY = 0
            j=0
            for j in range (11):
                    somaA += A[j]
            j=0
            for j in range (11):
                    somaXY += xy[j]

            print("Somatorio Matriz Obtida (M) = %.5f\nSomatorio Matriz dada = %.5f" % (somaA, somaXY))
            vetErro.append(abs(somaXY - somaA))

    print("\nERROS :")
    print(vetErro)


main()
