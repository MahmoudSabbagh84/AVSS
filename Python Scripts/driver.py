#!/usr/bin/python
import LED, os, upload, camera, sys, compress, log
array_length = 0
array_new_length = 0
array = []
logint = 0
os.system('sh /home/pi/Desktop/mosquitto_sub.sh &')

print ("clearing logs")
os.system('echo ""  > /home/pi/Desktop/mosquitto_logs.txt')

while True:
    try:
        if array_length < array_new_length: #if new data came in
            if array[len(array)-1] == "ok": #if it's an "ok" then go to a new line
                print ("")
                print len(array)
                print ("array_length = "), array_length
                print ("array_new_length = "), array_new_length
                array_length = array_new_length
            elif array[len(array)-1] == "TURN LED ON PLZ": #if it's a LED ON request
                logint = 1
                print ("TURNING ON THE LED")
                array_length = array_new_length
                LED.LEDON() #execute LEDON() function from LED.py
                log.LOG(logint)
                with open("/home/pi/Desktop/mosquitto_logs.txt", "a") as ins:
                    ins.write('ok\n')
            elif array[len(array)-1] == "TAKE A SELFIE": #if it's a selfie request
                logint = 2
                print ("TAKING A SELFIE")
                camera.CAMERA() #execute CAMERA() function from camera.py
                log.LOG(logint)
                array_length = array_new_length
                print ("COMPRESSING SELFIE")
                compress.COMPRESS() #execute COMPRESS() function from compress.py
                print ("SELFIE COMPRESSED")
                print ("UPLOADING SELFIE")
                upload.UPLOAD() #execute UPLOAD() function from upload.py
                logint = 3
                log.LOG(logint)
                print ("SELFIE UPLOADED")
                print ("UPLOADING COMPRESSED SELFIE")
                upload.UPLOADCOMPRESSED() #execute UPLOADCOMPRESSED() function from upload.py
                logint = 4
                log.LOG(logint)
                print ("COMPRESSED SELFIE UPLOADED")
                with open("/home/pi/Desktop/mosquitto_logs.txt", "a") as ins:
                    ins.write('ok\n')
            
        with open("/home/pi/Desktop/mosquitto_logs.txt", "r") as ins: #log cleanup, removes trailig \n
            for line in ins:
                array.append(line)
        array = [array.rstrip('\n') for array in open('/home/pi/Desktop/mosquitto_logs.txt')]
        array_new_length = len(array)
    
        if array_length == 0 and array_new_length == 0: #if logs are empty
            print ("skipping... \n")
            print array
            print ("")
    except KeyboardInterrupt: #when hitting Ctrl+C, clear logs and kill sub
        print ("Killing sub")
        os.system('killall mosquitto_sub')
        print ("clearing logs")
        os.system('echo ""  > /home/pi/Desktop/mosquitto_logs.txt')
        sys.exit()
