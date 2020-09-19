from picamera import PiCamera
from time import sleep
import os

def CAMERA():
    camera = PiCamera()
    #camera.rotation = 180
    camera.capture('/home/pi/Desktop/image.jpg')
    camera.close()
