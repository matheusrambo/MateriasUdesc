import cv2
import numpy as np
from PIL import Image
import copy
from matplotlib import pyplot as plt
import time

start_time = time.time()

for i in range(0, 215):
    img = cv2.imread('zframe%s.jpg' % i,0)
    img2 = copy.copy(img)
    template = cv2.imread('ModeloTampa.png',0)
    w, h = template.shape[::-1]

# metodos = 'cv2.TM_CCOEFF', 'cv2.TM_CCOEFF_NORMED', 'cv2.TM_CCORR',
            # 'cv2.TM_CCORR_NORMED', 'cv2.TM_SQDIFF', 'cv2.TM_SQDIFF_NORMED'

    meth = 'cv2.TM_CCOEFF' # método utilizado para encontrar o objeto
    img = copy.copy(img2)
    method = eval(meth)

    # Apply template Matching
    res = cv2.matchTemplate(img,template,method)
    min_val, max_val, min_loc, max_loc = cv2.minMaxLoc(res)

    # If the method is TM_SQDIFF or TM_SQDIFF_NORMED, take minimum
    if method in [cv2.TM_SQDIFF, cv2.TM_SQDIFF_NORMED]:
        top_left = min_loc
    else:
        top_left = max_loc
    bottom_right = (top_left[0] + w, top_left[1] + h)

    #contrói o retangulo para marcar o objeto de interesse
    cv2.rectangle(img,top_left, bottom_right, 255, 4)

    imgteste = Image.fromarray(img)
    imgteste.save('zframe%s-U.jpg' % i)

print("--- %s seconds ---" % (time.time() - start_time))