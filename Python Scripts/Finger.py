import sys
import FPS

if __name__ == '__main__':
    fps =  FPS.FPS_GT511C3(device_name='/dev/ttyAMA0',baud=9600,timeout=2,is_com=False)
    fps.UseSerialDebug = True
    fps.SetLED(True) # Turns ON the CMOS LED
    FPS.delay(1) # wait 1 second
    print ('Put your finger in the scan')
    counter = 0 # simple counter for wait 10 seconds
    while counter < 10:
        if fps.IsPressFinger():  #verify if the finger is in the scan
            print ('Your finger is in the scan')
            fps.SetLED(False) # Turns OFF the CMOS LED
            break
        else:
            FPS.delay(1) #wait 1 second
            counter = counter + 1
    
    fps.Close() # Closes serial connection
    pass
