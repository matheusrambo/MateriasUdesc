import numpy as np
import cv2

FUNDO = 0
LIMIAR = 240
AREA_MINIMA = 400
contagem = dict()


def limiarizar(img):
    y, x = img.shape[0], img.shape[1]
    nimg = np.zeros((img.shape[0], img.shape[1]), np.uint8)
    for i in range(0, y):
        for j in range(0, x):
            nimg[i, j] = int((img[i, j]) >= LIMIAR)
    return nimg


def rotular(label, img, aux, y, x):
    fila = [(y, x)]
    contagem[label] = 0

    while len(fila) > 0:
        y, x = fila.pop()
        aux[y, x] = label
        contagem[label] += 1
        # cima, direita, baixo, esquerda
        if y > 0:
            if img[y, x] == img[y - 1, x] and aux[y - 1, x] == -1:
                fila.append((y - 1, x))
        if x < len(img[0]) - 1:
            if img[y, x] == img[y, x + 1] and aux[y, x + 1] == -1:
                fila.append((y, x + 1))
        if y < len(img) - 1:
            if img[y, x] == img[y + 1, x] and aux[y + 1, x] == -1:
                fila.append((y + 1, x))
        if x > 0:
            if img[y, x] == img[y, x - 1] and aux[y, x - 1] == -1:
                fila.append((y, x - 1))


def identifica(img):
    y, x = img.shape[0], img.shape[1]
    aux = np.zeros((img.shape[0], img.shape[1]), int)

    aux.fill(-1)
    label = 1
    # checar bordas verticais esquerda e direita
    for i in range(0, y):
        rotular(0, img, aux, i, 0)
        rotular(0, img, aux, i, x - 1)
    # checar bordas horizontais cima e baixo
    for j in range(1, x - 1):
        rotular(0, img, aux, 0, j)
        rotular(0, img, aux, y - 1, j)
    # marcar normalmente o que nao eh fundo
    for i in range(1, y - 1):
        for j in range(1, x - 1):
            if aux[i, j] == -1:
                if img[i, j] == FUNDO:
                    rotular(0, img, aux, i, j)
                else:
                    rotular(label, img, aux, i, j)
                    label += 1
    return aux


def segmentar(img, aux):
    y, x = img.shape[0], img.shape[1]
    out = np.zeros(img.shape, np.uint8)
    for i in range(0, y):
        # so passa para a imagem final os pixels de interesse
        for j in range(0, x):
            if (aux[i, j] != FUNDO) and (contagem[aux[i, j]] >= AREA_MINIMA):
                out[i, j] = img[i, j]
    return out


def taxa(img):
    nimg = limiarizar(img)
    cv2.imwrite('taxaPerCapitaRouboCarros_ALTERADA.png', outimg)

# le a imagen
img_Taxa = cv2.imread('taxaPerCapitaRouboCarros.png', 0)

solda(img_solda)
bombeiro(img_bombeiro)
