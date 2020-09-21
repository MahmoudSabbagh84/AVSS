import mysql.connector, datetime, dataclasses
from dateutil import tz
from mysql.connector import Error\

FZ = tz.gettz('UTC')
TZ = tz.gettz('Asia/Tel_Aviv')
T = datetime.datetime.utcnow()
TS = time.astimezone(TZ)



def LOG(i):
    try:
        print ("Connect to MySQL database ")
        conn = mysql.connector.connect(host='192.168.0.101', user='pi', passwd='pipipass', db='test')
        print ("Connected!")
        if conn.is_connected():
            cursor = conn.cursor()
            print('Connected to MySQL database')
            if i == 1:
                from_zone = FZ
                to_zone = TZ
                time = T
                time = time.replace(tzinfo=FZ)
                
                strtime = str(TS)
                strtime = strtime [:-9]
                statement = "INSERT INTO `logs` (`logs`) VALUES ('%s - Turned LED on for 2 seconds');"% (strtime)
                cursor.execute(statement)
                print('Data has been sent')
            elif i == 2:
                from_zone = FZ
                to_zone = TZ
                time = T
                time = time.replace(tzinfo=FZ)
                
                strtime = str(TS)
                strtime = strtime [:-9]
                statement = "INSERT INTO `logs` (`logs`) VALUES ('%s - Took a picture');"% (strtime)
                cursor.execute(statement)
                print('Data has been sent')
            elif i == 3:
                from_zone = FZ
                to_zone = TZ
                time = T
                time = time.replace(tzinfo=FZ)
                
                strtime = str(TS)
                strtime = strtime [:-9]
                statement = "INSERT INTO `logs` (`logs`) VALUES ('%s - Uploaded the picture');"% (strtime)
                cursor.execute(statement)
                print('Data has been sent')
            elif i == 4:
                from_zone = FZ
                to_zone = TZ
                time = T
                time = time.replace(tzinfo=FZ)
                
                strtime = str(TS)
                strtime = strtime [:-9]
                statement = "INSERT INTO `logs` (`logs`) VALUES ('%s - Uploaded the compressed picture');"% (strtime)
                cursor.execute(statement)
                print('Data has been sent')
     
    except Error as e:
        print(e)
