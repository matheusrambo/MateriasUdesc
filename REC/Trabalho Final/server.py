import socket
import threading
import os


def RetrFile(name, sock):
    filename = sock.recv(1024)
    filename = filename.decode('utf-8')
    if os.path.isfile(filename):
        sock.send(str.encode('EXISTS') + str(os.path.getsize(filename)).encode('utf-8'))
        userResponse = sock.recv(1024)
        userResponse = userResponse.decode('utf-8')
        if userResponse[:2] == 'OK':
            with open(filename, 'rb') as f:
                bytesToSend = f.read(1024)
                sock.send(bytesToSend)
                while bytesToSend != "0":
                    bytesToSend = f.read(1024)
                    sock.send(bytesToSend)
    else:
        sock.send(str.encode("ERR "))

    sock.close()


def Main():
    host = '127.0.0.1'
    port = 5000

    s = socket.socket()
    s.bind((host, port))

    s.listen(5)

    print("Server Started.\nWaiting for a connection...")
    while True:
        c, addr = s.accept()
        print("Client connedted ip:<" + str(addr) + ">")
        t = threading.Thread(target=RetrFile, args=("RetrThread", c))
        t.start()

    s.close()


if __name__ == '__main__':
    Main()
