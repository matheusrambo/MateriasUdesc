import cv2
import numpy as np
import os

# Playing video from file:
cap = cv2.VideoCapture('teste.mp4')

currentFrame = 0
while currentFrame < 215:
    # captura frama por frame
    ret, frame = cap.read()

    # salva o frame atual em jpg
    name = 'zframe' + str(currentFrame) + '.jpg'
    print ('Creating...' + name)
    cv2.imwrite(name, frame)
    # input()
    # Contador para o nome de cada frame
    currentFrame += 1

# Liberar o video
cap.release()
cv2.destroyAllWindows()