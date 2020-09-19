from ftplib import FTP

SERVER = '192.168.0.101'
USERNAME = 'pi'
PASSWORD = 'secretpassword'
PORT = 6000
TIMEOUT = 30000
FHC = "/home/pi/Desktop/image-compressed.jpg"
FH = "/home/pi/Desktop/image.jpg"


def UPLOAD():
    ftp = FTP()
    ftp.connect(SERVER, PORT, TIMEOUT)
    ftp.login(USERNAME, PASSWORD)
    #ftp_connection = ftplib.FTP(server, username, password)
    remote_path = "/"
    ftp.cwd(remote_path)
    fh = open(FH , 'rb')
    ftp.storbinary('STOR image.jpg', fh)
    fh.close()

def UPLOADCOMPRESSED():

    ftp = FTP()
    ftp.connect(SERVER, PORT, TIMEOUT)
    ftp.login(USERNAME, PASSWORD)
    #ftp_connection = ftplib.FTP(server, username, password)
    remote_path = "/"
    ftp.cwd(remote_path)
    fh = open(FHC, 'rb')
    ftp.storbinary('STOR image-compressed.jpg', fh)
    fh.close()
