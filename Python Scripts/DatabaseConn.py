import mysql.connector
from mysql.connector import Error
 
 
def connect():
    """ Connect to MySQL database """
    try:
        conn = mysql.connector.connect(host='192.168.1.125',
                                       database='test',
                                       user='pi',
                                       password='pipipass')
        if conn.is_connected():
            cursor = conn.cursor()
            print('Connected to MySQL database')
            cursor.execute("INSERT INTO `logs` (`logs`) VALUES ('HERE WE GO');")
            print('Data has been sent')
 
    except Error as e:
        print(e)
        
 
    finally:
        conn.close()
 
 
if __name__ == '__main__':
    connect()

