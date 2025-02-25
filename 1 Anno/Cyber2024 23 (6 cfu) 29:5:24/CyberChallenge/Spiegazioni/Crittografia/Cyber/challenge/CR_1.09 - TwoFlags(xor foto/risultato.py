
import numpy as np
from PIL import Image

im1 = Image.open("/home/ale/Documenti/Cyber/CR_1.09 - TwoFlags/flag_enc.png")
im2 = Image.open("/home/ale/Documenti/Cyber/CR_1.09 - TwoFlags/notflag_enc.png")
im1np = np.array(im1)*255
im2np = np.array(im2)*255


key = np.bitwise_xor(im1np, im2np).astype(np.uint8)




Image.fromarray(key).save('/home/ale/Documenti/Cyber/CR_1.09 - TwoFlags/flag.png')