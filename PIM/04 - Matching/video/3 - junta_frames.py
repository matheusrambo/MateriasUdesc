import cv2
import numpy as np
import os

from os.path import isfile, join


def convert_frames_to_video(pathOut, fps):
    frame_array = []

    for i in range(0, 215):
        filename = 'zframe%s-U.jpg' % i
        # leitura de cada arquivo
        img = cv2.imread(filename)
        height, width, layers = img.shape
        size = (width, height)
        print(filename)
        # Inserindo os frames dentro de uma array de imagem
        frame_array.append(img)

    # Cria o arquivo do video que receberá a array de imagem
    out = cv2.VideoWriter(pathOut, cv2.VideoWriter_fourcc(*'DIVX'), fps, size)

    for i in range(len(frame_array)):
        # escrevendo o array de imagem
        out.write(frame_array[i])
    out.release()


def main():
    pathOut = 'video.avi'
    fps = 25.0
    convert_frames_to_video(pathOut, fps)


if __name__ == "__main__":
    main()
