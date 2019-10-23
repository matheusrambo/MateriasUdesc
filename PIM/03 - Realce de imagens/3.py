import numpy as np
import matplotlib.mlab as mlab
import matplotlib.pyplot as plt
from PIL import Image
import copy
import math

#quantidade de bits para representar os valores dos pixels
L = 256

def muda_valor(valor_antigo, novo_valor, lista):
    for i in range(0, len(lista)):
        if(lista[i] == valor_antigo):
            lista[i] = novo_valor

def main():
    nk = 0

    #pega a imagem escolhida
    img = Image.open('gonzalez3_10.png').convert('I')
    #plota o histograma da imagem original
    plt.hist(np.asarray(img).ravel(), 255)
    #salva o histograma como uma imagem
    plt.savefig('histogram_gonzalez3_10.jpg')
    #limpa o plt
    plt.clf()

    #pega novamente a imagem escolhida
    img2 = Image.open('gonzalez3_10.png').convert('I')
    #transforma a imagem em uma lista
    lista = np.asarray(img2).ravel().tolist()

    #calcula o mn, usado para calcular o histograma equalizado
    mn = img.size[0] * img.size[1]
    #copia a lista que representa a imagem original
    equalizado = copy.deepcopy(lista)
    #aplica a formula para equalização
    for i in range(0, L-1):
        nk += lista.count(i)
        eq = ((L - 1.00) / mn) * nk
        #funcao que faz o mapeamento do valor antigo para o novo na lista
        muda_valor(i, eq, equalizado)

    #transforma a lista em um np.array e plota seu histograma
    hist_equalizado = np.asarray(equalizado)
    plt.hist(np.asarray(hist_equalizado).ravel(), 255)
    plt.savefig('histogram_gonzalez3_10_equalizado.jpg')

main()
