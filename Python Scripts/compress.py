from PIL import Image

def COMPRESS():
    im = Image.open("/home/pi/Desktop/image.jpg")
    im = im.resize((640,360), Image.ANTIALIAS)
    im.save("/home/pi/Desktop/image-compressed.jpg", format="JPEG", optimize=True, quality=80)
